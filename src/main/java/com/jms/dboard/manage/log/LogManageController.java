package com.jms.dboard.manage.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.manage.content.service.ContentManageService;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.monitoring.service.MonitoringManageService;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.LogInfoVO;
import com.jms.dboard.manage.vo.LoginLogInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 로그 정보 컨트롤러
 *
 * @author shmoon
 */
@RequestMapping(value = "/cms")
@RestController
public class LogManageController {

	@Autowired
	LogManageService logManageService;
	/**
	 * 로그 정보 저장
	 * @return 
	 */
	@RequestMapping(value = "/log", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveLogInfo(@RequestBody LogInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = logManageService.saveLogInfo(param.getLogType(), param.getLogCode(),param.getLogInfo(),param.getClientId());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveLogInfo()
	
	/**
	 * 로그 정보 조회
	 * @return 
	 */
	@RequestMapping(value = "/log", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getLogInfoList(@ModelAttribute LogInfoVO param, HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");			
			if(userInfo == null) {
				param.setUserId("admin");
			}	else {
				param.setUserId(userInfo.getUserId());
			}
			resultMap = logManageService.getLogInfoList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveLogInfo()
	
	/**
	 * 로그 정보 삭제
	 * @return 
	 */
	@RequestMapping(value = "/log", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteLogInfoList(@RequestBody List<Integer> param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = logManageService.deleteLogInfoList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // deleteLogInfoList()
	
	/**
	 * 로그인 로그 정보 저장
	 * @return 
	 */
	@RequestMapping(value = "/loginlog", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveLoginLogInfo(@RequestBody LoginLogInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = logManageService.saveLoginLogInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveLogInfo()
	
	/**
	 * 로그인 로그 정보 조회
	 * @return 
	 */
	@RequestMapping(value = "/loginlog", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getLoginLogInfoList(@ModelAttribute LoginLogInfoVO param, HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");			
			if(userInfo == null) {
				param.setUserId("needlogin");
				param.setRoleType("M");
			}	else {
				param.setRoleType((userInfo.getRoleType() != null && !userInfo.getRoleType().equals(""))?userInfo.getRoleType():"M");
				param.setUserId(userInfo.getUserId());
			}
			resultMap = logManageService.getLoginLogInfoList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveLogInfo()
	
	/**
	 * 로그인 로그 정보 삭제
	 * @return 
	 */
	@RequestMapping(value = "/loginlog", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteLoginLogInfoList(@RequestBody List<Integer> param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = logManageService.deleteLoginLogInfoList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // deleteLogInfoList()
}