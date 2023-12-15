package com.jms.dboard.manage.promo.service;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.utils.FfmpegUtils;
import com.jms.dboard.common.utils.FileExtFilter;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.core.socket.SocketIOController;
import com.jms.dboard.manage.clients.dao.ClientsManageDao;
import com.jms.dboard.manage.clients.service.ClientsManageService;
import com.jms.dboard.manage.code.dao.CodeManageDao;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.log.service.LogManageService;
import com.jms.dboard.manage.promo.dao.PromoBoardDao;
import com.jms.dboard.manage.vo.ClientsInfoResultVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;
import com.jms.dboard.manage.vo.UserInfoVO;

import net.bramp.ffmpeg.FFmpegUtils;


@Service("promoBoardService")
public class PromoBoardServiceImpl implements PromoBoardService{

	@Autowired
	PromoBoardDao promoBoardDao;
	
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
	public Map<String,Object> getPromoBoardList(PromoBoardVO param) {
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
			if(param.getUserId() != null) {
				list = promoBoardDao.getPromoBoardList(param);
				totalCount = promoBoardDao.getTotalPromoBoardList(param);
			}
			
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
	public JSONObject savePromoBoardInfo(PromoBoardVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 저장되었습니다.";
		String fileType = "MOV";
		String boardType = "002";
		try {
			ServletRequestAttributes servletRequestAttribute =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
			
			UserInfoVO userInfo = (UserInfoVO) httpSession.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				String userOrganId = userInfo.getOrganId();
				/*홍보물 정보 저장 */
				if(param.getContentType().equals("007")) {
					boardType = "008";
					param.setBoardType(boardType);
				}
				int contentId = 0;
				resultCount = promoBoardDao.savePromoBoardInfo(param);
				contentId =param.getContentId();
				if(param.getFile() != null) {				
					if(param.getContentType().equals("001") || param.getContentType().equals("007")) {				
						fileType = "IMG";
						if(param.getFile().getSize() > 5242880) {				
							long orginSize = param.getFile().getSize() / MEGABYTE;
							resultCode = 500;
							resultMsg = "이미지 용량은 5Mb를 초과할 수 없습니다.(선택된 이미지 : "+orginSize+"Mb)";
							resultJson.put("result", resultCode);
							resultJson.put("resultMsg", resultMsg);
							
							return resultJson;
						}
					} 
					
					if(!FileExtFilter.fileExtIsReturnBoolean(param.getFile(),fileType)) {
						System.out.println(fileType+" 파일이 아님");					
						throw new FileFormatException();
					}
					
					String newFileName = CommonUtils.padLeftZeros(contentId+"",10)+"."+param.getFile().getOriginalFilename().substring(param.getFile().getOriginalFilename().lastIndexOf(".")+1, param.getFile().getOriginalFilename().length());
					String currentDate =CommonUtils.convertDateToStr("yyyyMMdd");
					param.setOriginFile(param.getFile().getOriginalFilename());
					param.setContentPath("/resources/promo/"+currentDate);
					param.setContentName(newFileName);
					param.setOriginFile(param.getFile().getOriginalFilename());
					/* 파일 저장 */
					CommonCodeVO codeVo = new CommonCodeVO();
					codeVo.setCodeName("PRO_PATH"); 
					codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
					String destinationDir = codeVo.getCodeInfo()+File.separator+currentDate;
					/*파일 저장 */				
					File newCreateFile = CommonUtils.uploadFile(param.getFile(),destinationDir,newFileName,CommonUtils.padLeftZeros(contentId+"",10));		
					int layoutType = 1;
					
					if(param.getContentType().equals("001") || param.getContentType().equals("007")){
						
						BufferedImage bi = null;
						try {
							bi = ImageIO.read(newCreateFile.getAbsoluteFile());
							layoutType = (bi.getWidth() >= bi.getHeight())?0:1;
						} catch(Exception e) {
							Iterator readers = ImageIO.getImageReadersByFormatName("JPEG");
						    ImageReader reader = null;
						    while(readers.hasNext()) {
						        reader = (ImageReader)readers.next();
						        if(reader.canReadRaster()) {
						            break;
						        }
						    }
	
						    //Stream the image file (the original CMYK image)
						    ImageInputStream input =   ImageIO.createImageInputStream(newCreateFile); 
						    reader.setInput(input); 
	
						    //Read the image raster
						    Raster raster = reader.readRaster(0, null); 
	
						    //Create a new RGB image
						    bi = new BufferedImage(raster.getWidth(), raster.getHeight(), 
						    BufferedImage.TYPE_4BYTE_ABGR); 
	
						    //Fill the new image with the old raster
						    bi.getRaster().setRect(raster);		
						    layoutType = 1;//(bi.getWidth() >= bi.getHeight())?0:1;
							
							if(bi != null)bi.flush();
							if(input != null)input.close();
							if(reader != null) reader.dispose();
							raster = null;
						} finally {				
							
							param.setLayoutType(layoutType);
						}					
					} else {
						//동영상
						// 풀사이즈로 변경 체크 했을 경우
						if(param.getVideoScale().equals("Y")) {
							Map<String,Object> configParam = new HashMap<String,Object>();
							configParam.put("configType", "VIDEO");					
							Map<String,Object> videoConfig = codeManageDao.getDboardConfig(configParam);
							String scaleSize = "";
							ffmpegUtils.init();
							Map<String,Object> videoInfo = ffmpegUtils.getVideoResolution(newCreateFile.getAbsolutePath());
							layoutType = ((Integer)videoInfo.get("width") >= (Integer) videoInfo.get("height"))?0:1;	
							
							if(videoConfig != null) {
								scaleSize = (String) videoConfig.get("configValue");
	
								if(layoutType == 1) {													
									System.out.println("세로 영상 Scaling 시작");
									String tmpFilePath = ffmpegUtils.convertVideo(newCreateFile,CommonUtils.padLeftZeros(contentId+"",10)+"_tmp.mp4", scaleSize);
									File scaledFile = new File(tmpFilePath);
									newCreateFile.delete();
									scaledFile.renameTo(newCreateFile);
//									newCreateFile.delete();
//									scaledFile.delete();
								}
							}
						}
					}
					param.setLayoutType(layoutType);
					resultJson.put("contentPath","/resources/promo/"+currentDate+"/"+newFileName);
				} 
				/** 게시위치 정보가 있을 경우 저장 */
	//			System.out.println("getLocation :"+param.getLocation().size());
				if(param.getClient() != null && param.getClient().size() > 0) {
					Map<String,Object> locationInfo = new HashMap<String,Object>();
					locationInfo.put("contentId", contentId);
					locationInfo.put("client", param.getClient());
					locationInfo.put("organId", userOrganId);
					locationInfo.put("boardType", boardType);
					contentManageDao.saveContClientsPromo(locationInfo);
				}
				promoBoardDao.updatePromoBoardInfo(param);
				
				/**긴급 컨텐츠 일 경우 클라이언트로 화면 갱신 명령 전달 */
				if(param.getUrgentYn() != null && param.getUrgentYn().equals("Y")) {
					Long currentDate =Long.parseLong(CommonUtils.convertDateToStr("yyyyMMddHHmmss"));
					if(currentDate >= Long.parseLong(param.getPostingStart()) && currentDate <= Long.parseLong(param.getPostingEnd())) {
						Map<String,Object> paramData = new HashMap<String,Object>();
						paramData.put("contentId", contentId);
						paramData.put("userId", param.getPublisher());
						paramData.put("roleType", userInfo.getRoleType());
						
						List<Integer> clientList = promoBoardDao.getClientsByContent(paramData);
						paramData.clear();
						for(int i = 0 ;i<clientList.size();i++) {
							paramData.put("action", "urgent");
							paramData.put("clientId", clientList.get(i));
							boolean result = SocketIOController.sendMessageToClient(paramData);
						}
					} else {
						System.out.println("현재 개시 시간이 아니기 때문에 화면 갱신 없음");
					}
					
				}
				/*
				 * 사용자 작업 로그
				 * */
				logManageService.saveLogInfo("INFO","I_MNG_PR_0001","홍보물 등록 ["+contentId+"]");
			} else {
				resultJson.put("result", 440);
				resultJson.put("resultMsg", "세션이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요.");
			}
			
		} catch(FileFormatException e) {
			resultCode = 500;	
			if(fileType.equals("IMG")) {
				resultMsg = "이미지는 JPG,PNG,GIF 파일만 지원합니다.";
			} else {
				resultMsg = "동영상은 MP4 파일만 지원합니다.";
			}		
			try {
				resultCount = promoBoardDao.deletePromoBoardInfo(param);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "홍보물 저장 중  오류가 발생했습니다.";
			try {
				resultCount = promoBoardDao.deletePromoBoardInfo(param);
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
	public JSONObject updatePromoBoardInfo(PromoBoardVO param) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		JSONObject resultJson = new JSONObject();
		int resultCode = 200;
		String resultMsg = "정상적으로 수정되었습니다.";
		String fileType = "MOV";
		String boardType = "002";
		try {
			ServletRequestAttributes servletRequestAttribute =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
			
			UserInfoVO userInfo = (UserInfoVO) httpSession.getAttribute("sessionUserInfo");
			if(userInfo != null) {
				int contentId = param.getContentId();
				
				if(param.getContentType().equals("007")) {
					boardType = "008";
					param.setBoardType(boardType);
				}
				
				CommonCodeVO codeVo = new CommonCodeVO();
				codeVo.setCodeName("PRO_PATH"); 
				codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
				PromoBoardResultVO contentInfo = promoBoardDao.getPromoBoardInfo(param);
				System.out.println("getBoardType :"+param.getBoardType());
				
				if(param.getFile() != null) {
					if(param.getContentType().equals("001") || param.getContentType().equals("007")) {
						fileType = "IMG";
						if(param.getFile().getSize() > 5242880) {	//5M		
							long orginSize = param.getFile().getSize() / MEGABYTE;
							resultCode = 500;
							resultMsg = "이미지 용량은 5Mb를 초과할 수 없습니다.(선택된 이미지 : "+orginSize+"Mb)";
							resultJson.put("result", resultCode);
							resultJson.put("resultMsg", resultMsg);
							
							return resultJson;
						}					
					} 
					if(!FileExtFilter.fileExtIsReturnBoolean(param.getFile(),fileType)) {
						System.out.println(fileType+" 파일이 아님");					
						throw new FileFormatException();
					}
					/* 이전 파일 삭제 */				
					String destinationDir = "";
					
					/* 파일 저장 */
					String newFileName = CommonUtils.padLeftZeros(contentId+"",10)+"."+param.getFile().getOriginalFilename().substring(param.getFile().getOriginalFilename().lastIndexOf(".")+1, param.getFile().getOriginalFilename().length());
					//String uploadFileName = CommonUtils.padLeftZeros(contentId+"",10)+"_origin."+param.getFile().getOriginalFilename().substring(param.getFile().getOriginalFilename().lastIndexOf(".")+1, param.getFile().getOriginalFilename().length());
					
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
//					System.out.println("destinationDir :"+destinationDir);
					File oldFile = new File(destinationDir+File.separator+param.getContentName());
					oldFile.delete();
					File newCreateFile = CommonUtils.uploadFile(param.getFile(),destinationDir,newFileName,CommonUtils.padLeftZeros(contentId+"",10));
					int layoutType = 1;
	//				param.setLayoutType(layoutType);
					if(param.getContentType().equals("001") || param.getContentType().equals("007")) {
						BufferedImage bi = null;
						try {
							bi = ImageIO.read(newCreateFile.getAbsoluteFile());
							layoutType = (bi.getWidth() >= bi.getHeight())?0:1;
						} catch(Exception e) {
							System.out.println(param.getFile().getOriginalFilename()+" -  "+e.getMessage());
							
							Iterator readers = ImageIO.getImageReadersByFormatName("JPEG");
						    ImageReader reader = null;
						    while(readers.hasNext()) {
						        reader = (ImageReader)readers.next();
						        if(reader.canReadRaster()) {
						            break;
						        }
						    }
	
						    //Stream the image file (the original CMYK image)
						    ImageInputStream input =   ImageIO.createImageInputStream(newCreateFile); 
						    reader.setInput(input); 
	
						    //Read the image raster
						    Raster raster = reader.readRaster(0, null); 
	
						    //Create a new RGB image
						    bi = new BufferedImage(raster.getWidth(), raster.getHeight(), 
						    BufferedImage.TYPE_4BYTE_ABGR); 
	
						    //Fill the new image with the old raster
						    bi.getRaster().setRect(raster);		
						    layoutType = 1;// (bi.getWidth() >= bi.getHeight())?0:1;
							
							if(bi != null)bi.flush();
							if(input != null)input.close();
							if(reader != null) reader.dispose();
							raster = null;
						} 
					} else {
						//동영상
						// 풀사이즈로 변경 체크 했을 경우
						if(param.getVideoScale().equals("Y")) {
							ffmpegUtils.init();
							Map<String,Object> configParam = new HashMap<String,Object>();
							configParam.put("configType", "VIDEO");					
							Map<String,Object> videoConfig = codeManageDao.getDboardConfig(configParam);
							String scaleSize = "";
							Map<String,Object> videoInfo = ffmpegUtils.getVideoResolution(newCreateFile.getAbsolutePath());
							layoutType = ((Integer)videoInfo.get("width") >= (Integer) videoInfo.get("height"))?0:1;	
							if(videoConfig != null) {
								scaleSize = (String) videoConfig.get("configValue");
	
								if(layoutType == 1) {	
									System.out.println("세로 영상 Scaling 시작");
									String tmpFilePath = ffmpegUtils.convertVideo(newCreateFile,CommonUtils.padLeftZeros(contentId+"",10)+"_tmp.mp4", scaleSize);
									File scaledFile = new File(tmpFilePath);
									newCreateFile.delete();
									scaledFile.renameTo(newCreateFile);
									//File newFile = new File(destinationDir+File.separator+newFileName);									
									//scaledFile.renameTo(newFile);
									newCreateFile.delete();
									
								}
							}
						}
					}
					System.out.println("layoutType : "+layoutType);
					param.setLayoutType(layoutType);
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
				} else {
					resultJson.put("contentPath",contentInfo.getContentPath()+"/"+contentInfo.getContentName()); 
					param.setLayoutType(contentInfo.getLayoutType());
				}
				/** 게시위치 삭제 */
				Map<String,Object> locationInfo = new HashMap<String,Object>();
				locationInfo.put("contentId", contentId);
				locationInfo.put("client", param.getClient());
				locationInfo.put("boardType", boardType);
				
				contentManageDao.deleteContClients(locationInfo);
				/** 게시위치 정보가 있을 경우 저장 */
				if(param.getClient() != null && param.getClient().size() > 0) {
					/**로케이션 값 중복 처리 임시 로직 */
					try {
						contentManageDao.saveContClientsPromo(locationInfo);
					} catch(Exception e) {
						
					}
				}
				resultCount = promoBoardDao.updatePromoBoardInfo(param);
				
				/**긴급 컨텐츠 일 경우 클라이언트로 화면 갱신 명령 전달 */
				if(param.getUrgentYn() != null && param.getUrgentYn().equals("Y")) {
					Long currentDate =Long.parseLong(CommonUtils.convertDateToStr("yyyyMMddHHmmss"));
					if(currentDate >= Long.parseLong(param.getPostingStart()) && currentDate <= Long.parseLong(param.getPostingEnd())) {
						Map<String,Object> paramData = new HashMap<String,Object>();
						paramData.put("contentId", contentId);
						paramData.put("userId", param.getPublisher());
						paramData.put("roleType", userInfo.getRoleType());
						List<Integer> clientList = promoBoardDao.getClientsByContent(paramData);
						paramData.clear();
						for(int i = 0 ;i<clientList.size();i++) {
							paramData.put("action", "urgent");
							paramData.put("clientId", clientList.get(i));
							paramData.put("boardIndex", 0);
							boolean result = SocketIOController.sendMessageToClient(paramData);
						}
						System.out.println("소켓 전송 완료");
					} else {
						System.out.println("현재 개시 시간이 아니기 때문에 화면 갱신 없음");
					}
				}
				/*
				 * 사용자 작업 로그
				 * */
				logManageService.saveLogInfo("INFO","I_MNG_PR_0002","홍보물 수정 ["+contentId+"]");
			} else {
				resultJson.put("result", 440);
				resultJson.put("resultMsg", "세션이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요.");
			}
		} catch(FileFormatException e) {
			resultCode = 500;	
			if(fileType.equals("IMG")) {
				resultMsg = "이미지는 JPG,PNG,GIF 파일만 지원합니다.";
			} else {
				resultMsg = "동영상은 MP4 파일만 지원합니다.";
			}			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "홍보물 수정 중  오류가 발생했습니다.";
		}
		resultJson.put("result", resultCode);
		resultJson.put("resultMsg", resultMsg);
		return resultJson;
	}

	@Override
	public Map<String,Object> deletePromoBoardInfo(List<Integer> params) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "정상적으로 삭제 되었습니다.";
		try {
			int resultCount = promoBoardDao.deletePromoBoardInfo(params);
			/*
			 * 사용자 작업 로그
			 * */
			logManageService.saveLogInfo("INFO","I_MNG_PR_0003","홍보물 삭제 "+params);
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
	public Map<String, Object> getPromoBoardInfo(int contentId) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		PromoBoardResultVO resultData = new PromoBoardResultVO();
		try {
			PromoBoardVO param = new PromoBoardVO();
			param.setContentId(contentId);
			resultData = promoBoardDao.getPromoBoardInfo(param);
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
	public int deletePromoBoardInfo(PromoBoardVO param) {
		// TODO Auto-generated method stub
				int resultCount = 0;
				try {
					resultCount = promoBoardDao.deletePromoBoardInfo(param);
					/*
					 * 사용자 작업 로그
					 * */
					logManageService.saveLogInfo("INFO","I_MNG_PR_0004","홍보물 삭제 ["+param.getContentId()+"]");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return resultCount;
	}
	

}
