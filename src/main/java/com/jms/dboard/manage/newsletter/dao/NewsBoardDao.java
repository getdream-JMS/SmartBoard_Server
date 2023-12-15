package com.jms.dboard.manage.newsletter.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.NewsBoardDetailsVO;
import com.jms.dboard.manage.vo.NewsBoardResultVO;
import com.jms.dboard.manage.vo.NewsBoardVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface NewsBoardDao {
	/**소식지 리스트 조회  */
	public List<NewsBoardResultVO> getNewsBoardList (NewsBoardVO param) throws Exception;
	
	/**소식지 리스트 조회  */
	public NewsBoardResultVO getNewsBoardInfo (NewsBoardVO param) throws Exception;

	/**소식지 전체 데이타 수  */
	public int getTotalNewsBoardList (NewsBoardVO param) throws Exception;
	
	/**소식지  저장*/
	public int saveNewsBoardInfo (NewsBoardVO param) throws Exception;
	
	/**소식지  이미지 정보 저장 */
	public int saveNewsBoardImage (List<NewsBoardDetailsVO> param) throws Exception;
	
	/**소식지 수정*/
	public int updateNewsBoardInfo (NewsBoardVO param) throws Exception;
	
	/**소식지 리스트 삭제*/
	public int deleteNewsBoardInfo (List<Integer> param) throws Exception;
	
	/**소식지 삭제*/
	public int deleteNewsBoardInfo (NewsBoardVO param) throws Exception;
	
	/**소식지 이미지  리스트 삭제*/
	public int deleteNewsBoardImage (List<Integer> param) throws Exception;
	
	/**소식지 이미지 정보 삭제*/
	public int deleteNewsBoardImage (NewsBoardVO param) throws Exception;
	
	public int getNextSeqAnnounce () throws Exception;
	
	/**TTS 리스트 삭제*/
	public int deleteTtsInfo (List<Integer> param) throws Exception;
	
	/**TTS 삭제*/
	public int deleteTtsInfo (NewsBoardVO param) throws Exception;
	
	/**소식지  TTS 정보 저장 */
	public int saveTtsInfo (List<NewsBoardDetailsVO> param) throws Exception;
}
