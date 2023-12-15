package com.jms.dboard.manage.system.service;


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
import com.jms.dboard.manage.vo.ContentInfoVO;
import com.jms.dboard.manage.vo.RoleInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

public interface SystemManageService  {
	/** 컨텐츠 통계 검색   */
	public Map<String,Object> getStatisticContents (ContentInfoVO param);
	
	/** 리소스 사용율 확인 스케쥴러   */
	public void retrieveResources ();
	
} 