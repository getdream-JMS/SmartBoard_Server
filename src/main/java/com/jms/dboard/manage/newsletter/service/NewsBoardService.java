package com.jms.dboard.manage.newsletter.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jms.dboard.manage.vo.NewsBoardDetailsVO;
import com.jms.dboard.manage.vo.NewsBoardVO;

public interface NewsBoardService {
	/**소식지 조회  */
	public Map<String,Object> getNewsBoardList (NewsBoardVO param);
	
	/**소식지 상세  */
	public Map<String,Object> getNewsBoardInfo (int param);
	
	/**소식지  저장*/
	public JSONObject saveNewsBoardInfo (NewsBoardVO param);
	
	/**소식지  이미지 정보 저장 */
	public int saveNewsBoardImage (List<NewsBoardDetailsVO> param);
	
	/**소식지 수정*/
	public JSONObject updateNewsBoardInfo (NewsBoardVO param);
	
	/**소식지 삭제*/
	public Map<String,Object> deleteNewsBoardInfo (List<Integer> param);
	
	/**소식지 이미지 정보 삭제*/
	public int deleteNewsBoardImage (NewsBoardVO param);
}
