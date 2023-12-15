package com.jms.dboard.manage.newsletter.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.NewsBoardDetailsVO;
import com.jms.dboard.manage.vo.NewsBoardResultVO;
import com.jms.dboard.manage.vo.NewsBoardVO;


@Repository(value = "newsBoardDao")
public class NewsBoardDaoImpl implements NewsBoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<NewsBoardResultVO> getNewsBoardList(NewsBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("newsBoard.selectNewsBoardList", param);
	}
	
	@Override
	public NewsBoardResultVO getNewsBoardInfo(NewsBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("newsBoard.selectNewsBoardInfo", param);
	}
	
	@Override
	public int saveNewsBoardInfo(NewsBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("newsBoard.saveNewsBoardInfo", param);
	}
	
	@Override
	public int saveNewsBoardImage(List<NewsBoardDetailsVO> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("newsBoard.saveNewsBoardImage", param);
	}
	
	@Override
	public int updateNewsBoardInfo(NewsBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("newsBoard.updateNewsBoardInfo", param);
	}
	
	@Override
	public int deleteNewsBoardInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("newsBoard.deleteNewsBoardList", param);
	}
	
	@Override
	public int deleteNewsBoardImage(NewsBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("newsBoard.deleteNewsBoardImage", param);
	}
	
	@Override
	public int deleteNewsBoardImage(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("newsBoard.deleteNewsBoardImageList", param);
	}
	
	@Override
	public int getTotalNewsBoardList(NewsBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("newsBoard.selectTotalNewsBoardList", param);
	}

	@Override
	public int deleteNewsBoardInfo(NewsBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("newsBoard.deleteNewsBoardInfo", param);
	}
	@Override
	public int getNextSeqAnnounce () throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("newsBoard.selectNextSeqNews");
	}
	@Override
	public int deleteTtsInfo(NewsBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("newsBoard.deleteTtsInfo", param);
	}
	
	@Override
	public int deleteTtsInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("newsBoard.deleteTtsInfoList", param);
	}
	@Override
	public int saveTtsInfo(List<NewsBoardDetailsVO> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("newsBoard.saveTtsInfo", param);
	}

	
}
