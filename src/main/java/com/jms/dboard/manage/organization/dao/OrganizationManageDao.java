package com.jms.dboard.manage.organization.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.OrganInfoResultVO;
import com.jms.dboard.manage.vo.OrganInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;


public interface OrganizationManageDao {
	/**조직 리스트 조회  */
	public List<OrganInfoResultVO> getOrganInfoList (OrganInfoVO param) throws Exception;
	
	/**조직 전체 리스트 갯수 조회  */
	public int getTotalOrganInfoList (OrganInfoVO param) throws Exception;
	
	/**기관 리스트 조회  */
	public List<OrganInfoResultVO> getOfficeInfoList (OrganInfoVO param) throws Exception;
	
	/**기관 전체 리스트 갯수 조회  */
	public int getTotalOfficeInfoList (OrganInfoVO param) throws Exception;
	
	/**부서원 리스트 조회  */
	public List<UserInfoVO> getUsersByOrgan (UserInfoSearchVO param) throws Exception;
	
	/**부서별 부서원 전체 리스트 갯수 조회  */
	public int getTotalUsersByOrganList (UserInfoSearchVO param) throws Exception;
	
	/**기관 리스트 조회  */
	public List<Map<String,Object>> getUserOfficeList (UserInfoVO param) throws Exception;
	
	/**전체 클라이언트 리스트 조회  */
	public List<Map<String,Object>> getAllClientList (UserInfoVO param) throws Exception;
	
}
