package com.jms.dboard.manage.menu.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jms.dboard.login.dao.LoginDao;
import com.jms.dboard.manage.dao.ServerMngDao;
import com.jms.dboard.manage.menu.dao.MenuDao;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	MenuDao menuDao;

	@Override
	public List<MenuInfoVO> getMenuList(UserInfoVO param) {
		// TODO Auto-generated method stub
		List<MenuInfoVO> menuList = new ArrayList<MenuInfoVO>();
		try {
			menuList = menuDao.getMenuList(param);
			System.out.println("list" + menuList.size());
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public Map<String, Object> getClientMenuInfo(ClientsInfoVO param){
		//TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {		
			list = menuDao.getClientMenuInfo(param);
			resultMap.put("data",list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 메뉴 조회 중 오류가 발생했습니다.";	
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> getExternalMenuPage(ClientBoardDataReqVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		Map<String,Object> resultData = new HashMap<String,Object>();
		try {		
			resultData = menuDao.getExternalMenuPage(param);
			resultMap.put("data",resultData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 메뉴 조회 중 오류가 발생했습니다.";	
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
}
