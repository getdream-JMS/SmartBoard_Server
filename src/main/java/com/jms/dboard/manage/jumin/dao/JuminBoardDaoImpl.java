package com.jms.dboard.manage.jumin.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "juminBoardDao")
public class JuminBoardDaoImpl implements JuminBoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<PromoBoardResultVO> getJuminBoardList(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("juminBoard.selectJuminBoardList", param);
	}
	
	@Override
	public PromoBoardResultVO getJuminBoardInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("juminBoard.selectJuminBoardInfo", param);
	}
	
	@Override
	public int saveJuminBoardInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("juminBoard.saveJuminBoardInfo", param);
	}
	
	@Override
	public int updateJuminBoardInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("juminBoard.updateJuminBoardInfo", param);
	}
	
	@Override
	public int deleteJuminBoardInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("juminBoard.deleteJuminBoardList", param);
	}
	
	@Override
	public int getTotalJuminBoardList(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("juminBoard.selectTotalJuminBoardList", param);
	}

	@Override
	public int deleteJuminBoardInfo(PromoBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("juminBoard.deleteJuminBoardInfo", param);
	}
	
	@Override
	public int getNextSeqJumin() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("juminBoard.selectNextSeqJumin");
	}

	@Override
	public List<Integer> getClientsByContent(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("juminBoard.selectClientsByContent",param);
	}

	@Override
	public Map<String, String> getVideoConfig() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("juminBoard.selectVideoConfig");
	}
}
