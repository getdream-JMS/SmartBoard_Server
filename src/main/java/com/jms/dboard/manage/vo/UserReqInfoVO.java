package com.jms.dboard.manage.vo;

import java.util.List;
import org.apache.ibatis.type.Alias;

import com.jms.dboard.common.vo.BaseSearchVO;

/**
 * 사용자등록 요청 정보 
 * */
@Alias("userReqInfoVO")
public class UserReqInfoVO extends BaseSearchVO{
	
	private int reqId;
	private String userId;
	private String userName;
	private String userPass;
	private String sabun;
	private String organId;
	private String organName;
	private String email;
	private String telNum;
	private String department;
	private List<String> roles;
	private List<String> organs;
	private String activeYn;
	private String photoPath;
	private String userInfo;
	private String isAccept;
	private String checkedMember;
	
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
	 * @return the userPass
	 */
	public String getUserPass() {
		return userPass;
	}
	/**
	 * @param userPass the userPass to set
	 */
	public void setUserPass(String userPass) {
	}
	
	/**
	 * @return the sabun
	 */
	public String getSabun() {
		return sabun;
	}
	/**
	 * @param sabun the sabun to set
	 */
	public void setSabun(String sabun) {
		this.sabun = sabun;
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
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	/**
	 * @return the organs
	 */
	public List<String> getOrgans() {
		return organs;
	}
	/**
	 * @param organs the organs to set
	 */
	public void setOrgans(List<String> organs) {
		this.organs = organs;
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
	 * @return the userInfo
	 */
	public String getUserInfo() {
		return userInfo;
	}
	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
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
	 * @return the checkedMember
	 */
	public String getCheckedMember() {
		return checkedMember;
	}
	/**
	 * @param checkedMember the checkedMember to set
	 */
	public void setCheckedMember(String checkedMember) {
		this.checkedMember = checkedMember;
	}
	
}
