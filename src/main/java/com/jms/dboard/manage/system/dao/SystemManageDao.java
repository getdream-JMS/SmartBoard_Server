package com.jms.dboard.manage.system.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ContentInfoVO;
import com.jms.dboard.manage.vo.OrganMemberInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

public interface SystemManageDao {
	/** 컨텐츠 통게 검색   */
	public List<Map<String,Object>> getStatisticContents(ContentInfoVO param) throws Exception;
	
	/** 컨텐츠 통계 전체 수    */
	public int getTotalStatisticContents(ContentInfoVO param) throws Exception;
}
