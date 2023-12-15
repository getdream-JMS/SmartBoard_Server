package com.jms.dboard.manage.missing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

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

import com.jms.dboard.common.vo.BaseSearchVO;
import com.jms.dboard.manage.content.service.ContentManageService;
import com.jms.dboard.manage.missing.service.MissingManageService;
import com.jms.dboard.manage.tonghap.service.TonghapInterfaceService;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientLocationInfoResultVO;
import com.jms.dboard.manage.vo.ClientLocationReqInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleResInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleManagerVO;
import com.jms.dboard.manage.vo.MissingPeopleReqInfoVO;
import com.jms.dboard.manage.vo.PromoBoardVO;
import com.jms.dboard.manage.vo.TonghapEventClientVO;

/**
 * 실종자 정보 연동 API 컨트롤러
 *
 * @author shmoon
 */
@RequestMapping(value = "/cms")
@RestController
public class MissingController {

	@Autowired
	MissingManageService missingManageService;
	

	/**
	 * 클라이언트 게시판 실종자 데이타 리스트  조회 
	 * @return 
	 */
	@RequestMapping(value = "/client/missing", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientMissingData(@ModelAttribute MissingPeopleReqInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String,Object> resultMap = missingManageService.getMissingClientList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientMissingData()
	
	/**
	 * 클라이언트 관리자 실종자 데이타 리스트  조회 
	 * @return 
	 */
	@RequestMapping(value = "/manager/missing", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getManagerMissingData(@ModelAttribute MissingPeopleManagerVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String,Object> resultMap = missingManageService.getMissingManagerList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getManagerMissingData()
	
	/**
	 * 관리자 실종자 데이타  저장 
	 * @return 
	 */
	@RequestMapping(value = "/manager/missing", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveManagerMissingData(@ModelAttribute MissingPeopleReqInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String,Object> resultMap = missingManageService.saveMissingInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveManagerMissingData()
	
	/**
	 * 관리자 실종자 데이타 수정 
	 * @return 
	 */
	@RequestMapping(value = "/manager/missing/{id}", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateManagerMissingData(@PathVariable("id") int id, @ModelAttribute MissingPeopleReqInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			param.setId(id);
			Map<String,Object> resultMap = missingManageService.updateMissingInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // updateManagerMissingData()
	
	/**
	 * 관리자 실종자 데이타 수정 
	 * @return 
	 */
	@RequestMapping(value = "/manager/missing/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getManagerMissingData(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {

			Map<String,Object> resultMap = missingManageService.getMissingInfo(id);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // updateManagerMissingData()
	
	/**
	 * 관리자 실종자 데이타 리스트  삭제 
	 * @return 
	 */
	@RequestMapping(value = "/manager/missing", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteManagerMissingData(@RequestBody List<Integer> param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String,Object> resultMap = missingManageService.deleteMissingInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // deleteManagerMissingData()
}