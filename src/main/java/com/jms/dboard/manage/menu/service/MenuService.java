package com.jms.dboard.manage.menu.service;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.menu.dao.MenuDao;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface MenuService {
	/**관리자 페이지 메뉴 정보  조회*/
	public List<MenuInfoVO> getMenuList (UserInfoVO param);
	
	
	/**클라이언트 페이지 메뉴 정보  조회*/
	public Map<String,Object> getClientMenuInfo (ClientsInfoVO param);
	
	/**iframe 내 페이지 URL 조회*/
	public Map<String,Object> getExternalMenuPage (ClientBoardDataReqVO param);
	
	
}
