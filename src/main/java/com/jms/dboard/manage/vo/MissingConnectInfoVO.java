package com.jms.dboard.manage.vo;


import org.apache.ibatis.type.Alias;


@Alias("missingConnectInfoVO")
public class MissingConnectInfoVO {
	int missingId;
	String title;
	String telNum;

	/**
	 * @return the missingId
	 */
	public int getMissingId() {
		return missingId;
	}
	/**
	 * @param missingId the missingId to set
	 */
	public void setMissingId(int missingId) {
		this.missingId = missingId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the telNum
	 */
	public String getTelNum() {
		return telNum;
	}
	/**
	 * @param telNum the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
}
