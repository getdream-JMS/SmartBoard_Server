package com.jms.dboard.manage.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.manage.user.service.UserManageService;
import com.jms.dboard.manage.vo.OrganInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

/**
 * User관련 컨트롤러
 *
 * @author shmoon
 */
@RestController
@RequestMapping(value = "/cms")
public class UserManageController {
	
	@Autowired
	UserManageService userManageService;
	/**
	 * 사용자 전체 조회 
	 * @return 
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUserInfoList(@ModelAttribute UserInfoSearchVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = userManageService.getUserInfoList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getUserInfoList()
	
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUsertDetail(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = userManageService.getUserInfo(userId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getUsertDetail()
	
	/**
	 * 사용자 등록
	 * @return 
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveUserInfo(@RequestBody UserInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = userManageService.saveUserInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveUserInfo()
	
	/**
	 * 사용자 등록 요청
	 * @return 
	 */
	@RequestMapping(value = "/users/req", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveUserInfoReq(@RequestBody UserReqInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = userManageService.saveUserReq(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveUserInfoReq()
	
	/**
	 * 사용자 권한 요청 확인 
	 * @return 
	 */
	@RequestMapping(value = "/users/req", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> acceptUserRequest(@RequestBody UserReqInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = userManageService.acceptUserRequest(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveUserInfoReq()
	
	/**
	 * 사용자 정보 수정
	 * 권한 정보 수정
	 * @return 
	 */
	@RequestMapping(value = "/users", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateUserInfo(@RequestBody UserInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{

		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = userManageService.updateUserInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // updateUserInfo()
	
	/**
	 * 사용자 정보 삭제
	 * @return 
	 */
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteUserInfo(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response)
	{

		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = userManageService.deleteUserInfo(userId);
			 return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // updateUserInfo()
	/**
	 * 사용자 등록 요청 리스트
	 * @return 
	 */
	@RequestMapping(value = "/users/req", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUserInfoReq(@ModelAttribute UserInfoSearchVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = userManageService.getUserInfoReqList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveUserInfoReq()
	
	/**
	 * 권한 요청한 조직원 정보 조회  
	 * @return 
	 */
	@RequestMapping(value = "/users/req/{userId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getOrganMemberInfo(@ModelAttribute UserReqInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = userManageService.getOrganMemberInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveUserInfoReq()
}
