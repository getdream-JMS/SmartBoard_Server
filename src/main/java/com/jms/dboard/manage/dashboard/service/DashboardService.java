package com.jms.dboard.manage.dashboard.service;


import java.util.Map;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface DashboardService {
	/**대시보드 정보  조회*/
	public Map<String,Object> getDashboardInfo (String userId);
}
