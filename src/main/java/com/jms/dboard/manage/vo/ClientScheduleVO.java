package com.jms.dboard.manage.vo;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.type.Alias;

/**
 * 클라이언트 주간 스케쥴 시간 정보  
 * */
@Alias("clientScheduleVO")
public class ClientScheduleVO {
	
	private int clientId;
	private int weekDay;
	private String startTime;
	private String endTime;
	private String useYn;
	private int ordering;
	
	
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
	 * @return the weekDay
	 */
	public int getWeekDay() {
		return weekDay;
	}
	/**
	 * @param weekDay the weekDay to set
	 */
	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	/**
	 * @return the ordering
	 */
	public int getOrdering() {
		return ordering;
	}
	/**
	 * @param ordering the ordering to set
	 */
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
		
}
