package com.jms.dboard.manage.external;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.utils.CookieUtil;
import com.jms.dboard.common.utils.HashUtil;
import com.jms.dboard.login.service.LoginService;
import com.jms.dboard.manage.user.service.UserManageService;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.LoginLogInfoVO;
import com.jms.dboard.manage.vo.RoleInfoVO;

/**
 * SSO 연동 API 관련 컨트롤러
 *
 * @author shmoon
 */
@CrossOrigin("*")
@Controller
public class SSOController {

	@Autowired
	UserManageService userManageService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LogManageService logManageService;
	
	/**
	 * 세션정보처리
	 * @return 
	 */
	@RequestMapping(value = "/ssoProc", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public String setSSOSessionInfo( HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
		
		UserInfoVO existUser = null;
		boolean isSuperUser = false;
		String loginResult = "로그인에 성공했습니다.";
		int loginStatus = 0;
		try {
			
			if(session != null) {
				//SSO 로그인 정보 가져오기
				String userId = (String)session.getAttribute("userId");
				String userName = (String)session.getAttribute("userName");
				String organId = (String)session.getAttribute("organId");
				String organName = (String)session.getAttribute("organName");
				String sessionId = session.getId();

				if(userId != null && !userId.equals("")) {
				//사용자 정보 설정
				UserInfoVO userInfo = new UserInfoVO();
				userInfo.setUserId(userId);
				userInfo = loginService.authUserInfo(userInfo);
//				Map<String,Object> userData = userManageService.getUserInfo(userId);
				
				if(userInfo == null) {
					userInfo = new UserInfoVO();
					userInfo.setUserId(userId);
					userInfo.setUserName(userName);
					userInfo.setRoleType("M");
					userInfo.setOrganId(organId);
					userInfo.setOrganName(organName);
					userInfo.setSessionId(sessionId);
					userInfo.setActiveYn("Y");
					userInfo.setUserPass(HashUtil.md5(userId));
					//관리역할 설정
					RoleInfoVO roleInfoVO = new RoleInfoVO();
					roleInfoVO.setRoleId("2");
					roleInfoVO.setRoleName("공고문관리자");
					roleInfoVO.setRoleType("M");
					List<RoleInfoVO> rolesList = new ArrayList<RoleInfoVO>();
					userInfo.setRoles(rolesList);
					
					rolesList.add(roleInfoVO);
				
					loginResult+=" [SSO 최초로그인]";
					//사용자 정보 저장
					userManageService.saveUserInfo(userInfo);
				} else {
//					userInfo = (UserInfoVO) userInfo.getRoleType();
					userInfo.setRoleType("M");
	        		if(userInfo.getRoles().size() > 0) {
	        			for(int i = 0;i < userInfo.getRoles().size();i++) {
	//        				System.out.println("role type: "+userInfo.getRoles().get(i).getRoleType());
	        				if( userInfo.getRoles().get(i).getRoleType().equals("S")) {
	        					userInfo.setRoleType("S");
	        					isSuperUser = true;
	        				}
	        				if(isSuperUser) break;
	        			}
	        		}
	        		loginResult+=" [SSO]";
					loginStatus = 1;
				}
				
				session.setAttribute("sessionUserInfo", userInfo);
				
				String remoteIpAdress = CommonUtils.getClientIP();
				
				/*
				 * 로그인 상태 로그 저장
				 * */ 
				LoginLogInfoVO loginLogInfoVO = new LoginLogInfoVO();
				loginLogInfoVO.setUserId(userInfo.getUserId());
				loginLogInfoVO.setUserName(userInfo.getUserName());
				loginLogInfoVO.setLoginResult(loginResult);
				loginLogInfoVO.setStatus(loginStatus);
				loginLogInfoVO.setUserIp(remoteIpAdress);
				loginLogInfoVO.setSessionId(sessionId);
				logManageService.saveLoginLogInfo(loginLogInfoVO);
				
				
				Cookie dboardCookie = CookieUtil.createCookie("accessFrom", "sso/"+userInfo.getUserId()+"/"+userInfo.getRoleType(), "/", 60*60*24);
//				Cookie userInfoCookie = CookieUtil.createCookie("userId", userInfo.getUserId(), "/", 0);
//				Cookie roleTypeCookie = CookieUtil.createCookie("roleType", userInfo.getRoleType(), "/", 0);

				response.addCookie(dboardCookie);
//				response.addCookie(userInfoCookie);
//				response.addCookie(roleTypeCookie);
				
				return "redirect:/pages/index.html";
				} else {
					System.out.println("세션정보는 있으나 사용자정보가 없음");
					return "redirect:/pages/sso-error.html";
				}
			} else {
				System.out.println("세션정보가 없음");
				return "redirect:/pages/sso-error.html";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/pages/sso-error.html";
		}
		
	} // setSSOSessionInfo()
	
}
