package com.asiainfo.alarm.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.asiainfo.alarm.dao"}, sqlSessionFactoryRef = "COCSqlSessionFactory")
public class COCConfig {
    @Bean(name = "COCDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.COC")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "COCSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("COCDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/COC/*.xml"));

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactory;
    }

    @Bean(name = "COCTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("COCDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
