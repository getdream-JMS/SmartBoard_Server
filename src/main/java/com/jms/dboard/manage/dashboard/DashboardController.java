package com.jms.dboard.manage.dashboard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.manage.dashboard.service.DashboardService;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * 대시보드 관련 컨트롤러
 *
 * @author shmoon
 */
@RestController
@RequestMapping(value = "/cms")
public class DashboardController {

	@Autowired
	DashboardService dashboardService;

/**
 * 메뉴 리스트 조회
 * */

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getDashBoardInfo(HttpServletRequest request, HttpServletResponse response,HttpSession session)
		{
			HttpHeaders headers = new HttpHeaders();
			Map<String,Object> resultMap = new HashMap<String,Object>();
			UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
			if(userInfo == null) {				
				userInfo = new UserInfoVO();
				userInfo.setUserId("admin");
				userInfo.setRoleType("S");
				session.setAttribute("sessionUserInfo", userInfo);
			}
			try {
				 resultMap = dashboardService.getDashboardInfo(userInfo.getUserId());
				return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result", 500);
				resultMap.put("resultMsg", e.getMessage());
				return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} // getContentInfoList()
}
