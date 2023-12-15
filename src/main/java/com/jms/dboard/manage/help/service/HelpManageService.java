package com.jms.dboard.manage.help.service;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;

import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;

public interface HelpManageService {
	/**솔루션 정보 조회  */
	public Map<String, Object> getHelpInfo();	
	
}
