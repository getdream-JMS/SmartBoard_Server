package com.jms.dboard.manage.content;

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

import com.jms.dboard.manage.content.service.ContentManageService;
import com.jms.dboard.manage.vo.ContentInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 컨텐츠 관리 컨트롤러
 *
 * @author shmoon
 */
@RestController
@RequestMapping(value = "/cms")
public class ContentManageController {

	Logger logger = Logger.getLogger(ContentManageController.class.getName());
	
	@Autowired
	ContentManageService contentManageService;
	
	
	/**
	 * 컨텐츠 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/contents", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getContentInfoList(@ModelAttribute ContentInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = contentManageService.getContentInfoList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getContentInfoList()
	
	/**
	 * 컨텐츠 상세 조회 
	 * @return 
	 */
	@RequestMapping(value = "/contents/{contentId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getAnnounceDetail(@PathVariable int contentId, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = contentManageService.getContentInfo(contentId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getAnnounceList()
	
		
	/**
	 * 컨텐츠 삭제
	 */
	@RequestMapping(value = "/contents", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteAnnounceInfo(@RequestBody List<ContentInfoVO> params, HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			resultMap = contentManageService.deleteContentInfo(params);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);



		} catch (Exception e) {
			resultMap.put("result", "");
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getStatusLiveTranscoding()
}
