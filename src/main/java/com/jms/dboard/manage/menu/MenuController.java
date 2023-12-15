package com.jms.dboard.manage.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.manage.menu.service.MenuService;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

/**
 * Menu관련 컨트롤러
 *
 * @author shmoon
 */
@RestController
@RequestMapping(value = "/cms")
public class MenuController {

	@Autowired
	MenuService menuService;

/**
 * 메뉴 리스트 조회
 * */

	@RequestMapping(value = "/menu", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> checkLoginInfo(HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{
//		System.out.println("params"+params);
		JSONObject authResult = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		UserInfoVO userInfo = (UserInfoVO)session.getAttribute("sessionUserInfo");
		List<MenuInfoVO> menuList = menuService.getMenuList(userInfo);
				
		authResult.put("result", 200);
		authResult.put("resultMsg", "");
     return new ResponseEntity<>(menuList, headers, HttpStatus.OK);
 }
}
