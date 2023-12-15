package com.jms.dboard.manage.organization.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.OrganInfoResultVO;
import com.jms.dboard.manage.vo.OrganInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "organizationManageDao")
public class OrganizationManageDaoImpl implements OrganizationManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<OrganInfoResultVO> getOrganInfoList(OrganInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("organMng.selectOrganInfoList", param);
	}
	
	@Override
	public List<OrganInfoResultVO> getOfficeInfoList(OrganInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("organMng.selectOfficeInfoList", param);
	}

	@Override
	public int getTotalOrganInfoList(OrganInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("organMng.selectTotalOrganInfoList", param);
	}

	@Override
	public int getTotalOfficeInfoList(OrganInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("organMng.selectTotalOfficeInfoList", param);
	}

	@Override
	public List<UserInfoVO> getUsersByOrgan(UserInfoSearchVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("organMng.selectUsersByOrgan", param);
	}

	@Override
	public int getTotalUsersByOrganList(UserInfoSearchVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("organMng.selectTotalOrganUserInfoCount", param);
	}

	@Override
	public List<Map<String,Object>> getUserOfficeList(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("organMng.selectUserOfficeList", param);
	}

	@Override
	public List<Map<String, Object>> getAllClientList(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("organMng.selectAllClientList", param);
	}
	
	
}
