package com.jms.dboard.manage.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;

@Repository(value = "serverMngDao")
public class ServerMngDaoImpl implements ServerMngDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	 
	@Override
	public List<ServerInfoVO> getServerList(ServerInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("serverMng.selectServerList", param);
	}

	@Override
	public ServerInfoVO getServerId(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("serverMng.selectServerId", param);
	}
	@Override
	public ServerInfoVO getServerIdByName(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("serverMng.selectServerIdByName", param);
	}
	
	@Override
	public int insertServerStatus(ServerStatusVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("serverMng.insertServerStatus", param);
	}

	@Override
	public int updateServerStatus(ServerStatusVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("serverMng.updateServerStatus", param);
	}
	
	@Override
	public List<ServerInfoVO> getServerIpList(ServerInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("serverMng.selectServerIpList", param);
	}

	@Override
	public ServerSnmpVO getServerSnmpInfo(String param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("serverMng.selectServerSnmpInfo", param);
	}

	@Override
	public int insertServerSnmpInfo(ServerSnmpVO param) {
		// TODO Auto-generated method stub
		return sqlSession.insert("serverMng.insertSvrMntrInfo", param);
	}

	@Override
	public int updateServerSnmpInfo(ServerSnmpVO param) {
		// TODO Auto-generated method stub
		return sqlSession.update("serverMng.updateSvrMntrInfo", param);
	}

	@Override
	public ServerDiskInfoVO selectSvrDiskUseInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("serverMng.selectSvrDiskUseInfo", param);
	}

	@Override
	public int insertSvrDiskUseInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sqlSession.insert("serverMng.insertSvrDiskUseInfo", param);
	}

	@Override
	public int updateSvrDiskUseInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sqlSession.update("serverMng.updateSvrDiskUseInfo", param);
	}

	@Override
	public int deleteSvrDiskUseInfo(String param) {
		// TODO Auto-generated method stub
		return sqlSession.delete("serverMng.deleteSvrDiskUseInfo", param);
	}

	@Override
	public List<ServerInfoVO> getServerListByIp(String serverIp) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("serverMng.selectServerListByIp", serverIp);
	}

}
