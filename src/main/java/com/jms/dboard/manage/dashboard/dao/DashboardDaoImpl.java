package com.jms.dboard.manage.dashboard.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "dashboardDao")
public class DashboardDaoImpl implements DashboardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public Map<String,Object> getDashboardInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("dashboardMng.selectDashboardInfo", param);
	}

	

}
