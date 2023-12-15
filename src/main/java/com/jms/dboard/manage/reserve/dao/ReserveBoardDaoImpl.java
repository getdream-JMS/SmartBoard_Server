package com.jms.dboard.manage.reserve.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.ReserveRoomInfoVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Repository(value = "reserveBoardDao")
public class ReserveBoardDaoImpl implements ReserveBoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ReserveRoomInfoVO> getReserveBoardList(ReserveRoomInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("reserveBoard.selectReserveBoardList", param);
	}
	
	@Override
	public ReserveRoomInfoVO getReserveBoardInfo(int param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("reserveBoard.selectReserveBoardInfo", param);
	}
	
	@Override
	public int saveReserveBoardInfo(ReserveRoomInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("reserveBoard.saveReserveBoardInfo", param);
	}
	
	@Override
	public int updateReserveBoardInfo(ReserveRoomInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("reserveBoard.updateReserveBoardInfo", param);
	}
	
	@Override
	public int deleteReserveBoardInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("reserveBoard.deleteReserveBoardList", param);
	}
	
	@Override
	public int getTotalReserveBoardList(ReserveRoomInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("reserveBoard.selectTotalReserveBoardList", param);
	}

	@Override
	public int deleteReserveBoardInfo(ReserveRoomInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("reserveBoard.deleteReserveBoardInfo", param);
	}

	@Override
	public List<Integer> getClientsByContent(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("reserveBoard.selectClientsByContent",param);
	}

	@Override
	public List<Map<String, Object>> getReserveRoomList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("reserveBoard.selectReserveRoomList");
	}
	
	@Override
	public List<ReserveRoomInfoVO> getReserveBoardListById(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("reserveBoard.selectReserveBoardListById", param);
	}
	
	@Override
	public int checkDupReserveBoardInfo(ReserveRoomInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("reserveBoard.checkDupReserveBoardInfo", param);
	}
}
