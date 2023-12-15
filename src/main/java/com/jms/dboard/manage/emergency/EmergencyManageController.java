package com.jms.dboard.manage.emergency;

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

import com.jms.dboard.manage.promo.service.PromoBoardService;
import com.jms.dboard.manage.vo.PromoBoardVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 긴급 컨텐츠 관리 컨트롤러
 *
 * @author shmoon
 */
@RequestMapping(value = "/cms")
@RestController

public class EmergencyManageController {

	Logger logger = Logger.getLogger(EmergencyManageController.class.getName());
	
	@Autowired
	PromoBoardService promoBoardService;
	
	/**
	 * 긴급컨텐츠 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/emergency", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getPromoList(@ModelAttribute PromoBoardVO param, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = promoBoardService.getPromoBoardList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getPromoList()
	
	/**
	 * 긴급컨텐츠 상세 조회 
	 * @return 
	 */
	@RequestMapping(value = "/emergency/{contentId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getPromoDetail(@PathVariable int contentId, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = promoBoardService.getPromoBoardInfo(contentId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getPromoList()
	
	/**
	 * 긴급컨텐츠 저장 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/emergency", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> savePromoInfo(@ModelAttribute PromoBoardVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				param.setPublisher(userInfo.getUserId());
			} else {
				param.setPublisher("admin");
			}
			resultJson = promoBoardService.savePromoBoardInfo(param);
			System.out.println(param.getTitle());
			System.out.println(param.getPostingStart());
			System.out.println(param.getPostingEnd());
			System.out.println(param.getPriority());
			System.out.println(param.getTags());
//			System.out.println(param.getLocation().size());
			System.out.println(param.getDelExpired());
			System.out.println(param.getFile());

			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);



		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", "긴급컨텐츠 저장 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // savePromoInfo()
	
	/**
	 * 긴급컨텐츠 수정 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/emergency/{contentId}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updatePromoInfo(@PathVariable int contentId,@ModelAttribute PromoBoardVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				param.setPublisher(userInfo.getUserId());
			} else {
				param.setPublisher("admin");
			}
			param.setContentId(contentId);
			resultJson = promoBoardService.updatePromoBoardInfo(param);
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);



		} catch (Exception e) {
			resultJson.put("result", 500);
			resultJson.put("resultMsg", "긴급컨텐츠 저장 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // savePromoInfo()
	
	/**
	 * 긴급컨텐츠 삭제
	 */
	@RequestMapping(value = "/emergency", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deletePromoInfo(@RequestBody List<Integer> params, HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			for(int i = 0; i< params.size();i++) {
				System.out.println(params.get(i));
			}
			resultMap = promoBoardService.deletePromoBoardInfo(params);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);



		} catch (Exception e) {
			resultMap.put("result", "");
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getStatusLiveTranscoding()
}
