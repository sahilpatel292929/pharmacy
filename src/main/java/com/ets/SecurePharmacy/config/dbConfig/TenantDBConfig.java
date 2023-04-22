package com.ets.SecurePharmacy.config.dbConfig;

import com.ets.SecurePharmacy.master.dao.TenantRepository;
import com.ets.SecurePharmacy.master.model.Tenant;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.ets.SecurePharmacy.tenant"},
        entityManagerFactoryRef = "tenantEntityRef",
        transactionManagerRef = "tenantTransactionRef"
)
public class TenantDBConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("masterDB")
    private DataSource defaultDS;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private MultiTenantConnectionProviderImpl multiTenantConnectionProvider;

    @Autowired
    private TenantSchemaResolver currentTenantIdentifierResolver;

    private DataSource createDataSource(String name) {
        Tenant config = tenantRepository.findByName(name);
        if (config != null) {
            DataSourceBuilder factory = DataSourceBuilder
                    .create().driverClassName(config.getDriverClassName())
                    .username(config.getUsername())
                    .password(config.getPassword())
                    .url(config.getUrl());
            DataSource ds = factory.build();
            return ds;
        } else {
            return defaultDS;
        }
    }


    @Bean(name = "tenantDataBase")
    public DataSource multiRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("db_main", defaultDS);
        tenantRepository.findAll().stream().forEach(data -> {
            targetDataSources.put(data.getName(), createDataSource(data.getName()));
        });
        AbstractRoutingDataSource a = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return DBContextHolder.getCurrentDb();
            }
        };
        a.setTargetDataSources(targetDataSources);
        a.setDefaultTargetDataSource(defaultDS);
        return a;
    }


    @Bean(name = "tenantEntityRef")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("tenantDataBase") DataSource dataSource
                                                                       ) {
        Map<String, Object> hibernateProperties = new LinkedHashMap<>(jpaProperties.getProperties());
        hibernateProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        hibernateProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
        hibernateProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
       // hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        return builder.dataSource(dataSource)
                .packages("com.ets.SecurePharmacy.tenant.model")
                .properties(hibernateProperties)
                .build();
    }


    @Bean(name = "tenantTransactionRef")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("tenantEntityRef") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}
