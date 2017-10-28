package com.cyosp.mpa.api.rest.core.v1.mapper;

/**
 * Created by CYOSP on 2017-08-02.
 */

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.cyosp.mpa.api.rest.core.v1.mapper", annotationClass = UseCoreDatasource.class, sqlSessionFactoryRef = CoreDatasourceConfig.SQL_SESSION_FACTORY_NAME)
public class CoreDatasourceConfig {
    public static final String SQL_SESSION_FACTORY_NAME = "coreSessionFactory";
    public static final String TX_MANAGER = "coreTxManager";

    @Bean(name = "coreDatasource")
    @Primary
    @ConfigurationProperties(prefix = "repository.core.v1.db")
    public DataSource coreDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = TX_MANAGER)
    @Primary
    public PlatformTransactionManager coreTxManager() {
        return new DataSourceTransactionManager(coreDataSource());
    }

    @Bean(name = CoreDatasourceConfig.SQL_SESSION_FACTORY_NAME)
    @Primary
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(coreDataSource());
        return sqlSessionFactoryBean.getObject();
    }
}

