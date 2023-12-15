package com.jms.dboard.common.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.vo.AnnounceBoardDetailsVO;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface CommonCodeDao {
	/**공통 코드 조회 By name */
	public CommonCodeVO getCommonCodeInfoByName (CommonCodeVO param) throws Exception;
	
	/**공통 코드 조회 By code */
	public CommonCodeVO getCommonCodeInfoByCode (CommonCodeVO param) throws Exception;

	/**공통 코드 조회 By 그룹 code */
	public List<CommonCodeVO> getCommonCodeInfoListByCode (CommonCodeVO param) throws Exception;
	
}
