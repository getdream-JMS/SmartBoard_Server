package com.jms.dboard.manage.code.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.code.dao.CodeManageDao;

@Service("codeManageService")
public class CodeManageServiceImpl implements CodeManageService{

	@Autowired
	CodeManageDao codeManageDao;
	
	
	@Autowired
	CommonCodeDao commonCodeDao;
	@Override
	public Map<String,Object> getResolutionList(Map<String,Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = codeManageDao.getResolutionList(param);
			resultMap.put("list",list);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "해상도 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getTouchTimeoutConfig(CommonCodeVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		Map<String,Object> data = new HashMap<String,Object>();
		try {
			data = codeManageDao.getTouchTimeoutConfig(param);
			resultMap.put("data",data);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "터치 타임아웃 설정 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

}
