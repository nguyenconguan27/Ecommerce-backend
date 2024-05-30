package com.ecommerceproject.config.datasourceconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@EntityScan(basePackages = "com.ecommerceproject.entity")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.ecommerceproject.repository",
        entityManagerFactoryRef = "operationEntityManagerFactory",
        transactionManagerRef = "operationTransactionManager"
)
public class OperationDataSource {

    @Primary
    @Bean(name = "operationdatasource")
    @ConfigurationProperties("spring.datasource.operation")
    public DataSource operationDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "operationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory1(
            EntityManagerFactoryBuilder builder,
            @Qualifier("operationdatasource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.ecommerceproject.entity")
                .persistenceUnit("first")
                .build();
    }

    @Primary
    @Bean(name = "operationTransactionManager")
    public PlatformTransactionManager transactionManager1(
            @Qualifier("operationEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
