package com.jms.dboard.manage.tonghap.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.common.vo.BaseSearchVO;
import com.jms.dboard.manage.vo.ClientLocationReqInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.TonghapEventClientVO;
import com.jms.dboard.manage.vo.TonghapEventReqInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "tonghapIntegrateDao")
public class TonghapInterfaceDaoImpl implements TonghapIntegrateDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Map<String, Object>> getClientLocationList(ClientLocationReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("tonghapMng.selectClientLocationList", param);
	}
	

	@Override
	public int getClientLocationCount(ClientLocationReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("tonghapMng.selectClientLocationCount", param);
	}
	
	@Override
	public int saveEmegencyInfo(TonghapEventReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("tonghapMng.insertEmegencyInfo", param);
	}

	@Override
	public List<Map<String, Object>> getEmergencyInfoList(ClientLocationReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("tonghapMng.selectEmergencyInfoList", param);
	}

	@Override
	public Map<String, Object> getClientStatus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("tonghapMng.selectClientStatus", param);
	}


	@Override
	public List<Map<String, Object>> getTonghapEventInfoClient(int param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("tonghapMng.selectTonghapEventInfoClient",param);
	}


	@Override
	public List<Map<String, Object>> getTonghapEventInfoCMS(BaseSearchVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("tonghapMng.selectTonghapEventInfoCMS", param);
	}

	@Override
	public int getTotalCountTonghapEvent(BaseSearchVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("tonghapMng.selectTotalCountTonghapEvent", param);
	}
	

}
