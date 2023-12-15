package com.jms.dboard.manage.version.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CompressZip;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.core.socket.SocketIOController;
import com.jms.dboard.core.socket.websocket.WebsocketEndpoint;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.version.dao.VersionManageDao;
import com.jms.dboard.manage.vo.*;
import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service("versionManageService")
public class VersionManageServiceImpl implements VersionManageService {

	@Autowired
	VersionManageDao versionManageDao;

	@Autowired
	CommonCodeDao commonCodeDao;

	@Autowired
	ContentManageDao contentManageDao;

	@Override
	public Map<String, Object> updateVersionInfo(Map<String,Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 모니터링 설정 정보가 업데이트 되었습니다.";
		try {
			int resultCount =  versionManageDao.updateVersionInfo(param);
			
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
	public Map<String, Object> getVersionInfo(Map<String,Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		Map<String,Object> data = new HashMap<String,Object>();
		try {
			param.put("ftpType","VERSION");
			data = versionManageDao.getVersionInfo(param);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "버전정보 조회 중 오류가 발생했습니다.";
			data.put("resultMsg", resultMsg);
			data.put("resultCode", resultCode);
		}
		return data;
	}

	public Map<String, Object> getClientVersionInfo(Map<String,Object>  param) {
		// TODO Auto-generated method stub
		Map<String,Object> data = new HashMap<String,Object>();
		try {


			param.put("ftpType","VERSION");
			data = versionManageDao.getClientVersionInfo(param);
			generatePatchFile(param, data);
			System.out.println("data : "+ data.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public boolean generatePatchFile(Map<String,Object>  param, Map<String,Object> data) {
		boolean result = false;
		/* 클라이언트 page 압축 */
		CommonCodeVO codeVo = new CommonCodeVO();
		codeVo.setCodeGrp("007");
		String patchPath = "";
		String pagePath = "";
		String resourcePath = "";
		BufferedWriter bf = null;
		int resultCode = 200;
		PrintWriter pw_promo = null;
		PrintWriter pw_client = null;

		try {

			Map<String,Object> promoResult = new HashMap<String,Object>();
			DownloadBoardResultVO clientMap = null;
			Map<String,Object> resultMap = new HashMap<String,Object>();
			List<CommonCodeVO> codeList = commonCodeDao.getCommonCodeInfoListByCode(codeVo);
			System.out.println("codeList : "+codeList.size());

			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			String strDate = dateFormat.format(date);
			String jsonPromoStr = "";
			String jsonClientStr = "";
			for(int i = 0 ; i < codeList.size(); i ++) {
				CommonCodeVO codeInfo = codeList.get(i);
				System.out.println("code info : "+codeInfo.getCodeInfo());
				if(codeInfo.getCodeName().equals("PATCH_PATH")) {
					patchPath = codeInfo.getCodeInfo();
				} else if(codeInfo.getCodeName().equals("PAGE_PATH")) {
					pagePath = codeInfo.getCodeInfo();
					String [] exceptFolder = {"resources"};

					makeZipFile(pagePath,patchPath,"page_"+data.get("pageVersion"), exceptFolder);
				} else if(codeInfo.getCodeName().equals("RESOURCE_PATH")) {
					resourcePath = codeInfo.getCodeInfo();
					File file = new File(resourcePath);

					//홍보물 데이타 조회
					try {
						ClientBoardDataReqVO paramData = new ClientBoardDataReqVO();
						paramData.setClientId((Integer) param.get("clientId"));
						paramData.setBoardIndex(0);
						paramData.setBoardType("002");
						paramData.setCurDate(strDate);
						//paramData.setUrgentYn("N");
						paramData.setPageCount(-1);
						promoResult = contentManageDao.getClientPromoBoardInfo(paramData);
						resultMap.put("result", resultCode);
						resultMap.put("data", promoResult);

						//JSONObject jsonObject = new JSONObject(resultMap);


						//jsonStr = objectMapper.writeValueAsString(resultMap);

						//System.out.println("promoResult :"+jsonObject.toString());
						clientMap = new DownloadBoardResultVO();
						clientMap = contentManageDao.getDownloadBoardList(paramData);

					} catch(Exception e) {
						e.printStackTrace();
						resultCode = 500;
						resultMap.put("result", resultCode);
						resultMap.put("data", jsonPromoStr);
					} finally {
						try {
							// 화면 표시를 위한 컨텐츠 데이타
							Map<String, Object> clientData = new HashMap<String,Object>();
							ObjectMapper objectMapper = new ObjectMapper();
							pw_promo = new PrintWriter(new FileWriter(resourcePath + File.separator + "client_data.json"));
							jsonPromoStr = objectMapper.writeValueAsString(resultMap);

							jsonPromoStr = "clientData="+jsonPromoStr;
							//clientData.put("clientData",jsonPromoStr);

							System.out.println("jsonPromoStr5 :" + jsonPromoStr);
							pw_promo.write(jsonPromoStr);

							/*  클라이언트에서 파일 관리를 위한 데이타 */
							objectMapper = new ObjectMapper();
							pw_client = new PrintWriter(new FileWriter(resourcePath + File.separator + "data.json"));
							jsonClientStr = objectMapper.writeValueAsString(clientMap);
							System.out.println("jsonClientStr1 :" + jsonClientStr);
							System.out.println("promoResult1 :" + resourcePath + File.separator + "data.json");
							pw_client.write(jsonClientStr);


						}catch(Exception e) {
							e.printStackTrace();
						}

					}

					// create new BufferedWriter for the output file
					//bf = new BufferedWriter(new FileWriter(file));

					// iterate map entries
					/*
					for (Map.Entry<String, Object> entry :
							promoResult.entrySet()) {

						// put key and value separated by a colon
						bf.write(entry.getKey() + ":"
								+ entry.getValue());

						// new line
						bf.newLine();
					}

					bf.flush();

					 */

					String [] exceptFolder = {"patch"};
					makeZipFile(resourcePath, patchPath, "resource_"+data.get("resourceVersion"),exceptFolder);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// always close the writer
				if(bf != null) bf.close();
				if(pw_promo !=null) pw_promo.close();
				if(pw_client !=null) pw_client.close();
			}
			catch (Exception e) {
			}
		}


		return result;
	}

	private void makeZipFile (String filePath,String patchPath, String fileName, String[] exceptDir) {
		System.out.println("filePath : " + filePath);
		System.out.println("patchPath : " + patchPath);
		System.out.println("fileName : " + fileName);
		try {
			if (!CompressZip.compress(filePath, patchPath, fileName,exceptDir)) {
				System.out.println("압축 실패");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	@Override
	public void sendEMGVersion() {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("eventType","EMG_PATCH");
		try {
			Map<String,Object> resultMap = versionManageDao.getClientVersionInfo(param);
			param.put("pageVersion",resultMap.get("pageVersion"));
			param.put("resourceVersion",resultMap.get("resourceVersion"));
			System.out.println("resultMap : "+resultMap.toString());
			WebsocketEndpoint.broadcastAll(param);
		}catch(Exception e) {
			param.put("pageVersion","");
			param.put("resourceVersion","");
			try {
				WebsocketEndpoint.broadcastAll(param);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			} catch (EncodeException ex) {
				throw new RuntimeException(ex);
			}
			e.printStackTrace();
		}

	}
}
