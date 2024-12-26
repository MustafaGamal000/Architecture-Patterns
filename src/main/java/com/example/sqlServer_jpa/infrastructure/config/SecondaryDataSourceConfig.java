package com.example.sqlServer_jpa.infrastructure.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.sqlServer_jpa.repository.active_mq",
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDataSourceConfig {
    @Value("${secondary.datasource.url}")
    private String url;
    @Value("${secondary.datasource.username}")

    private String username;
    @Value("${secondary.datasource.password}")

    private String password;
    @Value("${secondary.datasource.driver-class-name}")

    private String driverClassName;

//    @Bean(name = "secondaryDataSource")
//    public DataSource dataSource() {
//        // Explicitly specify the HikariDataSource type for consistent behavior
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }

//    @Bean(name = "secondaryDataSource")
//    @ConfigurationProperties(prefix = "secondary.datasource")
//    public DataSource dataSource() {
//        DataSource dataSource = DataSourceBuilder.create().build();
//        System.out.println("Secondary DataSource URL: " + ((HikariDataSource) dataSource).getJdbcUrl());
//        return dataSource;
//    }

    @Bean(name = "secondaryDataSource")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.sqlServer_jpa.entity.active_mq")
                .persistenceUnit("activeMQLock")
                .build();
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

