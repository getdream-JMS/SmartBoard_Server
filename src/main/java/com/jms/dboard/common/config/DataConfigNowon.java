package com.jms.dboard.common.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:database.properties" })
public class DataConfigNowon extends WebMvcConfigurerAdapter {
	
	@Autowired
    private Environment env;    
    
    @Bean(name="dataSourceNowon")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.nowon.datasource.jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("spring.nowon.datasource.jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("spring.nowon.datasource.jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.nowon.datasource.jdbc.password"));
        return dataSource;
    }
 
    
//    @Bean
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//    	DataSourceTransactionManager txManager = new DataSourceTransactionManager();
//    	txManager.setDataSource(dataSource);
//    	return txManager;
//    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean(name="sqlSessionFactoryBeanNowon")
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:mapper/**/NowonMngSqlMap.xml");
        sqlSessionFactory.setMapperLocations(resources);
        sqlSessionFactory.setTypeAliasesPackage("com.wows.dboard.manage.vo");
        return sqlSessionFactory;
    }
    
    @Bean(name="sqlSessionTemplateNowon", destroyMethod = "clearCache")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryBeanNowon") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean (name="transactionManagerNowon" )
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}