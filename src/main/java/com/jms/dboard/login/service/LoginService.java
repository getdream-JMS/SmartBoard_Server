package com.jms.dboard.login.service;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface LoginService {
	/**로그인정보  조회*/
	public UserInfoVO authUserInfo (UserInfoVO param);
}
