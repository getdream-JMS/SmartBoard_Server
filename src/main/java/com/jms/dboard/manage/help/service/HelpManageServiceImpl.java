package com.jms.dboard.manage.help.service;

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
import com.jms.dboard.manage.help.dao.HelpManageDao;
import com.jms.dboard.manage.vo.HelpInfoVO;

@Service("helpManageService")
public class HelpManageServiceImpl implements HelpManageService{

	@Autowired
	HelpManageDao helpManageDao;

	@Override
	public Map<String, Object> getHelpInfo() {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>(); 
		Map<String,Object> param = new HashMap<String,Object>(); 
		int resultCode = 200;
		String resultMsg = "";

		try { 
			Map<String,Object> resultInfo = helpManageDao.getHelpInfo(param);
			resultMap.put("data",resultInfo);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "솔루션정보 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
}
