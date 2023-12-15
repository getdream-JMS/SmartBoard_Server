package com.jms.dboard.manage.clients;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.manage.clients.service.ClientsManageService;
import com.jms.dboard.manage.vo.ClientScheduleVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 클라이언트 관리 컨트롤러
 *
 * @author shmoon
 */
@RestController
@RequestMapping(value = "/cms")
public class ClientsManageController {

	Logger logger = Logger.getLogger(ClientsManageController.class.getName());
	
	@Autowired
	ClientsManageService clientsManageService;
	
	/**
	 * 클라이언트 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/clients", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientList(@ModelAttribute ClientsInfoVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");			
			if(userInfo == null) {
				param.setUserId("admin");
			}	else {
				param.setRoleType(userInfo.getRoleType());
				param.setUserId(userInfo.getUserId());
			}
			
			 Map<String,Object> resultMap = clientsManageService.getClientsInfoList(param);
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
	 * 클라이언트 상세 조회 
	 * @return 
	 */
	@RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientDetail(@PathVariable int clientId, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = clientsManageService.getClientsInfo(clientId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientDetail()
	
	/**
	 * 클라이언트 저장 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/clients", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveClientInfo(@RequestBody ClientsInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
			String remoteIpAdress = CommonUtils.getClientIP();
			param.setClientIp(remoteIpAdress);
			resultJson = clientsManageService.saveClientsInfo(param);

			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);



		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", "클라이언트 저장 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveClientInfo()
	
	/**
	 * 클라이언트 수정 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateClientInfo(@PathVariable int clientId,@RequestBody ClientsInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{

		System.out.println("클라이언트 수정 요청");
		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {

			resultJson = clientsManageService.updateClientsInfo(param);
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);



		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", "클라이언트 수정 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveClientInfo()
	
	/**
	 * 클라이언트 삭제
	 */
	@RequestMapping(value = "/clients", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteClientInfo(@RequestBody List<Integer> params, HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
//			for(int i = 0; i< params.size();i++) {
//				System.out.println(params.get(i));
//			}
			resultMap = clientsManageService.deleteClientsInfo(params);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);



		} catch (Exception e) {
			resultMap.put("result", "");
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getStatusLiveTranscoding()
	
	/**
	 * 클라이언트 주간 스케쥴 수정 
	 * @return 
	 */
	@RequestMapping(value = "/clients/schedule", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateClientSchedule(@RequestBody List<ClientScheduleVO> param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String,Object> resultMap = clientsManageService.updateClientSchedule(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientDetail()
	/**
	 * 클라이언트 Holiday  수정 
	 * @return 
	 */
	@RequestMapping(value = "/clients/holiday", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateClientHoliday(@RequestBody ClientsInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String,Object> resultMap = clientsManageService.updateClientHoliday(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientDetail()
	
	/**
	 * 조직별 클라이언트 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/clients/organ/{organId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getOrganClientList(@PathVariable String organId,@ModelAttribute ClientsInfoVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {

			param.setOrganId(organId);
			 Map<String,Object> resultMap = clientsManageService.getOrganClientsInfoList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getOrganClientList()
	
	/**
	 * 클라이언트 제어
	 * @return 
	 */
	@RequestMapping(value = "/control", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> controlClients(@RequestBody Map<String,Object> param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{
		Map<String,Object> resultMap = new HashMap<String, Object>();
		HttpHeaders headers = new HttpHeaders();
		try {

//			param.setOrganId(organId);
			resultMap = clientsManageService.getClientControlInfo(param);
			return new ResponseEntity<>("OK", headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // controlClients()
	
	/**
	 * 클라이언트 공휴일  조회 
	 * @return 
	 */
	@RequestMapping(value = "/clients/schedule", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientHoliday (HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			/**
			 * 임시 코드 : 전체 클라이언트 설정임
			 * */
			int clientId = 0;
			 Map<String,Object> resultMap = clientsManageService.getClientHoliday(clientId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientDetail()
}
