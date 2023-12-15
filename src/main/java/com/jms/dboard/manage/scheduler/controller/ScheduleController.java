package com.jms.dboard.manage.scheduler.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jms.dboard.manage.scheduler.service.SchedulerManageService;


/**
 * 스케쥴 잡 관련 컨트롤러
 *
 * @author shmoon
 */
@Controller
public class ScheduleController {
	
	@Autowired
	SchedulerManageService schedulerManageService;
	/**
	 * 부서 및 사용자 동기화
	 * @return 
	 */
//	@Scheduled(cron = "0 0/2 * * * *")
//	@Scheduled(cron = "0 0 2 * * ?")
	@RequestMapping(value = "/cms/organ/sync", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> syncNowonInfo()
	{
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = schedulerManageService.syncNowonInfo();
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // syncNowonInfo()
	
	@Scheduled(cron = "0 0 1 * * ?")
//	@Scheduled(cron = "0 * * * * *")	
	public void deleteExpiredContents()
	{
		try {
			Date today = new Date();
			SimpleDateFormat format; 
			format = new SimpleDateFormat("yyyyMMddHHmmss"); 
			System.out.println("["+format.format(today)+"] START 파일 정리 DATA ");
			schedulerManageService.deleteExpiredContents(format.format(today));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // deleteExpiredContents()
	/*
	 * 일정 시간 클라이언트 상태값이 업데이트 되지 않으면 작동 중지로 변경
	 * */
//	@Scheduled(cron = "0 0/3 * * * *")

	public void updateClientMntrStatus()
	{
		try {
			Date today = new Date();
			SimpleDateFormat format; 
			format = new SimpleDateFormat("yyyyMMddHHmmss"); 
			schedulerManageService.updateClientMntrStatus();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	} // deleteExpiredContents()

	/*
	 * 다운로드 받을 페이지와 리소스 압축파일 생성
	 *
	 */
//	@Scheduled(cron = "0 0/3 * * * *")

	public void createCompressedFiles()
	{
		try {
			//schedulerManageService.createCompressedFiles();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	} // deleteExpiredContents()
}
