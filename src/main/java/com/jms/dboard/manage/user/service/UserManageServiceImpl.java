package com.jms.dboard.manage.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.user.dao.UserManageDao;
import com.jms.dboard.manage.vo.OrganMemberInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;



@Service("userManageService")
public class UserManageServiceImpl implements UserManageService{

	@Autowired
	UserManageDao userManageDao;
	
	
	@Autowired
	CommonCodeDao commonCodeDao;

	@Autowired
	LogManageService logManageService;
	
	@Override
	public Map<String, Object> getUserInfoList(UserInfoSearchVO param) {
		// TODO Auto-generated method stub
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Map<String, Object>> userList = null;
			int resultCode = 200;
			String resultMsg = "";
			int totalCount = 0;
			try {
				userList = userManageDao.getUserInfoList(param);
				totalCount = userManageDao.getTotalUserInfoList(param);
				
	     
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultCode = 500;
				resultMsg = "사용자 조회 중   오류가 발생했습니다.";
				
			}
			resultMap.put("list",userList);
			resultMap.put("totalCount",totalCount);
			resultMap.put("pageNum",param.getPageNum());
			resultMap.put("pageCount",param.getPageCount());
			resultMap.put("result", resultCode);
			resultMap.put("resultMsg", resultMsg);
			return resultMap;
	}


	@Override
	public Map<String, Object> getUserInfo(String param) {
		// TODO Auto-generated method stub
		ServletRequestAttributes servletRequestAttribute =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);		
		UserInfoVO sessionUserInfo = (UserInfoVO) httpSession.getAttribute("sessionUserInfo");
		String roleType = sessionUserInfo.getRoleType();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfoVO userInfo = null;
		int resultCode = 200;
		String resultMsg = "";
		try {

			userInfo = userManageDao.getUserInfo(param);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "사용자 정보 조회 중  오류가 발생했습니다.";
			
		}
		resultMap.put("data",userInfo);
		resultMap.put("roleType",roleType);
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}


	@Override
	public Map<String, Object> saveUserReq(UserReqInfoVO param) {
		// TODO Auto-generated method stub
		int resultCode = 200;
		String resultMsg = "";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		OrganMemberInfoVO memberInfo = null;
		try {
			param.setIsAccept("Y");
			memberInfo = userManageDao.getUserInfoReqInfo(param);
			if(memberInfo == null) {
				resultCode = 500;
				resultMsg = "조직원 정보에 없는 사용자 입니다.";
			} else {
				if(memberInfo.getIsAccept().equals("") && param.getCheckedMember().equals("Y")) {
					userManageDao.saveUserReq(param);
					if(param.getRoles() != null && param.getRoles().size() > 0) userManageDao.saveUserRoleReq(param);
					//if(param.getOrgans() != null && param.getOrgans().size() > 0) userManageDao.saveUserOrganReq(param);
					
					/*
					 * 사용자 작업 로그
					 * */
					logManageService.saveLogInfo("INFO","I_MNG_US_0004","사용자 권한 요청 ["+param.getUserId()+"("+param.getUserName()+")]");
					
				} else if(memberInfo.getIsAccept().equals("D")) {
					resultCode = 500;
					
					userManageDao.deleteReqUserRoleInfo(param);
					userManageDao.deleteReqUserOrganInfo(param);
					userManageDao.deleteReqUserInfo(param);
					
					resultMsg = "권한 요청 반려 상태입니다. 재요청 해주세요.";
				} else if(memberInfo.getIsAccept().equals("Y")) {
					resultCode = 500;
					resultMsg = "권한 등록이 완료된 사용자입니다.";
				} else if(memberInfo.getIsAccept().equals("N")) {
					resultCode = 500;
					resultMsg = "이미 사용자 등록 요청 상태입니다.";
				}
			}
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "사용자 등록 요청  중  오류가 발생했습니다.";
		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		resultMap.put("memberInfo", memberInfo);
		return resultMap;
	}


	@Override
	public Map<String, Object> saveUserInfo(UserInfoVO param) {
		// TODO Auto-generated method stub
		int resultCode = 200;
		String resultMsg = "";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userManageDao.saveUserInfo(param);
			if(param.getRoles().size() > 0) userManageDao.saveUserRoleInfo(param);
			if(param.getClients().size() > 0) userManageDao.saveUserClientInfo(param);			
			/*
			 * 사용자 작업 로그
			 * */
			logManageService.saveLogInfo("INFO","I_MNG_US_0001","사용자 등록 ["+param.getUserId()+"("+param.getUserName()+")]");
			
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "이미 등록된 사용자 입니다.";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "사용자 정보 저장 중  오류가 발생했습니다.";
		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> updateUserInfo(UserInfoVO param) {
		// TODO Auto-generated method stub
		int resultCode = 200;
		String resultMsg = "";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userManageDao.updateUserInfo(param);
			
			userManageDao.deleteUserRoleInfo(param);
			userManageDao.deleteUserClientInfo(param);
			if(param.getRoles().size() > 0) userManageDao.saveUserRoleInfo(param);
			if(param.getClients().size() > 0) userManageDao.saveUserClientInfo(param);
			/*
			 * 사용자 작업 로그
			 * */
			logManageService.saveLogInfo("INFO","I_MNG_US_0002","사용자 수정 ["+param.getUserId()+"("+param.getUserName()+")]");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "사용자 정보 저장 중  오류가 발생했습니다.";
		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}


	@Override
	public Map<String, Object> acceptUserRequest(UserReqInfoVO param) {
		// TODO Auto-generated method stub
			int resultCode = 200;
			String resultMsg = "";
			Map<String, Object> resultMap = new HashMap<String, Object>();
			try {
				/**
				 * 외부연동으로 사용자 정보 조회
				 * 
				 * */
				//userManageDao.saveUserInfo(param);
				//요청 권한 정보 사용자 권한 테이블로 복사
				//요청 지점 관리 정보 사용자 지점관리 테이블로 복사
				/**
				 * 조회된 사용자 정보 user_mst 테이블로 저장
				 * 사용자 요청 테이블 is_accept 변경
				 * */
				if(param.getIsAccept().equals("Y")) {
					userManageDao.saveReqUserInfo(param);
					userManageDao.saveReqUserRoleInfo(param);
					//userManageDao.saveReqUserOrganInfo(param);
				}
				userManageDao.updateUserReq(param);
				/*
				 * 사용자 작업 로그
				 * */
				logManageService.saveLogInfo("INFO","I_MNG_US_0005","사용자 권한 요청 수락 ["+param.getUserId()+"("+param.getUserName()+")]");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultCode = 500;
				resultMsg = "사용자 등록 중  오류가 발생했습니다.";
			}
			resultMap.put("result", resultCode);
			resultMap.put("resultMsg", resultMsg);
			return resultMap;

	}


	@Override
	public Map<String, Object> getUserInfoReqList(UserInfoSearchVO param) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<OrganMemberInfoVO> userList = null;
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		try {
			userList = userManageDao.getUserInfoReqList(param);
			totalCount = userManageDao.getTotalUserInfoReqList(param);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "사용자 조회 중 오류가 발생했습니다.";
			
		}
		resultMap.put("list",userList);
		resultMap.put("totalCount",totalCount);
		resultMap.put("pageNum",param.getPageNum());
		resultMap.put("pageCount",param.getPageCount());
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}


	@Override
	public Map<String, Object> getOrganMemberInfo(UserReqInfoVO param) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		OrganMemberInfoVO memberInfo = null;
		int resultCode = 200;
		String resultMsg = "";
		try {

			memberInfo = userManageDao.getUserInfoReqInfo(param);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "조직원 정보 조회 중  오류가 발생했습니다.";
			
		}
		resultMap.put("data",memberInfo);
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}


	@Override
	public Map<String, Object> deleteUserInfo(String userId) {
		// TODO Auto-generated method stub
		int resultCode = 200;
		String resultMsg = "정상적으로 사용자 정보가 삭제되었습니다.";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfoVO param = new UserInfoVO();
		param.setUserId(userId);
		try {
			
			
			userManageDao.deleteUserRoleInfo(param);
			userManageDao.deleteUserOrganInfo(param);
			userManageDao.deleteUserInfo(param);
			userManageDao.deleteReqUserRoleInfo(param);
			userManageDao.deleteReqUserOrganInfo(param);
			userManageDao.deleteReqUserInfo(param);
			/*
			 * 사용자 작업 로그
			 * */
			logManageService.saveLogInfo("INFO","I_MNG_US_0003","사용자 삭제 ["+param.getUserId()+"]");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "사용자 정보 삭제 중  오류가 발생했습니다.";
		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}
	
}
