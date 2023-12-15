package com.jms.dboard.manage.announce.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.utils.FileExtFilter;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.core.PDFUtils;
import com.jms.dboard.manage.announce.dao.AnnounceBoardDao;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.vo.AnnounceBoardDetailsVO;
import com.jms.dboard.manage.vo.AnnounceBoardResultVO;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.UserInfoVO;


@Service("announceBoardService")
public class AnnounceBoardServiceImpl implements AnnounceBoardService{

	@Autowired
	AnnounceBoardDao announceBoardDao;
	
	@Autowired
	ContentManageDao contentManageDao;
	
	@Autowired
	CommonCodeDao commonCodeDao;
	
	@Autowired
	LogManageService logManageService;
	
	@Override
	public Map<String,Object> getAnnounceBoardList(AnnounceBoardVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<AnnounceBoardResultVO> list = new ArrayList<AnnounceBoardResultVO>();
		try {
//			int startRowNum = (param.getPageNum() - 1) * param.getPageCount();
//			param.setStartRowNum(startRowNum);
			if(param.getUserId() != null) {
				param.setBoardType("001");
				list = announceBoardDao.getAnnounceBoardList(param);
				totalCount = announceBoardDao.getTotalAnnounceBoardList(param);
			}
			resultMap.put("totalCount", totalCount);
			resultMap.put("pageNum", param.getPageNum());
			resultMap.put("pageCount", param.getPageCount());
			resultMap.put("list",list);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "공고문 조회 중 오류가 발생했습니다.";
		}	
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public JSONObject saveAnnounceBoardInfo(AnnounceBoardVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 저장되었습니다.";
		try {
			ServletRequestAttributes servletRequestAttribute =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
			
			UserInfoVO userInfo = (UserInfoVO) httpSession.getAttribute("sessionUserInfo");
			/*공고문 정보 저장 */
			String userOrganId = userInfo.getOrganId();
			String currentDate =CommonUtils.convertDateToStr("yyyyMMdd");
			if(param.getFile() != null) {
				if(FileExtFilter.fileExtIsReturnBoolean(param.getFile(),"PDF")) {
					param.setOriginFile(param.getFile().getOriginalFilename());
					param.setContentPath("/resources/announce/"+currentDate);
				} else {
					System.out.println("PDF 파일이 아님");
					throw new FileFormatException();
				}
				
			}
			resultCount = announceBoardDao.saveAnnounceBoardInfo(param);
			int contentId =param.getContentId();
			
			List<AnnounceBoardDetailsVO> imageList = null;
			
			if (param.getFile() != null) {				
				String newFileName = CommonUtils.padLeftZeros(contentId+"",10)+"."+param.getFile().getOriginalFilename().substring(param.getFile().getOriginalFilename().lastIndexOf(".")+1, param.getFile().getOriginalFilename().length());
				param.setContentName(newFileName);
				/* 파일 저장 */
				CommonCodeVO codeVo = new CommonCodeVO();
				codeVo.setCodeName("ANN_PATH"); 
				codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
				imageList = new ArrayList<AnnounceBoardDetailsVO>();

				String destinationDir = codeVo.getCodeInfo()+File.separator+currentDate;
				/*파일 저장 */				
				File newCreateFile = CommonUtils.uploadFile(param.getFile(),destinationDir,newFileName,CommonUtils.padLeftZeros(contentId+"",10));			
				imageList = PDFUtils.pdf2Image(newCreateFile, destinationDir,contentId);
				resultCount = announceBoardDao.saveAnnounceBoardImage(imageList);
				/**
				 * TTS 정보 저장
				 * */
				resultCount = announceBoardDao.saveTtsInfo(imageList);
				resultCount = announceBoardDao.updateAnnounceBoardInfo(param);
			}
			
			/** 게시위치 정보가 있을 경우 저장 */
//			System.out.println("getLocation :"+param.getLocation().size());
			if(param.getClient() != null && param.getClient().size() > 0) {
				Map<String,Object> locationInfo = new HashMap<String,Object>();
				locationInfo.put("contentId", contentId);
				locationInfo.put("client", param.getClient());
				locationInfo.put("organId", userOrganId);
				locationInfo.put("boardType", "001");
				contentManageDao.saveContClientsPromo(locationInfo);
			}
			/*
			if(param.getLocation() != null && param.getLocation().size() > 0) {
				Map<String,Object> locationInfo = new HashMap<String,Object>();
				locationInfo.put("contentId", contentId);
				for(int i = 0; i<param.getLocation().size();i++) {
				System.out.println("게시위치 : "+param.getLocation().get(i));
				}
				locationInfo.put("location", param.getLocation());
				locationInfo.put("boardType", "001");
				
				contentManageDao.saveContClients(locationInfo);
			}
			*/
			resultJson.put("contentPath","/resources/announce/"+currentDate);
			resultJson.put("contents",imageList);
			/*
			 * 사용자 작업 로그
			 * */
			logManageService.saveLogInfo("INFO","I_MNG_AN_0001","공시공고 등록["+contentId+"]");
		} catch(FileFormatException e) {
			resultCode = 500;
			resultMsg = "공고문은 PDF파일만 지원합니다.";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "공고문 저장 중  오류가 발생했습니다.";
			
		} 
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		return resultJson;
	}

	@Override
	public int saveAnnounceBoardImage(List<AnnounceBoardDetailsVO> param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		try {
			resultCount = announceBoardDao.saveAnnounceBoardImage(param);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultCount;
	}

	@Override
	public JSONObject updateAnnounceBoardInfo(AnnounceBoardVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 수정되었습니다.";
		try {
			int contentId = param.getContentId();
			AnnounceBoardResultVO contentInfo = announceBoardDao.getAnnounceBoardInfo(param);
			resultJson.put("contentPath", contentInfo.getContentPath());
			resultJson.put("contents",contentInfo.getContents());			

			if(param.getFile() != null) {
				
				if(!FileExtFilter.fileExtIsReturnBoolean(param.getFile(),"PDF")) {
					System.out.println("PDF 파일이 아님");
					throw new FileFormatException();
				}
				
				/* 이전 파일 삭제 */
				CommonCodeVO codeVo = new CommonCodeVO();
				codeVo.setCodeName("ANN_PATH"); 
				codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
				String[] resourcePath = contentInfo.getContentPath().split("/");
				String destinationDir = codeVo.getCodeInfo()+"/"+resourcePath[resourcePath.length - 1];		
				
				param.setOriginFile(param.getFile().getOriginalFilename());
//				param.setContentPath(contentInfo.get);
				
				
				/* 파일 저장 */
				String newFileName = CommonUtils.padLeftZeros(contentId+"",10)+"."+param.getFile().getOriginalFilename().substring(param.getFile().getOriginalFilename().lastIndexOf(".")+1, param.getFile().getOriginalFilename().length());
				File newCreateFile = CommonUtils.uploadFile(param.getFile(),destinationDir,newFileName,CommonUtils.padLeftZeros(contentId+"",10));
				param.setContentName(newFileName);
//				codeVo.setCodeName("ANN_PATH"); 
//				codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
//				String destinationDir = codeVo.getCodeInfo()+File.separator+currentDate;
				/* 폴더 경로가 없으면 생성 */
				
//				CommonUtils.createFolder(destinationDir);
				
//				File oldFile = new File(destinationDir+File.separator+contentInfo.getContentName());
//				if(oldFile.exists()) {
//					oldFile.delete();
//				}

//				File newCreateFile = new File(destinationDir+File.separator+newFileName);
//				byte[] data = param.getFile().getBytes();
//				FileOutputStream fos = new FileOutputStream(destinationDir+File.separator+newFileName);
//				fos.write(data);
//				fos.close();

//				param.getFile().transferTo(newCreateFile);
				List<AnnounceBoardDetailsVO> imageList = new ArrayList<AnnounceBoardDetailsVO>();
				if(newCreateFile != null) {
					imageList = PDFUtils.pdf2Image(newCreateFile, destinationDir,contentId);
				/*컨텐츠가 있을 경우 기존 변환된 이미지 정보 및 TTS 정보  삭제*/
					announceBoardDao.deleteAnnounceBoardImage(param);
					announceBoardDao.deleteTtsInfo(param);
					/**
					 * 생성된 이미지 정보 저장
					 * */
					resultCount = announceBoardDao.saveAnnounceBoardImage(imageList);
					/**
					 * TTS 정보 저장
					 * */					
					resultCount = announceBoardDao.saveTtsInfo(imageList);
				}
				
				resultJson.put("contents",imageList);
			} else {
				resultJson.put("contentPath",contentInfo.getContentPath()); 
				resultJson.put("contents",contentInfo.getContents());
			}
			resultCount = announceBoardDao.updateAnnounceBoardInfo(param);
			/** 게시위치 삭제 */
			Map<String,Object> locationInfo = new HashMap<String,Object>();
			locationInfo.put("contentId", contentId);
			locationInfo.put("client", param.getClient());
			locationInfo.put("boardType", "001");			
			
			contentManageDao.deleteContClients(locationInfo);
			/** 게시위치 정보가 있을 경우 저장 */
			if(param.getClient() != null && param.getClient().size() > 0) {
				/**로케이션 값 중복 처리 임시 로직 */
				try {
					contentManageDao.saveContClientsPromo(locationInfo);
				} catch(Exception e) {
					
				}
			}
			/*
			 * 사용자 작업 로그
			 * */
			logManageService.saveLogInfo("INFO","I_MNG_AN_0002","공시공고 수정["+contentId+"]");
		} catch(FileFormatException e) {
			resultCode = 500;
			resultMsg = "공고문은 PDF파일만 지원합니다.";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "공고문 수정 중  오류가 발생했습니다.";
		}
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		return resultJson;
	}

	@Override
	public Map<String,Object> deleteAnnounceBoardInfo(List<Integer> params) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 삭제 되었습니다.";
		try {
			announceBoardDao.deleteAnnounceBoardImage(params);
			announceBoardDao.deleteTtsInfo(params);
			int resultCount = announceBoardDao.deleteAnnounceBoardInfo(params);
			
			/*
			 * 사용자 작업 로그
			 * */
			logManageService.saveLogInfo("INFO","I_MNG_AN_0003","공시공고 삭제 "+params);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "공고문 삭제 중  오류가 발생했습니다.";
		}
		
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		
		return resultMap;
	}

	@Override
	public int deleteAnnounceBoardImage(AnnounceBoardVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		try {
			resultCount = announceBoardDao.deleteAnnounceBoardImage(param);
			/*
			 * 사용자 작업 로그
			 * */
			logManageService.saveLogInfo("INFO","I_MNG_AN_0004","공시공고 삭제 ["+param.getContentId()+"]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultCount;
	}

	@Override
	public Map<String, Object> getAnnounceBoardInfo(int contentId) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		AnnounceBoardResultVO resultData = new AnnounceBoardResultVO();
		try {
			System.out.println("contentId :"+contentId);
			AnnounceBoardVO param = new AnnounceBoardVO();
			param.setContentId(contentId);
			resultData = announceBoardDao.getAnnounceBoardInfo(param);
			System.out.println(resultData);
			resultMap.put("data",resultData);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "공고문 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	

}
