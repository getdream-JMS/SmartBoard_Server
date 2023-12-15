package com.jms.dboard.manage.version.dao;

import com.jms.dboard.manage.code.dao.CodeManageDao;
import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;
import com.jms.dboard.manage.vo.MonitoringResInfoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "versionManageDao")
public class VersionManageDaoImpl implements VersionManageDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
public int updateVersionInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("versionMng.updateVersionInfo", param);
		}

@Override
public Map<String, Object> getVersionInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("versionMng.selectVersionInfo", param);
		}

@Override
public Map<String, Object> getClientVersionInfo(Map<String, Object> param) throws Exception {
	// TODO Auto-generated method stub
	return sqlSession.selectOne("versionMng.selectClientVersionInfo", param);
	}
	
}
