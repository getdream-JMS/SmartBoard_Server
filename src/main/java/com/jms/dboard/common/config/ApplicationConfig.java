package com.jms.dboard.common.config;

import java.util.HashMap;
import java.util.Map;

import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import com.jms.dboard.common.interceptor.SessionInterceptor;
import com.jms.dboard.common.listener.SessionListener;
import com.jms.dboard.manage.log.dao.LogManageDao;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.log.service.LogManageServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.jms.dboard.*")
@EnableWebMvc
@EnableScheduling
@EnableAsync
@PropertySource( value={"classpath:snmp.properties", "classpath:weather.properties", "classpath:ffmpeg.properties"})
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
    private Environment env;
	
	private int THREADS_COUNT = 3;
	@Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to /admin and /user to their index.html
//            	System.out.println("Redirect");
            	registry.addRedirectViewController("/login", "/pages/login.html");
//            	registry.addRedirectViewController("/noti", "/pages/index.html");
//            	registry.addRedirectViewController("/promotion", "/pages/index.html");
//            	registry.addRedirectViewController("/contents", "/pages/index.html");
//            	registry.addRedirectViewController("/organization", "/pages/index.html");
            	
            	
//                registry.addViewController("/src/").setViewName("forward:/index");
            }
            
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**");
            }

        };
    }
	
//	@Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();	
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
    
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/");
//    	registry.addResourceHandler("/**").addResourceLocations("file:D:\\Utils\\httpd-2.4.41-win64-VS16\\Apache24\\htdocs");
    }
    

    @Bean
    public Persister persister(){
      return new Persister();
    }
    
    @Bean
    SessionListener sessionListner() {
         return new SessionListener();
    }
    
    
    @Bean
    SessionInterceptor localInterceptor() {
         return new SessionInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // Register guest interceptor with single path pattern
       registry.addInterceptor(localInterceptor())
       .addPathPatterns("/cms/**","/**")
	   .excludePathPatterns("/login","/cms/**","/cms/**/**","/resources/**","/resources","/ssoProc");
//       .excludePathPatterns("/login","/cms/login/auth","/cms/client/*");

    }
    
  
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/pages/index.html");
    }
    
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1024 * 1024 * 500);
//        multipartResolver.setMaxUploadSize(-1);
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }
   
    @Bean
    public Map<String, Object> cmsStatusMap() {
        final Map<String, Object> cmsStatusMap = new HashMap<>();
        return cmsStatusMap;
    }
    
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(THREADS_COUNT);
        return threadPoolTaskScheduler;
    }
    
    
}
