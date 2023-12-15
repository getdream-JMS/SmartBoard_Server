package com.jms.dboard.manage.version.service;

import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;

import java.util.Map;

public interface VersionManageService {
	/**패치 버전 정보 저장   */
	public Map<String,Object> updateVersionInfo (Map<String,Object> param);


	/**클라이언트에서 패치 버전 정보 조회*/
	public Map<String,Object> getClientVersionInfo (Map<String,Object> param);

	/**관리지페이지 패치 버전 정보 조회*/
	public Map<String,Object> getVersionInfo (Map<String,Object> param);

	public void sendEMGVersion ();

}
