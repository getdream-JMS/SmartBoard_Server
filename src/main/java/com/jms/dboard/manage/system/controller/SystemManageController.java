package com.jms.dboard.manage.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.manage.system.service.SystemManageService;
import com.jms.dboard.manage.user.service.UserManageService;
import com.jms.dboard.manage.vo.ContentInfoVO;
import com.jms.dboard.manage.vo.OrganInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

/**
 * System 관련 컨트롤러
 * 통계, 서버 모니터링
 *
 * @author shmoon
 */
@RestController
@RequestMapping(value = "/cms")
public class SystemManageController {
	
	@Autowired
	SystemManageService systemManageService;
	
	@Resource(name = "cmsStatusMap")
	private Map<String, Object> cmsStatusMap;
	
	/**
	 * 컨텐츠 통계 조회 
	 * @return 
	 */
	@RequestMapping(value = "/system/statistic", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getStatisticContents(@ModelAttribute ContentInfoVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		Map<String,Object> resultMap  = new HashMap<String, Object>();
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");		
			if(userInfo == null) {
				param.setUserId("admin");
			}	else {
				param.setUserId(userInfo.getUserId());
			}
			resultMap = systemManageService.getStatisticContents(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getStatisticContents()
	
	/**
	 * 서버 리소스 조회 
	 * @return 
	 */
//	@Scheduled(fixedRate = 5000)
	public void retrieveResources()
	{

//
//		Map<String,Object> resultMap  = new HashMap<String, Object>();
//		HttpHeaders headers = new HttpHeaders();
		try {
			
			systemManageService.retrieveResources();

		} catch (Exception e) {

		}
	} // getStatisticContents()

	/**
	 * CMS 자원 조회
	 * @return 
	 */
	@RequestMapping(value = "/system/resources", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getCmsResource(@ModelAttribute ContentInfoVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		Map<String,Object> resultMap  = new HashMap<String, Object>();
		HttpHeaders headers = new HttpHeaders();
		UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
		try {
			String sessionExpired = "N";
			if(userInfo == null) {
				sessionExpired = "Y";
			}
			if(cmsStatusMap.isEmpty()) {
				return new ResponseEntity<>("{\"sessionExpired\": \""+sessionExpired+"\", \"disk\":{\"total\":0,\"usage\":0,\"used\":0},\"memory\":{\"total\":0,\"usage\":\"0\",\"used\":0},\"os\":{\"name\":\"\"},\"cpu\":{\"cores\":0,\"usage\":\"0\",\"name\":\"\"},\"nic\":{\"name\":\"\",\"speed\":0}}", headers, HttpStatus.OK);
			}
			cmsStatusMap.put("sessionExpired", sessionExpired);
			return new ResponseEntity<>(cmsStatusMap, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getStatisticContents()
}
