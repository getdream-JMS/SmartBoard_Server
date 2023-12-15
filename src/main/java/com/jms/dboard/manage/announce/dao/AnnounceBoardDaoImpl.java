package com.jms.dboard.manage.announce.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.AnnounceBoardDetailsVO;
import com.jms.dboard.manage.vo.AnnounceBoardResultVO;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "announceBoardDao")
public class AnnounceBoardDaoImpl implements AnnounceBoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<AnnounceBoardResultVO> getAnnounceBoardList(AnnounceBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("announceBoard.selectAnnounceBoardList", param);
	}
	
	@Override
	public AnnounceBoardResultVO getAnnounceBoardInfo(AnnounceBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("announceBoard.selectAnnounceBoardInfo", param);
	}
	
	@Override
	public int saveAnnounceBoardInfo(AnnounceBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("announceBoard.saveAnnounceBoardInfo", param);
	}
	
	@Override
	public int saveAnnounceBoardImage(List<AnnounceBoardDetailsVO> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("announceBoard.saveAnnounceBoardImage", param);
	}
	
	@Override
	public int updateAnnounceBoardInfo(AnnounceBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("announceBoard.updateAnnounceBoardInfo", param);
	}
	
	@Override
	public int deleteAnnounceBoardInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("announceBoard.deleteAnnounceBoardList", param);
	}
	
	@Override
	public int deleteAnnounceBoardImage(AnnounceBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("announceBoard.deleteAnnounceBoardImage", param);
	}
	
	@Override
	public int deleteAnnounceBoardImage(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("announceBoard.deleteAnnounceBoardImageList", param);
	}
	
	@Override
	public int getTotalAnnounceBoardList(AnnounceBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("announceBoard.selectTotalAnnounceBoardList", param);
	}

	@Override
	public int deleteAnnounceBoardInfo(AnnounceBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("announceBoard.deleteAnnounceBoardInfo", param);
	}
	@Override
	public int getNextSeqAnnounce () throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("announceBoard.selectNextSeqAnnounce");
	}
	@Override
	public int deleteTtsInfo(AnnounceBoardVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("announceBoard.deleteTtsInfo", param);
	}
	
	@Override
	public int deleteTtsInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("announceBoard.deleteTtsInfoList", param);
	}
	@Override
	public int saveTtsInfo(List<AnnounceBoardDetailsVO> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("announceBoard.saveTtsInfo", param);
	}

	
}
