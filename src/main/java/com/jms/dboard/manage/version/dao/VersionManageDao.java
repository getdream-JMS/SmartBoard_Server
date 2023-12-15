package com.jms.dboard.manage.version.dao;

import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;
import com.jms.dboard.manage.vo.MonitoringResInfoVO;

import java.util.List;
import java.util.Map;

public interface VersionManageDao {
	public int updateVersionInfo(Map<String, Object> param) throws Exception;

	/**
	 * 클라이언트로부터 버전 정보 조회
	 */
	public Map<String, Object> getVersionInfo(Map<String, Object> param) throws Exception;

	public Map<String, Object> getClientVersionInfo(Map<String, Object> param) throws Exception;
}

