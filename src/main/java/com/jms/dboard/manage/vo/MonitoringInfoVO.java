package com.jms.dboard.manage.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.sun.istack.Nullable;
import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("monitoringInfoVO")
public class MonitoringInfoVO {
	int clientId;
	String clientName;
    int interval;
    int monitorType;
    String monitorAction;

    FtpServerInfoVO ftpInfo;

	String ftpType;
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
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}
	/**
	 * @param interval the interval to set
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}
	/**
	 * @return the monitorType
	 */
	public int getMonitorType() {
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
	 * @return the ftpInfo
	 */
	public FtpServerInfoVO getFtpInfo() {
		return ftpInfo;
	}
	/**
	 * @param ftpInfo the ftpInfo to set
	 */
	public void setFtpInfo(FtpServerInfoVO ftpInfo) {
		this.ftpInfo = ftpInfo;
	}

	public String getFtpType() {
		return ftpType;
	}

	public void setFtpType(String ftpType) {
		this.ftpType = ftpType;
	}
}
