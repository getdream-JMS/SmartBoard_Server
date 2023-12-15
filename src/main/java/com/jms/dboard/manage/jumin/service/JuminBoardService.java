package com.jms.dboard.manage.jumin.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jms.dboard.manage.vo.PromoBoardVO;

public interface JuminBoardService {
	/**주민소식 조회  */
	public Map<String,Object> getJuminBoardList (PromoBoardVO param);
	
	/**주민소식 상세  */
	public Map<String,Object> getJuminBoardInfo (int param);
	
	/**주민소식  저장*/
	public JSONObject saveJuminBoardInfo (PromoBoardVO param);
	
	/**주민소식 수정*/
	public JSONObject updateJuminBoardInfo (PromoBoardVO param);
	
	/**주민소식 삭제*/
	public Map<String,Object> deleteJuminBoardInfo (List<Integer> param);
	
	/**주민소식 이미지 정보 삭제*/
	public int deleteJuminBoardInfo (PromoBoardVO param);
	
	
}
