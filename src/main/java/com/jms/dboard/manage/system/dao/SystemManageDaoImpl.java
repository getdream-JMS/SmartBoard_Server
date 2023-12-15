package com.jms.dboard.manage.system.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.ContentInfoVO;
import com.jms.dboard.manage.vo.OrganMemberInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

@Repository(value = "systemManageDao")
public class SystemManageDaoImpl implements SystemManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Map<String, Object>> getStatisticContents(ContentInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("systemMng.selectStatisticContents", param);
	}

	@Override
	public int getTotalStatisticContents(ContentInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("systemMng.selectTotalStatisticContents", param);
	}

}
