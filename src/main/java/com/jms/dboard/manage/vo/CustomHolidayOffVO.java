package com.jms.dboard.manage.vo;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.type.Alias;

/**
 * 사용자 설정 공휴일 
 * */
@Alias("customHolidayOffVO")
public class CustomHolidayOffVO {
	
	private String clientId;
	private String exceptDay;
	private String exceptType;
	private String dayTitle;
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
	 * @return the exceptDay
	 */
	public String getExceptDay() {
		return exceptDay;
	}
	/**
	 * @param exceptDay the exceptDay to set
	 */
	public void setExceptDay(String exceptDay) {
		this.exceptDay = exceptDay;
	}
	/**
	 * @return the exceptType
	 */
	public String getExceptType() {
		return exceptType;
	}
	/**
	 * @param exceptType the exceptType to set
	 */
	public void setExceptType(String exceptType) {
		this.exceptType = exceptType;
	}
	/**
	 * @return the dayTitle
	 */
	public String getDayTitle() {
		return dayTitle;
	}
	/**
	 * @param dayTitle the dayTitle to set
	 */
	public void setDayTitle(String dayTitle) {
		this.dayTitle = dayTitle;
	}
	
	
}
