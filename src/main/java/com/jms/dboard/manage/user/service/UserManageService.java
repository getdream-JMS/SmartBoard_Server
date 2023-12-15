package com.jms.dboard.manage.user.service;


import java.util.Map;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

public interface UserManageService  {
	/** 사용자 리스트 검색   */
	public Map<String,Object> getUserInfoList (UserInfoSearchVO param);
	
	/** 사용자 상세 정보    */
	public Map<String, Object> getUserInfo(String param);
	
	/** 사용자 등록 요청    */
	public Map<String, Object> saveUserReq(UserReqInfoVO param);
	
	/** 사용자 등록  - 기본정보  */
	public Map<String, Object> saveUserInfo(UserInfoVO param);
	
	/** 사용자 정보 수정  - 기본정보  */
	public Map<String, Object> updateUserInfo(UserInfoVO param);
	
	/** 사용자 정보 삭제   */
	public Map<String, Object> deleteUserInfo(String param);
	
	/** 사용자 등록 요청 수락   */
	public Map<String, Object> acceptUserRequest(UserReqInfoVO param);
	
	/** 사용자 리스트 검색   */
	public Map<String,Object> getUserInfoReqList (UserInfoSearchVO param); 
	
	/** 조직원 정보 조회  */
	public Map<String,Object> getOrganMemberInfo (UserReqInfoVO param); 
	
} 