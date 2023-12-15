package com.jms.dboard.manage.service;


import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;

public interface ServerMngService {
	/**서버 리스트 조회*/
	public List<ServerInfoVO> getServerList(ServerInfoVO param);	
	
	/**서버 아이디 조회
	 * Param : Map<String,Object>
	 * */
	public ServerInfoVO getServerId(Map<String,Object> param);	
	
	/**서버 이름으로 서버정보 조회
	 * Param : Map<String,Object>
	 * */
	public ServerInfoVO getServerIdById(Map<String,Object> param);	
	/**서버 상태 추가
	 * Param : ServerStatusVO
	 * */
	public int insertServerStatus(ServerStatusVO param);
	
	/**서버 상태 수정
	 * Param : ServerStatusVO
	 * */
	public int updateServerStatus(ServerStatusVO param);
	
	
	/**서버 아이피 리스트 조회*/
	public List<ServerInfoVO> getServerIpList(ServerInfoVO param);	
	
	public ServerSnmpVO getServerSnmpInfo(String param);	 
	public int insertServerSnmpInfo(ServerSnmpVO param);
	public int updateServerSnmpInfo(ServerSnmpVO param);
	
	public ServerDiskInfoVO selectSvrDiskUseInfo(Map<String, Object> param);
	public int insertSvrDiskUseInfo(Map<String, Object> param);
	public int updateSvrDiskUseInfo(Map<String, Object> param);
	public int deleteSvrDiskUseInfo(String param);
	
}
