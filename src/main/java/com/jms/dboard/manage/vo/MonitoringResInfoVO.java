package com.jms.dboard.manage.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("monitoringResInfoVO")
public class MonitoringResInfoVO {
	String clientId;
	String clientName;
    Integer monitorType;
    String monitorAction;
    Integer interval;
    Integer layoutType;
    String smartFrame;
    List<Map<String,Object>> monitor;
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * @param monitorType the monitorType to set
	 */
	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
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
	 * @return the monitorAction
	 */
	public String getMonitorAction() {
		return monitorAction;
	}
	/**
	 * @param monitorAction the monitorAction to set
	 */
	public void setMonitorAction(String monitorAction) {
		this.monitorAction = monitorAction;
	}
		
	/**
	 * @return the interval
	 */
	public Integer getInterval() {
		return interval;
	}
	/**
	 * @param interval the interval to set
	 */
	public void setInterval(Integer interval) {
		this.interval = interval;
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
	/**
	 * @return the layoutType
	 */
	public Integer getLayoutType() {
		return layoutType;
	}
	/**
	 * @param layoutType the layoutType to set
	 */
	public void setLayoutType(Integer layoutType) {
		this.layoutType = layoutType;
	}
	/**
	 * @return the smartFrame
	 */
	public String getSmartFrame() {
		return smartFrame;
	}
	/**
	 * @param smartFrame the smartFrame to set
	 */
	public void setSmartFrame(String smartFrame) {
		this.smartFrame = smartFrame;
	}
	
}