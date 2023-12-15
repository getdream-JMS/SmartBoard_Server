package com.jms.dboard.manage.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;

public interface ServerMngDao {
	public List<ServerInfoVO> getServerList(ServerInfoVO param) throws Exception;
	public ServerInfoVO getServerId(Map<String,Object> param) throws Exception;
	
	public ServerInfoVO getServerIdByName(Map<String,Object> param) throws Exception;
	public int insertServerStatus(ServerStatusVO param) throws Exception;
	public int updateServerStatus(ServerStatusVO param) throws Exception;
	public List<ServerInfoVO> getServerIpList(ServerInfoVO param) throws Exception;
	/**
	 * 서버 SNMP 정보
	 * */
	public ServerSnmpVO getServerSnmpInfo(String param) throws Exception;
	
	/** 서버 System 상태 insert */
	public int insertServerSnmpInfo(ServerSnmpVO map);

	/** 서버 System 상태 update */
	public int updateServerSnmpInfo(ServerSnmpVO map) ;

	/** 서버 System Disk 사용 정보 조회 */
	public ServerDiskInfoVO selectSvrDiskUseInfo(Map<String,Object> map) ;

	/** 서버 System Disk 사용 정보 Insert */
	public int insertSvrDiskUseInfo(Map<String,Object> map) ;

	/** 서버 System Disk 사용 정보 Update */
	public int updateSvrDiskUseInfo(Map<String,Object> map) ;

	/** 서버 System Disk 사용 정보 Delete */
	public int deleteSvrDiskUseInfo(String serverip);
	
	public List<ServerInfoVO> getServerListByIp(String serverIp) throws Exception; 

}
