package com.jms.dboard.manage.clients.service;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;

import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientScheduleVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;

public interface ClientsManageService {
	/**클라이언트 조회  */
	public Map<String,Object> getClientsInfoList (ClientsInfoVO param);
	
	/**클라이언트 상세  */
	public Map<String,Object> getClientsInfo (int param);
	
	/**클라이언트  저장*/
	public JSONObject saveClientsInfo (ClientsInfoVO param);
	
	/**클라이언트 수정*/
	public JSONObject updateClientsInfo (ClientsInfoVO param);
	
	/**클라이언트 단일 삭제*/
	public Map<String,Object> deleteClientsInfo (ClientsInfoVO param);
	
	/**클라이언트 리스트 삭제*/
	public Map<String,Object> deleteClientsInfo (List<Integer> param);
	
	/**클라이언트 스테쥴 수정*/
	public Map<String,Object> updateClientSchedule (List<ClientScheduleVO> param);
	
	/**클라이언트 Holiday 수정*/
	public Map<String,Object> updateClientHoliday (ClientsInfoVO param);
	
	/** 클라이어트 초기 템플릿 URL 조회 */
	public String getInitPage(ClientBoardDataReqVO param);

	/** 조직별 클라이언트  조회 */
	public Map<String,Object> getOrganClientsInfoList(ClientsInfoVO param);
	
	/** 클라이언트 공휴일  조회 */
	public Map<String,Object> getClientHoliday(int clientId);
	/** 클라이언트 제어   */
	
	public Map<String,Object> getClientControlInfo(Map<String,Object> param);
	
	/**클라이언트 보드 수정*/
	public Map<String,Object>  updateClientBoardInfo (ClientBoardsInfoVO param);
	
	/**스마트 액자 페이지 리스트 조회*/
	public Map<String,Object>  getClientSmartFramePageList (ClientBoardsInfoVO param);
	
	/**템플릿에서 header/footer 활성화를 위한 설정 조회 목적*/
	public Map<String,Object>  getClientInfo (int clientId);
	
	/**템플릿에서 클라이언트 별 보드리스트 정보 목적*/
	public Map<String,Object>  selectClientBoardsList (int clientId);

}