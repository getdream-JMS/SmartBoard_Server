package com.jms.dboard.manage.scheduler.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.OrganInfo2VO;
import com.jms.dboard.manage.vo.OrganMemberInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;
import com.jms.dboard.manage.vo.WeatherInfoVO;

@Repository(value = "schedulerManageDao")
public class SchedulerManageDaoImpl implements SchedulerManageDao{
	
	@Autowired
    @Qualifier("sqlSessionTemplateNowon")
	private SqlSessionTemplate sqlSession3;
	
	
	@Autowired
    @Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<OrganInfo2VO> getNowonDeptList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession3.selectList("nowonMng.selectNowonDept");
	}

	@Override
	public List<UserInfoVO> getNowonUserList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession3.selectList("nowonMng.selectNowonUser");
	}

	@Override
	public int deleteNowonDeptInfo() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("nowonMng.deleteNowonDept");
	}

	@Override
	public int deleteNowonUserInfo() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("nowonMng.deleteNownUserInfo");
	}

	@Override
	public int insertNowonDeptInfo(List<OrganInfo2VO> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("nowonMng.insertNowonDeptInfo",params);
	}

	@Override
	public int insertNowonUserInfo(List<UserInfoVO> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("nowonMng.insertNowonUserInfo",params);
	}
	
	@Override
	public List<Map<String, Object>> getExpiredContents(String curDate) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("contentManage.selectExpiredContentInfoList", curDate);
	}

	@Override
	public int updateClientMntrStatus() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("clientsMng.updateClientMntrStatus");
	}

	@Override
	public int deleteWeatherData() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("weatherMng.deleteWeatherData");
	}

	@Override
	public int saveWeatherData(WeatherInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("weatherMng.insertWeatherData",param);
	}

	@Override
	public WeatherInfoVO getClientWeatherInfo() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("weatherMng.selectClientWeatherInfo");
	}
}
