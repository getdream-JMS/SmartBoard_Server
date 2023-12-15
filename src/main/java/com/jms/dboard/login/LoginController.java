package com.jms.dboard.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.utils.CookieUtil;
import com.jms.dboard.common.utils.HashUtil;
import com.jms.dboard.login.service.LoginService;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.vo.LoginLogInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;


@CrossOrigin("*")
@RequestMapping(value = "/cms")
@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	LogManageService logManageService;

	protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	/**
	 * 사용자 정보 찾기
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/login/auth", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> checkLoginInfo(@RequestBody UserInfoVO param, HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
		HttpHeaders headers = new HttpHeaders();

		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMessage = "";
		String loginResult = "로그인에 성공했습니다.";
		int loginStatus = 0;
		String userName = "";
		String sessionId = "";
		UserInfoVO userInfo = new UserInfoVO();
		try {
			/*
			 * 사용자 정보 조회
			 * */
			
			userInfo = loginService.authUserInfo(param);
			//세션 아이디 저장
			sessionId = session.getId();
			
//			System.out.println("checkLoginInfo sessionId :"+sessionId);
			if(userInfo != null) {    
				userName = userInfo.getUserName();
				if(userInfo.getActiveYn().equals("N")) {
					resultCode = 503;
					//resultMessage = "사용이 중지된 사용자 입니다. 관리자에게 문의해주세요.";
					resultMessage = "로그인에 실패 했습니다.";
					loginResult = "로그인에 실패 했습니다.";
				}else {
//					System.out.println("getUserPass :"+userInfo.getUserPass());
//					System.out.println("입력 패스워드 :"+HashUtil.md5(param.getUserPass()));
					if(!userInfo.getUserPass().equals(HashUtil.md5(param.getUserPass()))) {
						resultCode = 500;
						//resultMessage = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
						resultMessage= "로그인에 실패 했습니다.";
						loginResult = "로그인에 실패 했습니다.";
					} else {
						boolean isSuperUser = false;
		        		userInfo.setRoleType("M");
		        		if(userInfo.getRoles().size() > 0) {
		        			for(int i = 0;i < userInfo.getRoles().size();i++) {
//		        				System.out.println("role type: "+userInfo.getRoles().get(i).getRoleType());
		        				if( userInfo.getRoles().get(i).getRoleType().equals("S")) {
		        					userInfo.setRoleType("S");
		        					isSuperUser = true;
		        				}
		        				if(isSuperUser) break;
		        			}
		        		}
		        		
						loginStatus = 1;
						userInfo.setSessionId(sessionId);
						userInfo.setUserPass("");
						session.setAttribute("sessionUserInfo", userInfo);
						
						Cookie dboardCookie = CookieUtil.createCookie("accessFrom", "web", "/", 60*60*24);
						response.addCookie(dboardCookie);
					}
				}
				//        	}      	
			} else {			
//				System.out.println("userInfo : "+userInfo);
				resultCode = 500;
				//resultMessage = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
				resultMessage = "로그인에 실패 했습니다.";
				loginResult = "로그인에 실패 했습니다.";
			}

			String remoteIpAdress = CommonUtils.getClientIP();
			
			/*
			 * 로그인 상태 로그 저장
			 * */ 
			LoginLogInfoVO loginLogInfoVO = new LoginLogInfoVO();
			loginLogInfoVO.setUserId(param.getUserId());
			loginLogInfoVO.setUserName(userName);
			loginLogInfoVO.setLoginResult(loginResult);
			loginLogInfoVO.setStatus(loginStatus);
			loginLogInfoVO.setUserIp(remoteIpAdress);
			loginLogInfoVO.setSessionId(sessionId);
			logManageService.saveLoginLogInfo(loginLogInfoVO);
		} catch(Exception e) {
			e.printStackTrace();
			resultCode = 500;
			resultMessage = "DB 연결에 실패 했습니다.";
		} finally {
			resultMap.put("result", resultCode);
			resultMap.put("resultMsg", resultMessage);
			resultMap.put("userInfo", userInfo);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);
		}
	}
}
