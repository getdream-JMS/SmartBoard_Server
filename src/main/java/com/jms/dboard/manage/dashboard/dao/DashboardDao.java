package com.jms.dboard.manage.dashboard.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.MenuInfoVO;
import com.jms.dboard.manage.vo.MenuReqVO;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public interface DashboardDao {
	/*대시보드 클라이언트 상태  조회 */
	public Map<String,Object> getDashboardInfo(ClientsInfoVO param) throws Exception;
}
