package com.jms.dboard.login.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface LoginDao {
	/*로그인 정보 확인 */
	public UserInfoVO authUserInfo(UserInfoVO param) throws Exception;
}
