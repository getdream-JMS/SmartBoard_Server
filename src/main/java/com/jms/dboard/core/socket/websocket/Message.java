package com.jms.dboard.core.socket.websocket;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.MonitoringReqInfoVO;

public class Message {
	private String eventType;
	private String monitorType;
    private int clientId;
    private String clientIp;
    private Map<String,Object> resource;
    private List<Map<String,Object>> monitor;

	private String pageVersion;

	private String resourceVersion;


    /**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	/**
	 * @return the monitorType
	 */
	public String getMonitorType() {
		return monitorType;
	}

	/**
	 * @param monitorType the monitorType to set
	 */
	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}

	@Override
    public String toString() {
        return super.toString();
    }
    
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
	 * @return the monitor
	 */
	public List<Map<String,Object>> getMonitor() {
		return monitor;
	}

	/**
	 * @param monitor the monitor to set
	 */
	public void setMonitor(List<Map<String,Object>> monitor) {
		this.monitor = monitor;
	}

	public String getPageVersion() {
		return pageVersion;
	}

	public void setPageVersion(String pageVersion) {
		this.pageVersion = pageVersion;
	}

	public String getResourceVersion() {
		return resourceVersion;
	}

	public void setResourceVersion(String resourceVersion) {
		this.resourceVersion = resourceVersion;
	}
}