package com.jms.dboard.common.monitor.snmp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.vo.AlarmVO;
import com.jms.dboard.manage.service.ServerMngService;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@Component
public class NmsSnmpClient{	

	private Properties propSnmpConf;

	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	private Persister persister;

	private int _iMaxPartition;
	private int _iSnmpPort;
	private String _sOidCpu;
	private String _sOidMemoryTotal;
	private String _sOidMemoryUsed;
	private String _sOidMemoryBuffered;
	private String _sOidMemoryCached;
	private String _sOidMemoryFree;
	private String _sOidPartitionName;
	private String _sOidPartitionRto;
	private String _sOidPartitionTotal;
	private String _sOidPartitionUsed;
	private String _sSnmpCommunity;
	private String _sOidNicIfDesc;
	private String _sOidNicIfOperStatus;
	private List<String> _nicManageName;
	private List<String> _nicServiceName;	
	

	private AlarmVO alarmVo = null;
	
	@Autowired
	private ServerMngService serverMngService;

	private boolean _init()
	{
		boolean result = false;
		InputStream propFile = null;
		try {

			propSnmpConf=new Properties();
			String propertiesPath = System.getenv("AMUZ_SNMP_PROPERTY");
			
			if(propertiesPath == null || propertiesPath.equals("")) propertiesPath="D:\\home\\amuzlab\\snmp-property.properties";

			if(propertiesPath != null && !propertiesPath.equals("")) {
				propFile = new FileInputStream(propertiesPath);
				propSnmpConf.load(propFile);

				_iMaxPartition = Integer.parseInt(propSnmpConf.getProperty("snmp.partition.max.count").trim());
				_iSnmpPort = Integer.parseInt(propSnmpConf.getProperty("snmp.port").trim());
				_sOidCpu = propSnmpConf.getProperty("snmp.oid.cpu").trim();
				_sOidMemoryTotal = propSnmpConf.getProperty("snmp.oid.memory.total").trim();
				_sOidMemoryUsed = propSnmpConf.getProperty("snmp.oid.memory.used").trim();
				_sOidMemoryBuffered = propSnmpConf.getProperty("snmp.oid.memory.buffered").trim();
				_sOidMemoryCached = propSnmpConf.getProperty("snmp.oid.memory.cached").trim();
				_sOidMemoryFree = propSnmpConf.getProperty("snmp.oid.memory.free").trim();

				_sOidPartitionName = propSnmpConf.getProperty("snmp.oid.partition.name").trim();
				_sOidPartitionRto = propSnmpConf.getProperty("snmp.oid.partition.rto").trim();
				_sOidPartitionTotal = propSnmpConf.getProperty("snmp.oid.partition.total").trim();
				_sOidPartitionUsed = propSnmpConf.getProperty("snmp.oid.partition.used").trim();

				_sOidNicIfDesc = propSnmpConf.getProperty("snmp.oid.nic.if.desc").trim();
				_sOidNicIfOperStatus = propSnmpConf.getProperty("snmp.oid.nic.if.oper.status").trim();

				_sSnmpCommunity = propSnmpConf.getProperty("snmp.community.name").trim();
				_nicManageName = Arrays.asList(propSnmpConf.getProperty("snmp.nic.manage.name").trim().split(","));
				_nicServiceName = Arrays.asList(propSnmpConf.getProperty("snmp.nic.service.name").trim().split(","));

				result = loadAlarmConfig();
			} else {
				return false;
			}
			// if
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			if(propFile != null)
				try {
					propFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	/** �ֱ������� ����Ǹ�, ��ϵ� ��� ��� ����, SNMP�� ���� �ڿ� ��뷮�� �����ؼ� DB�� �����Ѵ�.
	 */
//	@Scheduled(fixedRate = 5000) 
	public void doSnmp() // 10�� �ֱ�� ����ȴ�.
	{
//		System.out.println("singletonBean :"+singletonBean);
//		System.out.println("singletonBean.getWorkCount :"+singletonBean.getWorkCount());
//		singletonBean.setWorkCount(singletonBean.getWorkCount()+1);
//		for(int i = 0; i < singletonBean.getTrJobStatus().size();i++) {
//			System.out.println("singletonBean.getWorkCount :"+singletonBean.getTrJobStatus().get(i).get("serverId"));
//			System.out.println("singletonBean.getWorkCount :"+singletonBean.getTrJobStatus().get(i).get("workCount"));
//		}
//		System.out.println("### START SNMP GET SERVER ###");
		loadAlarmConfig();
		if (!_init()) {
			return;
		} // if

		SnmpClientHandler snmp = null;
		//		List<Map<String, Object>> lstMapSvr = null;
		Map<String, Object> paramMap = null;
		
		
		try {
			// ��񸮽�Ʈ�� �о�´�.
			//			SimpleSnmpClient snmpClient = null;
			Map<String,Object> snmpClientMap= null; 
			
			ServerInfoVO serverInfoVO = new ServerInfoVO();
			List<ServerInfoVO> serverList = serverMngService.getServerIpList(serverInfoVO);
			JSONObject resultJson = null;
			if(serverList.size() > 0) {
				String serverIp = "";
				String serverName = "";
				ServerInfoVO svrInfo = null; 
				snmpClientMap= new HashMap<String,Object>(); 				
				snmp = new SnmpClientHandler();
				snmp.setnMax(_iMaxPartition);
				snmp.setPort(_iSnmpPort);
				snmp.setCpuOidValue(_sOidCpu);
				snmp.setMemoryTotalOidValue(_sOidMemoryTotal);
				snmp.setMemoryUsedOidValue(_sOidMemoryUsed);
				snmp.setMemoryBufferedOidValue(_sOidMemoryBuffered);
				snmp.setMemoryCachedOidValue(_sOidMemoryCached);
				snmp.setMemoryFreeOidValue(_sOidMemoryFree);					
				snmp.setPartNmOidValue(_sOidPartitionName);
				snmp.setPartRtoOidValue(_sOidPartitionRto);
				snmp.setPartTotalOidValue(_sOidPartitionTotal);
				snmp.setPartUsedOidValue(_sOidPartitionUsed);		
				
				/** NIC snmp */
				snmp.setNicIfDescValue(_sOidNicIfDesc);
				snmp.setNicIfOperStatusValue(_sOidNicIfOperStatus);
				snmp.setNicManageName(_nicManageName);
				snmp.setNicServiceName(_nicServiceName);

				if (!snmp.initSnmp()) {
					return;
				} // if
				
				paramMap = new HashMap<String, Object>();
				int sameAlarmCount = 0;
				int newErrCode = 0;
				String errorDesc = "";
				Map<String, Object> mapData = null;
				ServerSnmpVO ServerSnmpVO = null;
				for(int j = 0;j<serverList.size();j++) {
					svrInfo = serverList.get(j);
					serverIp = svrInfo.getServerIp();
					snmpClientMap.put("serverIp" , serverIp);
					mapData =snmp.getSnmpValue(snmpClientMap, _sSnmpCommunity);		
//					System.out.println(new JSONObject(mapData).toString());
					resultJson = new JSONObject(mapData);
					ServerSnmpVO mapStstNet = serverMngService.getServerSnmpInfo(serverIp);
					newErrCode = (int) mapData.get("ststNet");
//					System.out.println(svrSn+":"+mapStstNet);
					
					
					
					if (mapStstNet != null) { // 기존 기록이 DB 에 있다면		
						mapStstNet.setMmryUseRto(resultJson.getJSONObject("memory").getFloat("mmryUseRto"));
						mapStstNet.setCpuUseRto(resultJson.getFloat("cpuUseRto"));
						mapStstNet.setStstNet(resultJson.getInt("ststNet"));
						serverMngService.updateServerSnmpInfo(mapStstNet); // DB에 업데이트
						
						if(resultJson.getInt("ststNet") == -1) {
							System.out.println("[ERROR] TRANSCODER SNMP Network Fail, START FAILOVER");
						}
					} else if ((Integer) mapData.get("ststNet") == 1 || (Integer) mapData.get("ststNet") == -2) { // 성공인 경우만 추가한다.
						mapStstNet = new ServerSnmpVO();
						mapStstNet.setServerIp(serverIp);
						mapStstNet.setMmryUseRto(resultJson.getJSONObject("memory").getFloat("mmryUseRto"));
						mapStstNet.setCpuUseRto(resultJson.getFloat("cpuUseRto"));
						mapStstNet.setStstNet(resultJson.getInt("ststNet"));
						serverMngService.insertServerSnmpInfo(mapStstNet); // SNMP 수집 데이터 DB 에 저장
					} else { // SNMP 성공 이력이 없었고, 실패했다면 바로 종료
						continue;
					} // if				
					

					// DB에 DISK 사용량 파티션별 사용률 업데이트. 1분동안 수신한 데이터만 유지 (Table: TB_SVR_DISK_USE_INFO)
					String partNm = "";
					for (int i = 0; i < resultJson.getJSONArray("disk").length(); i++) {
						paramMap.clear();
						partNm = ((JSONObject) resultJson.getJSONArray("disk").get(i)).getString("iUseName");
						if (partNm == null || partNm.equals("") ) {
							break;
						} // if
						paramMap.put("serverIp", serverIp);
						paramMap.put("pttnNm", partNm);
						paramMap.put("pttnUseRto",((JSONObject) resultJson.getJSONArray("disk").get(i)).getInt("iUseRto"));
//						System.out.println("serverIp :" + serverIp);
//						System.out.println("partNm :" + serverMngService.selectSvrDiskUseInfo(paramMap));
						if (serverMngService.selectSvrDiskUseInfo(paramMap) == null) {
							serverMngService.insertSvrDiskUseInfo(paramMap);
						} else {
							serverMngService.updateSvrDiskUseInfo(paramMap);
						} // if
						serverMngService.deleteSvrDiskUseInfo(serverIp); // 1분 경과한 레코드는 삭제하는군.
					} // for
					
					Map<String,Object> nicMap = (Map<String, Object>) mapData.get("nicInfo");		
//					System.out.println(svrSn+":"+nicMap);
					if(nicMap != null) {
						Map<String,Object> nicTypeMap = (Map<String, Object>) nicMap.get("nicInput");
						List<Map<String,Object>> nicStatusList = (List<Map<String, Object>>) nicTypeMap.get("status");
						
						for(int k = 0; k < nicStatusList.size();k++) {
							Map<String,Object> nicInfo = nicStatusList.get(k);
							String nicStatus = (String) nicInfo.get("status");

//							_logDao.addAlertLog(svrSn, logSysName, errorDesc , (nicStatus.equals("1") ? LogsUtils.LEVEL_INFO : LogsUtils.LEVEL_ERROR));

		
						}
						nicTypeMap.clear();
						nicStatusList.clear();
						nicTypeMap = (Map<String, Object>) nicMap.get("nicManage");
						nicStatusList = (List<Map<String, Object>>) nicTypeMap.get("status");
						for(int k = 0; k < nicStatusList.size();k++) {
							Map<String,Object> nicInfo = nicStatusList.get(k);
							String nicStatus = (String) nicInfo.get("status");

//								_logDao.addAlertLog(svrSn, logSysName, errorDesc , (nicStatus.equals("1") ? LogsUtils.LEVEL_INFO : LogsUtils.LEVEL_ERROR));

						}
						nicTypeMap.clear();
						nicStatusList.clear();
						/* 캡쳐 서버 인 경우에만 공인망 체크 */
//
//						nicTypeMap = (Map<String, Object>) nicMap.get("nicOutput");
//						nicStatusList = (List<Map<String, Object>>) nicTypeMap.get("status");
//						for(int k = 0; k < nicStatusList.size();k++) {
//							Map<String,Object> nicInfo = nicStatusList.get(k);
//							String nicStatus = (String) nicInfo.get("status");
//
//								_logDao.addAlertLog(svrSn, logSysName, errorDesc , (nicStatus.equals("1") ? LogsUtils.LEVEL_INFO : LogsUtils.LEVEL_ERROR));
//
//						}

					} else {
						
					}
					mapData.clear();
					paramMap.clear();
					snmpClientMap.clear();
				}					
				
			} else {
				return;
			}

		} catch (Exception e) {
		} finally {
			//			lstMapSvr = null;
			_sOidCpu = null;
			_sOidMemoryTotal = null;
			_sOidMemoryUsed = null;
			_sOidMemoryBuffered = null;
			_sOidMemoryCached = null;
			_sOidPartitionName = null;
			_sOidPartitionRto = null;
			_sOidPartitionTotal = null;
			_sOidPartitionUsed = null;
			_sSnmpCommunity = null;
			if (snmp != null) {
				snmp.Destroy();
			}
			//			logger.debug("### END SNMP GET SERVER ###");
		} // try


	} // doSnmp()
	private boolean loadAlarmConfig()
	{
		if (persister == null) { // @Autowired �� ��ü������ ���� �˼� ���� ������ �����Ѵ�.
			return false;
		}
		try {
			this.alarmVo = persister.read(AlarmVO.class, getClass().getResourceAsStream("/message/alarm-properties.xml"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}