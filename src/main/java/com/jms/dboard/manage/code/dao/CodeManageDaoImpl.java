package com.jms.dboard.manage.code.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientScheduleVO;
import com.jms.dboard.manage.vo.ClientsInfoResultVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.common.vo.CommonCodeVO;

@Repository(value = "codeManageDao")
public class CodeManageDaoImpl implements CodeManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Map<String,Object>> getResolutionList(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("configCodeMng.selectResolutionList", param);
	}

	@Override
	public Map<String, Object> getDboardConfig(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("configCodeMng.selectDboardConfig", param);
	}
	
	@Override
	public Map<String, Object> getTouchTimeoutConfig(CommonCodeVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("configCodeMng.selectTouchTimeoutConfig", param);
	}
}
