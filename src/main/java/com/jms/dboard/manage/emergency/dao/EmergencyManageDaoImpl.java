package com.jms.dboard.manage.emergency.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;

@Repository(value = "emergencyManageDao")
public class EmergencyManageDaoImpl implements EmergencyManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<PromoBoardResultVO> getEmergencyList(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("emergencyBoard.selectEmergencyList", param);
	}
	
	@Override
	public PromoBoardResultVO getEmergencyInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("emergencyBoard.selectEmergencyBoardInfo", param);
	}
	
	@Override
	public int saveEmergencyInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("emergencyBoard.saveEmergencyBoardInfo", param);
	}
	
	@Override
	public int updateEmergencyInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("emergencyBoard.updateEmergencyBoardInfo", param);
	}
	
	@Override
	public int deleteEmergencyInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("emergencyBoard.deleteEmergencyBoardList", param);
	}
	
	@Override
	public int getTotalEmergencyList(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("emergencyBoard.selectTotalEmergencyBoardList", param);
	}

	@Override
	public int deleteEmergencyInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("emergencyBoard.deleteEmergencyBoardInfo", param);
	}
	
	@Override
	public int getNextSeqPromo() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("emergencyBoard.selectNextSeqPromo");
	}
}
