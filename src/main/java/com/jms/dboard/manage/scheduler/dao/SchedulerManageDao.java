package com.jms.dboard.manage.scheduler.dao;

import java.util.List;

import java.util.Map;

import com.jms.dboard.manage.vo.OrganInfo2VO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.WeatherInfoVO;

public interface SchedulerManageDao {
	/** 기관 리스트 검색   */
	public List<OrganInfo2VO> getNowonDeptList() throws Exception;
	
	/** 사용자 리스트 검색   */
	public List<UserInfoVO> getNowonUserList() throws Exception;
	
	public int deleteNowonDeptInfo() throws Exception;
	public int deleteNowonUserInfo() throws Exception;
	
	public int insertNowonDeptInfo(List<OrganInfo2VO> params) throws Exception;
	public int insertNowonUserInfo(List<UserInfoVO> params) throws Exception;
	
	public List<Map<String,Object>> getExpiredContents(String curDate) throws Exception;
	public int updateClientMntrStatus() throws Exception;
	public int deleteWeatherData() throws Exception;
	public int saveWeatherData(WeatherInfoVO param) throws Exception;
	
	public WeatherInfoVO getClientWeatherInfo() throws Exception;
	
}
