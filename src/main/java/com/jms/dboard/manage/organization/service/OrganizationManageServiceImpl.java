package com.jms.dboard.manage.organization.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.organization.dao.OrganizationManageDao;
import com.jms.dboard.manage.scheduler.service.SchedulerManageService;
import com.jms.dboard.manage.vo.OrganInfoResultVO;
import com.jms.dboard.manage.vo.OrganInfoVO;
import com.jms.dboard.manage.vo.TtsInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;



@Service("organizationManageService")
public class OrganizationManageServiceImpl implements OrganizationManageService{

	@Autowired
	OrganizationManageDao organManageDao;
	@Autowired
	SchedulerManageService schedulerManageService;
	
	@Autowired
	CommonCodeDao commonCodeDao;
	@Override
	public Map<String,Object> getOrganInfoList(OrganInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<OrganInfoResultVO> list = new ArrayList<OrganInfoResultVO>();
//		try {
//			schedulerManageService.syncNowonInfo(); //서대문구청
//		} catch(Exception e) {			
			try {
				list = organManageDao.getOrganInfoList(param);
				totalCount = organManageDao.getTotalOrganInfoList(param);
				resultMap.put("totalCount", totalCount);
				resultMap.put("pageNum", param.getPageNum());
				resultMap.put("pageCount", param.getPageCount());
				resultMap.put("list",list);
			} catch (Exception e) {
				e.printStackTrace();
				resultCode = 500;
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			}
//		} 
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String,Object> getOfficeInfoList(OrganInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<OrganInfoResultVO> list = new ArrayList<OrganInfoResultVO>();
		try {
			list = organManageDao.getOfficeInfoList(param);
			totalCount = organManageDao.getTotalOfficeInfoList(param);
			resultMap.put("totalCount", totalCount);
			resultMap.put("pageNum", param.getPageNum());
			resultMap.put("pageCount", param.getPageCount());
			resultMap.put("list",list);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "클라이언트 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> getUsersByOrgan(UserInfoSearchVO param) {
		// TODO Auto-generated method stub
				Map<String,Object> resultMap = new HashMap<String,Object>();
				int resultCode = 200;
				String resultMsg = "";
				int totalCount = 0;
				List<UserInfoVO> list = new ArrayList<UserInfoVO>();
				try {
					list = organManageDao.getUsersByOrgan(param);
					totalCount = organManageDao.getTotalUsersByOrganList(param);
					resultMap.put("totalCount", totalCount);
					resultMap.put("pageNum", param.getPageNum());
					resultMap.put("pageCount", param.getPageCount());
					resultMap.put("list",list);
					
		     
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultCode = 500;
					resultMsg = "부서원 조회 중 오류가 발생했습니다.";
				}
				resultMap.put("result",resultCode);
				resultMap.put("resultMsg",resultMsg);
				return resultMap;
	}

	@Override
	public Map<String, Object> getUserOfficeList(UserInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = organManageDao.getUserOfficeList(param);		
			resultMap.put("list",list);
		 
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "관리지점 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> getAllClientList(UserInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = organManageDao.getAllClientList(param);		
			resultMap.put("list",list);
		 
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "관리지점 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
}
