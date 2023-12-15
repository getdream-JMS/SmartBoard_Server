package com.jms.dboard.manage.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

/**
 * 회의실 기본 정보 
 * */
@Alias("reserveRoomInfoVO")
public class ReserveRoomInfoVO extends BaseSearchVO{
	
	private int roomId;
	private String roomName;
	private String roomInfo;
	private int roomNumber;
	private String useYn;
	private int reserveId;
	private String title;
	private String place;
	private String organizer;
	//private MultipartFile file;
	private String filePath;
	private String fileName;
	private String originFileName;
	private String reserveInfo;
	private String startTime;
	private String endTime;
	private int contentType;
	private String userId;
	private String createDate;
	private int remainTime;
	/**
	 * @return the roomId
	 */
	public int getRoomId() {
		return roomId;
	}
	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/**
	 * @return the roomInfo
	 */
	public String getRoomInfo() {
		return roomInfo;
	}
	/**
	 * @param roomInfo the roomInfo to set
	 */
	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}
	/**
	 * @return the roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
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
	 * @return the reserveId
	 */
	public int getReserveId() {
		return reserveId;
	}
	/**
	 * @param reserveId the reserveId to set
	 */
	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
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
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return the organizer
	 */
	public String getOrganizer() {
		return organizer;
	}
	/**
	 * @param organizer the organizer to set
	 */
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the originFileName
	 */
	public String getOriginFileName() {
		return originFileName;
	}
	/**
	 * @param originFileName the originFileName to set
	 */
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	/**
	 * @return the reserveInfo
	 */
	public String getReserveInfo() {
		return reserveInfo;
	}
	/**
	 * @param reserveInfo the reserveInfo to set
	 */
	public void setReserveInfo(String reserveInfo) {
		this.reserveInfo = reserveInfo;
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
	 * @return the contentType
	 */
	public int getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(int contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the remainTime
	 */
	public int getRemainTime() {
		return remainTime;
	}
	/**
	 * @param remainTime the remainTime to set
	 */
	public void setRemainTime(int remainTime) {
		this.remainTime = remainTime;
	}
	
}
