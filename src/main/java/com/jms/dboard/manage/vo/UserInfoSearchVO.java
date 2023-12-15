package com.jms.dboard.manage.vo;

import java.util.List;
import org.apache.ibatis.type.Alias;

import com.jms.dboard.common.vo.BaseSearchVO;

/**
 * 사용자 정보 조회  
 * */
@Alias("userInfoSearchVO")
public class UserInfoSearchVO extends BaseSearchVO{
	
	private String userId;
	private String userName;
	private String userPass;
	private String organId;
	private String organName;
	private String email;
	private String telnum;
	private String department;
	private List<RoleInfoVO> roles;
	private List<OrganInfoVO> organs;
	private String activeYn;
	private String photoPath;
	private String userInfo;
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
		this.userPass = userPass;
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
	 * @return the telnum
	 */
	public String getTelnum() {
		return telnum;
	}
	/**
	 * @param telnum the telnum to set
	 */
	public void setTelnum(String telnum) {
		this.telnum = telnum;
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
}
