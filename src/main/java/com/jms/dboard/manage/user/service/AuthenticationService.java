package com.jms.dboard.manage.user.service;


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
import com.jms.dboard.manage.vo.RoleInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Service
public class AuthenticationService implements UserDetailsService {
	@Autowired
	private UserManageDao userManageDao;
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		UserInfoVO userInfo  = null;;
		UserDetails userDetails = null;
		try {
			userInfo = userManageDao.getUserInfo(userName);
			List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
			List<RoleInfoVO> roleInfoList = userInfo.getRoles();
			for(int i=0;i<roleInfoList.size();i++) {
				grantedAuthorityList.add(new SimpleGrantedAuthority(roleInfoList.get(i).getRoleName()));
			}
			userDetails = (UserDetails)new User(userInfo.getUserName(), 
					userInfo.getUserPass(), grantedAuthorityList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userDetails;
	}
} 