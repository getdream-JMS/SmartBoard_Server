package com.jms.dboard.manage.clients.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.BoardTemplateInfoVO;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientScheduleVO;
import com.jms.dboard.manage.vo.ClientTemplateInfoVO;
import com.jms.dboard.manage.vo.ClientsInfoResultVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.CustomHolidayOffVO;


public interface ClientsManageDao {
	/**클라이언트 리스트 조회  */
	public List<ClientsInfoResultVO> getClientsInfoList (ClientsInfoVO param) throws Exception;
	
	/**클라이언트 상세정보, 보드 리스트  조회,   */
	public ClientsInfoResultVO getClientsInfo (ClientsInfoVO clientId) throws Exception;
	
	/**클라이언트 상세정보  조회  */
	public Map<String,Object> getClientInfo (ClientsInfoVO param) throws Exception;

	/**클라이언트 전체 데이타 수  */
	public int getTotalClientsInfoList (ClientsInfoVO param) throws Exception;
	
	/**클라이언트  저장*/
	public int saveClientsInfo (ClientsInfoVO param) throws Exception;
	
	/**클라이언트 Board  저장*/
	public int saveClientBoardsInfo (ClientsInfoVO param) throws Exception;
	
	/**클라이언트 Board  삭제 - Client ID로 삭제*/
	public int deleteClientsBoardInfo (ClientsInfoVO param) throws Exception;	
	
	/**클라이언트 Board  삭제 - List 로 삭제*/
	public int deleteClientsBoardInfo (List<Integer> param) throws Exception;	
	
	/**클라이언트 수정*/
	public int updateClientsInfo (ClientsInfoVO param) throws Exception;
	
	/**클라이언트 리스트 삭제*/
	public int deleteClientsInfo (List<Integer> param) throws Exception;
	
	/**클라이언트 삭제*/
	public int deleteClientsInfo (ClientsInfoVO param) throws Exception;
	
	public int getNextSeqClients () throws Exception;
	
	/**클라이언트 주간 작동 스케쥴 저장 */
	public int saveClientSchedule (List<ClientScheduleVO> params) throws Exception;
	
	/**클라이언트 주간 작동 스케쥴 저장 */
	public int updateClientSchedule (List<ClientScheduleVO> params) throws Exception;
	
	/**클라이언트 주간 작동 스케쥴 삭제 */
	public int deleteClientSchedule (List<Integer> params) throws Exception;
	
	/**클라이어트 초기 템플릿 URL 조회 */
	public List<Map<String,Object>> getInitPage (ClientBoardDataReqVO param) throws Exception;
	
	/**클라이어트 스마트액자 초기 템플릿 URL 조회 */
	public List<Map<String,Object>> getInitSmartFramePage (ClientBoardDataReqVO param) throws Exception;
	
	
	/**조직별 클라이언트 리스트 조회  */
	public List<ClientsInfoResultVO> getOrganClientsInfoList (ClientsInfoVO param) throws Exception;
	
	/** 클라이언트별 보드 타입 조회  */
	public ClientBoardsInfoVO getClientBoardInfo (ClientBoardDataReqVO param) throws Exception;
	
	
	public ClientsInfoResultVO getClientHoliday (ClientsInfoVO param) throws Exception;
	
	/**클라이언트 휴일 작동 설정 수정*/
	public int updateClientsOperationStatus (ClientsInfoVO params) throws Exception;
	
	/**클라이언트 사용자 설정 휴일 삭제*/
	public int deleteExceptSchedule () throws Exception;
	
	/**클라이언트 사용자 설정 휴일 추가*/
	public int insertExceptSchedule (List<CustomHolidayOffVO> params) throws Exception;
	
	/**휴일 체크 */
	public Map<String,Object> getIsOffDay () throws Exception;
	
	/**클라이언트 주간 작동 스케쥴 삭제 */
	public int deleteClientsMonitoringInfo (List<Integer> params) throws Exception;
	
	/**클라이언트 보드 상태 저장 */
	public int updateClientBoardInfo (ClientBoardsInfoVO param) throws Exception;
	
	/**클라이언트 상세정보  저장  */
	public int  saveBoardTemplateInfo (List<BoardTemplateInfoVO> param) throws Exception;
	
	/**클라이언트 템플릿 저장*/
	public int saveClientTemplateInfo (List<ClientTemplateInfoVO> param) throws Exception;		
	
	/**클라이언트 템플릿 카운터 조회*/
	public Map<String,Object> selectClientTemplateCount (ClientsInfoVO param);
	
	public int updateBoardTemplateInfo (List<BoardTemplateInfoVO> param) throws Exception; 
	
	public int updateBoardTemplateInfo (BoardTemplateInfoVO param) throws Exception; 
	
	public int deleteBoardTemplateInfo (ClientsInfoVO param) throws Exception;  
	
	/**클라이언트 템플릿 삭제 */
	public int deleteClientsTemplate (List<Integer> params) throws Exception;
	
	/**클라이언트 템플릿 컨텐츠 삭제 */
	public int deleteTemplateContents (List<Integer> params) throws Exception;	
	
	/**스마트 액자 페이지 리스트 조회  */
	public List<Map<String,Object>> getClientSmartFramePageList (ClientBoardsInfoVO param) throws Exception;
	
	/**스마트 액자 페이지 리스트 조회  */
	public List<BoardTemplateInfoVO> selectClientBoardsList (ClientsInfoVO param) throws Exception;
	
	/**클라이언트 스마트액자 저장*/
	public int saveSmartFrameInfo (Map<String,Object> param) throws Exception;
	
	/**클라이언트 스마트액자 삭제*/
	public int deleteSmartFrameInfo (ClientsInfoVO param) throws Exception;
	
}
