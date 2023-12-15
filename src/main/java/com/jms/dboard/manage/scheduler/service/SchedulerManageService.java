package com.jms.dboard.manage.scheduler.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.management.relation.RoleInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jms.dboard.manage.user.dao.UserManageDao;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.RoleInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

public interface SchedulerManageService  {
	/** 노원 부서 및사용자 정보 조회   */
	public Map<String,Object> syncNowonInfo ();
	
	public void deleteExpiredContents (String curDate);
	
	public void updateClientMntrStatus();
	
	public void getWeatherData();
	
	public Map<String,Object> getClientWeatherInfo();

	// public Map<String,Object> createCompressedFiles();
} 