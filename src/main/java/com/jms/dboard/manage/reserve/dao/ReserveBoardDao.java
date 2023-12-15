package com.jms.dboard.manage.reserve.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ReserveRoomInfoVO;

public interface ReserveBoardDao {
	

	/**회의실 리스트 조회  */
	public List<Map<String,Object>> getReserveRoomList () throws Exception;
	
	
	public List<ReserveRoomInfoVO> getReserveBoardList (ReserveRoomInfoVO param) throws Exception;
	
	/**회의실 예약 정보 상세 조회  */
	public ReserveRoomInfoVO getReserveBoardInfo (int param) throws Exception;

	/**회의실 예약 정보 전체 데이타 수  */
	public int getTotalReserveBoardList (ReserveRoomInfoVO param) throws Exception;
	
	/**회의실 예약 정보  저장*/
	public int saveReserveBoardInfo (ReserveRoomInfoVO param) throws Exception;	
	
	/**회의실 예약 정보 수정*/
	public int updateReserveBoardInfo (ReserveRoomInfoVO param) throws Exception;
	
	/**회의실 예약 정보 리스트 삭제*/
	public int deleteReserveBoardInfo (List<Integer> param) throws Exception;
	
	/**회의실 예약 정보 삭제*/
	public int deleteReserveBoardInfo (ReserveRoomInfoVO param) throws Exception;	

	public List<Integer> getClientsByContent (Map<String,Object> param) throws Exception;
	
	/**삭제 회의실 리스트 조회  */
	public List<ReserveRoomInfoVO> getReserveBoardListById (List<Integer> param) throws Exception;
	
	/**회의실 예약 중복 체크*/
	public int checkDupReserveBoardInfo (ReserveRoomInfoVO param) throws Exception;	
	
	
	
}
