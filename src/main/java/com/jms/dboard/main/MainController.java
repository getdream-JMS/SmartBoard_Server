package com.jms.dboard.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jms.dboard.manage.clients.service.ClientsManageService;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * Main 컨트롤러
 * redirect URL 
 *
 * @author shmoon
 */
@Controller
public class MainController {
	@Autowired
	ClientsManageService clientsManageService;
	
	@Autowired
	LogManageService logManageService;
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping(value = {"/","/index","/index.html","/noti","/noti/*","/promotion",
			"/promotion/*","/contents","/contents/*","/organization","/organization/*",
			"/office","/office/*","/client","/client/*","/user","/user/*","/system","/system/*","/control","/schedule","/schedule/*","/jumin","/jumin/*"})
	public String index(HttpServletRequest request, HttpServletResponse response)
	{
		return "redirect:/pages/index.html";
    }
	
//	@RequestMapping(value = "/login.html")
//	public String login(HttpServletRequest request, HttpServletResponse response)
//	{
//		System.out.println("/src/login.html");
//		return "redirect:/src/login.html";
//    }
	
	@RequestMapping(value = {"/cms/client/index"}, method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public String initPage(@ModelAttribute ClientBoardDataReqVO param, HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
//		UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
		System.out.println("=============================");
		System.out.println("초기 페이지 요청");
		UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");			
		if(userInfo == null) {
			param.setOrganId("3100000");
		}	else {
			param.setOrganId(userInfo.getOrganId());
		}
//		
//		System.out.println("getClientId : "+param.getClientId());
//		System.out.println("getBoardIndex : "+param.getBoardIndex());
//		System.out.println("resolutionWidth : "+param.getResolutionWidth());
//		System.out.println("resolutionHeight : "+param.getResolutionHeight());
//		System.out.println("=============================");
		String initPageUrl = clientsManageService.getInitPage(param);
	System.out.println("initPageUrl :"+initPageUrl);
		return "redirect:"+initPageUrl;
//		return "redirect:/src/index.html";
    }
	
	@RequestMapping(value = {"/cms/client/index"}, method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
	public String locationInfo(@ModelAttribute ClientBoardDataReqVO param, HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
//		UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
		System.out.println("=============================");
		System.out.println("초기 페이지 요청");
		UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");			
		if(userInfo == null) {
			param.setOrganId("10000000");
		}	else {
			param.setOrganId(userInfo.getOrganId());
		}
//		
//		System.out.println("getClientId : "+param.getClientId());
//		System.out.println("getBoardIndex : "+param.getBoardIndex());
//		System.out.println("resolutionWidth : "+param.getResolutionWidth());
//		System.out.println("resolutionHeight : "+param.getResolutionHeight());
//		System.out.println("=============================");
		String initPageUrl = clientsManageService.getInitPage(param);
//		System.out.println("initPageUrl :"+initPageUrl);
		return "redirect:"+initPageUrl;
//		return "redirect:/src/index.html";
    }
	
	/**
     * 사용자 정보 찾기
     *
     * @param userId
     * @return
     */
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String gotoLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
//		System.out.println("로그인 페이지로 이동");
		return "redirect:/pages/login.html";


    }
	@RequestMapping(value = {"/cms/noti"})
	public String noti(HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println("/pages/index.html");
		return "redirect:/pages/index.html";
    }
	/**
     * 사용자 로그아웃
     *
     * @param userId
     * @return
     */
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
//		System.out.println("로그인 페이지로 이동");
		
		if (session != null){
			
			// 1: 세션 데이터 조회
//			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo"); 
//			System.out.println("httpSession.getId() : "+httpSession.getId());
//			logManageService.saveLogoutLogInfo(httpSession.getId());
			//로그아웃 정보 저
			// 2: 기존의 세션 데이터를 모두 삭제
			session.invalidate();
//		    System.out.println("session :"+session);
		}
		return "redirect:/pages/login.html";


    }
}
