package com.jms.dboard.login.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "loginDao")
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public UserInfoVO authUserInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("authUser.selectUserAuthInfo", param);
	}

	

}
