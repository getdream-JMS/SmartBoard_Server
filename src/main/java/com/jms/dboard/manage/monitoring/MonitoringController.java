package com.jms.dboard.manage.monitoring;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.jms.dboard.manage.content.service.ContentManageService;
import com.jms.dboard.manage.monitoring.service.MonitoringManageService;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;

/**
 * 서버 모니터링 및 클라이언트 상태  API 관련 컨트롤러
 *
 * @author shmoon
 */
@RequestMapping(value = "/cms")
@RestController
public class MonitoringController {

	@Autowired
	MonitoringManageService monitoringService;
	/**
	 * Clients PC 리소스 저장 
	 * @return 
	 */
	@RequestMapping(value = "/clients/status", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientsStatus(@RequestBody Map<String,Object> param, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("클라이언트 리소스 전달 받음");
		System.out.println("clientId :"+param.get("clientId"));
//		System.out.println(param.get("boardIndex"));
//		System.out.println(param.get("mem"));
//		System.out.println(param.get("cpu"));
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			param.put("status", 1);
			resultMap = monitoringService.saveClientStatus(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientsStatus()
	
	/**
	 * 일정 시간 클라이언트 리소스 값이 갱신되지 않을 경우 상태 오류로 변경  
	 * @return 
	 */
//	@Scheduled(fixedRate = 40000)
	public void checkClientStatus()
	{	
		
		try {
			monitoringService.checkClientStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // checkClientStatus()
	
	/**
	 * Client 화면 상태 저장
	 * @return 
	 */
	@RequestMapping(value = "/client/monitoring", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveClientMonitoring(@RequestBody MonitoringReqInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
		System.out.println("clientId :"+param.getMonitor().get(0).get("fileName"));
//		System.out.println(param.get("boardIndex"));
//		System.out.println(param.get("resolutionWidth"));
//		System.out.println(param.get("resolutionHeight"));
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = monitoringService.saveClientMonitoring(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientMonitoring()
	
	/**
	 * Client 화면 상태 조회
	 * @return 
	 */
	@RequestMapping(value = "/client/monitoring/{clientId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientMonitoring(@PathVariable Integer clientId, HttpServletRequest request, HttpServletResponse response)
	{
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = monitoringService.getClientMonitoring(clientId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientMonitoring()
	
	/**
	 * Client 모니터링 설정 저장
	 * @return 
	 */
	@RequestMapping(value = "/client/monitoring/{clientId}", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateClientMonitoringConf(@PathVariable String clientId,  @RequestBody MonitoringInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println("clientId :"+param.get("clientId"));
//		System.out.println(param.get("boardIndex"));
//		System.out.println(param.get("resolutionWidth"));
//		System.out.println(param.get("resolutionHeight"));
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = monitoringService.updateClientMonitoringConf(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // updateClientMonitoringConf()
	
	/**
	 * Client 모니터링 설정 조회
	 * @return 
	 */
	@RequestMapping(value = "/client/monitoring/conf", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientMonitoringConf(@RequestBody MonitoringInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println("clientId :"+param.getClientId());
//		System.out.println(param.get("boardIndex"));
//		System.out.println(param.get("resolutionWidth"));
//		System.out.println(param.get("resolutionHeight"));
//		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		MonitoringInfoVO resultMap = null;
		try {
			resultMap = monitoringService.getClientMonitoringConf(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
//			resultMap = new HashMap<String,Object>();
//			resultMap.put("result", 500);
//			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientMonitoringConf()
}