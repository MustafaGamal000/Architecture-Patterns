package com.example.sqlServer_jpa.infrastructure.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.sqlServer_jpa.repository.question",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

//    @Primary
//    @Bean(name = "primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource") // Binds to spring.datasource properties
//    public DataSource dataSource() {
//        // Ensure HikariDataSource is explicitly used for type safety
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }

/*    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create().build();
        System.out.println("Primary DataSource URL: " + ((HikariDataSource) dataSource).getJdbcUrl());
        return dataSource;
    }
 */
//    @Bean(name = "primaryDataSource")
//    @Primary
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=questionsdb");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("sa");
//        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        return dataSource;
//    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.sqlServer_jpa.entity.question")
                .persistenceUnit("question")
                .build();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
