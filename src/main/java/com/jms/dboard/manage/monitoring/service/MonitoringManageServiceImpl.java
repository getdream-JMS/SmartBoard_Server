package com.jms.dboard.manage.monitoring.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.monitoring.dao.MonitoringManageDao;
import com.jms.dboard.manage.organization.dao.OrganizationManageDao;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientScheduleVO;
import com.jms.dboard.manage.vo.ClientsInfoResultVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;
import com.jms.dboard.manage.vo.MonitoringResInfoVO;
import com.jms.dboard.manage.vo.OrganInfoResultVO;
import com.jms.dboard.manage.vo.OrganInfoVO;
import com.jms.dboard.manage.vo.TtsInfoVO;



@Service("monitoringManageService")
public class MonitoringManageServiceImpl implements MonitoringManageService{

	@Autowired
	MonitoringManageDao monitoringManageDao;
	
	
	@Autowired
	CommonCodeDao commonCodeDao;
	@Override
	public Map<String, Object> saveClientStatus(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 클라이언트 리소스 정보가 갱신되었습니다.";
		
		try {
			
			param.put("mem", (param.get("mem") != null)?param.get("mem"):0);
			param.put("disk", (param.get("disk") != null)?param.get("disk"):0);
//			System.out.println("saveClientStatus "+param.get("cpu"));
//			System.out.println(param.get("mem"));
//			System.out.println(param.get("disk"));
//			System.out.println("clientId :"+param.get("clientId"));
//			System.out.println(param.get("status"));
//			
			int resultCount = monitoringManageDao.updateClientStatus(param);
			System.out.println(resultCount);
			if(resultCount == 0) {
				monitoringManageDao.insertClientStatus(param);
			}
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 리소스 상태 저장중  오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	@Override
	public void checkClientStatus() {
		// TODO Auto-generated method stub
		try {
			monitoringManageDao.checkClientStatus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> saveClientMonitoring(MonitoringReqInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 모니터링 정보가 업데이트 되었습니다.";
		try {
			
			
			String monitorType =  monitoringManageDao.getClientMonitoringType(param);	
		
			if(param.getMonitorType() == 1) {
				param.getMonitor().get(0).put("boardIndex",0);
			}
			
			if(param.getMonitor().size() > 0) {
				if(monitorType != null && !monitorType.equals("")) {
					if(Integer.valueOf(monitorType) == param.getMonitorType()) {
						for(int i= 0 ;i < param.getMonitor().size();i++) {						
							int updateCount = monitoringManageDao.updateClientMonitoring(param.getMonitor().get(i));
							if(updateCount == 0) {
								monitoringManageDao.saveClientMonitoring(param.getMonitor().get(i));
							}						
						}
						
					} else {
						//monitoringManageDao.deleteClientMonitoring(param);
						try {
							for(int i= 0 ;i < param.getMonitor().size();i++) {						
								int updateCount = monitoringManageDao.updateClientMonitoring(param.getMonitor().get(i));
								if(updateCount == 0) {
									monitoringManageDao.saveClientMonitoring(param.getMonitor().get(i));
								}						
							}
							//monitoringManageDao.saveClientMonitoring(param.getMonitor());
						} catch(DuplicateKeyException e) {
							System.out.println("키중복");
							monitoringManageDao.updateClientMonitoring(param.getMonitor());
						} catch(Exception e) {
							resultCode = 500;
						}
						
					}
					
				} else {
	//				System.out.println("(param.getMonitor().get(1) :"+param.getMonitor().get(1).get("fileName"));
					
					try {
						int updateCount = monitoringManageDao.updateClientMonitoring(param.getMonitor().get(0));
						if(updateCount == 0) {
							monitoringManageDao.saveClientMonitoring(param.getMonitor().get(0));
						}
					} catch(Exception e) {
						resultCode = 500;
						e.printStackTrace();
						
					}
				}
				
			} else {
				monitoringManageDao.deleteClientMonitoring(param);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 보드화면 캡처 저장중  오류가 발생했습니다.";
			System.out.println("클라이언트 보드화면 캡처 저장중  오류가 발생했습니다.");
		}
//		try {
//			System.out.println("param.getResource() :"+param.getResource());
//			Map<String,Object> resourceMap = new HashMap<String,Object>();
//			resourceMap.put("clientId", param.getClientId());
//			resourceMap.put("status", 1);
//			saveClientStatus(resourceMap);
//			
//		}catch(Exception e) {
//			resultCode = 500;
//			resultMsg = "클라이언트 리소스 상태 저장중  오류가 발생했습니다.";
//			e.printStackTrace();
//		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> updateClientMonitoringConf(MonitoringInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 모니터링 설정 정보가 업데이트 되었습니다.";
		try {
			int resultCount =  monitoringManageDao.updateClientMonitoringConf(param);	
			
		}catch(Exception e) {
			resultCode = 500;
			resultMsg = "클라이언트 모니터링 설정 정보 저장중  오류가 발생했습니다.";
			e.printStackTrace();
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	
	@Override
	public MonitoringInfoVO getClientMonitoringConf(MonitoringInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 모니터링 설정 정보가 조회 되었습니다.";
		MonitoringInfoVO monitoringInfoData = null;
		try {
			param.setFtpType("MONITOR");
			monitoringInfoData=  monitoringManageDao.getClientMonitoringConf(param);				
		} catch(Exception e) {
			resultCode = 500;
			resultMsg = "클라이언트 모너티링 설정 정보 조회 중 오류가 발생했습니다.";
			e.printStackTrace();
		}

		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return monitoringInfoData;
	}
	@Override
	public Map<String, Object> getClientMonitoring(int clientId) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 모니터링 정보가 조회 되었습니다.";
		MonitoringResInfoVO monitoringResInfoVO = null;
		try {			
			monitoringResInfoVO=  monitoringManageDao.getClientMonitoring(clientId);		
			resultMap.put("data", monitoringResInfoVO);
		} catch(Exception e) {
			resultCode = 500;
			resultMsg = "클라이언트 모너티링 정보 조회 중 오류가 발생했습니다.";
			e.printStackTrace();
		}

		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
}
