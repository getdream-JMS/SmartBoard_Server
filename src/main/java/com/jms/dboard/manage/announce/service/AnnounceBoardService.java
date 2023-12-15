package com.jms.dboard.manage.announce.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jms.dboard.manage.vo.AnnounceBoardDetailsVO;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface AnnounceBoardService {
	/**공고문 조회  */
	public Map<String,Object> getAnnounceBoardList (AnnounceBoardVO param);
	
	/**공고문 상세  */
	public Map<String,Object> getAnnounceBoardInfo (int param);
	
	/**공고문  저장*/
	public JSONObject saveAnnounceBoardInfo (AnnounceBoardVO param);
	
	/**공고문  이미지 정보 저장 */
	public int saveAnnounceBoardImage (List<AnnounceBoardDetailsVO> param);
	
	/**공고문 수정*/
	public JSONObject updateAnnounceBoardInfo (AnnounceBoardVO param);
	
	/**공고문 삭제*/
	public Map<String,Object> deleteAnnounceBoardInfo (List<Integer> param);
	
	/**공고문 이미지 정보 삭제*/
	public int deleteAnnounceBoardImage (AnnounceBoardVO param);
}
