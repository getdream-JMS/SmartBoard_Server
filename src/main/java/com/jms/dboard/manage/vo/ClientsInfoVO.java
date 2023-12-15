package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("clientsInfoVO")
public class ClientsInfoVO extends BaseSearchVO {
	int clientId;
    String clientName;
    String organId;
    String clientIp;
    String holidayOff;
    String customHolidayOff;
    String clientAddr;
    String longitude;
    String latitude;
    String activeYn;
    int layoutType;
    int resolutionId;    
    String smartFrame;
    
    List<ClientBoardsInfoVO>  boards;
    List<CustomHolidayOffVO> customHolidays;
    List<ClientScheduleVO> weekSchedule;
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
	 * @return the organId
	 */
	public String getOrganId() {
		return organId;
	}
	/**
	 * @param organId the organId to set
	 */
	public void setOrganId(String organId) {
		this.organId = organId;
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
	 * @return the holidayOff
	 */
	public String getHolidayOff() {
		return holidayOff;
	}
	/**
	 * @param holidayOff the holidayOff to set
	 */
	public void setHolidayOff(String holidayOff) {
		this.holidayOff = holidayOff;
	}
	/**
	 * @return the customHolidayOff
	 */
	public String getCustomHolidayOff() {
		return customHolidayOff;
	}
	/**
	 * @param customHolidayOff the customHolidayOff to set
	 */
	public void setCustomHolidayOff(String customHolidayOff) {
		this.customHolidayOff = customHolidayOff;
	}
	
	/**
	 * @return the clientAddr
	 */
	public String getClientAddr() {
		return clientAddr;
	}
	/**
	 * @param clientAddr the clientAddr to set
	 */
	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the activeYn
	 */
	public String getActiveYn() {
		return activeYn;
	}
	/**
	 * @param activeYn the activeYn to set
	 */
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	/**
	 * @return the boards
	 */
	public List<ClientBoardsInfoVO> getBoards() {
		return boards;
	}
	/**
	 * @param boards the boards to set
	 */
	public void setBoards(List<ClientBoardsInfoVO> boards) {
		this.boards = boards;
	}
	/**
	 * @return the customHolidays
	 */
	public List<CustomHolidayOffVO> getCustomHolidays() {
		return customHolidays;
	}
	/**
	 * @param customHolidays the customHolidays to set
	 */
	public void setCustomHolidays(List<CustomHolidayOffVO> customHolidays) {
		this.customHolidays = customHolidays;
	}
	/**
	 * @return the weekSchedule
	 */
	public List<ClientScheduleVO> getWeekSchedule() {
		return weekSchedule;
	}
	/**
	 * @param weekSchedule the weekSchedule to set
	 */
	public void setWeekSchedule(List<ClientScheduleVO> weekSchedule) {
		this.weekSchedule = weekSchedule;
	}
	/**
	 * @return the layoutType
	 */
	public int getLayoutType() {
		return layoutType;
	}
	/**
	 * @param layoutType the layoutType to set
	 */
	public void setLayoutType(int layoutType) {
		this.layoutType = layoutType;
	}
	/**
	 * @return the resolutionId
	 */
	public int getResolutionId() {
		return resolutionId;
	}
	/**
	 * @param resolutionId the resolutionId to set
	 */
	public void setResolutionId(int resolutionId) {
		this.resolutionId = resolutionId;
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
