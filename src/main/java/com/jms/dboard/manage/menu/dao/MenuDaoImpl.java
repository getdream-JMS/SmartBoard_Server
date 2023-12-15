package com.jms.dboard.manage.menu.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "menuDao")
public class MenuDaoImpl implements MenuDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<MenuInfoVO> getMenuList(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("menuMng.selectMenuList", param);
	}

	@Override
	public List<Map<String, Object>> getClientMenuInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("menuMng.selectClientMenuInfo", param);
	}

	@Override
	public Map<String, Object> getExternalMenuPage(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("menuMng.selectExternalMenuPage", param);
	}

	

}
