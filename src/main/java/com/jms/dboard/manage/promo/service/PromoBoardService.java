package com.jms.dboard.manage.promo.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.PromoBoardVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface PromoBoardService {
	/**홍보물 조회  */
	public Map<String,Object> getPromoBoardList (PromoBoardVO param);
	
	/**홍보물 상세  */
	public Map<String,Object> getPromoBoardInfo (int param);
	
	/**홍보물  저장*/
	public JSONObject savePromoBoardInfo (PromoBoardVO param);
	
	/**홍보물 수정*/
	public JSONObject updatePromoBoardInfo (PromoBoardVO param);
	
	/**홍보물 삭제*/
	public Map<String,Object> deletePromoBoardInfo (List<Integer> param);
	
	/**홍보물 이미지 정보 삭제*/
	public int deletePromoBoardInfo (PromoBoardVO param);
	
	
}
