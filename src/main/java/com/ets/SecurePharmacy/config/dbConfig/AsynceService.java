package com.ets.SecurePharmacy.config.dbConfig;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AsynceService {


    @Value("${main.datasource.driver-class-name}")
    private String driver;

    @Value("${main.datasource.username}")
    private String userName;

    @Value("${main.datasource.password}")
    private String password;

    @Value("${DATABASE_COMMON_URL}")
    private String url;


    @Autowired
    private MultiTenantConnectionProviderImpl multiTenantConnectionProvider;

    @Autowired
    private TenantSchemaResolver currentTenantIdentifierResolver;

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, DataSource> map = new HashMap<>();


    @Async
    public void addTenant(String tenantKey) {
        map.put(tenantKey, constructDataSource(tenantKey));
    }


    @Async
    public void updateTenant(String tenantKey) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource(tenantKey, dataSource);

        entity(dataSource, multiTenantConnectionProvider, currentTenantIdentifierResolver);
    }


    public DataSource constructDataSource(String tenant) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource(tenant, dataSource);

        try (Connection connection = dataSource.getConnection()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
        entity(dataSource, multiTenantConnectionProvider, currentTenantIdentifierResolver);

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
        resourceDatabasePopulator.execute(dataSource);


        return dataSource;
    }

    private void dataSource(String tenant, DriverManagerDataSource dataSource) {
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url + tenant + "?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&autoReconnect=true");
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
    }

    public LocalContainerEntityManagerFactoryBean entity(DataSource dataSource,
                                                         MultiTenantConnectionProvider connectionProvider,
                                                         CurrentTenantIdentifierResolver tenantResolver
    ) {

        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        emfBean.setDataSource(dataSource);
        emfBean.setPackagesToScan("com.ets.SecurePharmacy.tenant.model");
        emfBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emfBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        Map<String, Object> hibernateProperties = new HashMap<>();
        hibernateProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        hibernateProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
        hibernateProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
        hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        emfBean.setJpaPropertyMap(hibernateProperties);
        emfBean.afterPropertiesSet();
        return emfBean;
    }

    public JpaTransactionManager transactionManager(EntityManagerFactory tenantEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(tenantEntityManager);
        transactionManager.afterPropertiesSet();
        return transactionManager;
    }

}
