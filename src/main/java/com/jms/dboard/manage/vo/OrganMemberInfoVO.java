package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("organMemberInfoVO")
public class OrganMemberInfoVO extends BaseSearchVO {
	int reqId;
    String userId;
    String userPass;
    String userName;
    String organId;
    String email;
    String telNum;
    String phone;
    String position;
    String duty;
    String photoPath;
    int seq;
    String appNo;
    int display;
    String isAccept;
    String createDate;
    String organName;
    private List<RoleInfoVO> roles;
	private List<OrganInfoVO> organs;
    
	/**
	 * @return the reqId
	 */
	public int getReqId() {
		return reqId;
	}
	/**
	 * @param reqId the reqId to set
	 */
	public void setReqId(int reqId) {
		this.reqId = reqId;
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
	 * @return the userPass
	 */
	public String getUserPass() {
		return userPass;
	}
	/**
	 * @param userPass the userPass to set
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the duty
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * @param duty the duty to set
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * @return the photoPath
	 */
	public String getPhotoPath() {
		return photoPath;
	}
	/**
	 * @param photoPath the photoPath to set
	 */
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	/**
	 * @return the appNo
	 */
	public String getAppNo() {
		return appNo;
	}
	/**
	 * @param appNo the appNo to set
	 */
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	/**
	 * @return the display
	 */
	public int getDisplay() {
		return display;
	}
	/**
	 * @param display the display to set
	 */
	public void setDisplay(int display) {
		this.display = display;
	}
	
	/**
	 * @return the isAccept
	 */
	public String getIsAccept() {
		return isAccept;
	}
	/**
	 * @param isAccept the isAccept to set
	 */
	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
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
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}
	/**
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	/**
	 * @return the roles
	 */
	public List<RoleInfoVO> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<RoleInfoVO> roles) {
		this.roles = roles;
	}
	/**
	 * @return the organs
	 */
	public List<OrganInfoVO> getOrgans() {
		return organs;
	}
	/**
	 * @param organs the organs to set
	 */
	public void setOrgans(List<OrganInfoVO> organs) {
		this.organs = organs;
	}
	
}
