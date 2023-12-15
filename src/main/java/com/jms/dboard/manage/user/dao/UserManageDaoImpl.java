package com.jms.dboard.manage.user.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.OrganMemberInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

@Repository(value = "userManageDao")
public class UserManageDaoImpl implements UserManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Map<String, Object>> getUserInfoList(UserInfoSearchVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("userMng.selectUserInfoList", param);
	}

	@Override
	public UserInfoVO getUserInfo(UserInfoSearchVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("userMng.selectUserInfo", param);
	}

	@Override
	public int saveUserReq(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("userMng.saveUserReq", param);
	}
	
	@Override
	public int saveUserRoleReq(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("userMng.saveUserRoleReq", param);
	}
	
	@Override
	public int saveUserOrganReq(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("userMng.saveUserOrganReq", param);
	}
	
	@Override
	public int saveUserInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("userMng.saveUserInfo", param);
	}
	@Override
	public UserInfoVO getUserInfo(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("userMng.selectUserInfo", userId);
	}

	@Override
	public UserInfoVO getRoleInfo(String userName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalUserInfoList(UserInfoSearchVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("userMng.selectTotalUserInfoCount", param);
	}

	@Override
	public int saveUserRoleInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("userMng.saveUserRoleInfo", param);
	}

	@Override
	public int saveUserOrganInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("userMng.saveUserOrganInfo", param);
	}

	@Override
	public int deleteUserRoleInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserRoleInfo", param);
	}

	@Override
	public int deleteUserOrganInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserOrganInfo", param);
	}

	@Override
	public int updateUserInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("userMng.updateUserInfo", param);
	}

	@Override
	public int updateUserReq(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("userMng.updateUserReq", param);
	} 
	
	@Override
	public int saveReqUserInfo(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("userMng.saveReqUserInfo", param);
	}

	@Override
	public List<OrganMemberInfoVO> getUserInfoReqList(UserInfoSearchVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("userMng.selectUserInfoReqList", param);
	}

	@Override
	public int getTotalUserInfoReqList(UserInfoSearchVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("userMng.selectTotalUserInfoReqList", param);
	}

	@Override
	public OrganMemberInfoVO getUserInfoReqInfo(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("userMng.selectUserInfoReqInfo", param);
	}

	@Override
	public int saveReqUserRoleInfo(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("userMng.saveReqUserRoleInfo", param);
	}

	@Override
	public int saveReqUserOrganInfo(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("userMng.saveReqUserOrganInfo", param);
	}

	@Override
	public int deleteReqUserInfo(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserReq", param);
	}

	@Override
	public int deleteReqUserRoleInfo(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserRoleReq", param);
	}

	@Override
	public int deleteReqUserOrganInfo(UserReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserOrganReq", param);
	}

	@Override
	public int deleteUserInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserInfo", param);
	}

	@Override
	public int saveUserClientInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("userMng.saveUserClientInfo", param);
	}

	@Override
	public int deleteUserClientInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserClientInfo", param);
	}
	
	
	@Override
	public int deleteReqUserInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserReqByUserId", param);
	}

	@Override
	public int deleteReqUserRoleInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserRoleReqByUserId", param);
	}
	
	@Override
	public int deleteReqUserOrganInfo(UserInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("userMng.deleteUserOrganReqByUserId", param);
	}
}
