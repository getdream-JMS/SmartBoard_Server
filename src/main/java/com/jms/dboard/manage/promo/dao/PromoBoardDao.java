package com.jms.dboard.manage.promo.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;

public interface PromoBoardDao {
	/**홍보물 리스트 조회  */
	public List<PromoBoardResultVO> getPromoBoardList (PromoBoardVO param) throws Exception;
	
	/**홍보물 리스트 조회  */
	public PromoBoardResultVO getPromoBoardInfo (PromoBoardVO param) throws Exception;

	/**홍보물 전체 데이타 수  */
	public int getTotalPromoBoardList (PromoBoardVO param) throws Exception;
	
	/**홍보물  저장*/
	public int savePromoBoardInfo (PromoBoardVO param) throws Exception;
	
	
	/**홍보물 수정*/
	public int updatePromoBoardInfo (PromoBoardVO param) throws Exception;
	
	/**홍보물 리스트 삭제*/
	public int deletePromoBoardInfo (List<Integer> param) throws Exception;
	
	/**홍보물 삭제*/
	public int deletePromoBoardInfo (PromoBoardVO param) throws Exception;
	
	public int getNextSeqPromo () throws Exception;
	
	public List<Integer> getClientsByContent (Map<String,Object> param) throws Exception;
	
	/**동영상 설정 조회  */
	public Map<String,String> getVideoConfig () throws Exception;
	
	
}
