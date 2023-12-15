package com.jms.dboard.manage.vo;

import org.apache.ibatis.type.Alias;

@Alias("serverSnmpVO")
public class ServerSnmpVO {
	String serverIp;
	float cpuUseRto;
	float mmryUseRto;
	float diskUseRto;
	int ststNet;
	String createDate;
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public float getCpuUseRto() {
		return cpuUseRto;
	}
	public void setCpuUseRto(float cpuUseRto) {
		this.cpuUseRto = cpuUseRto;
	}	
	public float getMmryUseRto() {
		return mmryUseRto;
	}
	public void setMmryUseRto(float mmryUseRto) {
		this.mmryUseRto = mmryUseRto;
	}
	public float getDiskUseRto() {
		return diskUseRto;
	}
	public void setDiskUseRto(float diskUseRto) {
		this.diskUseRto = diskUseRto;
	}

	public int getStstNet() {
		return ststNet;
	}
	public void setStstNet(int ststNet) {
		this.ststNet = ststNet;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
}
