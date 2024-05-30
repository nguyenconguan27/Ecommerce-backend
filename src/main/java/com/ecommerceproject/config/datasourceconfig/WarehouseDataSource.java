package com.ecommerceproject.config.datasourceconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;


@EntityScan(basePackages = "com.ecommerceproject.dwentity")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.ecommerceproject.dwrepository",
        entityManagerFactoryRef = "warehouseEntityManagerFactory",
        transactionManagerRef = "warehouseTransactionManager"
)

public class WarehouseDataSource {

    @Bean(name = "warehousedatasource")
    @ConfigurationProperties("spring.datasource.warehouse")
    public DataSource warehouseDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "warehouseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(
            EntityManagerFactoryBuilder builder,
            @Qualifier("warehousedatasource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.ecommerceproject.dwentity")
                .persistenceUnit("second")
                .build();
    }

    @Bean(name = "warehouseTransactionManager")
    public PlatformTransactionManager transactionManager2(
            @Qualifier("warehouseEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
