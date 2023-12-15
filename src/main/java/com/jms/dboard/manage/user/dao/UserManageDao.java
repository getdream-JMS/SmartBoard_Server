package com.jms.dboard.manage.user.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.OrganMemberInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

public interface UserManageDao {
	/** 사용자 리스트 검색   */
	public List<Map<String,Object>> getUserInfoList(UserInfoSearchVO param) throws Exception;
	
	/** 사용자 전체 수    */
	public int getTotalUserInfoList(UserInfoSearchVO param) throws Exception;
	
	/** 사용자 상세 정보    */
	public UserInfoVO getUserInfo(UserInfoSearchVO param) throws Exception;
	
	/** 사용자 등록 요청 저장    */
	public int saveUserReq(UserReqInfoVO param) throws Exception;
	
	/** 사용자 등록 요청 - 권한 저장    */
	public int saveUserRoleReq(UserReqInfoVO param) throws Exception;
	
	/** 사용자 등록 요청 - 관리 지점  저장    */
	public int saveUserOrganReq(UserReqInfoVO param) throws Exception;	
	
	/** 사용자 등록    */
	public int saveUserInfo(UserInfoVO param) throws Exception;
	
	/** 사용자 수정    */
	public int updateUserInfo(UserInfoVO param) throws Exception;
	
	/** 사용자 등록  - 권한  */
	public int saveUserRoleInfo(UserInfoVO param) throws Exception;
	
	/** 사용자 등록  - 할당된 클라이언트  */
	public int saveUserClientInfo(UserInfoVO param) throws Exception;
	
	
	/** 사용자 등록  - 지점  */
	public int saveUserOrganInfo(UserInfoVO param) throws Exception;
	
	/** 사용자 권한 삭제  */
	public int deleteUserRoleInfo(UserInfoVO param) throws Exception;
	
	/** 사용자 지점 삭제  */
	public int deleteUserOrganInfo(UserInfoVO param) throws Exception;	
	
	/** 사용자 삭제  */
	public int deleteUserInfo(UserInfoVO param) throws Exception;	
	
	
	public UserInfoVO getUserInfo(String userName) throws Exception;
	
	public UserInfoVO getRoleInfo(String userName) throws Exception;
	
	/** 사용자  요청 정보 수정    */
	public int updateUserReq(UserReqInfoVO param) throws Exception;
	
	/** 요청 사용자 정보 사용자 테이블로 복사    */
	public int saveReqUserInfo(UserReqInfoVO param) throws Exception;
	
	/** 요청 사용자  권한 정보  복사    */
	public int saveReqUserRoleInfo(UserReqInfoVO param) throws Exception;
	
	/** 요청 사용자 기관 로 복사    */
	public int saveReqUserOrganInfo(UserReqInfoVO param) throws Exception;
	
		
	/** 사용자 권한요청 리스트 검색   */
	public List<OrganMemberInfoVO> getUserInfoReqList(UserInfoSearchVO param) throws Exception;
	
	/** 사용자 권한 요청 전체 수    */
	public int getTotalUserInfoReqList(UserInfoSearchVO param) throws Exception;
	
	/** 사용자 권한 요청 정보    */
	public OrganMemberInfoVO getUserInfoReqInfo(UserReqInfoVO param) throws Exception;
	
	
	/** 요청 사용자 정보 삭제    */
	public int deleteReqUserInfo(UserInfoVO param) throws Exception;
	
	/** 요청 사용자  권한 정보  삭제    */
	public int deleteReqUserRoleInfo(UserInfoVO param) throws Exception;
	
	/** 요청 사용자 기관 삭제  */
	public int deleteReqUserOrganInfo(UserInfoVO param) throws Exception;
	
	/** 요청 사용자 정보 삭제  사용자 아이디  */
	public int deleteReqUserInfo(UserReqInfoVO param) throws Exception;
	
	/** 요청 사용자  권한 정보  삭제 사용자 아이디   */
	public int deleteReqUserRoleInfo(UserReqInfoVO param) throws Exception;
	
	/** 요청 사용자 기관 삭제 사용자 아이디  */
	public int deleteReqUserOrganInfo(UserReqInfoVO param) throws Exception;
	
	/** 사용자 클라이언트 삭제  */
	public int deleteUserClientInfo(UserInfoVO param) throws Exception;	
	
}
