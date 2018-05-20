package com.web.machineversion.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.web.machineversion.dao", sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
public class SessionFactoryConfig {
    private final DataSource dataSource;

    @Autowired
    public SessionFactoryConfig(@Qualifier("mysqlDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager getTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
