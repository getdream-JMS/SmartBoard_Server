package com.jms.dboard.manage.version;

import com.jms.dboard.manage.version.service.VersionManageService;
import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 서버 모니터링 및 클라이언트 상태  API 관련 컨트롤러
 *
 * @author shmoon
 */
@RequestMapping(value = "/cms")
@RestController
public class VersionController {

	@Autowired
	VersionManageService versioningService;
	/**
	 * 패치 버전 정보 저장
	 * @return 
	 */
	@RequestMapping(value = "/version", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateVersionInfo(@RequestBody Map<String,Object> param, HttpServletRequest request, HttpServletResponse response)
	{
//		System.out.println(param.get("boardIndex"));
//		System.out.println(param.get("mem"));
//		System.out.println(param.get("cpu"));
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			param.put("status", 1);
			resultMap = versioningService.updateVersionInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // updateVersionInfo()
	
	/**
	 * CMS -> SERVER 버전 정보 조회
	 * @param 	 * 	Map<String,Object>
	 * @return 
	 */
	@RequestMapping(value = "/version", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getVersionInfo(@RequestBody Map<String,Object> param, HttpServletRequest request, HttpServletResponse response)
	{

		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			param.put("status", 1);
			System.out.println("getVersionInfo :"+param.toString());
			resultMap = versioningService.getVersionInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getVersionInfo()

	/**
	 * CMS -> SERVER 버전 상세 정보 조회
	 * @param 	 * 	Map<String,Object>
	 * @return
	 */
	@RequestMapping(value = "/version/{id}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getVersionInfoDetail(@PathVariable int id, HttpServletRequest request, HttpServletResponse response)
	{

		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("id", id);
			resultMap = versioningService.getVersionInfo(paramMap);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getVersionInfoDetail()

	/**
	 * CMS -> CLIENT 페이지 수정 및 컨텐츠 등록 및 삭제 후 클라이언트로 패치 정보를 전달
	 * @param 	 * 	Map<String,Object>
	 * @return
	 */
	@RequestMapping(value = "/version/apply", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public void sendEMGVersion(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			versioningService.sendEMGVersion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // sendEMGVersion()
}