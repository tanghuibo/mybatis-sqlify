package io.github.tanghuibo.mybatissqlify.config;

import io.github.tanghuibo.mybatissqlify.dao.SqlLogInterceptor;
import io.github.tanghuibo.mybatissqlify.dao.SqlifyMapper;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author tanghuibo
 * @date 2019/12/21下午7:53
 */
@Configuration
public class MybatisConfig {

    @Bean
    public DataSource getDataSource() {
        return new UnpooledDataSource();
    }

    @Bean
    TransactionFactory getTransactionFactory() {
        return new JdbcTransactionFactory();
    }

    @Bean
    public Environment getEnvironment(DataSource dataSource, TransactionFactory transactionFactory) {
        return new Environment("development", transactionFactory, dataSource);
    }

    @Bean("mybatisProperties")
    public Properties getProperties() {
        return new Properties();
    }

    @Bean
    public org.apache.ibatis.session.Configuration getConfiguration(Environment environment,
                                                                    SqlLogInterceptor sqlLogInterceptor,
                                                                    @Qualifier("mybatisProperties") Properties properties) {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setEnvironment(environment);
        configuration.addMapper(SqlifyMapper.class);
        configuration.setVariables(properties);
        configuration.addInterceptor(sqlLogInterceptor);
        return configuration;
    }

    @Bean
    public SqlSessionFactory getSqlSessionFactory(org.apache.ibatis.session.Configuration configuration) {
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    @Bean
    SqlifyMapper getSqlifyMapper(SqlSessionFactory sqlSessionFactory) {
        return sqlSessionFactory.openSession().getMapper(SqlifyMapper.class);
    }



}
