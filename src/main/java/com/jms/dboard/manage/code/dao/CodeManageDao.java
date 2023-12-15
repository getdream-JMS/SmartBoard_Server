package com.jms.dboard.manage.code.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientScheduleVO;
import com.jms.dboard.manage.vo.ClientsInfoResultVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.common.vo.CommonCodeVO;

public interface CodeManageDao {
	/**해상도  리스트 조회  */
	public List<Map<String,Object>> getResolutionList (Map<String,Object> param) throws Exception;
	
	/** Dboard 설정 조회      */
	public Map<String, Object> getDboardConfig(Map<String, Object> param) throws Exception;
	
	/**클라이언트로부터 코드데이타 조회*/
	public Map<String, Object> getTouchTimeoutConfig(CommonCodeVO param) throws Exception;
}
