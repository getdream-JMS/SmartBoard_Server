package com.jms.dboard.manage.log.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.LogInfoResultVO;
import com.jms.dboard.manage.vo.LogInfoVO;
import com.jms.dboard.manage.vo.LoginLogInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "logManageDao")
public class LogManageDaoImpl implements LogManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<LogInfoResultVO> getLogDashboard(LogInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("logMng.selectLogDashboard", param);
	}

	@Override
	public int saveLogInfo(LogInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("logMng.saveLogInfo", param);
	}

	@Override
	public List<LogInfoResultVO> getLogInfoList(LogInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("logMng.selectLogInfoList", param);
	}

	@Override
	public int deleteLogInfoList(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("logMng.deleteLogInfoList", param);
	}

	@Override
	public int getTotalLogInfoList(LogInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("logMng.selectTotalLogInfoList", param);
	}
	
	@Override
	public List<LoginLogInfoVO> getLoginLogInfoList(LoginLogInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("logMng.selectLoginLogInfoList", param);
	}

	@Override
	public int getTotalLoginLogInfoList(LoginLogInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("logMng.selectTotalLoginLogInfoList", param);
	}
	@Override
	public int saveLoginLogInfo(LoginLogInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("logMng.saveLoginLogInfo", param);
	}
	
	@Override
	public int deleteLoginLogInfoList(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("logMng.deleteLoginLogInfoList", param);
	}

	@Override
	public int updateLoginLogInfo(LoginLogInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("logMng.updateLoginLogInfo", param);
	}

	@Override
	public LoginLogInfoVO getLoginLogInfo(LoginLogInfoVO param) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("logMng.selectLoginLogInfo", param);
	}
	
	@Override
	public int saveLogoutLogInfo(LoginLogInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("logMng.saveLogoutLogInfo", param);
	}
	
}
