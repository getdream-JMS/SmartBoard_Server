package com.jms.dboard.manage.jumin.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;

public interface JuminBoardDao {
	/**주민소식 리스트 조회  */
	public List<PromoBoardResultVO> getJuminBoardList (PromoBoardVO param) throws Exception;
	
	/**주민소식 리스트 조회  */
	public PromoBoardResultVO getJuminBoardInfo (PromoBoardVO param) throws Exception;

	/**주민소식 전체 데이타 수  */
	public int getTotalJuminBoardList (PromoBoardVO param) throws Exception;
	
	/**주민소식  저장*/
	public int saveJuminBoardInfo (PromoBoardVO param) throws Exception;
	
	
	/**주민소식 수정*/
	public int updateJuminBoardInfo (PromoBoardVO param) throws Exception;
	
	/**주민소식 리스트 삭제*/
	public int deleteJuminBoardInfo (List<Integer> param) throws Exception;
	
	/**주민소식 삭제*/
	public int deleteJuminBoardInfo (PromoBoardVO param) throws Exception;
	
	public int getNextSeqJumin () throws Exception;
	
	public List<Integer> getClientsByContent (Map<String,Object> param) throws Exception;
	
	/**동영상 설정 조회  */
	public Map<String,String> getVideoConfig () throws Exception;
	
	
}
