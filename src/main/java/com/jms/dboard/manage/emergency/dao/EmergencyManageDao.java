package com.jms.dboard.manage.emergency.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;

public interface EmergencyManageDao {
	/**긴급컨텐츠 리스트 조회  */
	public List<PromoBoardResultVO> getEmergencyList (PromoBoardVO param) throws Exception;
	
	/**긴급컨텐츠 리스트 조회  */
	public PromoBoardResultVO getEmergencyInfo (PromoBoardVO param) throws Exception;

	/**긴급컨텐츠 전체 데이타 수  */
	public int getTotalEmergencyList (PromoBoardVO param) throws Exception;
	
	/**긴급컨텐츠  저장*/
	public int saveEmergencyInfo (PromoBoardVO param) throws Exception;
	
	
	/**긴급컨텐츠 수정*/
	public int updateEmergencyInfo (PromoBoardVO param) throws Exception;
	
	/**긴급컨텐츠 리스트 삭제*/
	public int deleteEmergencyInfo (List<Integer> param) throws Exception;
	
	/**긴급컨텐츠 삭제*/
	public int deleteEmergencyInfo (PromoBoardVO param) throws Exception;
	
	public int getNextSeqPromo () throws Exception;
	
}
