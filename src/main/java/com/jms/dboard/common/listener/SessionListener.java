package com.jms.dboard.common.listener;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jms.dboard.common.config.ApplicationConfig;
import com.jms.dboard.common.config.SpringJobBeanFactory;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.login.service.LoginService;
import com.jms.dboard.login.service.LoginServiceImpl;
import com.jms.dboard.manage.log.dao.LogManageDao;
import com.jms.dboard.manage.log.dao.LogManageDaoImpl;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.log.service.LogManageServiceImpl;
import com.jms.dboard.manage.vo.LogInfoVO;
import com.jms.dboard.manage.vo.LoginLogInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;
 
public class SessionListener implements HttpSessionListener {	
//	@Autowired
//	LogManageService logManageService;
	
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("==== Session is created ====");
        event.getSession().setMaxInactiveInterval(60*60);
        System.out.println("login sessionId: "+event.getSession().getId());
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    	;
 //   	String sessionId = (String)event.getSession().getAttribute("sessionId");
    	System.out.println("sessionDestroyed=========================================");
    	
//    	System.out.println("sessionId :"+session.getId());
 		HttpSession session = event.getSession();
 		String sessionId = session.getId();
 		System.out.println("logout sessionId :"+sessionId);
    	UserInfoVO userInfo = (UserInfoVO) session.getAttribute("sessionUserInfo");
//    	System.out.println("userInfo :"+userInfo.getUserId());
//    	logManageService = new LogManageDaoImpl();
//    	System.out.println("logManageService :"+logManageService);
//    	System.out.println("logManageService saveLogoutLogInfo:"+logManageService.saveLogoutLogInfo(session.getId()));
//    	LogManageDaoImpl logManageService = new LogManageDaoImpl();
//    	LoginLogInfoVO loginLogInfoVO = new LoginLogInfoVO();
 //   	System.out.println("logManageService :"+logManageService);
    	try {
    		
            
            LogManageService logManageService = SpringJobBeanFactory.getBean(LogManageService.class);
    		LoginLogInfoVO loginLogInfoVO = new LoginLogInfoVO();
    		loginLogInfoVO.setStatus(0);
        	loginLogInfoVO.setLoginResult("정상적으로 로그아웃 되었습니다.");
    		loginLogInfoVO.setSessionId(sessionId);
    		logManageService.saveLogoutLogInfo(sessionId);
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//ApplicationContext ctx = 
        //        WebApplicationContextUtils.
        //              getWebApplicationContext(session.getServletContext());
 
        //  LogManageService logManageService = 
        //              (LogManageService) ctx.getBean("logManageService");


    	/*
		 * �α��� ���� �α� ����
		 * */ 
//    	ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        
//    	LogManageServiceImpl logManageService = (LogManageServiceImpl)ac.getBean("logManager");
//    	HttpSession session = event.getSession();
//        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
    	
        //request�� �Ķ���Ϳ� ���� �ʰ� ����Ҽ� �ֵ��� ����
//        LogManageServiceImpl logManageService = (LogManageServiceImpl)ac.getBean("logManager");
//        
//		LoginLogInfoVO loginLogInfoVO = new LoginLogInfoVO();
//		loginLogInfoVO.setStatus(0);
//    	loginLogInfoVO.setLoginResult("���������� �α׾ƿ� �Ǿ����ϴ�.");
//		loginLogInfoVO.setSessionId(sessionId);
//		logManageService.saveLogoutLogInfo();
    	event.getSession().invalidate();
    	
//        System.out.println("==== Session is destroyed ====");
    }
   
}


//@Component
//public class SessionListener implements ApplicationListener<ApplicationEvent> {
//
//	@Autowired
//	HttpSession httpSession;
//	
//	
//	
//	@Override
//	public void onApplicationEvent(ApplicationEvent applicationEvent) {
//	    if(applicationEvent instanceof HttpSessionCreatedEvent){ //If event is a session created event
//	    	 System.out.println("  HttpSessionCreatedEvent  :" ); //log data
//	     }else if(applicationEvent instanceof HttpSessionDestroyedEvent){ //If event is a session destroy event
//	        // handler.expireCart();
//	    	 HttpSession httpSession = ((HttpServletRequest) applicationEvent).getSession();
//	    	 System.out.println(" Session is destory  :" +httpSession.getId()); //log data
//	    	 System.out.println(" Session is destory  :" ); //log data
//	     }else if(applicationEvent instanceof AuthenticationSuccessEvent){ //If event is a session destroy event
//	    	 System.out.println("  athentication is success  :" ); //log data
//	     }else{
//	     }  
//	}
//
//}