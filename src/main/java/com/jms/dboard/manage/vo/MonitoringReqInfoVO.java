package com.jms.dboard.manage.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("monitoringReqInfoVO")
public class MonitoringReqInfoVO {
	int clientId;
    String clientIp;
    Map<String, Object> resource;
    Integer monitorType;
    List<Map<String,Object>> monitor;
	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the clientIp
	 */
	public String getClientIp() {
		return clientIp;
	}
	/**
	 * @param clientIp the clientIp to set
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	/**
	 * @return the resource
	 */
	public Map<String, Object> getResource() {
		return resource;
	}
	/**
	 * @param resource the resource to set
	 */
	public void setResource(Map<String, Object> resource) {
		this.resource = resource;
	}	
	/**
	 * @return the monitorType
	 */
	public Integer getMonitorType() {
		return monitorType;
	}
	/**
	 * @param monitorType the monitorType to set
	 */
	public void setMonitorType(int monitorType) {
		this.monitorType = monitorType;
	}
	/**
	 * @return the monitor
	 */
	public List<Map<String, Object>> getMonitor() {
		return monitor;
	}
	/**
	 * @param monitor the monitor to set
	 */
	public void setMonitor(List<Map<String, Object>> monitor) {
		this.monitor = monitor;
	}
}