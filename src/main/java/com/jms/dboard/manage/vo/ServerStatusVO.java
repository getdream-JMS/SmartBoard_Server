package com.jms.dboard.manage.vo;

import org.apache.ibatis.type.Alias;

@Alias("serverStatusVO")
public class ServerStatusVO {
	String serverId;
	int serviceStatus;
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public int getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(int serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

}
