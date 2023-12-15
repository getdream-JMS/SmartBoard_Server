package com.jms.dboard.common.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.vo.AnnounceBoardDetailsVO;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "commonCodeDao")
public class CommonCodeDaoImpl implements CommonCodeDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public CommonCodeVO getCommonCodeInfoByName(CommonCodeVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("commonCode.selectCommonCodeInfoByName", param);
	}

	@Override
	public CommonCodeVO getCommonCodeInfoByCode(CommonCodeVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("commonCode.selectCommonCodeInfoByCode", param);
	}

	@Override
	public List<CommonCodeVO> getCommonCodeInfoListByCode(CommonCodeVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("commonCode.selectCommonCodeInfoListByCode", param);
	}

}
