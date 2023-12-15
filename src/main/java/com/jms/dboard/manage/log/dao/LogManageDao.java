package com.jms.dboard.manage.log.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.LogInfoResultVO;
import com.jms.dboard.manage.vo.LogInfoVO;
import com.jms.dboard.manage.vo.LoginLogInfoVO;


public interface LogManageDao {
	/* 대시보드 용 로그 정보 조회 */
	public List<LogInfoResultVO> getLogDashboard(LogInfoVO param) throws Exception;
	/* 로그정보 저장 */
	public int saveLogInfo(LogInfoVO param) throws Exception;
	
	/*로그 정보  조회 */
	public List<LogInfoResultVO> getLogInfoList(LogInfoVO params) throws Exception;
	
	/*로그 정보 삭제 조회 */
	public int deleteLogInfoList(List<Integer> param) throws Exception;
	
	/*전체 로그 카운트 조회 */
	public int getTotalLogInfoList(LogInfoVO param) throws Exception;
	
	/* 로그인 로그정보 저장 */
	public int saveLoginLogInfo(LoginLogInfoVO param) throws Exception;
	
	/* 로그인 로그 정보  조회 */
	public List<LoginLogInfoVO> getLoginLogInfoList(LoginLogInfoVO params) throws Exception;
	
	/*전체 로그인 로그 카운트 조회 */
	public int getTotalLoginLogInfoList(LoginLogInfoVO param) throws Exception;
	
	/*로그인 로그 정보 삭제 조회 */
	public int deleteLoginLogInfoList(List<Integer> param) throws Exception;
	
	/* 로그아웃 로그정보 수정 */
	public int updateLoginLogInfo(LoginLogInfoVO param) throws Exception;
	
	/**사용자 로그 상태 조회  */
	public LoginLogInfoVO getLoginLogInfo (LoginLogInfoVO param);
	
	/* 로그아웃 로그정보 추가*/
	public int saveLogoutLogInfo(LoginLogInfoVO param) throws Exception;
	
}
