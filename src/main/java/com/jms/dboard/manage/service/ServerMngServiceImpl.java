package com.jms.dboard.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jms.dboard.manage.dao.ServerMngDao;
import com.jms.dboard.manage.vo.ServerDiskInfoVO;
import com.jms.dboard.manage.vo.ServerInfoVO;
import com.jms.dboard.manage.vo.ServerSnmpVO;
import com.jms.dboard.manage.vo.ServerStatusVO;

@Service("serverMngService")
public class ServerMngServiceImpl implements ServerMngService{

	@Autowired
	ServerMngDao serverMngDao;
	
	@Override
	public List<ServerInfoVO> getServerList(ServerInfoVO param) {
		// TODO Auto-generated method stub
		List<ServerInfoVO> serverList = null;
		try {
			serverList = new ArrayList<ServerInfoVO>();
			return serverMngDao.getServerList(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverList;
	}

	@Override
	public ServerInfoVO getServerId(Map<String,Object> param) {
		// TODO Auto-generated method stub
		ServerInfoVO resultData = null;
		try {
			resultData =  serverMngDao.getServerId(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultData;
	}

	@Override
	public int insertServerStatus(ServerStatusVO param) {
		// TODO Auto-generated method stub
		int insertCount = 0;
		try {
			insertCount =  serverMngDao.insertServerStatus(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insertCount;
	}

	@Override
	public int updateServerStatus(ServerStatusVO param) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		try {
			updateCount =  serverMngDao.updateServerStatus(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateCount;
	}

	@Override
	public List<ServerInfoVO> getServerIpList(ServerInfoVO param) {
		// TODO Auto-generated method stub
		List<ServerInfoVO> serverList = null;
		try {
			serverList = new ArrayList<ServerInfoVO>();
			return serverMngDao.getServerIpList(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverList;
	}

	@Override
	public ServerSnmpVO getServerSnmpInfo(String param) {
		// TODO Auto-generated method stub
		ServerSnmpVO resultData = null;
		try {
			resultData =  serverMngDao.getServerSnmpInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultData;
	}

	@Override
	public int insertServerSnmpInfo(ServerSnmpVO param) {
		// TODO Auto-generated method stub
		int insertCount = 0;
		try {
			insertCount =  serverMngDao.insertServerSnmpInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insertCount;
	}

	@Override
	public int updateServerSnmpInfo(ServerSnmpVO param) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		try {
			updateCount =  serverMngDao.updateServerSnmpInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateCount;
	}

	@Override
	public ServerDiskInfoVO selectSvrDiskUseInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		ServerDiskInfoVO resultData = null;
		try {
			resultData =  serverMngDao.selectSvrDiskUseInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultData;
	}

	@Override
	public int insertSvrDiskUseInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int insertCount = 0;
		try {
			insertCount =  serverMngDao.insertSvrDiskUseInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insertCount;
	}

	@Override
	public int updateSvrDiskUseInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		try {
			updateCount =  serverMngDao.updateSvrDiskUseInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateCount;
	}

	@Override
	public int deleteSvrDiskUseInfo(String param) {
		// TODO Auto-generated method stub
		int deleteCount = 0;
		try {
			deleteCount =  serverMngDao.deleteSvrDiskUseInfo(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleteCount;
	}

	@Override
	public ServerInfoVO getServerIdById(Map<String, Object> param) {
		// TODO Auto-generated method stub
		ServerInfoVO resultData = null;
		try {
			resultData =  serverMngDao.getServerIdByName(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultData;
	}
}
