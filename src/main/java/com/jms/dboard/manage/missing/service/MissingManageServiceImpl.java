package com.jms.dboard.manage.missing.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.missing.dao.MissingManageDao;
import com.jms.dboard.manage.vo.MissingPeopleResInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleInfoVO;
import com.jms.dboard.manage.vo.MissingPeopleManagerVO;
import com.jms.dboard.manage.vo.MissingPeopleReqInfoVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;


@Service("missingManageService")
public class MissingManageServiceImpl implements MissingManageService{

	@Autowired
	MissingManageDao missingManageDao;
	
	@Autowired
	ContentManageDao contentManageDao;
	
	@Autowired
	CommonCodeDao commonCodeDao;
	@Override
	public Map<String,Object> getMissingClientList( MissingPeopleReqInfoVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<MissingPeopleResInfoVO> list = new ArrayList<MissingPeopleResInfoVO>();
		try {
//			int startRowNum = (param.getPageNum() - 1) * param.getPageCount();
//			param.setStartRowNum(startRowNum);
//			System.out.println(param.getSearchContentType().get(0));
			Date date = Calendar.getInstance().getTime();  
		     DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");  
		     String strDate = dateFormat.format(date);  
		     param.setCurDate(strDate);
			list = missingManageDao.getMissingClientList(param);
			totalCount = list.size();
			resultMap.put("totalCount", totalCount);
			resultMap.put("list",list);
			
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	 
     @Override
 	public Map<String,Object> getMissingManagerList( MissingPeopleManagerVO param) {
 		// TODO Auto-generated method stub
 		Map<String,Object> resultMap = new HashMap<String,Object>();
 		int resultCode = 200;
 		String resultMsg = "";
 		int totalCount = 0;
 		List<MissingPeopleResInfoVO> list = new ArrayList<MissingPeopleResInfoVO>();
 		try {
// 			int startRowNum = (param.getPageNum() - 1) * param.getPageCount();
// 			param.setStartRowNum(startRowNum);
// 			System.out.println(param.getSearchContentType().get(0));

 			list = missingManageDao.getMissingManagerList(param);
 			totalCount = missingManageDao.getTotalMissingManagerList(param);
			resultMap.put("totalCount", totalCount);
			resultMap.put("pageNum", param.getPageNum());
			resultMap.put("pageCount", param.getPageCount());
		resultMap.put("list",list);
		
 			resultMap.put("totalCount", totalCount);
 			resultMap.put("list",list);
 			
      
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			resultCode = 500;
 			resultMsg = "조회 중 오류가 발생했습니다.";
 		}
 		resultMap.put("result",resultCode);
 		resultMap.put("resultMsg",resultMsg);
 		return resultMap;
 	}
	@Override
	public Map<String, Object> saveMissingInfo(MissingPeopleReqInfoVO param) {
		// TODO Auto-generated method stub
		int missingId = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 저장되었습니다.";
		
		try {
			
			/* 실종 인물 정보 저장 */
			missingManageDao.saveMissingInfo(param);
			missingId = param.getId();
			if(param.getFile() != null) {
				String newFileName = CommonUtils.padLeftZeros(missingId+"",10)+"."+param.getFile().getOriginalFilename().substring(param.getFile().getOriginalFilename().lastIndexOf(".")+1, param.getFile().getOriginalFilename().length());
				String currentDate =CommonUtils.convertDateToStr("yyyyMMdd");
				param.setOriginFile(param.getFile().getOriginalFilename());
				param.setContentPath("/resources/missing/"+currentDate);
				param.setPhotoName(newFileName);
//				param.setOriginFile(param.getFile().getOriginalFilename());

				/* 파일 저장 */
				CommonCodeVO codeVo = new CommonCodeVO();
				codeVo.setCodeName("MISSING_PATH"); 
				codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
				String destinationDir = codeVo.getCodeInfo()+File.separator+currentDate;
				/*파일 저장 */				
				File newCreateFile = CommonUtils.uploadFile(param.getFile(),destinationDir,newFileName,CommonUtils.padLeftZeros(missingId+"",10));			
				
				resultMap.put("contentPath","/resources/missing/"+currentDate+"/"+newFileName);
			} 

			missingManageDao.updateMissingInfo(param);
			if(param.getConnect() != null && !param.getConnect().equals("")) {
				List<Map<String, Object>> paramMap = 
						   new ObjectMapper().readValue(param.getConnect(), new TypeReference<List<Map<String, Object>>>(){});
				param.setConnects(paramMap);
				missingManageDao.saveMissingConnectInfo(param);
			}
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "저장 중  오류가 발생했습니다.";
			
		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> updateMissingInfo(MissingPeopleReqInfoVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 수정되었습니다.";
		try {
			System.out.println("ID ===================>"+param.getId());
			int missingId = param.getId();			
			
			if(param.getFile() != null) {
				/* 이전 파일 삭제 */
				CommonCodeVO codeVo = new CommonCodeVO();
				codeVo.setCodeName("MISSING_PATH"); 
				codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
				MissingPeopleInfoVO contentInfo = missingManageDao.getMissingInfo(param.getId());
				String destinationDir = "";
				
				/* 파일 저장 */
				String newFileName = CommonUtils.padLeftZeros(missingId+"",10)+"."+param.getFile().getOriginalFilename().substring(param.getFile().getOriginalFilename().lastIndexOf(".")+1, param.getFile().getOriginalFilename().length());
				
				
				if(contentInfo.getContentPath() != null) {
					String[] resourcePath = contentInfo.getContentPath().split("/");
					destinationDir = codeVo.getCodeInfo()+"/"+resourcePath[resourcePath.length - 1];
					resultMap.put("contentPath", contentInfo.getContentPath()+"/"+contentInfo.getPhotoName());		
					param.setContentPath(contentInfo.getContentPath());
				 } else {
					 String currentDate =CommonUtils.convertDateToStr("yyyyMMdd");
					 param.setContentPath("/resources/missing/"+currentDate);
					 destinationDir = codeVo.getCodeInfo()+File.separator+currentDate;
					 resultMap.put("contentPath","/resources/missing/"+currentDate+"/"+newFileName);
				 }
				param.setOriginFile(param.getFile().getOriginalFilename());
				param.setPhotoName(newFileName);
				File newCreateFile = CommonUtils.uploadFile(param.getFile(),destinationDir,newFileName,CommonUtils.padLeftZeros(missingId+"",10));

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
			
			resultCount = missingManageDao.updateMissingInfo(param);
			
			if(param.getConnect() != null && !param.getConnect().equals("")) {
				List<Map<String, Object>> paramMap = 
						   new ObjectMapper().readValue(param.getConnect(), new TypeReference<List<Map<String, Object>>>(){});
				param.setConnects(paramMap);
				missingManageDao.deleteMissingConnectInfo(param);
				missingManageDao.saveMissingConnectInfo(param);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "수정 중 오류가 발생했습니다.";
		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}

	@Override
	public Map<String,Object> deleteMissingInfo(List<Integer> params) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 삭제 되었습니다.";
		try {
			missingManageDao.deleteMissingConnectInfo(params);
			int resultCount = missingManageDao.deleteMissingInfo(params);
     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "실종자 정보 삭제 중  오류가 발생했습니다.";
		}
		
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		
		return resultMap;
	}


	@Override
	public int deleteMissingInfo(MissingPeopleInfoVO param) {
		// TODO Auto-generated method stub
				int resultCount = 0;
				try {
					resultCount = missingManageDao.deleteMissingInfo(param);
		     
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return resultCount;
	}

	@Override
	public Map<String, Object> getMissingInfo(int param) {
		// TODO Auto-generated method stub
				Map<String,Object> resultMap = new HashMap<String,Object>();
				int resultCode = 200;
				String resultMsg = "";
				try {
					MissingPeopleInfoVO missingInfo = missingManageDao.getMissingInfo(param);
					resultMap.put("data",missingInfo);
					
		     
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultCode = 500;
					resultMsg = "실종자 정보 조회 중 오류가 발생했습니다.";
				}
				resultMap.put("result",resultCode);
				resultMap.put("resultMsg",resultMsg);
				return resultMap;
		
		 
	}
	

}
