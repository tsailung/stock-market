package com.metapro.stock.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    private final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        logger.info("---------------- init dataSource ---------------- ");
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }


}
