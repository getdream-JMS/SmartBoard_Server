package com.jms.dboard.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jms.dboard.authentication.CustomAuthenticationFailureHandler;
import com.jms.dboard.authentication.CustomAuthenticationSuccessHandler;
import com.jms.dboard.authentication.CustomWebSecurityExpressionHandler;
import com.jms.dboard.manage.user.service.AuthenticationService;


@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SecurityWebApplicationInitializer extends WebSecurityConfigurerAdapter  { 
	/*
	 * DB 접속을 통하여 사용자 정보 및 권한을 확인할 Service
	 */
	@Autowired 
	AuthenticationService authenticationService;

	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(authenticationService)
		.passwordEncoder(new ShaPasswordEncoder(256));
	}



	/**
	 * Security 권한체크에 해당하지 않도록 설정을 합니다.
	 */

	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/resources/**");
	}


	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(
				"/favicon.ico",
				"/cms/login/auth",
				"/login",
				"/ssoProc").permitAll()
		.antMatchers(
				"/pages/login.html",
				"/pages/sso-error.html",
				"/pages/failureLogin.html").anonymous()
//		.antMatchers("/admsys/**"). hasAnyAuthority("SYSADM")		
		.and()
		.formLogin()
		.usernameParameter("userId")
		.passwordParameter("userPass")
		.loginPage("/login")
		.loginProcessingUrl("/cms/login/auth")
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/pages/login.html")
		.invalidateHttpSession(true)
		.permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/accessDenied.html");
	}



	/**
	 * AuthenticationSuccessHandler 설정
	 * 로그인 실패시 처리되는 Handler입니다.
	 *
	 * <bean id="customeAuthenticationFailureHandler" class="com.intercast.security.handler.CustomAuthenticationFailureHandler">
	 *  <property name="defaultFailureUrl" value="/failureLogin.htm" />
	 * </bean>
	 * @return
	 */
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
		CustomAuthenticationFailureHandler handler = new CustomAuthenticationFailureHandler();
		handler.setDefaultFailureUrl("/failureLogin.html");
		return handler;
	}



	/**
	 * AuthenticationSuccessHandler 설정
	 * 로그인 성공시 처리되는 Handler입니다.
	 *
	 * <bean id="customeAuthenticationSuccessHandler" class="com.intercast.security.handler.CustomAuthenticationSuccessHandler">
	 *  <property name="defaultTargetUrl" value="/index.htm" />
	 * </bean>
	 * @return
	 */
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
		handler.setDefaultTargetUrl("/pages/index.html");
		return handler;
	}



	/**
	 * AccessDecisionManager 설정
	 *
	 * Security 설정중 http.authorizeRequests().accessDecisionManager(accessDecisionManager()) 에서 호출합니다.
	 * 권한 체크에 대한 여러가지 방법 중 AffirmativeBased 사용
	 *
	 * <bean id="accessDecisionManager" class="org.springframework.security.access.vote.AffirmativeBased">
	 *  <constructor-arg>
	 *   <list>
	 *    <ref bean="expressionVoter" />
	 *   </list>
	 *  </constructor-arg>
	 *  <property name="allowIfAllAbstainDecisions" value="false"/>
	 * </bean>
	 *
	 * @return
	 */
	@Bean
	public AffirmativeBased accessDecisionManager() {
		List<AccessDecisionVoter<?>> voters = new ArrayList<AccessDecisionVoter<?>>();
		voters.add(expressionVoter());

		AffirmativeBased affirmativeBased = new AffirmativeBased(voters);
		affirmativeBased.setAllowIfAllAbstainDecisions(false);
		return  affirmativeBased;
	}


	/**
	 * 사용자 정의 권한을 사용하고자 할 경우 생성합니다.
	 * WebExpressionVoter에 생성한 권한 설정 Handler을 SET 합니다.
	 *
	 * <bean id="customExpressionHandler"
	 *  class="com.intercast.security.handler.CustomWebSecurityExpressionHandler">
	 *   <property name="defaultRolePrefix" value="" />
	 * </bean>
	 *
	 * <bean id="expressionVoter"
	 *  class="org.springframework.security.web.access.expression.WebExpressionVoter">
	 *  <property name="expressionHandler" ref="customExpressionHandler"></property>
	 * </bean>
	 *
	 * @return
	 */
	@Bean
	public WebExpressionVoter expressionVoter() {
		WebExpressionVoter voter = new WebExpressionVoter();

		CustomWebSecurityExpressionHandler handler = new CustomWebSecurityExpressionHandler();
		handler.setDefaultRolePrefix("");
		voter.setExpressionHandler(handler);
		return voter;
	}
}