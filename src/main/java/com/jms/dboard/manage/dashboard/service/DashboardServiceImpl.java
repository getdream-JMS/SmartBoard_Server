package com.jms.dboard.manage.dashboard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jms.dboard.login.dao.LoginDao;
import com.jms.dboard.manage.clients.dao.ClientsManageDao;
import com.jms.dboard.manage.dao.ServerMngDao;
import com.jms.dboard.manage.dashboard.dao.DashboardDao;
import com.jms.dboard.manage.log.dao.LogManageDao;
import com.jms.dboard.manage.menu.dao.MenuDao;
import com.jms.dboard.manage.vo.ClientsInfoResultVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.LogInfoResultVO;
import com.jms.dboard.manage.vo.LogInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	DashboardDao dashboardDao;
	
	@Autowired
	LogManageDao logManageDao;

	@Override
	public Map<String,Object> getDashboardInfo(String userId) {
		// TODO Auto-generated method stub
		Map<String,Object> clientInfo = new HashMap<String,Object>();
		List<LogInfoResultVO> logInfo = new ArrayList<LogInfoResultVO>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		try {
			/**
			 * 클라이언트 대시보드 정보
			 * */
			ServletRequestAttributes servletRequestAttribute =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
			
			UserInfoVO userInfo = (UserInfoVO) httpSession.getAttribute("sessionUserInfo");
			ClientsInfoVO param = new ClientsInfoVO();
			param.setUserId(userId);
			param.setRoleType(userInfo.getRoleType());
			clientInfo = dashboardDao.getDashboardInfo(param);
			resultMap.put("clientInfo",clientInfo);
			/**
			 * 로그  대시보드 정보
			 * */
			LogInfoVO logParam = new LogInfoVO();
			logParam.setUserId(userId);
			logParam.setRowNumber(20);
			logParam.setLogType("ERR");
			logInfo = logManageDao.getLogDashboard(logParam);
			resultMap.put("logInfo",logInfo);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "대시보드 정보 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}


	
}
