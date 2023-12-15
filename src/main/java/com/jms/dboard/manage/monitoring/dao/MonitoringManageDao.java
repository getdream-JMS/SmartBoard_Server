package com.jms.dboard.manage.monitoring.dao;

import java.util.List;
import java.util.Map;

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

public interface MonitoringManageDao {
	/*사용자별 권한 소유 메뉴 리스트 조회 */
	public int insertClientStatus(Map<String,Object> param) throws Exception;
	public int updateClientStatus(Map<String,Object> param) throws Exception;
	public int checkClientStatus() throws Exception;
	public String getClientMonitoringType(MonitoringReqInfoVO param) throws Exception;	
	public int saveClientMonitoring(List<Map<String,Object>> param) throws Exception;
	public int saveClientMonitoring(Map<String,Object> param) throws Exception;
	public MonitoringResInfoVO getClientMonitoring(int clientId) throws Exception;
	public int updateClientMonitoring(List<Map<String,Object>> param) throws Exception;
	public int updateClientMonitoring(Map<String,Object> param) throws Exception;
	public int deleteClientMonitoring(MonitoringReqInfoVO param) throws Exception;
	public int deleteClientMonitoring(List<Integer> param) throws Exception;
	public int saveClientMonitoringConf(MonitoringInfoVO param) throws Exception;
	public int updateClientMonitoringConf(MonitoringInfoVO param) throws Exception;
	public MonitoringInfoVO getClientMonitoringConf(MonitoringInfoVO param) throws Exception;	
	/**클라이언트 모니터링 설정 정보 삭제 */
	public int deleteClientsMonitoringConf (List<Integer> params) throws Exception;
}

