package com.jms.dboard.manage.announce.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.AnnounceBoardDetailsVO;
import com.jms.dboard.manage.vo.AnnounceBoardResultVO;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface AnnounceBoardDao {
	/**공고문 리스트 조회  */
	public List<AnnounceBoardResultVO> getAnnounceBoardList (AnnounceBoardVO param) throws Exception;
	
	/**공고문 리스트 조회  */
	public AnnounceBoardResultVO getAnnounceBoardInfo (AnnounceBoardVO param) throws Exception;

	/**공고문 전체 데이타 수  */
	public int getTotalAnnounceBoardList (AnnounceBoardVO param) throws Exception;
	
	/**공고문  저장*/
	public int saveAnnounceBoardInfo (AnnounceBoardVO param) throws Exception;
	
	/**공고문  이미지 정보 저장 */
	public int saveAnnounceBoardImage (List<AnnounceBoardDetailsVO> param) throws Exception;
	
	/**공고문 수정*/
	public int updateAnnounceBoardInfo (AnnounceBoardVO param) throws Exception;
	
	/**공고문 리스트 삭제*/
	public int deleteAnnounceBoardInfo (List<Integer> param) throws Exception;
	
	/**공고문 삭제*/
	public int deleteAnnounceBoardInfo (AnnounceBoardVO param) throws Exception;
	
	/**공고문 이미지  리스트 삭제*/
	public int deleteAnnounceBoardImage (List<Integer> param) throws Exception;
	
	/**공고문 이미지 정보 삭제*/
	public int deleteAnnounceBoardImage (AnnounceBoardVO param) throws Exception;
	
	public int getNextSeqAnnounce () throws Exception;
	
	/**TTS 리스트 삭제*/
	public int deleteTtsInfo (List<Integer> param) throws Exception;
	
	/**TTS 삭제*/
	public int deleteTtsInfo (AnnounceBoardVO param) throws Exception;
	
	/**공고문  TTS 정보 저장 */
	public int saveTtsInfo (List<AnnounceBoardDetailsVO> param) throws Exception;
}
