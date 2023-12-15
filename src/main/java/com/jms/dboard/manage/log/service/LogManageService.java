package com.jms.dboard.manage.log.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.jms.dboard.manage.vo.LogInfoVO;
import com.jms.dboard.manage.vo.LoginLogInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Service
public interface LogManageService {
	
	
	/**오류 로그정보 저장  */
	public Map<String,Object> saveLogInfo (String logType,String logCode,String logInfo);
	
	/**오류 로그정보 저장  */
	public Map<String,Object> saveLogInfo (String logType,String logCode,String logInfo,int clientId);
	
	/**오류 로그정보 조회  */
	public Map<String,Object> getLogInfoList (LogInfoVO param);
	
	/**오류 로그정보 삭제  */
	public Map<String,Object> deleteLogInfoList (List<Integer> params);
	
	/**로그인 로그정보 저장  */
	public Map<String,Object> saveLoginLogInfo (LoginLogInfoVO param);
	
	/**로그인 로그정보 조회  */
	public Map<String,Object> getLoginLogInfoList (LoginLogInfoVO param);
	
	/**로그인 로그정보 삭제  */
	public Map<String,Object> deleteLoginLogInfoList (List<Integer> params);
	
	/**로그아웃 로그정보 저장  */
	
	public Map<String,Object> saveLogoutLogInfo (String sessionId);
	
	/**로그아웃 로그정보 수정  */
	public Map<String,Object> updateLogoutLogInfo (UserInfoVO param);	
	
}
