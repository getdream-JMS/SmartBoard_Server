package com.jms.dboard.manage.reserve;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jms.dboard.manage.reserve.service.ReserveBoardService;
import com.jms.dboard.manage.vo.ReserveRoomInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 회의실 예약 관리 컨트롤러
 *
 * @author shmoon
 */
@RequestMapping(value = "/cms")
@RestController

public class ReserveBoardController implements HandlerExceptionResolver {

	Logger logger = Logger.getLogger(ReserveBoardController.class.getName());
	
	@Autowired
	ReserveBoardService reserveBoardService;
	
	/**
	 * 회의실 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/reserve/room", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getReserveRoomList(HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String,Object> resultMap = reserveBoardService.getReserveRoomList();
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getReserveList()
	
	/**
	 * 회의실 예약 상세 조회 
	 * 
	/**
	 * 회의실 예약 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/reserve", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getReserveList(@ModelAttribute ReserveRoomInfoVO param, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				
				param.setRoleType(userInfo.getRoleType());
				param.setUserId(userInfo.getUserId());
			}
			 Map<String,Object> resultMap = reserveBoardService.getReserveBoardList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getReserveList()
	
	/**
	 * 회의실 예약 상세 조회 
	 * @return 
	 */
	@RequestMapping(value = "/reserve/{reserveId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getReserveDetail(@PathVariable int reserveId, HttpServletRequest request, HttpServletResponse response)
	{


		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			 Map<String,Object> resultMap = reserveBoardService.getReserveBoardInfo(reserveId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getReserveList()
	
	/**
	 * 회의실 예약 저장 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/reserve", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> saveReserveInfo(@ModelAttribute ReserveRoomInfoVO param, MultipartFile file, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{


		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				param.setUserId(userInfo.getUserId());
			} else {
				param.setUserId("admin");
			}
			resultJson = reserveBoardService.saveReserveBoardInfo(param,file);
//			System.out.println(param.getTitle());
//			System.out.println(param.getPlace());
//			System.out.println(param.getStartTime());
//			System.out.println(param.getEndTime());
//			System.out.println(param.getRoomId());
//			System.out.println(param.getOrganizer());
//			System.out.println(param.getDelExpired());
//			System.out.println(param.getFile());

			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);



		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", "회의실 예약 저장 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveReserveInfo()
	
	/**
	 * 회의실 예약 수정 
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/reserve/{reserveId}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> updateReserveInfo(@PathVariable int reserveId,@ModelAttribute ReserveRoomInfoVO param, MultipartFile file, HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{

		
		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				param.setUserId(userInfo.getUserId());
			} else {
				param.setUserId("admin");
			}
			param.setReserveId(reserveId);
			resultJson = reserveBoardService.updateReserveBoardInfo(param,file);
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);



		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", "회의실 예약 저장 중 오류가 발생했습니다.");
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // saveReserveInfo()
	
	/**
	 * 회의실 예약 삭제
	 */
	@RequestMapping(value = "/reserve", method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteReserveInfo(@RequestBody List<Integer> params, HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject resultJson = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			resultMap = reserveBoardService.deleteReserveBoardInfo(params);
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
