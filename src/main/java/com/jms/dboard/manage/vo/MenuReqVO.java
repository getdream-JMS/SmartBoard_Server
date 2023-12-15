package com.jms.dboard.manage.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Alias("menuReqVO")
public class MenuReqVO {
	String userId;
	String userName;
	
	public String getUserId() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}
