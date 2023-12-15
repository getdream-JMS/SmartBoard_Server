package com.jms.dboard.manage.missing.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jms.dboard.manage.vo.MissingPeopleResInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleManagerVO;
import com.jms.dboard.manage.vo.MissingPeopleReqInfoVO;
import com.jms.dboard.manage.vo.PromoBoardVO;


public interface MissingManageService {
	/**실종자 조회  클라이언트 */
	public Map<String,Object> getMissingClientList (MissingPeopleReqInfoVO param);
	
	
	/**실종자 조회 관리페이지 */
	public Map<String,Object> getMissingManagerList (MissingPeopleManagerVO param);
	
	/**실종자 상세  */
	public Map<String,Object> getMissingInfo (int param);
	
	/**실종자  저장*/
	public Map<String,Object>  saveMissingInfo (MissingPeopleReqInfoVO param);
	
	/**실종자 수정*/
	public Map<String,Object>  updateMissingInfo (MissingPeopleReqInfoVO param);
	
	/**실종자 삭제*/
	public Map<String,Object> deleteMissingInfo (List<Integer> param);
	
	/**실종자 이미지 정보 삭제*/
	public int deleteMissingInfo (MissingPeopleInfoVO param);
	
	
}
