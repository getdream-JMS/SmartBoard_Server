package com.jms.dboard.manage.jumin;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jms.dboard.manage.jumin.service.JuminBoardService;
import com.jms.dboard.manage.vo.PromoBoardVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 주민소식 관리 컨트롤러
 *
 * @author shmoon
 */
@RequestMapping(value = "/cms")
@RestController

public class JuminBoardController implements HandlerExceptionResolver {

	Logger logger = Logger.getLogger(JuminBoardController.class.getName());
	
	@Autowired
	JuminBoardService juminBoardService;
	
	/**
	 * 주민소식 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/jumin", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJuminList(@ModelAttribute PromoBoardVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				
				param.setRoleType(userInfo.getRoleType());
				param.setUserId(userInfo.getUserId());
			}
			 Map<String,Object> resultMap = juminBoardService.getJuminBoardList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getJuminList()
	
	/**
	 * 주민소식 상세 조회 
	 * @return 
	 */
	@RequestMapping(value = "/jumin/{contentId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getJuminDetail(@PathVariable int contentId, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = juminBoardService.getJuminBoardInfo(contentId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getJuminList()
	
	/**
	 * 주민소식 저장 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/jumin", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveJuminInfo(@ModelAttribute PromoBoardVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
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
			resultJson = juminBoardService.saveJuminBoardInfo(param);
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
			resultJson.put("resultMsg", "주민소식 저장 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveJuminInfo()
	
	/**
	 * 주민소식 수정 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/jumin/{contentId}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateJuminInfo(@PathVariable int contentId,@ModelAttribute PromoBoardVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
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
			resultJson = juminBoardService.updateJuminBoardInfo(param);
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);



		} catch (Exception e) {
			resultJson.put("result", 500);
			resultJson.put("resultMsg", "주민소식 저장 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveJuminInfo()
	
	/**
	 * 주민소식 삭제
	 */
	@RequestMapping(value = "/jumin", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteJuminInfo(@RequestBody List<Integer> params, HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			resultMap = juminBoardService.deleteJuminBoardInfo(params);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);



		} catch (Exception e) {
			resultMap.put("result", "");
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getStatusLiveTranscoding()
	 @Override
	    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exc) {        
	        ModelAndView modelAndView = new ModelAndView("file");
	        if (exc instanceof MaxUploadSizeExceededException) {
	        	System.out.println("File size exceeds limit!");
	            modelAndView.getModel().put("message", "File size exceeds limit!");
	        }
	        return modelAndView;
	    }
}
