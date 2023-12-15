package com.jms.dboard.manage.organization;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.manage.announce.service.AnnounceBoardService;
import com.jms.dboard.manage.organization.service.OrganizationManageService;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.OrganInfoVO;
import com.jms.dboard.manage.vo.TtsInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 기관 및 부서 관리 컨트롤러
 *
 * @author shmoon
 */
@RestController
@RequestMapping(value = "/cms")
public class OrganizationManageController {

	Logger logger = Logger.getLogger(OrganizationManageController.class.getName());
	
	@Autowired
	OrganizationManageService organManageService;
	/**
	 * 조직 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/organ", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getOrganList(@ModelAttribute OrganInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = organManageService.getOrganInfoList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getOrganList()
	
	/**
	 * 지점  리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/location", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getOfficeList(@ModelAttribute OrganInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = organManageService.getOfficeInfoList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getOfficeList()
	
	/**
	 * 사용자 관리 지점  리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/location/user", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUserOfficeList(@ModelAttribute OrganInfoVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				param.setUserId(userInfo.getUserId());
			}
			 Map<String,Object> resultMap = organManageService.getUserOfficeList(userInfo);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getOfficeList()
	
	/**
	 * 프로모 등록 시 클라이인트 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/location/client", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getAllClientList(@ModelAttribute OrganInfoVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				param.setUserId(userInfo.getUserId());
				param.setRoleType(userInfo.getRoleType());
			}
			 Map<String,Object> resultMap = organManageService.getAllClientList(userInfo);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getOfficeList()
	
	/**
	 * 조직별 부서원  리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/organ/users/{organId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getUsersByOrgan(@PathVariable String organId, @ModelAttribute UserInfoSearchVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("organId: "+organId);
//			System.out.println("userName: "+param.getUserName());
			param.setOrganId(organId);
			 Map<String,Object> resultMap = organManageService.getUsersByOrgan(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getOfficeList()
}
