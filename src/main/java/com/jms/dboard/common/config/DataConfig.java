package com.jms.dboard.common.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@MapperScan("sqlMap")
public class DataConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
    private Environment env;
    
    
    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.jdbc.password"));
        return dataSource;
    }
 
    
//    @Bean
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//    	DataSourceTransactionManager txManager = new DataSourceTransactionManager();
//    	txManager.setDataSource(dataSource);
//    	return txManager;
//    }
    
    @Bean
    @Primary
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:mapper/**/*SqlMap.xml");
        sqlSessionFactory.setMapperLocations(resources);
        sqlSessionFactory.setTypeAliasesPackage("com.jms.dboard.manage.user.vo,com.jms.dboard.manage.vo,vo,com.jms.dboard.common.vo");
        return sqlSessionFactory;
    }
    
    @Bean(destroyMethod = "clearCache")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean
    @Primary
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
