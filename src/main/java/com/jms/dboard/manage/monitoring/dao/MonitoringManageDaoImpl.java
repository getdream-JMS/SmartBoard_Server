package com.jms.dboard.manage.monitoring.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;
import com.jms.dboard.manage.vo.MonitoringResInfoVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "monitoringManageDao")
public class MonitoringManageDaoImpl implements MonitoringManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;


	@Override
	public int insertClientStatus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("monitorMng.insertClientStatus", param);
	}

	@Override
	public int updateClientStatus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("monitorMng.updateClientStatus", param);
	}

	@Override
	public int checkClientStatus() throws Exception {
		return sqlSession.update("monitorMng.updateClientErrorStatus");
		
	}

	@Override
	public String getClientMonitoringType(MonitoringReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("monitorMng.selectClientMonitoringType", param);
	}

	@Override
	public int saveClientMonitoring(List<Map<String,Object>> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("monitorMng.insertClientMonitoring", param);
	}
	
	@Override
	public int saveClientMonitoring(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("monitorMng.insertClientMonitoring", param);
	}
	
	@Override
	public int updateClientMonitoring(List<Map<String,Object>> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("monitorMng.updateClientMonitoring", param);
	}
	
	@Override
	public int updateClientMonitoring(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("monitorMng.updateClientMonitoring", param);
	}
	
	@Override
	public int deleteClientMonitoring(MonitoringReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("monitorMng.deleteClientMonitoring", param);
	}
	
	@Override
	public int deleteClientMonitoring(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("monitorMng.deleteClientMonitoringList", param);
	}
	
	@Override
	public int saveClientMonitoringConf(MonitoringInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("monitorMng.insertClientMonitoringConf", param);
	}
	@Override
	public int updateClientMonitoringConf(MonitoringInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("monitorMng.updateClientMonitoringConf", param);
	}

	@Override
	public MonitoringInfoVO getClientMonitoringConf(MonitoringInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("monitorMng.selectClientMonitoringConf", param);
	}

	@Override
	public MonitoringResInfoVO getClientMonitoring(int clientId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("monitorMng.selectClientMonitoring", clientId);
	}
	
	@Override
	public int deleteClientsMonitoringConf(List<Integer> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("monitorMng.deleteClientMonitoringConf", params);
	}
	
}
