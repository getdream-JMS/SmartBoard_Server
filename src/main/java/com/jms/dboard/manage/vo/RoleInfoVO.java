package com.jms.dboard.manage.vo;

import org.apache.ibatis.type.Alias;

/**
 *  권한정보 정보 
 * */
@Alias("roleInfoVO")
public class RoleInfoVO {
	
	private String roleId;
	private String roleName;
	private String roleType;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the roleType
	 */
	public String getRoleType() {
		return roleType;
	}
	/**
	 * @param roleType the roleType to set
	 */
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}		
}
