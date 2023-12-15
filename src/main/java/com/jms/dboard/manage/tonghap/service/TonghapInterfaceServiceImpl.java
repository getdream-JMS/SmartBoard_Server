package com.jms.dboard.manage.tonghap.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jms.dboard.common.HttpUtils;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.vo.BaseSearchVO;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.menu.dao.MenuDao;
import com.jms.dboard.manage.tonghap.dao.TonghapIntegrateDao;
import com.jms.dboard.manage.vo.ClientLocationInfoResultVO;
import com.jms.dboard.manage.vo.ClientLocationReqInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.TonghapEventClientVO;
import com.jms.dboard.manage.vo.TonghapEventReqInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Service("tonghapInterfaceService")
public class TonghapInterfaceServiceImpl implements TonghapInterfaceService{
	
	@Autowired
	TonghapIntegrateDao tonghapDao;
	
	@Autowired
	CommonCodeDao commonCodeDao;
	
	@Override
	public ClientLocationInfoResultVO getClientLocation(ClientLocationReqInfoVO param) {
		// TODO Auto-generated method stub
		ClientLocationInfoResultVO resultData = new ClientLocationInfoResultVO();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> statusMap = new HashMap<String,Object>();
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		int totalCount = 0;
		String id = "53";
		String num = "3100000";
		String adcd = "077";		
		String nm = "노원구청 디지털 게시판";
		String adr = "서울특별시 노원구 상계6.7동 노해로 437";
		String x = "37.654218";
		String y = "127.057299";
		int resultCode = 200;
		String errorStr = "";
		try {
			
			datas = tonghapDao.getClientLocationList(param);
			if(datas.size() == 0) {
				resultCode = 404;
				errorStr = "조회한 시설물 정보가 없습니다.";
			}
			/*
			if(param.getId().equals("") &&  param.getAdr().equals("") && param.getNm().equals("") ) {
				resultData.setTotalCount(totalCount);
				resultMap.put("id", id);
				resultMap.put("num", num);
				resultMap.put("adcd", adcd);
				resultMap.put("nm", nm);
				resultMap.put("adr", adr);
				resultMap.put("x", x);
				resultMap.put("y", y);
				statusMap.put("power", 1);
				statusMap.put("err", 0);
				resultMap.put("status", statusMap);
				
				datas.add(resultMap);
				resultData.setDatas(datas);
				resultData.setResultCode(200);
				resultData.setErrorString("");
			} else {
				resultData.setTotalCount(0);			
//				datas.add(resultMap);
				resultData.setDatas(datas);
				resultData.setResultCode(404);
				resultData.setErrorString("조회한 시설물 정보가 없습니다.");
			}
			*/
//			totalCount = tonghapDao.getClientLocationCount(param);
			totalCount =  datas.size();
			Map<String,Object> statusInfo = null;
			for(int i=0; i < datas.size();i++) {
				statusMap = new HashMap<String,Object>();
				statusInfo = datas.get(i);
				System.out.println(statusInfo.get("id"));
				statusMap = tonghapDao.getClientStatus(statusInfo);

				System.out.println(statusMap);
				statusInfo.put("status", statusMap);
			}
//			datas =tonghapDao.getClientLocationList(param);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			totalCount = 0;
			resultCode = 500;
			errorStr = "클라이언트 위치및 상태 정보 조회 중 오류가 발생했습니다.";
			e.printStackTrace();
		}finally {
			resultData.setTotalCount(totalCount);
			resultData.setDatas(datas);
			resultData.setResultCode(resultCode);
			resultData.setErrorString(errorStr);
		}
		
		
		return resultData;
	}

	@Override
	public Map<String, Object> getTonghapEventInfo(TonghapEventReqInfoVO param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject saveEmegencyInfo(TonghapEventReqInfoVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String result = "OK";
		String resultMsg = "정상적으로 저장되었습니다.";
		try {
			tonghapDao.saveEmegencyInfo(param);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			result = "ERROR";
			resultMsg = "클라이언트 저장 중  오류가 발생했습니다.";
			
		}
		 
		resultJson.put("resultCode", resultCode);
		resultJson.put("result", result);
		resultJson.put("message", resultMsg);
		resultJson.put("data", "");
		return resultJson;
	}

	@Override
	public JSONObject getTonghapEventInfoClient(int param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		List<Map<String,Object>> resultData =null;
		int resultCode = 200;
		String resultMsg = "";
		try {
			resultData = tonghapDao.getTonghapEventInfoClient(param);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "긴급이벤트 조회 중  오류가 발생했습니다.";
			
		}
		 
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		resultJson.put("data", resultData);
		return resultJson;
	}

	@Override
	public Map<String,Object> getTonghapEventInfoCMS(BaseSearchVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = tonghapDao.getTonghapEventInfoCMS(param);
			totalCount = tonghapDao.getTotalCountTonghapEvent(param);
			resultMap.put("totalCount", totalCount);
			resultMap.put("pageNum", param.getPageNum());
			resultMap.put("pageCount", param.getPageCount());
			resultMap.put("list",list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "긴급이벤트 조회중  오류가 발생했습니다.";
			
		}
		 
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);

		return resultMap;
	}

	@Override
	public JSONObject getWeatherInfo() {
		// TODO Auto-generated method stub
		JSONObject resultJson = null; 
		try {
			CommonCodeVO param = new CommonCodeVO();
			param.setCodeName("EXT_IF_WEATHER");
			CommonCodeVO codeData = commonCodeDao.getCommonCodeInfoByName(param);
			System.out.println("codeData.getCodeInfo() :"+codeData.getCodeInfo());
			if(codeData != null && !codeData.getCodeInfo().equals("")) {
				String weatherUrl = codeData.getCodeInfo();
				String weatherData = HttpUtils.sendGet(weatherUrl);
				System.out.println(weatherData);
				resultJson = new JSONObject(weatherData);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return resultJson;
	}
}
