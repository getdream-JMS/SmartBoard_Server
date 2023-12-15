package com.jms.dboard.manage.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.manage.clients.service.ClientsManageService;
import com.jms.dboard.manage.code.service.CodeManageService;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 설정 코드 관리 컨트롤러
 *
 * @author shmoon
 */
@RestController
@RequestMapping(value = "/cms")
public class CodeManageController {

	Logger logger = Logger.getLogger(CodeManageController.class.getName());
	
	@Autowired
	CodeManageService codeManageService;
	
	/**
	 * Resolution 코드 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/code/resolution", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getResolutionList(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{

		
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
	
			 Map<String,Object> resultMap = codeManageService.getResolutionList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientList()

	/**
	 * Resolution 코드 리스트 조회
	 * @return
	 */
	@RequestMapping(value = "/code/version", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getVersion(@RequestParam Map<String,Object> param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {

			Map<String,Object> resultMap = codeManageService.getResolutionList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientList()
}
