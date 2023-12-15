package com.jms.dboard.manage.eventschedule.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;


@Repository(value = "eventScheduleBoardDao")
public class EventScheduleBoardDaoImpl implements EventScheduleBoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<PromoBoardResultVO> getPromoBoardList(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("promoBoard.selectPromoBoardList", param);
	}
	
	@Override
	public PromoBoardResultVO getPromoBoardInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("promoBoard.selectPromoBoardInfo", param);
	}
	
	@Override
	public int savePromoBoardInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("promoBoard.savePromoBoardInfo", param);
	}
	
	@Override
	public int updatePromoBoardInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("promoBoard.updatePromoBoardInfo", param);
	}
	
	@Override
	public int deletePromoBoardInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("promoBoard.deletePromoBoardList", param);
	}
	
	@Override
	public int getTotalPromoBoardList(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("promoBoard.selectTotalPromoBoardList", param);
	}

	@Override
	public int deletePromoBoardInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("promoBoard.deletePromoBoardInfo", param);
	}
	
	@Override
	public int getNextSeqPromo() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("promoBoard.selectNextSeqPromo");
	}

	@Override
	public List<Integer> getClientsByContent(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("promoBoard.selectClientsByContent",param);
	}

	@Override
	public Map<String, String> getVideoConfig() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("promoBoard.selectVideoConfig");
	}
}
