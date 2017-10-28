package com.cyosp.mpa.api.rest.homebank.v1dot2.mapper;

/**
 * Created by CYOSP on 2017-08-02.
 */

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.cyosp.mpa.api.rest.homebank.v1dot2.mapper", annotationClass = UseHomebankDatasource.class, sqlSessionFactoryRef = HomebankDatasourceConfig.SQL_SESSION_FACTORY_NAME)
public class HomebankDatasourceConfig {
    public static final String SQL_SESSION_FACTORY_NAME = "homebankSessionFactory";
    public static final String TX_MANAGER = "homebankTxManager";

    @Bean(name = "homebankDatasource")
    @ConfigurationProperties(prefix = "repository.homebank.v1dot2.db")
    public DataSource homebankDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = TX_MANAGER)
    public PlatformTransactionManager homebankTxManager() {
        return new DataSourceTransactionManager(homebankDataSource());
    }

    @Bean(name = HomebankDatasourceConfig.SQL_SESSION_FACTORY_NAME)
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(homebankDataSource());
        return sqlSessionFactoryBean.getObject();
    }
}

