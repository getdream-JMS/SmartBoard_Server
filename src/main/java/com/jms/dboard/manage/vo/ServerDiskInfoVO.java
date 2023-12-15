package com.jms.dboard.manage.vo;

import org.apache.ibatis.type.Alias;

@Alias("serverDiskInfoVO")
public class ServerDiskInfoVO {
	String serverIp;
	String pttnNm;
	int pttnRto;
	String createDate;
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getPttnNm() {
		return pttnNm;
	}
	public void setPttnNm(String pttnNm) {
		this.pttnNm = pttnNm;
	}
	public int getPttnRto() {
		return pttnRto;
	}
	public void setPttnRto(int pttnRto) {
		this.pttnRto = pttnRto;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
