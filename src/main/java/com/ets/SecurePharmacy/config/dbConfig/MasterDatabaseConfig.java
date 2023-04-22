package com.ets.SecurePharmacy.config.dbConfig;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.ets.SecurePharmacy.master"},
        entityManagerFactoryRef = "masterEntityRef",
        transactionManagerRef = "masterTransactionRef"
)
public class MasterDatabaseConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "masterDB")
    @ConfigurationProperties(prefix="main.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean(name = "masterEntityRef")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("masterDB") DataSource dataSource) {

        Map<String, Object> hibernateProperties = new LinkedHashMap<>(jpaProperties.getProperties());
        hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");

        return builder.dataSource(dataSource)
                .packages("com.ets.SecurePharmacy.master.model")
                .properties(hibernateProperties)
                .build();
    }

    @Primary
    @Bean(name = "masterTransactionRef")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("masterEntityRef") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }

}
