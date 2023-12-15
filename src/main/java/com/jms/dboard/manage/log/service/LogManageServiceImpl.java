package com.jms.dboard.manage.log.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.manage.log.dao.LogManageDao;
import com.jms.dboard.manage.log.dao.LogManageDaoImpl;
import com.jms.dboard.manage.vo.LogInfoResultVO;
import com.jms.dboard.manage.vo.LogInfoVO;
import com.jms.dboard.manage.vo.LoginLogInfoVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.UserInfoVO;


@Service("logManageService")
public class LogManageServiceImpl implements LogManageService{

	
	@Autowired
	LogManageDao logManageDao;
	
	@Autowired
	HttpSession httpSession;
	
	@Override
	public Map<String, Object> saveLogInfo(String logType,String logCode,String logInfo) {

		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		LogInfoVO logParam = new LogInfoVO();
		
		int resultCode = 200;
		String resultMsg = "";
		try {
			ServletRequestAttributes servletRequestAttribute =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(false);
			
			UserInfoVO userInfo = (UserInfoVO) httpSession.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				logParam.setUserId(userInfo.getUserId());
			}
			String remoteIpAdress = CommonUtils.getClientIP();
			logParam.setLogType(logType);
			logParam.setLogCode(logCode);
			logParam.setLogInfo(logInfo);
			logParam.setUserIp(remoteIpAdress);
			
			logManageDao.saveLogInfo(logParam);     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "로그 정보 저장 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> saveLogInfo(String logType,String logCode,String logInfo,int clientId) {
		/*
		 * logType
		 * logCode
		 */
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		LogInfoVO logParam = new LogInfoVO();
		
		int resultCode = 200;
		String resultMsg = "";
		try {
			String remoteIpAdress = CommonUtils.getClientIP();
			logParam.setLogType(logType);
			logParam.setLogCode(logCode);
			logParam.setLogInfo(logInfo);
			logParam.setUserIp(remoteIpAdress);

			logManageDao.saveLogInfo(logParam);     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "로그 정보 저장 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getLogInfoList(LogInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<LogInfoResultVO> list = new ArrayList<LogInfoResultVO>();
		try {
//			System.out.println(param.getPageCount());
//			System.out.println(param.getStartRowNum());
//			System.out.println(param.getStartDate());
//			System.out.println(param.getUserId());
//			System.out.println(param.getEndDate());
//			System.out.println(param.getLogType());
			list = logManageDao.getLogInfoList(param);
			totalCount = logManageDao.getTotalLogInfoList(param);
			resultMap.put("totalCount", totalCount);
			resultMap.put("pageNum", param.getPageNum());
			resultMap.put("pageCount", param.getPageCount());
			resultMap.put("list",list);     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "로그 정보  조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteLogInfoList(List<Integer> params) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";

		List<PromoBoardResultVO> list = new ArrayList<PromoBoardResultVO>();
		try {
			logManageDao.deleteLogInfoList(params);			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "로그 정보 삭제 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> saveLoginLogInfo(LoginLogInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";

		List<PromoBoardResultVO> list = new ArrayList<PromoBoardResultVO>();
		try {
			LoginLogInfoVO loginInfo = logManageDao.getLoginLogInfo(param);
//			System.out.println("loginInfo :"+loginInfo);
//			if(loginInfo != null && loginInfo.getStatus() == 1) {			
//				logManageDao.updateLoginLogInfo(param);
//			}
			logManageDao.saveLoginLogInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "로그인 정보 저장중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> getLoginLogInfoList(LoginLogInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<LoginLogInfoVO> list = new ArrayList<LoginLogInfoVO>();
		try {

			list = logManageDao.getLoginLogInfoList(param);
			
			totalCount = logManageDao.getTotalLoginLogInfoList(param);
			resultMap.put("totalCount", totalCount);
			resultMap.put("pageNum", param.getPageNum());
			resultMap.put("pageCount", param.getPageCount());
			resultMap.put("list",list);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "로그아웃 정보 저장중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteLoginLogInfoList(List<Integer> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateLogoutLogInfo(UserInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		try {
			String remoteIpAdress = CommonUtils.getClientIP();
			
			LoginLogInfoVO loginLogInfoVO = new LoginLogInfoVO();

	    	loginLogInfoVO.setUserId(param.getUserId());
	    	loginLogInfoVO.setUserName(param.getUserName());
	    	loginLogInfoVO.setUserIp(remoteIpAdress);
	    	loginLogInfoVO.setLoginResult("정상적으로 로그아웃 되었습니다.");
			logManageDao.updateLoginLogInfo(loginLogInfoVO);     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "로그아웃 정보 저장중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}	
	
	@Override
	public Map<String, Object> saveLogoutLogInfo(String param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		//ServletRequestAtt/ributes servletRequestAttribute =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		//HttpSession httpSession = servletRequestAttribute.getRequest().getSession(false);
		try {
//			String remoteIpAdress = CommonUtils.getClientIP();
//			System.out.println("sessionId :" +httpSession.getId());
			LoginLogInfoVO loginLogInfoVO = new LoginLogInfoVO();
//			String sessoinId = httpSession.getId();
//	    	loginLogInfoVO.setUserId(param.getUserId());
//	    	loginLogInfoVO.setUserName(param.getUserName());
			loginLogInfoVO.setSessionId(param);
	    	loginLogInfoVO.setStatus(0);
	    	loginLogInfoVO.setLoginResult("정상적으로 로그아웃 되었습니다.");

			logManageDao.saveLogoutLogInfo(loginLogInfoVO);     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "로그아웃 정보 저장중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}	

}
