package com.jms.dboard.manage.content.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.*;

public interface ContentManageDao {
	/** 클라이언트 별 컨텐츠 삭제*/
	public int deleteContClients (Map<String,Object> param) throws Exception;
	
	/** 클라이언트 별 컨텐츠 저장*/
	public int saveContClients (Map<String,Object> params)  throws Exception;
	
	/** 클라이언트  공고문 보드 정보 조회 */
	public List<Map<String,Object>> getClientAnnounceBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	/** 클라이언트  홍보물 보드 정보 조회 */
	public Map<String,Object> getClientPromoBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	
	/** 클라이언트  홍보물 보드 정보 조회 */
	public List<Map<String,Object>>  getClientPromotionBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	/** 컨텐츠 리스트  조회 */
	public List<Map<String,Object>>  getContentInfoList (ContentInfoVO params)  throws Exception;
	
	
	/** 컨텐츠   전체 데이타 갯수 조회 */
	public int getTotalContentsList (ContentInfoVO params)  throws Exception;
	/** 클라이언트 공고문   전체 데이타 갯수 조회 */
	public int getTotalClientAnnounceBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	/** 클라이언트 홍보물   전체 데이타 갯수 조회 */
	public int getTotalClientPromoBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	/** 긴급 컨텐츠   전체 데이타 갯수 조회 */
	public int getCountUrgentContents (ClientBoardDataReqVO params)  throws Exception;
	
	/** 이미지 컨텐츠를 보여줄 홍보물 게시판 정보 조회 */
	public Map<String,Object> getImageBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	/** 클라이언트 별 컨텐츠 저장*/
	public int saveContClientsPromo (Map<String,Object> params)  throws Exception;
	
	/** 클라이언트  행사일정 보드 데이타 조회 */
	public List<Map<String,Object>>  getClientEventBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	/** 클라이언트  소식지 보드 정보 조회 */
	public List<Map<String,Object>> getClientNewsletterBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	/** 클라이언트  주민소식 보드 정보 조회 */
	public Map<String,Object>  getClientJuminBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	/** 클라이언트 주민소식   전체 데이타 갯수 조회 */
	public int getTotalClientJuminBoardInfo (ClientBoardDataReqVO params)  throws Exception;
	
	
	/** 클라이언트  공지사항 정보 조회 */
	public List<Map<String,Object>>  getClientNoticeInfo (ClientBoardDataReqVO params)  throws Exception;
	
	/** 클라이이언 > 회의실 예약 정보 조회 */
	public ReserveRoomInfoVO  getReserveBoardData (ClientBoardDataReqVO params)  throws Exception;

	public DownloadBoardResultVO getDownloadBoardList (ClientBoardDataReqVO params)  throws Exception;

}
