package com.jms.dboard.manage.reserve.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.utils.FfmpegUtils;
import com.jms.dboard.common.utils.FileExtFilter;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.clients.dao.ClientsManageDao;
import com.jms.dboard.manage.code.dao.CodeManageDao;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.reserve.dao.ReserveBoardDao;
import com.jms.dboard.manage.vo.ReserveRoomInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;



@Service("reserveBoardService")
public class ReserveBoardServiceImpl implements ReserveBoardService{

	@Autowired
	ReserveBoardDao reserveBoardDao;
	
	@Autowired
	ContentManageDao contentManageDao;
	
	@Autowired
	ClientsManageDao clientsManageDao;
	
	@Autowired
	CommonCodeDao commonCodeDao;
	
	@Autowired
	LogManageService logManageService;
	
	@Autowired
	FfmpegUtils ffmpegUtils;
	
	@Autowired
	CodeManageDao codeManageDao;
	
	private static final long  MEGABYTE = 1024L * 1024L;
	
	
	@Override
	public Map<String,Object> getReserveRoomList() {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
//			int startRowNum = (param.getPageNum() - 1) * param.getPageCount();
//			param.setStartRowNum(startRowNum);
//			System.out.println(param.getSearchContentType().get(0));
			list = reserveBoardDao.getReserveRoomList();
			resultMap.put("list",list);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "회의실 예약 정보 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	
	@Override
	public Map<String,Object> getReserveBoardList(ReserveRoomInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<ReserveRoomInfoVO> list = new ArrayList<ReserveRoomInfoVO>();
		try {
//			int startRowNum = (param.getPageNum() - 1) * param.getPageCount();
//			param.setStartRowNum(startRowNum);
//			System.out.println(param.getSearchContentType().get(0));
			if(param.getUserId() != null) {
				list = reserveBoardDao.getReserveBoardList(param);
				totalCount = reserveBoardDao.getTotalReserveBoardList(param);
			}
			
			resultMap.put("totalCount", totalCount);
			resultMap.put("pageNum", param.getPageNum());
			resultMap.put("pageCount", param.getPageCount());
			resultMap.put("list",list);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "회의실 예약 정보 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public JSONObject saveReserveBoardInfo(ReserveRoomInfoVO param, MultipartFile file) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 저장되었습니다.";
		int reserveId = 0;

		try {
			ServletRequestAttributes servletRequestAttribute =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
			
			UserInfoVO userInfo = (UserInfoVO) httpSession.getAttribute("sessionUserInfo");
			if(userInfo != null) {				
				/*회의실 예약 정보 정보 저장 */				
//				System.out.println(param.getTitle());
//				System.out.println(param.getPlace());
//				System.out.println(param.getStartTime());
//				System.out.println(param.getEndTime());
//				System.out.println(param.getRoomId());
//				System.out.println(param.getOrganizer());
				int dupCount = reserveBoardDao.checkDupReserveBoardInfo(param);				
				
				if(dupCount == 0) {			
					resultCount = reserveBoardDao.saveReserveBoardInfo(param);
	
					reserveId =param.getReserveId();
					if(param.getContentType() == 1) {
						if(file != null) {			
				
							String fileType = "IMG";
							if(file.getSize() > 5242880) {				
								long orginSize = file.getSize() / MEGABYTE;
								resultCode = 500;
								resultMsg = "이미지 용량은 5Mb를 초과할 수 없습니다.(선택된 이미지 : "+orginSize+"Mb)";
								resultJson.put("result", resultCode);
								resultJson.put("resultMsg", resultMsg);
								
								return resultJson;
							}
							
							if(!FileExtFilter.fileExtIsReturnBoolean(file,fileType)) {
								System.out.println(fileType+" 파일이 아님");					
								throw new FileFormatException();
							}
							
							String newFileName = CommonUtils.padLeftZeros(reserveId+"",10)+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
							String currentDate =CommonUtils.convertDateToStr("yyyyMMdd");
							param.setOriginFileName(file.getOriginalFilename());
							param.setFileName(newFileName);
							param.setFilePath("/resources/reserve/"+currentDate);
	
							/* 파일 저장 */
							CommonCodeVO codeVo = new CommonCodeVO();
							codeVo.setCodeName("RESERVE_PATH"); 
							codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
							String destinationDir = codeVo.getCodeInfo()+File.separator+currentDate;
							/*파일 저장 */				
							File newCreateFile = CommonUtils.uploadFile(file,destinationDir,newFileName,CommonUtils.padLeftZeros(reserveId+"",10));	
							newCreateFile.setWritable(true); //쓰기가능설정
							newCreateFile.setReadable(true);	//읽기가능설정
	
							resultJson.put("contentPath","/resources/reserve/"+currentDate+"/"+newFileName);
						}
					}
					resultCount = reserveBoardDao.updateReserveBoardInfo(param);
	
					/*
					 * 사용자 작업 로그
					 * */
					logManageService.saveLogInfo("INFO","I_MNG_RR_0001","회의실 예약 정보 등록");
				} else {					
					resultCode = 500;
					resultMsg = "선택한 회의실에 중복된 예약정보가 있습니다.";
				}
			} else {
				resultCode = 440;
				resultMsg = "세션이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요.";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "회의실 예약 정보 저장 중  오류가 발생했습니다.";
			try {
				//resultCount = reserveBoardDao.deleteReserveBoardInfo(param);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		return resultJson;
	}

	@Override
	public JSONObject updateReserveBoardInfo(ReserveRoomInfoVO param, MultipartFile file) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 수정되었습니다.";

		try {
			ServletRequestAttributes servletRequestAttribute =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
			
			UserInfoVO userInfo = (UserInfoVO) httpSession.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				
				int dupCount = reserveBoardDao.checkDupReserveBoardInfo(param);				
				System.out.println("dupCount :"+dupCount);
				System.out.println("reserveId :"+param.getReserveId());
				System.out.println("roomId :"+param.getRoomId());
				System.out.println("startTime :"+param.getStartTime());
				System.out.println("endTime :"+param.getEndTime());
				if(dupCount == 0) {	
					CommonCodeVO codeVo = new CommonCodeVO();
					codeVo.setCodeName("RESERVE_PATH"); 
					codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
					ReserveRoomInfoVO reserveInfo = reserveBoardDao.getReserveBoardInfo(param.getReserveId());
					String destinationDir = "";
					if(param.getContentType() == 1) {
						
						if(file != null) {			
				
							String fileType = "IMG";
							if(file.getSize() > 5242880) {				
								long orginSize = file.getSize() / MEGABYTE;
								resultCode = 500;
								resultMsg = "이미지 용량은 5Mb를 초과할 수 없습니다.(선택된 이미지 : "+orginSize+"Mb)";
								resultJson.put("result", resultCode);
								resultJson.put("resultMsg", resultMsg);
								
								return resultJson;
							}
							
							
							
							if(!FileExtFilter.fileExtIsReturnBoolean(file,fileType)) {
								System.out.println(fileType+" 파일이 아님");					
								throw new FileFormatException();
							}
							
							String newFileName = CommonUtils.padLeftZeros(param.getReserveId()+"",10)+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
							
							if(reserveInfo.getFilePath() != null && !reserveInfo.getFilePath().equals("")) {
								String[] resourcePath = reserveInfo.getFilePath().split("/");
								destinationDir = codeVo.getCodeInfo()+"/"+resourcePath[resourcePath.length - 1];
								resultJson.put("contentPath", reserveInfo.getFilePath()+"/"+reserveInfo.getFilePath());
							 } else {
								 String currentDate =CommonUtils.convertDateToStr("yyyyMMdd");
								 param.setFilePath("/resources/reserve/"+currentDate);
								 destinationDir = codeVo.getCodeInfo()+File.separator+currentDate;
								 resultJson.put("contentPath","/resources/reserve/"+currentDate+"/"+newFileName);
							 }
							
							param.setOriginFileName(file.getOriginalFilename());
							param.setFileName(newFileName);
	//						System.out.println("destinationDir :"+destinationDir);
							/* 이전파일이 있을 경우 파일 삭제 */
							File oldFile = new File(destinationDir+File.separator+param.getFileName());
							oldFile.delete();
							/* 파일 저장 */
							File newCreateFile = CommonUtils.uploadFile(file,destinationDir,newFileName,CommonUtils.padLeftZeros(param.getReserveId()+"",10));
							
							newCreateFile.setWritable(true); //쓰기가능설정
							newCreateFile.setReadable(true);	//읽기가능설정
	
						}
					} else {
						/* 이전파일이 있을 경우 파일 삭제 */
						String[] resourcePath = reserveInfo.getFilePath().split("/");
						destinationDir = codeVo.getCodeInfo()+"/"+resourcePath[resourcePath.length - 1];
						File oldFile = new File(destinationDir+File.separator+param.getFileName());
						oldFile.delete();
					}
					resultCount = reserveBoardDao.updateReserveBoardInfo(param);
					/*
					 * 사용자 작업 로그
					 * */
					logManageService.saveLogInfo("INFO","I_MNG_RR_0002","회의실 예약 정보 수정");
				} else {					
					resultCode = 500;
					resultMsg = "선택한 회의실에 중복된 예약정보가 있습니다.";
				}
			} else {
				resultCode = 440;
				resultMsg = "세션이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요.";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "회의실 예약 정보 수정 중  오류가 발생했습니다.";
		}
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		return resultJson;
	}

	@Override
	public Map<String,Object> deleteReserveBoardInfo(List<Integer> params) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 삭제 되었습니다.";
		try {
			
			
			/*파일 삭제*/
			List<ReserveRoomInfoVO> deleteInfoList = reserveBoardDao.getReserveBoardListById(params);
			
			CommonCodeVO codeVo = new CommonCodeVO();
			codeVo.setCodeName("RESERVE_PATH"); 
			codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);

			for(int i=0; i< deleteInfoList.size();i++) {
				ReserveRoomInfoVO dataInfo = deleteInfoList.get(i);
				String[] resourcePath = dataInfo.getFilePath().split("/");
				String destinationDir = codeVo.getCodeInfo()+"/"+resourcePath[resourcePath.length - 1];
				File oldFile = new File(destinationDir+File.separator+dataInfo.getFileName());
				oldFile.delete();
			}
			int resultCount = reserveBoardDao.deleteReserveBoardInfo(params);
			/*
			 * 사용자 작업 로그
			 * */
			logManageService.saveLogInfo("INFO","I_MNG_RR_0003","회의실 예약 정보 삭제 ["+StringUtils.join(params, ",")+"]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "회의실 예약 정보 삭제 중  오류가 발생했습니다.";
		}
		
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		
		return resultMap;
	}


	@Override
	public Map<String, Object> getReserveBoardInfo(int reserveId) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		ReserveRoomInfoVO resultData = new ReserveRoomInfoVO();
		try {
			resultData = reserveBoardDao.getReserveBoardInfo(reserveId);
			resultMap.put("data",resultData);			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "회의실 예약 정보 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public int deleteReserveBoardInfo(ReserveRoomInfoVO param) {
		// TODO Auto-generated method stub
				int resultCount = 0;
				try {
					resultCount = reserveBoardDao.deleteReserveBoardInfo(param);
					/*
					 * 사용자 작업 로그
					 * */
					logManageService.saveLogInfo("INFO","I_MNG_RR_0004","회의실 예약 정보 삭제 ["+param.getReserveId()+"]");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return resultCount;
	}
	

}
