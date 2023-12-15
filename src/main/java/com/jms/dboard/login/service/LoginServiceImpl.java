package com.jms.dboard.login.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jms.dboard.login.dao.LoginDao;
import com.jms.dboard.manage.dao.ServerMngDao;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDao loginDao;

	@Override
	public UserInfoVO authUserInfo(UserInfoVO param) {
		// TODO Auto-generated method stub
		UserInfoVO userInfo = null;
		try {
			userInfo = loginDao.authUserInfo(param);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfo;
	}


	
}
