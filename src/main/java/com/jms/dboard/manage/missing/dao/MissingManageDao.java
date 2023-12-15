package com.jms.dboard.manage.missing.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.MissingPeopleResInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleManagerVO;
import com.jms.dboard.manage.vo.MissingPeopleReqInfoVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;

public interface MissingManageDao {
	/**실종자 리스트 조회  */
	public List<MissingPeopleResInfoVO> getMissingClientList (MissingPeopleReqInfoVO param) throws Exception;
	
	/**클라이언트 실종자 리스트 조회  */
	public List<MissingPeopleResInfoVO> getMissingManagerList (MissingPeopleManagerVO param) throws Exception;
	
	
	/**실종자 상제 정보 조회  */
	public MissingPeopleInfoVO getMissingInfo (int param) throws Exception;

	/**실종자 전체 데이타 수  */
	public int getTotalMissingManagerList (MissingPeopleManagerVO param) throws Exception;
	
	/**실종자  저장*/
	public int saveMissingInfo (MissingPeopleReqInfoVO param) throws Exception;
	
	/**실종자  연락처 저장*/
	public int saveMissingConnectInfo (MissingPeopleReqInfoVO param) throws Exception;
	
	/**실종자  연락처 삭제*/
	public int deleteMissingConnectInfo (MissingPeopleReqInfoVO param) throws Exception;
	
	
	/**실종자 수정*/
	public int updateMissingInfo (MissingPeopleReqInfoVO param) throws Exception;
	
	/**실종자 리스트 삭제*/
	public int deleteMissingInfo (List<Integer> param) throws Exception;
	
	/**실종자 연락처 리스트 삭제*/
	public int deleteMissingConnectInfo (List<Integer> param) throws Exception;
	
	
	/**실종자 삭제*/
	public int deleteMissingInfo (MissingPeopleInfoVO param) throws Exception;
	
}
