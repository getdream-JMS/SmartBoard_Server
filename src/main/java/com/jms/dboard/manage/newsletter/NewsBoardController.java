package com.jms.dboard.manage.newsletter;

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

import com.jms.dboard.manage.newsletter.service.NewsBoardService;
import com.jms.dboard.manage.vo.NewsBoardVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 소식지 관리 컨트롤러
 *
 * @author shmoon
 */
@RestController
@RequestMapping(value = "/cms")
public class NewsBoardController {

	Logger logger = Logger.getLogger(NewsBoardController.class.getName());
	
	@Autowired
	NewsBoardService newsBoardService;
	/**
	 * 소식지 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/newsletter", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getNewsList(@ModelAttribute NewsBoardVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				
				param.setRoleType(userInfo.getRoleType());
				param.setUserId(userInfo.getUserId());
			}
			Map<String,Object> resultMap = newsBoardService.getNewsBoardList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getNewsList()
	
	/**
	 * 소식지 상세 조회 
	 * @return 
	 */
	@RequestMapping(value = "/newsletter/{contentId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getNewsDetail(@PathVariable int contentId, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = newsBoardService.getNewsBoardInfo(contentId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getNewsList()
	
	/**
	 * 소식지 저장 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/newsletter", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveNewsInfo(@ModelAttribute NewsBoardVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
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
			resultJson = newsBoardService.saveNewsBoardInfo(param);
//			System.out.println(param.getTitle());
//			System.out.println(param.getPostingStart());
//			System.out.println(param.getPostingEnd());
//			System.out.println(param.getPriority());
//			System.out.println(param.getTags());
//			System.out.println(param.getLocation().size());
//			System.out.println(param.getDelExpired());
//			System.out.println(param.getFile());

			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);



		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", "소식지 저장 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveNewsInfo()
	
	/**
	 * 소식지 수정 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/newsletter/{contentId}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateNewsInfo(@PathVariable int contentId,@ModelAttribute NewsBoardVO param, HttpServletRequest request, HttpServletResponse response)
	{

		System.out.println("소식지 수정 요청");
		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println(contentId);
//			System.out.println(param.getTitle());
//			System.out.println(param.getPostingStart());
//			System.out.println(param.getPostingEnd());
//			System.out.println(param.getPriority());
//			System.out.println(param.getTags());
//			System.out.println(param.getLocation().size());
//			System.out.println(param.getDelExpired());
//			System.out.println(param.getFile());
			resultJson = newsBoardService.updateNewsBoardInfo(param);
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);



		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", "소식지 수정 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveNewsInfo()
	
	/**
	 * 소식지 삭제
	 */
	@RequestMapping(value = "/newsletter", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteNewsInfo(@RequestBody List<Integer> params, HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			for(int i = 0; i< params.size();i++) {
				System.out.println(params.get(i));
			}
			resultMap = newsBoardService.deleteNewsBoardInfo(params);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);



		} catch (Exception e) {
			resultMap.put("result", "");
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getStatusLiveTranscoding()
}
