package com.jms.dboard.manage.emergency.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.emergency.dao.EmergencyManageDao;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;


@Service("emergencyManageService")
public class EmergencyManageServiceImpl implements EmergencyManageService{

	@Autowired
	EmergencyManageDao emergencyManageDao;
	
	@Autowired
	ContentManageDao contentManageDao;
	
	@Autowired
	CommonCodeDao commonCodeDao;
	@Override
	public Map<String,Object> getEmergencyList(PromoBoardVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<PromoBoardResultVO> list = new ArrayList<PromoBoardResultVO>();
		try {
//			int startRowNum = (param.getPageNum() - 1) * param.getPageCount();
//			param.setStartRowNum(startRowNum);
//			System.out.println(param.getSearchContentType().get(0));

			list = emergencyManageDao.getEmergencyList(param);
			totalCount = emergencyManageDao.getTotalEmergencyList(param);
			resultMap.put("totalCount", totalCount);
			resultMap.put("pageNum", param.getPageNum());
			resultMap.put("pageCount", param.getPageCount());
			resultMap.put("list",list);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "홍보물 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public JSONObject saveEmergencyInfo(PromoBoardVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 저장되었습니다.";
		
		try {
			
			/*홍보물 정보 저장 */
			String contentType = param.getContentType();
			int contentId = 0;
			resultCount = emergencyManageDao.saveEmergencyInfo(param);
			contentId =param.getContentId();
			if(param.getFile() != null) {
				String newFileName = CommonUtils.padLeftZeros(contentId+"",10)+"."+param.getFile().getOriginalFilename().substring(param.getFile().getOriginalFilename().lastIndexOf(".")+1, param.getFile().getOriginalFilename().length());
				String currentDate =CommonUtils.convertDateToStr("yyyyMMdd");
				param.setOriginFile(param.getFile().getOriginalFilename());
				param.setContentPath("/resources/promo/"+currentDate);
				param.setContentName(newFileName);
				param.setOriginFile(param.getFile().getOriginalFilename());
				
				
				System.out.println("contentId:"+contentId);
				/* 파일 저장 */
				CommonCodeVO codeVo = new CommonCodeVO();
				codeVo.setCodeName("PRO_PATH"); 
				codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
				String destinationDir = codeVo.getCodeInfo()+File.separator+currentDate;
				/*파일 저장 */				
				File newCreateFile = CommonUtils.uploadFile(param.getFile(),destinationDir,newFileName,CommonUtils.padLeftZeros(contentId+"",10));			
				BufferedImage bi = ImageIO.read(newCreateFile.getAbsoluteFile());
				param.setLayoutType((bi.getWidth() >= bi.getHeight())?0:1);
				bi.flush();
				
				resultJson.put("contentPath","/resources/promo/"+currentDate+"/"+newFileName);
			} 
			/** 게시위치 정보가 있을 경우 저장 */
//			System.out.println("getLocation :"+param.getLocation().size());
			if(param.getClient() != null && param.getClient().size() > 0) {
				Map<String,Object> locationInfo = new HashMap<String,Object>();
				locationInfo.put("contentId", contentId);
				locationInfo.put("client", param.getClient());
				locationInfo.put("boardType", "002");
				contentManageDao.saveContClientsPromo(locationInfo);
			}
			emergencyManageDao.updateEmergencyInfo(param);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "홍보물 저장 중  오류가 발생했습니다.";
			
		}
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		return resultJson;
	}

	@Override
	public JSONObject updateEmergencyInfo(PromoBoardVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 수정되었습니다.";
		try {
			int contentId = param.getContentId();
			
			
			
			if(param.getFile() != null) {
				/* 이전 파일 삭제 */
				CommonCodeVO codeVo = new CommonCodeVO();
				codeVo.setCodeName("PRO_PATH"); 
				codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
				PromoBoardResultVO contentInfo = emergencyManageDao.getEmergencyInfo(param);
				String destinationDir = "";
				
				/* 파일 저장 */
				String newFileName = CommonUtils.padLeftZeros(contentId+"",10)+"."+param.getFile().getOriginalFilename().substring(param.getFile().getOriginalFilename().lastIndexOf(".")+1, param.getFile().getOriginalFilename().length());
				
				
				if(contentInfo.getContentPath() != null) {
					String[] resourcePath = contentInfo.getContentPath().split("/");
					destinationDir = codeVo.getCodeInfo()+"/"+resourcePath[resourcePath.length - 1];
					resultJson.put("contentPath", contentInfo.getContentPath()+"/"+contentInfo.getContentName());
				 } else {
					 String currentDate =CommonUtils.convertDateToStr("yyyyMMdd");
					 param.setContentPath("/resources/promo/"+currentDate);
					 destinationDir = codeVo.getCodeInfo()+File.separator+currentDate;
					 resultJson.put("contentPath","/resources/promo/"+currentDate+"/"+newFileName);
				 }
				param.setOriginFile(param.getFile().getOriginalFilename());
				param.setContentName(newFileName);
				File newCreateFile = CommonUtils.uploadFile(param.getFile(),destinationDir,newFileName,CommonUtils.padLeftZeros(contentId+"",10));
				BufferedImage bi = ImageIO.read(newCreateFile.getAbsoluteFile());
				param.setLayoutType((bi.getWidth() >= bi.getHeight())?0:1);
				bi.flush();
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
			}
			/** 게시위치 삭제 */
			Map<String,Object> locationInfo = new HashMap<String,Object>();
			locationInfo.put("contentId", contentId);
			locationInfo.put("client", param.getClient());
			locationInfo.put("boardType", "002");
			contentManageDao.deleteContClients(locationInfo);
			/** 게시위치 정보가 있을 경우 저장 */
			if(param.getClient() != null && param.getClient().size() > 0) {
				/**로케이션 값 중복 처리 임시 로직 */
				try {
					contentManageDao.saveContClientsPromo(locationInfo);
				} catch(Exception e) {
					
				}
			}
			resultCount = emergencyManageDao.updateEmergencyInfo(param);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "홍보물 수정 중  오류가 발생했습니다.";
		}
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		return resultJson;
	}

	@Override
	public Map<String,Object> deleteEmergencyInfo(List<Integer> params) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 삭제 되었습니다.";
		try {
			int resultCount = emergencyManageDao.deleteEmergencyInfo(params);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "홍보물 삭제 중  오류가 발생했습니다.";
		}
		
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		
		return resultMap;
	}


	@Override
	public Map<String, Object> getEmergencyInfo(int contentId) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		PromoBoardResultVO resultData = new PromoBoardResultVO();
		try {
			System.out.println("contentId :"+contentId);
			PromoBoardVO param = new PromoBoardVO();
			param.setContentId(contentId);
			resultData = emergencyManageDao.getEmergencyInfo(param);
			System.out.println(resultData);
			resultMap.put("data",resultData);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "홍보물 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public int deleteEmergencyInfo(PromoBoardVO param) {
		// TODO Auto-generated method stub
				int resultCount = 0;
				try {
					resultCount = emergencyManageDao.deleteEmergencyInfo(param);
		     
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return resultCount;
	}
	

}
