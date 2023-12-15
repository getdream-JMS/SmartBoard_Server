package com.jms.dboard.manage.monitoring.service;

import java.util.Map;

import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;

public interface MonitoringManageService {
	/**클라이언트 리소스 상태 저장   */
	public Map<String,Object> saveClientStatus (Map<String,Object> param);
	
	
	/**클라이언트 연결 상태 업데이트   */
	public void checkClientStatus ();
	
	/**클라이언트 화면 상태 업데이트   */
	public Map<String,Object> saveClientMonitoring (MonitoringReqInfoVO param);
	/**클라이언트 화면 상태 조회   */
	public Map<String,Object> getClientMonitoring (int clientId);
	
	/**클라이언트 모니터링 설정 조회  */
	public MonitoringInfoVO getClientMonitoringConf (MonitoringInfoVO param);
	
	/**클라이언트 모니터링 설정 변경  */
	public Map<String,Object> updateClientMonitoringConf (MonitoringInfoVO param);
}
