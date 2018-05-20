package com.web.machineversion.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    /**
     * 注入数据源
     */
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource getGpDataSource() {
        return new DataSource();
    }

}



//
//
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//
//
//    @Value("${spring.datasource.url}")
//    private String dataSourceUrl;
//
//    @Value("${spring.datasource.username}")
//    private String user;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Bean
//    public DataSource primaryDataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(dataSourceUrl); //数据源
//        config.setUsername(user); //用户名
//        config.setPassword(password); //密码
//        config.addDataSourceProperty("cachePrepStmts", "true"); //是否自定义配置，为true时下面两个参数才生效
//        config.addDataSourceProperty("prepStmtCacheSize", "250"); //连接池大小默认25，官方推荐250-500
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //单条语句最大长度默认256，官方推荐2048
//        config.addDataSourceProperty("useServerPrepStmts", "true"); //新版本MySQL支持服务器端准备，开启能够得到显著性能提升
//        config.addDataSourceProperty("useLocalSessionState", "true");
//        config.addDataSourceProperty("useLocalTransactionState", "true");
//        config.addDataSourceProperty("rewriteBatchedStatements", "true");
//        config.addDataSourceProperty("cacheResultSetMetadata", "true");
//        config.addDataSourceProperty("cacheServerConfiguration", "true");
//        config.addDataSourceProperty("elideSetAutoCommits", "true");
//        config.addDataSourceProperty("maintainTimeStats", "false");
//
//        return new HikariDataSource(config);
//    }
//}
//
