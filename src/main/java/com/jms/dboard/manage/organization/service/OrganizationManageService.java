package com.jms.dboard.manage.organization.service;

import java.util.Map;

import com.jms.dboard.manage.vo.OrganInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface OrganizationManageService {
	/**조직  조회  */
	public Map<String,Object> getOrganInfoList (OrganInfoVO param);
	
	/**기관  조회  */
	public Map<String,Object> getOfficeInfoList (OrganInfoVO param);
	
	/**조직별 부서원  조회  */
	public Map<String,Object> getUsersByOrgan (UserInfoSearchVO param);
	
	/**사용자 관리 지점  조회  */
	public Map<String,Object> getUserOfficeList (UserInfoVO param);
	
	/**클라이언트  관리 지점  조회  */
	public Map<String,Object> getAllClientList (UserInfoVO param);
	
}
