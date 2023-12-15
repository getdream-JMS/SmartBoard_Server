package com.jms.dboard.manage.reserve.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.manage.vo.ReserveRoomInfoVO;

public interface ReserveBoardService {
	
	
	/**회의실 리스트 조회  */
	public Map<String,Object> getReserveRoomList ();
	
	/**회의실 예약 정보 조회  */
	public Map<String,Object> getReserveBoardList (ReserveRoomInfoVO param);
	
	/**회의실 예약 정보 상세  */
	public Map<String,Object> getReserveBoardInfo (int reserveId);
	
	/**회의실 예약 정보  저장*/
	public JSONObject saveReserveBoardInfo (ReserveRoomInfoVO param,MultipartFile file);
	
	/**회의실 예약 정보 수정*/
	public JSONObject updateReserveBoardInfo (ReserveRoomInfoVO param,MultipartFile file);
	
	/**회의실 예약 정보 삭제*/
	public Map<String,Object> deleteReserveBoardInfo (List<Integer> param);
	
	/**회의실 예약 정보 이미지 정보 삭제*/
	public int deleteReserveBoardInfo (ReserveRoomInfoVO param);
	
	
}
