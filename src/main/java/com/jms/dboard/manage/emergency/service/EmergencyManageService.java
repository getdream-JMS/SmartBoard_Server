package com.jms.dboard.manage.emergency.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import com.jms.dboard.manage.vo.PromoBoardVO;


public interface EmergencyManageService {
	/**긴급컨텐츠 조회  */
	public Map<String,Object> getEmergencyList (PromoBoardVO param);
	
	/**긴급컨텐츠 상세  */
	public Map<String,Object> getEmergencyInfo (int param);
	
	/**긴급컨텐츠  저장*/
	public JSONObject saveEmergencyInfo (PromoBoardVO param);
	
	/**긴급컨텐츠 수정*/
	public JSONObject updateEmergencyInfo (PromoBoardVO param);
	
	/**긴급컨텐츠 삭제*/
	public Map<String,Object> deleteEmergencyInfo (List<Integer> param);
	
	/**긴급컨텐츠 이미지 정보 삭제*/
	public int deleteEmergencyInfo (PromoBoardVO param);
	
	
}
