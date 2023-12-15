package com.jms.dboard.common.monitor.snmp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;


import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jms.dboard.common.utils.CommonUtils;


/** snmp4j 라이브러리를 이용해서 장비의 자원&성능 정보를 수집한다. - commented by shMoon */
public class SnmpClientHandler {

	private int port;
	private String cpuOidValue;
	private String memoryTotalOidValue;
	private String memoryUsedOidValue;
	private String memoryBufferedOidValue;
	private String memoryCachedOidValue;
	private String memoryFreeOidValue;	
	private String partNmOidValue;
	private String partRtoOidValue;
	private String partTotalOidValue;
	private String partUsedOidValue;

	private String nicIfDescValue;
	private String nicIfOperStatusValue;
	
	private List<String> nicManageName;
	private List<String> nicServiceName;
	//		private String nicIfInOctectsValue;
	//		private String nicIfOutOctectsValue;
	//		private String nicIfMngOctectsValue;



	private int nMax;

	private int snmpVersion1 = SnmpConstants.version1;
//	private int snmpVersion2 = SnmpConstants.version2c;
	Snmp snmp;
	HashMap<String, Object> hMap;
	private TreeUtils treeUtils;

	PDU pdu;

	public int getPort() { return port; }
	public void setPort(int port) { this.port = port; }

	public String getCpuOidValue() { return cpuOidValue; }
	public void setCpuOidValue(String cpuOidValue) { this.cpuOidValue = cpuOidValue; }

	public String getMemoryTotalOidValue() { return memoryTotalOidValue; }
	public void setMemoryTotalOidValue(String memoryTotalOidValue) { this.memoryTotalOidValue = memoryTotalOidValue; }

	public String getMemoryUsedOidValue() { return memoryUsedOidValue; }
	public void setMemoryUsedOidValue(String memoryUsedOidValue) { this.memoryUsedOidValue = memoryUsedOidValue; }

	public String getMemoryBufferedOidValue() { return memoryBufferedOidValue; }
	public void setMemoryBufferedOidValue(String memoryBufferedOidValue) { this.memoryBufferedOidValue = memoryBufferedOidValue; }

	public String getMemoryCachedOidValue() { return memoryCachedOidValue; }
	public void setMemoryCachedOidValue(String memoryCachedOidValue) { this.memoryCachedOidValue = memoryCachedOidValue; }
	
	
	public String getMemoryFreeOidValue() {
		return memoryFreeOidValue;
	}
	public void setMemoryFreeOidValue(String memoryFreeOidValue) {
		this.memoryFreeOidValue = memoryFreeOidValue;
	}
	public String getPartNmOidValue() { return partNmOidValue; }
	public void setPartNmOidValue(String partNmOidValue) { this.partNmOidValue = partNmOidValue; }

	public String getPartRtoOidValue() { return partRtoOidValue; }
	public void setPartRtoOidValue(String partRtoOidValue) { this.partRtoOidValue = partRtoOidValue; }

	/**
	 * @return the partTotalOidValue
	 */
	public String getPartTotalOidValue() {
		return partTotalOidValue;
	}
	/**
	 * @param partTotalOidValue the partTotalOidValue to set
	 */
	public void setPartTotalOidValue(String partTotalOidValue) {
		this.partTotalOidValue = partTotalOidValue;
	}
	/**
	 * @return the partUsedOidValue
	 */
	public String getPartUsedOidValue() {
		return partUsedOidValue;
	}
	/**
	 * @param partUsedOidValue the partUsedOidValue to set
	 */
	public void setPartUsedOidValue(String partUsedOidValue) {
		this.partUsedOidValue = partUsedOidValue;
	}
	public int getnMax() { return nMax; }
	public void setnMax(int nMax) { this.nMax = nMax; }


	public String getNicIfDescValue() {
		return nicIfDescValue;
	}
	public void setNicIfDescValue(String nicIfDescValue) {
		this.nicIfDescValue = nicIfDescValue;
	}
	public String getNicIfOperStatusValue() {
		return nicIfOperStatusValue;
	}
	public void setNicIfOperStatusValue(String nicIfOperStatusValue) {
		this.nicIfOperStatusValue = nicIfOperStatusValue;
	}
	
	public List<String> getNicManageName() {
		return nicManageName;
	}
	public void setNicManageName(List<String> nicManageName) {
		this.nicManageName = nicManageName;
	}
	public List<String> getNicServiceName() {
		return nicServiceName;
	}
	public void setNicServiceName(List<String> nicServiceName) {
		this.nicServiceName = nicServiceName;
	}
	public boolean initSnmp()
	{
		try {
			hMap = new HashMap<String, Object>();
			pdu = new PDU();
			pdu.add(new VariableBinding(new OID(cpuOidValue)));
			pdu.add(new VariableBinding(new OID(memoryTotalOidValue)));
			pdu.add(new VariableBinding(new OID(memoryUsedOidValue)));
			pdu.add(new VariableBinding(new OID(memoryBufferedOidValue)));
			pdu.add(new VariableBinding(new OID(memoryCachedOidValue)));
			pdu.add(new VariableBinding(new OID(memoryFreeOidValue)));
			
			pdu.setType(PDU.GET);

			snmp = new Snmp(new DefaultUdpTransportMapping());
			treeUtils = new TreeUtils(snmp,new DefaultPDUFactory());
			snmp.listen();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	} // initSnmp()

	public void Destroy()
	{
		if (pdu.getVariableBindings() != null) {
			pdu.getVariableBindings().clear();
			pdu = null;
		}
		if (snmp != null) { // added by yseun
			try {
				snmp.close();
			} catch (Exception e2) {}
			snmp = null;
		}
	} // Destroy()

	/* return HashMap --> Map */
	public HashMap<String, Object> getSnmpValue(Map<String,Object> serverInfo, String communityExt)
	{
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try {			
			hMap.clear();
//			System.out.println(serverInfo.get("serverIp") + "/" + port);
			CommunityTarget comtarget = new CommunityTarget();
			comtarget.setCommunity(new OctetString(communityExt));
			comtarget.setVersion(snmpVersion1);
			comtarget.setAddress(new UdpAddress(serverInfo.get("serverIp") + "/" + port));
			comtarget.setRetries(1);
			comtarget.setTimeout(500);
			ResponseEvent response = snmp.get(pdu, comtarget);
			if (response == null) {
				return hMap;
			}

			PDU responsePDU = response.getResponse();
			if (responsePDU == null) {
				return hMap;
			}

			if (responsePDU.getErrorStatus() != PDU.noError) {
				hMap.put("errDesc", "[SNMP] Error Status = " + responsePDU.getErrorStatus() + ", Error Text = " + responsePDU.getErrorStatusText());
				return hMap;
			}

			Vector<? extends VariableBinding> vector = (Vector<? extends VariableBinding>) responsePDU.getVariableBindings();
			hMap.put("serverIp", serverInfo.get("serverIp"));
			hMap.put("serverName", serverInfo.get("serverName"));
			if (vector != null) {
				float totalMemory = 0.0f;
				float usedMemory = 0.0f;
//				float freeMemory = 0.0f;
				float cacheMemory = 0.0f;
				float availMemory = 0.0f;
				for (int i = 0; i < vector.size(); ++i) {
					VariableBinding vb = vector.get(i);
					//						System.out.println("vb.getOid().toString() :"+vb.getOid().toString());
					if (cpuOidValue.equals(vb.getOid().toString())) {
						hMap.put("cpuUseRto", Integer.valueOf(100 - vb.getVariable().toInt()));
					} else if (memoryTotalOidValue.equals(vb.getOid().toString())) {
						totalMemory += vb.getVariable().toInt();
						
					} else if (memoryBufferedOidValue.equals(vb.getOid().toString())) {
						cacheMemory += vb.getVariable().toInt();							
					} else if (memoryCachedOidValue.equals(vb.getOid().toString())) {
						cacheMemory += vb.getVariable().toInt();							
					} else if (memoryUsedOidValue.equals(vb.getOid().toString())) {
						availMemory += vb.getVariable().toInt();
					} 
//					else if (memoryFreeOidValue.equals(vb.getOid().toString())) {
//						freeMemory += vb.getVariable().toInt();
//					}
					// if
				} // for	
				vector.clear();		
				
				
				DecimalFormat decimalFormat = new DecimalFormat("#.#");
				usedMemory = totalMemory - cacheMemory - availMemory;
				
				
//				System.out.println("totalMemory:"+totalMemory);
//				System.out.println("cacheMemory:"+cacheMemory);	
//				System.out.println("availMemory:"+availMemory);
				
//				System.out.println("freeMemory:"+freeMemory);
				
				
				dataMap.put("mmryUseRto", Float.valueOf(decimalFormat.format((usedMemory == 0 ? 0.0 : ( CommonUtils.round(((usedMemory)/ totalMemory) * 100.0f,1))))));
//				dataMap.put("mmryCacheRto", Float.valueOf(decimalFormat.format((cacheMemory == 0 ? 0.0 : ( CommonUtils.round((cacheMemory / totalMemory) * 100.0f,1))))));
//				dataMap.put("mmryAvailRto", Float.valueOf(decimalFormat.format((cacheMemory == 0 ? 0.0 : ( CommonUtils.round(((availMemory) / totalMemory) * 100.0f,1))))));
				dataMap.put("mmryFreeRto", Float.valueOf(decimalFormat.format(((usedMemory+cacheMemory) == 0 ? 0.0 : ( CommonUtils.round(((totalMemory- usedMemory)/ totalMemory) * 100.0f,1))))));
				
				dataMap.put("totalMemory", CommonUtils.formatFileSize(totalMemory));
				dataMap.put("usedMemory", CommonUtils.formatFileSize(usedMemory));	
				dataMap.put("freeMemory", CommonUtils.formatFileSize(totalMemory- usedMemory));
//				dataMap.put("cacheMemory", CommonUtils.formatFileSize(cacheMemory));
//				dataMap.put("availableMemory", CommonUtils.formatFileSize(availMemory));	
				hMap.put("memory", dataMap);



			} // if
			int iMaxPartUse = 0;
			long iTotalPart = 0;
			long iUsedPart = 0;
			
			List<Map<String,Object>> diskInfoArray = new ArrayList<Map<String,Object>>();
			for (int i = 1; i <= nMax; i++) {
				Map<String,Object> iPartInfo = getPartition(i, comtarget);		
				
				int iPartUse = (int) iPartInfo.get("iUseRto");
				iTotalPart += (int) iPartInfo.get("iUseTotal");
				iUsedPart += (int) iPartInfo.get("iUseUsed");

				if (iPartUse < 0) { // 파티션 정보 추출 실패
					break;
				} else if (iPartUse > iMaxPartUse) {
					iMaxPartUse = iPartUse;
				} // if
				iPartInfo.put("diskTotalSize", CommonUtils.formatFileSize(iTotalPart));
				iPartInfo.put("diskUsedSize", CommonUtils.formatFileSize(iUsedPart));
				diskInfoArray.add(iPartInfo);
				
			} // for
//			hMap.put("diskUseRto", iMaxPartUse);
//			hMap.put("diskTotal", CommonUtils.formatFileSize(iTotalPart));
//			hMap.put("diskUsed", CommonUtils.formatFileSize(iUsedPart));
			
//			dataMap = new HashMap<String,Object>();			
//			dataMap.put("diskUseRto", iMaxPartUse);
//			dataMap.put("diskTotal", CommonUtils.formatFileSize(iTotalPart));
//			dataMap.put("diskUsed", CommonUtils.formatFileSize(iUsedPart));
			hMap.put("disk", diskInfoArray);
			
			Map<String,Object> iIfInfo = getInterface(comtarget);
			//				for (Map.Entry<String, Object> entry : iIfInfo.entrySet()) {
			//		            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			//		        }
			int netErr = (int) iIfInfo.get("netErrorCount");
			hMap.put("ststNet", (netErr > 0)?-2:1); // Interface card에 하나라도 문제가 있으면 오류
			hMap.put("nic", iIfInfo);
			return hMap;
		} catch (Exception e) {
			e.printStackTrace();
			hMap.put("errDesc", "[SNMP] Error Exception = " + e.getMessage());
			return hMap;
		} finally {
			if (hMap.size() == 0 || hMap.get("errDesc") != null) {
//				System.out.println(hMap.size() +","+ hMap.get("errDesc"));
				hMap.put("serverIp", serverInfo.get("serverIp"));
				hMap.put("serverName", serverInfo.get("serverName"));
				hMap.put("cpuUseRto", 0);
				dataMap.clear();
				dataMap.put("mmryUseRto", 0);
				dataMap.put("mmryFreeRto", 0);				
				dataMap.put("totalMemory",0);
				dataMap.put("usedMemory", 0);	
				dataMap.put("freeMemory", 0);
				hMap.put("memory", dataMap);
				hMap.put("disk", null);
				hMap.put("nic", null);
				hMap.put("ststNet", -1);
			}
		}
	} // getSnmpValue()
	

	@SuppressWarnings("rawtypes")
	public Map<String,Object> getPartition(int nPosition, CommunityTarget comtarget)
	{
		int iUseRto = -1;
		int iUseTotal = 0;
		int iUseUsed = 0;
		String iUseName = "";
		Map<String,Object> iRtoInfo = new HashMap<String,Object>();
		iRtoInfo.put("iUseRto",iUseRto);
		iRtoInfo.put("iUseTotal",iUseTotal);
		iRtoInfo.put("iUseUsed",iUseUsed);
		PDU partPDU = new PDU();
		try {
			partPDU.add(new VariableBinding(new OID(partNmOidValue + String.valueOf(nPosition))));
			partPDU.add(new VariableBinding(new OID(partRtoOidValue + String.valueOf(nPosition))));
			partPDU.add(new VariableBinding(new OID(partTotalOidValue + String.valueOf(nPosition))));
			partPDU.add(new VariableBinding(new OID(partUsedOidValue + String.valueOf(nPosition))));
			ResponseEvent response = snmp.get(partPDU, comtarget);
			if (response == null) {
				return iRtoInfo;
			}
			PDU responsePDU = response.getResponse();
			if (responsePDU == null) {
				return iRtoInfo;
			}
			if (responsePDU.getErrorStatus() != PDU.noError) {
				return iRtoInfo;
			}
			Vector vector = (Vector) responsePDU.getVariableBindings();
			
			for (int i = 0; i < vector.size(); i++) {
				VariableBinding variableBinding = (VariableBinding) vector.get(i);
				if (variableBinding.getOid().toString().indexOf(partNmOidValue) == 0) {
//					System.out.println(variableBinding.getVariable().toString());
//					hMap.put(variableBinding.getOid().toString().replaceAll(partNmOidValue, "partNm"), variableBinding.getVariable().toString());
					iUseName = variableBinding.getVariable().toString();
				} else if (variableBinding.getOid().toString().indexOf(partRtoOidValue) == 0) {
					iUseRto = variableBinding.getVariable().toInt();
//					hMap.put(variableBinding.getOid().toString().replaceAll(partRtoOidValue, "partRto"), Integer.valueOf(iUseRto));
				} else if (variableBinding.getOid().toString().indexOf(partTotalOidValue) == 0) {
					iUseTotal = variableBinding.getVariable().toInt();
//					hMap.put(variableBinding.getOid().toString().replaceAll(partTotalOidValue, "partTotal"), iUseTotal);
				} else if (variableBinding.getOid().toString().indexOf(partUsedOidValue) == 0) {
					iUseUsed = variableBinding.getVariable().toInt();
//					hMap.put(variableBinding.getOid().toString().replaceAll(partUsedOidValue, "partUsed"), iUseUsed);
				}
			}
		} catch (Exception e) {
			iUseRto = -1;
		}
		iRtoInfo.put("iUseName",iUseName);
		iRtoInfo.put("iUseRto",iUseRto);
		iRtoInfo.put("iUseTotal",iUseTotal);
		iRtoInfo.put("iUseUsed",iUseUsed);
		return iRtoInfo;
	} // getPartition()

	@SuppressWarnings("rawtypes")
	public Map<String,Object> getInterface(CommunityTarget comtarget)
	{

		Map<String,Object> iIfcInfo = new HashMap<String,Object>();
		Map<String,Object> iIfcInfoName = null;
		List<Map<String,Object>> ifcInfoList = null;
		Map<String,Object> subifcInfo = null;

		try {
			List<String> ifDescription = getSubTreeData("STR",getOID(nicIfDescValue),comtarget );
			List<String> ifOperStatus = getSubTreeData("INT",getOID(nicIfOperStatusValue),comtarget );		

			int errorCount = 0;
			int totalErrorCount = 0;
			ifcInfoList = new ArrayList<Map<String,Object>>();			
			subifcInfo = new HashMap<String,Object>();
			//				System.out.println("ifDescription.size() :"+ifDescription);
			//				System.out.println("ifOperStatus.size() :"+ifOperStatus);
			if(ifDescription.size() > 0 && ifOperStatus.size() > 0) {
				/*try {					
					List<Integer> nicStreamArray = getIndexFromStrArray(nicServiceName, ifDescription);
					for(int i = 0;i < nicStreamArray.size();i++) {	
						try {
							iIfcInfoName = new HashMap<String,Object>();
							iIfcInfoName.put("name",ifDescription.get(nicStreamArray.get(i)).replace("\"",""));
							iIfcInfoName.put("status",ifOperStatus.get(nicStreamArray.get(i)));	
							ifcInfoList.add(iIfcInfoName);
							if(ifOperStatus.get(nicStreamArray.get(i)).equals("2")) {
								errorCount++;
								totalErrorCount++;
							}						
						}catch(Exception e) {
							System.out.println("서비스 네트웍 카드 상태 조회중 오류 발생");
						}
						iIfcInfoName = new HashMap<String,Object>();
					}	
					subifcInfo.put("status", ifcInfoList);
					subifcInfo.put("errorCount", errorCount);
					iIfcInfo.put("service",subifcInfo );
				} catch(Exception e) {
					e.printStackTrace();
				}
*/
				ifcInfoList = new ArrayList<Map<String,Object>>();			
				subifcInfo = new HashMap<String,Object>();
				errorCount = 0;

				List<Integer> nicManageArray = getIndexFromStrArray(nicManageName, ifDescription);

				for(int i = 0;i < nicManageArray.size();i++) {	
					try {
						iIfcInfoName = new HashMap<String,Object>();
						iIfcInfoName.put("name",ifDescription.get(nicManageArray.get(i)).replace("\"",""));
						iIfcInfoName.put("status",ifOperStatus.get(nicManageArray.get(i)));
						ifcInfoList.add(iIfcInfoName);
						if(ifOperStatus.get(nicManageArray.get(i)).equals("2")) {
							errorCount++;
							totalErrorCount++;
						}
					}catch(Exception e) {
						System.out.println("관리 망 네트웍 카드 상태 조회중 오류 발생");
					}		
				}
				subifcInfo.put("status", ifcInfoList);
				subifcInfo.put("errorCount", errorCount);
				iIfcInfo.put("manage",subifcInfo );

//				ifcInfoList = new ArrayList<Map<String,Object>>();			
//				subifcInfo = new HashMap<String,Object>();
//				errorCount = 0;
//				if(svrTypeCd.equals("02")) {				
//					nicNameArray.clear(); 
//					nicNameArray.add("eno2");
//					List<Integer> nicPublicArray = getIndexFromStrArray(nicNameArray, ifDescription);
//					for(int i = 0;i < nicPublicArray.size();i++) {	
//						try {
//							iIfcInfoName = new HashMap<String,Object>();
//							iIfcInfoName.put("name",ifDescription.get(nicPublicArray.get(i)).replace("\"",""));
//							iIfcInfoName.put("status",ifOperStatus.get(nicPublicArray.get(i)));
//							ifcInfoList.add(iIfcInfoName);
//							if(ifOperStatus.get(nicPublicArray.get(i)).equals("2")) {
//								errorCount++;
//								totalErrorCount++;
//							}
//						}catch(Exception e) {
//							System.out.println("공인망 네트웍 카드 상태 조회중 오류 발생");
//						}				
//					}
//					subifcInfo.put("status", ifcInfoList);
//					subifcInfo.put("errorCount", errorCount);
//					iIfcInfo.put("nicOutput",subifcInfo );
//				} else {
//					subifcInfo.put("status", ifcInfoList);
//					subifcInfo.put("errorCount", errorCount);
//					iIfcInfo.put("nicOutput",subifcInfo );
//				}

				iIfcInfo.put("netErrorCount",totalErrorCount );

				//}
			}
		} catch (Exception e) {
			//				iUseRto = -1;
		}

		return iIfcInfo;
	}

	private List<Integer> getIndexFromStrArray(List<String> list,List<String> tartgetList) {

		List<Integer> result = new ArrayList<Integer>();
		String nicName = "";
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < tartgetList.size(); j++) {
				nicName = tartgetList.get(j).replace("\"","").trim();
				
				if(nicName.equals(list.get(i).trim())){
//					System.out.println(list.get(i).trim());
					result.add(j); 
					break;
				}
			}
		}
		return result;


	}
	public List<String> getSubTreeData(String type, OID oid,CommunityTarget comtarget){
		List<String> subTreeList = new ArrayList<String>();
		try {
			List<TreeEvent> events = treeUtils.getSubtree(comtarget, oid);
			if(events == null || events.size() == 0){
				//			  System.out.println("No result returned."+oid);
				return subTreeList;
			}

			for (TreeEvent event : events) {
				if(event != null){
					if (event.isError()) {
						System.err.println("oid [" + oid + "] " + event.getErrorMessage());
					}

					VariableBinding[] varBindings = event.getVariableBindings();
					if(varBindings == null || varBindings.length == 0){
						//			      System.out.println("No result returned."+oid);
					} else {
						for (VariableBinding varBinding : varBindings) {
							if(type.equals("STR")) {
								subTreeList.add("\""+varBinding.getVariable()+"\"");		    		
							} else {
								subTreeList.add(varBinding.getVariable().toString());
							}
						}
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return subTreeList;
	}

	private OID getOID(String oidStr){
		return new OID(oidStr);
	}
	
} // CommonSnmp
