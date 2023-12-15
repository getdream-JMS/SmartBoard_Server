package com.jms.dboard.manage.tonghap.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.common.vo.BaseSearchVO;
import com.jms.dboard.manage.vo.ClientLocationReqInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.TonghapEventClientVO;
import com.jms.dboard.manage.vo.TonghapEventReqInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface TonghapIntegrateDao {

	/*클라이언트 위치  조회 */
	public List<Map<String,Object>> getClientLocationList(ClientLocationReqInfoVO param) throws Exception;
	
	public Map<String,Object> getClientStatus(Map<String, Object> param) throws Exception;
	
	/*클라이언트 위치 전체 수 */
	public int getClientLocationCount(ClientLocationReqInfoVO param) throws Exception;	
	
	/* 긴급 이벤트  저장 */
	public int saveEmegencyInfo(TonghapEventReqInfoVO param) throws Exception;
	
	/* 긴급 이벤트  조회 */
	public List<Map<String,Object>> getEmergencyInfoList(ClientLocationReqInfoVO param) throws Exception;
	
	public List<Map<String,Object>> getTonghapEventInfoClient(int param) throws Exception;
	public List<Map<String,Object>> getTonghapEventInfoCMS(BaseSearchVO param) throws Exception;
	public int getTotalCountTonghapEvent(BaseSearchVO param) throws Exception;
	
}
