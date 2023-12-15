package com.jms.dboard.manage.help.dao;

import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jms.dboard.manage.vo.HelpInfoVO;

@Repository(value = "helpManageDao")
public class HelpManageDaoImpl implements HelpManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public Map<String,Object> getHelpInfo(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("helInfoMng.selectHelpInfo", param);
	}
	
}
