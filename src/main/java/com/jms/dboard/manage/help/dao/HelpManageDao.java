package com.jms.dboard.manage.help.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientScheduleVO;
import com.jms.dboard.manage.vo.ClientsInfoResultVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.HelpInfoVO;


public interface HelpManageDao {
	/**솔루션 정보 조회  */
	public Map<String,Object> getHelpInfo (Map<String,Object> param) throws Exception;
	
	
}
