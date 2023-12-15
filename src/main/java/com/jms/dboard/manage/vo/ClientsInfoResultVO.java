package com.jms.dboard.manage.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("clientsInfoResultVO")
public class ClientsInfoResultVO {
	int clientId;
    String organId;
    String organName;
    String clientName;
    String clientIp;    
    String holidayOff;
    String customHolidayOff;
    String longitude;
    String latitude;
    int status; // 클라이언트 상태
    String createDate;
    String updateDate;
    String activeYn;
    List<Map<String,Object>>  boards;
    List<CustomHolidayOffVO> customHolidays;
    List<ClientScheduleVO> weekSchedule;
    int totalCount;
    String tBar;
    String bBar;
    int layoutType;
    int resolutionId;
    String smartFrame;
    
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}	
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
		
	public List<Map<String,Object>> getBoards() {
		return boards;
	}
	public void setBoards(List<Map<String,Object>>boards) {
		this.boards = boards;
	}
	public String getHolidayOff() {
		return holidayOff;
	}
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
	 * @param customHolidayOff the customHolidayOff to set
	 */
	public void setCustomHolidayOff(String customHolidayOff) {
		this.customHolidayOff = customHolidayOff;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
	public List<CustomHolidayOffVO> getCustomHolidays() {
		return customHolidays;
	}
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the tBar
	 */
	public String gettBar() {
		return tBar;
	}
	/**
	 * @param tBar the tBar to set
	 */
	public void settBar(String tBar) {
		this.tBar = tBar;
	}
	/**
	 * @return the bBar
	 */
	public String getbBar() {
		return bBar;
	}
	/**
	 * @param bBar the bBar to set
	 */
	public void setbBar(String bBar) {
		this.bBar = bBar;
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
