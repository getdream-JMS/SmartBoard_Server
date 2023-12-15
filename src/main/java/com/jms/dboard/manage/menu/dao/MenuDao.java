package com.jms.dboard.manage.menu.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface MenuDao {
	/*사용자별 권한 소유 메뉴 리스트 조회 */
	public List<MenuInfoVO> getMenuList(UserInfoVO param) throws Exception;
	
	/*클라이언트 화면 메뉴 리스트 조회 */
	public List<Map<String,Object>> getClientMenuInfo(ClientsInfoVO param) throws Exception;
	
	/*클라이언트 iframe 내 URL 조회 */
	public Map<String,Object> getExternalMenuPage(ClientBoardDataReqVO param) throws Exception;
	
}
