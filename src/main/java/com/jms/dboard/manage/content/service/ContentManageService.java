package com.jms.dboard.manage.content.service;

import java.util.List;
import java.util.Map;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.ContentInfoVO;


public interface ContentManageService {
	
	
	/**클라이언트 보드별 데이타 조회  */
	public Map<String,Object> getContentsInfoByBoard (ClientBoardDataReqVO param);
	
	/**클라이언트 보드별 데이타 조회  */
	public Map<String,Object> getContentInfoList (ContentInfoVO param);
	
	/**홍보물 상세  */
	public Map<String,Object> getContentInfo (int param);
	
	/**홍보물 삭제*/
	public Map<String,Object> deleteContentInfo (List<ContentInfoVO> param);
	
	/**클라이언트 메인 보드 데이타 조회  */
	public Map<String,Object> getContentsInfoByMainBoard (ClientBoardDataReqVO param);
	
	/**클라이언트 공지 데이타 조회  */
	public Map<String,Object> getClientNoticeInfo (ClientBoardDataReqVO param);
	
	/**클라이언트 행사일정 데이타 조회  */
	public Map<String,Object> getClientEventData (ClientBoardDataReqVO param);
	
	/**회의실 데이타 조회  */
	public Map<String,Object> getReserveBoardData (ClientBoardDataReqVO param);

	/** 클라이언트 어플에서 다운로드 할 파일 정보 요청 */
	public Map<String,Object> getDownloadInfo (Map<String,Object> param);


}
