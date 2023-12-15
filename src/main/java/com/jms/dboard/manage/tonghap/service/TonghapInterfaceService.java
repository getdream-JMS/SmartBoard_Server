package com.jms.dboard.manage.tonghap.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jms.dboard.common.vo.BaseSearchVO;
import com.jms.dboard.manage.vo.ClientLocationInfoResultVO;
import com.jms.dboard.manage.vo.ClientLocationReqInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.TonghapEventClientVO;
import com.jms.dboard.manage.vo.TonghapEventReqInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface TonghapInterfaceService {
	
	/**클라이언트 위지 정보*/
	public ClientLocationInfoResultVO getClientLocation (ClientLocationReqInfoVO param);
	
	/**통합 플렛폼 이벤트 저장*/
	public JSONObject saveEmegencyInfo (TonghapEventReqInfoVO param);
	
	/**통합 플렛폼 이벤트*/
	public Map<String,Object> getTonghapEventInfo (TonghapEventReqInfoVO param);
	
	public JSONObject getTonghapEventInfoClient(int param);
	
	public Map<String,Object> getTonghapEventInfoCMS (BaseSearchVO param);
	
	public JSONObject getWeatherInfo ();
	
}
