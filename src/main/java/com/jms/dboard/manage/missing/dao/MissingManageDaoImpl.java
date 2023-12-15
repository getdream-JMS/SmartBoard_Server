package com.jms.dboard.manage.missing.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.MissingPeopleResInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleManagerVO;
import com.jms.dboard.manage.vo.MissingPeopleReqInfoVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;

@Repository(value = "missingManageDao")
public class MissingManageDaoImpl implements MissingManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<MissingPeopleResInfoVO> getMissingClientList(MissingPeopleReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("missingBoard.selectMissingClientList", param);
	}
	
	@Override
	public List<MissingPeopleResInfoVO> getMissingManagerList(MissingPeopleManagerVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("missingBoard.selectMissingManagerList", param);
	}
	
	@Override
	public MissingPeopleInfoVO getMissingInfo(int param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("missingBoard.selectMissingBoardInfo", param);
	}
	
	@Override
	public int saveMissingInfo(MissingPeopleReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("missingBoard.saveMissingBoardInfo", param);
	}
	
	@Override
	public int updateMissingInfo(MissingPeopleReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("missingBoard.updateMissingBoardInfo", param);
	}
	
	@Override
	public int deleteMissingInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("missingBoard.deleteMissingBoardList", param);
	}
	
	@Override
	public int getTotalMissingManagerList(MissingPeopleManagerVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("missingBoard.selectTotalMissingManagerList", param);
	}

	@Override
	public int deleteMissingInfo(MissingPeopleInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("missingBoard.deleteMissingBoardInfo", param);
	}

	@Override
	public int saveMissingConnectInfo(MissingPeopleReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("missingBoard.saveMissingConnectInfo", param);
	}

	@Override
	public int deleteMissingConnectInfo(MissingPeopleReqInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("missingBoard.deleteMissingConnectInfo", param);
	}

	@Override
	public int deleteMissingConnectInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("missingBoard.deleteMissingConnectList", param);
	}
}
