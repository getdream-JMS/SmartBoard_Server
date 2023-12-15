package com.jms.dboard.manage.clients.service;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOServer;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.core.socket.SocketIOController;
import com.jms.dboard.core.socket.SocketIOServerpDemon;
import com.jms.dboard.manage.clients.dao.ClientsManageDao;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.monitoring.dao.MonitoringManageDao;
import com.jms.dboard.manage.vo.BoardTemplateInfoVO;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientScheduleVO;
import com.jms.dboard.manage.vo.ClientTemplateInfoVO;
import com.jms.dboard.manage.vo.ClientsInfoResultVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;



@Service("clientsManageService")
public class ClientsManageServiceImpl implements ClientsManageService{

	@Autowired
	ClientsManageDao clientsManageDao;
	
	@Autowired
	MonitoringManageDao monitoringManageDao;
	
	@Autowired
	CommonCodeDao commonCodeDao;
	@Override
	public Map<String,Object> getClientsInfoList(ClientsInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		
		List<ClientsInfoResultVO> list = new ArrayList<ClientsInfoResultVO>();
		try {
//			System.out.println("getClient Id :"+param.getClientId());
//			System.out.println("getLatitude :"+param.getLatitude());
//			System.out.println("getLongitude :"+param.getLongitude());
//			if(param.getClientId() > 0) {
//				clientsManageDao.updateClientsInfo(param);
//			}
				list = clientsManageDao.getClientsInfoList(param);
				totalCount = clientsManageDao.getTotalClientsInfoList(param);
				resultMap.put("totalCount", totalCount);
				resultMap.put("pageNum", param.getPageNum());
				resultMap.put("pageCount", param.getPageCount());
				resultMap.put("list",list);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public JSONObject saveClientsInfo(ClientsInfoVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 저장되었습니다.";
		int clientId = 0;
		try {
			
			/*클라이언트 정보 저장 */
//			System.out.println("param.getLayoutType() :"+param.getLayoutType());
//			System.out.println("param.getResolutionId() :"+param.getResolutionId());
//			System.out.println("param.getSmartFrame() :"+param.getSmartFrame());
			
			resultCount = clientsManageDao.saveClientsInfo(param);
			clientId = param.getClientId();
			resultCount = clientsManageDao.saveClientBoardsInfo(param);
			List<BoardTemplateInfoVO> boardTempInfoList = new ArrayList<BoardTemplateInfoVO>();
			List<ClientTemplateInfoVO> clientTempInfoList = new ArrayList<ClientTemplateInfoVO>();
			BoardTemplateInfoVO boardTempInfo = null;
			ClientTemplateInfoVO clientTempInfo = null;
			if(param.getBoards().size() > 0) {
				for(int i=0; i<param.getBoards().size();i++) {
					boardTempInfo = new BoardTemplateInfoVO();
					boardTempInfo.setClientId(clientId);
					int templateId = 11;
					boardTempInfo.setBoardIndex(param.getBoards().get(i).getBoardIndex());
					if(param.getBoards().get(i).getBoardType().equals("002")){
						templateId = 12;
					} else if(param.getBoards().get(i).getBoardType().equals("008")){ //스마트액자
						templateId = 16;
					}  
					System.out.println("boardType :"+param.getBoards().get(i).getBoardType());
					System.out.println("clientId :"+clientId);
					System.out.println("templateId :"+templateId);
					System.out.println("boardIndex :"+param.getBoards().get(i).getBoardIndex());
					
					boardTempInfo.setTemplateId(templateId);
					boardTempInfoList.add(boardTempInfo);
					String[] contentType = new String[]{"001","002","007"};
					for(int j = 0; j < contentType.length;j++) {						
						clientTempInfo = new ClientTemplateInfoVO();
						clientTempInfo.setTemplateId(12);
						clientTempInfo.setContentType(contentType[j]);
						clientTempInfo.setBoardIndex(param.getBoards().get(i).getBoardIndex());
						clientTempInfo.setTemplateType(1);
						clientTempInfo.setLayoutType(param.getLayoutType());
						clientTempInfo.setClientId(clientId);		
						clientTempInfoList.add(clientTempInfo);
					}
				}
				Map<String, Object> statusMap = new HashMap<String,Object>();
				statusMap.put("clientId", clientId);
				statusMap.put("cpu", 0);
				statusMap.put("mem", 0);
				statusMap.put("disk", 0);
				statusMap.put("status", 0);
				
				//클라이언트 상태 기본 값
				monitoringManageDao.insertClientStatus(statusMap);
				
				//클라이언트 화면 모니터링 기본 설정값 저장
				MonitoringInfoVO monitoringInfoVO = new MonitoringInfoVO();
				monitoringInfoVO.setClientId(clientId);
				monitoringInfoVO.setMonitorAction("Y");
				monitoringInfoVO.setInterval(10);
				monitoringManageDao.saveClientMonitoringConf(monitoringInfoVO);
				resultCount = clientsManageDao.saveBoardTemplateInfo(boardTempInfoList);
				clientsManageDao.saveClientTemplateInfo(clientTempInfoList);
				if(param.getSmartFrame().equals("Y")) {
					Map<String,Object> smartData = new HashMap<String,Object>();
					smartData.put("clientId", clientId);
					smartData.put("templateId", 22);
					clientsManageDao.saveSmartFrameInfo(smartData);
				}
				
			}
			
//			ClientScheduleVO weekScheduleMap = null;
//			List<ClientScheduleVO> weekScheduleList = new ArrayList<ClientScheduleVO>();
//			for(int i=1; i<8;i++) {
//				weekScheduleMap = new ClientScheduleVO();
//				weekScheduleMap.setClientId(param.getClientId());
//				weekScheduleMap.setWeekDay(i);
//				weekScheduleMap.setOrdering((i == 1)?7:i-1);
//				weekScheduleList.add(weekScheduleMap);
//			}
//			clientsManageDao.saveClientSchedule(weekScheduleList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 저장 중  오류가 발생했습니다.";
			
		}
		 InetAddress ip=null;
		  try {

			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());

		  } catch (UnknownHostException e) {

			e.printStackTrace();

		  }
		resultJson.put("requestUrl", "http://"+ip.getHostAddress()+"/cms/client/index");
		resultJson.put("clientId", clientId);
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		return resultJson;
	}

	@Override
	public JSONObject updateClientsInfo(ClientsInfoVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 수정되었습니다.";
		try {
			/**
			 * 클라이언트 보드 수정
			 * 기존 데이타 삭제 후 신규 추가
			 * */
//			System.out.println("getLatitude :"+param.getLatitude());
//			System.out.println("getLongitude :"+param.getLongitude());
			clientsManageDao.updateClientsInfo(param);
			if(param.getBoards() != null && param.getBoards().size() > 0) {
				MonitoringReqInfoVO monitoringReqInfoVO = new MonitoringReqInfoVO();
				monitoringReqInfoVO.setClientId(param.getClientId());
				monitoringManageDao.deleteClientMonitoring(monitoringReqInfoVO);
				clientsManageDao.deleteClientsBoardInfo(param);
				clientsManageDao.saveClientBoardsInfo(param);
				List<BoardTemplateInfoVO> boardTempInfoList = new ArrayList<BoardTemplateInfoVO>();
				BoardTemplateInfoVO  boardTempVO = null;
				clientsManageDao.deleteBoardTemplateInfo(param);
				for(int i = 0; i <param.getBoards().size();i++) {
					boardTempVO = new BoardTemplateInfoVO();
					boardTempVO.setClientId(param.getClientId());
					boardTempVO.setBoardIndex(param.getBoards().get(i).getBoardIndex());
					int templateId = 11;
					if(param.getBoards().get(i).getBoardType().equals("002")){
						templateId = 12;
					} else if(param.getBoards().get(i).getBoardType().equals("008")){ //스마트액자
						templateId = 16;
					}  
					boardTempVO.setTemplateId(templateId);
					boardTempInfoList.add(boardTempVO);		
					
				}
				clientsManageDao.saveBoardTemplateInfo(boardTempInfoList);
			}

			Map<String,Object> tempCountData = clientsManageDao.selectClientTemplateCount(param);
			//System.out.println("tempCountData : " + tempCountData.toString());			
			List<ClientTemplateInfoVO> clientTempInfoList = new ArrayList<ClientTemplateInfoVO>();
			ClientTemplateInfoVO clientTempInfo = null;
			
			
			if((Long)tempCountData.get("templateCnt") == 0) {

				for(int j = 0; j < (Long)tempCountData.get("boardCnt") ;j++) {		
					
					String[] contentType = new String[]{"001","002","007"};
					for(int k = 0; k < contentType.length;k++) {						
						clientTempInfo = new ClientTemplateInfoVO();
						int templateId = 11;
						if(param.getBoards().get(j).getBoardType().equals("002")){
							templateId = 12;
						} else if(param.getBoards().get(j).getBoardType().equals("008")){ //스마트액자
							templateId = 16;
						}
						clientTempInfo.setTemplateId(templateId);
						clientTempInfo.setContentType(contentType[k]);
						clientTempInfo.setBoardIndex(param.getBoards().get(j).getBoardIndex());
						clientTempInfo.setTemplateType(1);
						clientTempInfo.setLayoutType(param.getLayoutType());
						clientTempInfo.setClientId(param.getClientId());		
						clientTempInfoList.add(clientTempInfo);
					}		
				}
				clientsManageDao.saveClientTemplateInfo(clientTempInfoList);
			}
			
			if(param.getSmartFrame() != null) {
				clientsManageDao.deleteSmartFrameInfo(param);
				if(param.getSmartFrame().equals("Y")) {
					Map<String,Object> smartData = new HashMap<String,Object>();
					smartData.put("clientId", param.getClientId());
					smartData.put("templateId", 22);
					clientsManageDao.saveSmartFrameInfo(smartData);
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 수정 중  오류가 발생했습니다.";
		}
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		return resultJson;
	}
	/**
	 * 클라이언트 단일 삭제
	 * */
	@Override
	public Map<String,Object> deleteClientsInfo(ClientsInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 삭제 되었습니다.";
		try {
			int resultCount = clientsManageDao.deleteClientsInfo(param);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 삭제 중  오류가 발생했습니다.";
		}
		
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		
		return resultMap;
	}
	/**
	 * 클라이언트 리스트 삭제
	 * */
	@Override
	public Map<String,Object> deleteClientsInfo(List<Integer> params) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 삭제 되었습니다.";
		try {
			monitoringManageDao.deleteClientMonitoring(params);			
			monitoringManageDao.deleteClientsMonitoringConf(params);
			clientsManageDao.deleteClientsMonitoringInfo(params);
			clientsManageDao.deleteClientsBoardInfo(params);
			clientsManageDao.deleteClientSchedule(params);
			clientsManageDao.deleteClientsTemplate(params);
			int resultCount = clientsManageDao.deleteClientsInfo(params);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 삭제 중  오류가 발생했습니다.";
		}
		
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getClientsInfo(int clientId) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		ClientsInfoResultVO resultData = new ClientsInfoResultVO();
		try {
			ClientsInfoVO param = new ClientsInfoVO();
			param.setClientId(clientId);
			resultData = clientsManageDao.getClientsInfo(param);
			resultMap.put("data",resultData);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> updateClientSchedule(List<ClientScheduleVO> param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 스케쥴이 수정되었습니다.";
		try {
			clientsManageDao.updateClientSchedule(param);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 스케쥴 수정 중  오류가 발생했습니다.";
		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> updateClientHoliday(ClientsInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 스케쥴이 수정되었습니다.";
		List<String> duplicatedDate = new ArrayList<String>();
		try {
			clientsManageDao.updateClientsOperationStatus(param);
			clientsManageDao.deleteExceptSchedule();
			clientsManageDao.insertExceptSchedule(param.getCustomHolidays());
			
		} catch(DuplicateKeyException e) {
			resultCode = 500;
			resultMsg = "이미 지정된 날짜가 있습니다.";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 스케쥴 수정 중  오류가 발생했습니다.";
		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}
	/** 클라이어트 초기 템플릿 URL 조회 */
	@Override
	public String getInitPage(ClientBoardDataReqVO param) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> templatePageList = null;
		String templatePage = "";
		try {
//			System.out.println(param.getClientId());
//			System.out.println("getOrganId :"+param.getOrganId());
			ClientsInfoVO clientInfo = new ClientsInfoVO();
			clientInfo.setClientId(param.getClientId());
			System.out.println("getInitPage client id : "+param.getClientId());
			Map<String,Object> resultClientData = clientsManageDao.getClientInfo(clientInfo);
			
			System.out.println("param.getSmartFrame() "+param.getSmartFrame());
			if(param.getSmartFrame() == null && resultClientData.get("smartFrame").equals("Y")) {
				templatePageList = clientsManageDao.getInitSmartFramePage(param);
			} else {
				templatePageList = clientsManageDao.getInitPage(param);
			}
//			System.out.println(templatePageList.size());
			if(templatePageList.size() > 0) {
//				int randomIdx = CommonUtils.getRandomNumberInRange(0, templatePageList.size()-1);
				int randomIdx = (param.getBoardIndex()== 2)?0:CommonUtils.getRandomNumberInRange(0, templatePageList.size()-1);
//				System.out.println("randomIdx :"+randomIdx);
				templatePage = (String) templatePageList.get(randomIdx).get("templateUrl");
//				System.out.println("templatePage :"+templatePage);
				templatePage+=((templatePage.indexOf("?")>-1)?"&":"?")+"clientId="+param.getClientId()+"&boardIndex="+param.getBoardIndex()+"&urgentYn="+param.getUrgentYn()+"&alarmYn="+templatePageList.get(randomIdx).get("alarmYn");
			}
			
//			System.out.println("templatePage :"+templatePage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return templatePage;
	}
	@Override
	public Map<String,Object> getOrganClientsInfoList(ClientsInfoVO param) {
		// TODO Auto-generated method stub
				Map<String,Object> resultMap = new HashMap<String,Object>();
				int resultCode = 200;
				String resultMsg = "";
				int totalCount = 0;
				List<ClientsInfoResultVO> list = new ArrayList<ClientsInfoResultVO>();
				try {
					list = clientsManageDao.getOrganClientsInfoList(param);
					resultMap.put("totalCount", totalCount);
					resultMap.put("pageNum", param.getPageNum());
					resultMap.put("pageCount", param.getPageCount());
					resultMap.put("list",list);
					
		     
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultCode = 500;
					resultMsg = "조직별 클라이언트 조회 중 오류가 발생했습니다.";
				}
				resultMap.put("result",resultCode);
				resultMap.put("resultMsg",resultMsg);
				return resultMap;
	}

	@Override
	public Map<String, Object> getClientHoliday(int clientId) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		try {
			ClientsInfoVO param = new ClientsInfoVO();
			param.setClientId(clientId);
			ClientsInfoResultVO holidayData = clientsManageDao.getClientHoliday(param);
			resultMap.put("data",holidayData);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "정상적으로 처리되었습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> getClientControlInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		try {
			boolean result = false;
			if(param.get("action").equals("RELOAD")) {
				result = SocketIOController.sendMessageToBoard(param);
			} else {
				result = SocketIOController.sendMessageToClient(param);
			}

		}catch(Exception e) {
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 제어 작업 중  오류가 발생 했습니다.";
		}
//		.socketIOserver.getBroadcastOperations().sendEvent("clientControl", controlData);
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> updateClientBoardInfo(ClientBoardsInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 클라이언트 상태가 수정되었습니다.";
		try {
			clientsManageDao.updateClientBoardInfo(param);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 상태 수정 중  오류가 발생했습니다.";
		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
		
	}

	@Override
	public Map<String, Object> getClientSmartFramePageList(ClientBoardsInfoVO param) {
		// TODO Auto-generated method stub
				Map<String,Object> resultMap = new HashMap<String,Object>();
				int resultCode = 200;
				String resultMsg = "";
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				try {
//					System.out.println("getClient Id :"+param.getClientId());
//					System.out.println("getLatitude :"+param.getLatitude());
//					System.out.println("getLongitude :"+param.getLongitude());
//					if(param.getClientId() > 0) {
//						clientsManageDao.updateClientsInfo(param);
//					}
						list = clientsManageDao.getClientSmartFramePageList(param);
						resultMap.put("list",list);
					
		     
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultCode = 500;
					resultMsg = "스마트액자 페이지리스트 조회 중 오류가 발생했습니다.";
				}
				resultMap.put("result",resultCode);
				resultMap.put("resultMsg",resultMsg);
				return resultMap;
	}

	@Override
	public Map<String, Object> getClientInfo(int param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		Map<String,Object> resultData= new HashMap<String,Object>();

		try {
			ClientsInfoVO clientInfoVO = new ClientsInfoVO();
			clientInfoVO.setClientId(param);
			resultData = clientsManageDao.getClientInfo(clientInfoVO);
			resultMap.put("data",resultData);			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트("+param+") 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectClientBoardsList(int clientId) {
		// TODO Auto-generated method stub
				Map<String,Object> resultMap = new HashMap<String,Object>();
				int resultCode = 200;
				String resultMsg = "";
				List<BoardTemplateInfoVO> list = new ArrayList<BoardTemplateInfoVO>();
				try {
//					System.out.println("getClient Id :"+param.getClientId());
//					System.out.println("getLatitude :"+param.getLatitude());
//					System.out.println("getLongitude :"+param.getLongitude());
//					if(param.getClientId() > 0) {
//						clientsManageDao.updateClientsInfo(param);
//					}
					ClientsInfoVO param = new ClientsInfoVO();
					param.setClientId(clientId);
						list = clientsManageDao.selectClientBoardsList(param);
						resultMap.put("list",list);
					
		     
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultCode = 500;
					resultMsg = "클라이언트 보드 리스트 조회 중 오류가 발생했습니다.";
				}
				resultMap.put("result",resultCode);
				resultMap.put("resultMsg",resultMsg);
				return resultMap;
	}
}