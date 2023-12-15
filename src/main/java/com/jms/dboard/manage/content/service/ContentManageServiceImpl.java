package com.jms.dboard.manage.content.service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.SystemOutLogger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.announce.dao.AnnounceBoardDao;
import com.jms.dboard.manage.announce.service.AnnounceBoardService;
import com.jms.dboard.manage.clients.dao.ClientsManageDao;
import com.jms.dboard.manage.clients.service.ClientsManageService;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.promo.dao.PromoBoardDao;
import com.jms.dboard.manage.promo.service.PromoBoardService;
import com.jms.dboard.manage.vo.AnnounceBoardDetailsVO;
import com.jms.dboard.manage.vo.AnnounceBoardResultVO;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.ContentInfoVO;
import com.jms.dboard.manage.vo.PromoBoardResultVO;
import com.jms.dboard.manage.vo.PromoBoardVO;
import com.jms.dboard.manage.vo.ReserveRoomInfoVO;


@Service("contentManageService")
public class ContentManageServiceImpl implements ContentManageService{


	@Autowired
	ContentManageDao contentManageDao;

	@Autowired
	ClientsManageDao clientsManageDao;

	@Autowired
	CommonCodeDao commonCodeDao;

	@Autowired
	AnnounceBoardService announceBoardService;

	@Autowired
	PromoBoardService promoBoardService;
	@Override
	public Map<String,Object> getContentsInfoByBoard(ClientBoardDataReqVO param) {
		// TODO Auto-generated method stub
		System.out.println("=============================");
		System.out.println("컨텐츠 리스트 조회");
		System.out.println("게시판 인덱스 : "+param.getBoardIndex());
		System.out.println("게시판 종류 : "+param.getBoardType());
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int totalCount = 0;
		try {
			Map<String,Object> isOffDayData = clientsManageDao.getIsOffDay();
			resultMap.put("isHolidayOff",isOffDayData.get("isHolidayOff"));
			if(isOffDayData.get("isHolidayOff").equals("N")) {
				Date date = Calendar.getInstance().getTime();  
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");  
				String strDate = dateFormat.format(date);  
				param.setCurDate(strDate);
				ClientBoardsInfoVO clientBoardInfo = clientsManageDao.getClientBoardInfo(param); 
				int boardIndex = clientBoardInfo.getBoardIndex() - clientBoardInfo.getBoardMinIndex();
				if(clientBoardInfo != null) {
					String boardType = clientBoardInfo.getBoardType();
					int boardCount = clientBoardInfo.getBoardCount();

//					System.out.println("boardType :"+boardType);
					param.setBoardType(boardType);
					param.setIsManualPaging(true);
					if(boardType.equals("001")) {

						totalCount = contentManageDao.getTotalClientAnnounceBoardInfo(param);
						int countPerPage = totalCount / boardCount;
						param.setStartRowNum(countPerPage * boardIndex);
						if(boardCount == 1) {
							param.setPageCount(-1);
						} else {		            		  
							if(boardIndex == boardCount-1) {
								countPerPage+=totalCount % boardCount;
							}
							param.setPageCount(countPerPage);
						}  
						//System.out.println("real Board index :" + clientBoardInfo.getBoardIndex());
						//System.out.println("getBoardMinIndex :" + clientBoardInfo.getBoardMinIndex());
						//System.out.println("getBoardCount :" + clientBoardInfo.getBoardCount());
						//System.out.println(clientBoardInfo.getBoardCount() - clientBoardInfo.getBoardMinIndex());
						//System.out.println(boardCount);
						//System.out.println(param.getPageCount());
						//System.out.println(totalCount);
						//System.out.println(param.getStartRowNum());
						//	            		  System.out.println("getClientId : " + param.getClientId());
						//	            		  System.out.println("getCurDate : " + param.getCurDate());
						//	            		  System.out.println("getBoardIndex : " + param.getBoardIndex());
						//	            		  System.out.println("getTemplateType : " + param.getTemplateType());
						//	            		  System.out.println("getUrgentYn : " + param.getUrgentYn());
						//	            		  System.out.println("boardCount : " + boardCount);
						//	            		  System.out.println("getPageCount : " + param.getPageCount());
						//	            		  System.out.println("getStartRowNum : " + param.getStartRowNum());
						param.setPageNum(boardIndex+1);
						list = contentManageDao.getClientAnnounceBoardInfo(param);
						resultMap.put("data",list);
					} else if(boardType.equals("002") || boardType.equals("008")) {

						int urgentCount = 0;
						if(param.getUrgentYn() != null && param.getUrgentYn().equals("Y")) {
							urgentCount = contentManageDao.getCountUrgentContents(param);	            					  
							if(urgentCount == 0) {
								param.setUrgentYn("N");
							}
						}

						Map<String,Object> imgBoardInfo = contentManageDao.getImageBoardInfo(param);

						
						//	            		  param.setContentType("001");
						totalCount = contentManageDao.getTotalClientPromoBoardInfo(param);
						boardCount = Integer.valueOf(imgBoardInfo.get("count").toString());
//						System.out.println("boardCount : "+ boardCount);
//						System.out.println("minBoardIndex : "+ imgBoardInfo.get("minBoardIndex"));
						resultMap.put("bBar", imgBoardInfo.get("bBar"));
						int countPerPage = totalCount / boardCount;
						int pageNum = Integer.valueOf((String)imgBoardInfo.get("minBoardIndex"));
//						int pageNum = param.getBoardIndex();
						param.setStartRowNum(countPerPage * (pageNum-1));
						if(boardCount == 1) {
							param.setPageCount(-1);
						} else {		            		  
							if(pageNum == boardCount) {
								countPerPage+=totalCount % boardCount;
							}
							param.setPageCount(countPerPage);
						}
						
						Map<String,Object> data = contentManageDao.getClientPromoBoardInfo(param);
						
//						int remained = param.getPageCount() / boardCount;
//						System.out.println("param.setStartRowNum : "+countPerPage * (pageNum-1));
//						System.out.println("boardCount : "+boardCount);
//						System.out.println("totalCount : "+totalCount);
//						System.out.println("remained : "+remained);
						param.setPageNum(pageNum);
//	            		System.out.println("pageNum :"+pageNum);
//	            		System.out.println("param.getPageCount() :"+param.getPageCount());
//	            		System.out.println(param.getStartRowNum());
//						System.out.println("boardType : "+param.getBoardType());
//						System.out.println("templateType : "+param.getTemplateType());
//						System.out.println("contentType : "+param.getContentType());
//						System.out.println("boardCount : "+boardCount);
//						System.out.println("나머지 : "+totalCount % boardCount);
//					
						System.out.println("all : "+data.get("all"));
						List<PromoBoardResultVO> imgData = new ArrayList<PromoBoardResultVO>();
						List<PromoBoardResultVO> moveData = new ArrayList<PromoBoardResultVO>();
						List<PromoBoardResultVO> subData = new ArrayList<PromoBoardResultVO>();
						List<PromoBoardResultVO> urlData = new ArrayList<PromoBoardResultVO>();
						List<PromoBoardResultVO> ntcData = new ArrayList<PromoBoardResultVO>();
						System.out.println("getPageCount : " + param.getPageCount());
						//if(param.getPageCount() > 0) {
							List<PromoBoardResultVO> newAllData = (List) data.get("all");
							System.out.println("newAllData : " + newAllData.size());
							for(int i = 0; i < newAllData.size();i++) {
								String contentType = ((PromoBoardResultVO) newAllData.get(i)).getContentType();
								System.out.println("contentType ==>"+contentType);
								switch (contentType) {
								  case "001":
									imgData.add((PromoBoardResultVO) newAllData.get(i));
								    break;
								  case "002":
									  moveData.add((PromoBoardResultVO) newAllData.get(i));
								    break;
								  case "003":
									  subData.add((PromoBoardResultVO) newAllData.get(i));
								    break;
								  case "004":
									  urlData.add((PromoBoardResultVO) newAllData.get(i));
								    break;
								  case "006":
									  ntcData.add((PromoBoardResultVO) newAllData.get(i));
								    break;
								  case "007":
									  imgData.add((PromoBoardResultVO) newAllData.get(i));
								    break;
								}
							}
							data.put("img", imgData);
							data.put("mov", moveData);
							data.put("sub", subData);
							data.put("url", ntcData);
							
						//}

//						System.out.println(imgData);
//						System.out.println(moveData);
						
//						  ArrayList<Map<String,Object>> intArrayList =
//						          new ArrayList<Map<String,Object>>(Arrays.asList(data.get("all"));
//
//						  ArrayList<Integer> newArrayList =
//						          new ArrayList<>(intArrayList.subList(2, 5));
//						  
//						List<Map<String,Object>> img = Arrays.copyOf(data.get("all"), param.getStartRowNum(), pageNum * param.getPageCount());
//						for(int i = 0; i < ((ArrayList)data.get("img")).size();i++) {
//							Map<String,Object> data = (ArrayList)data.get("img")).get
//							System.out.println("");
//							img.add(resultMsg)
//						}
						resultMap.put("data",data);
						
					} else if(boardType.equals("006")) {
//	            		  System.out.println("curDate : "+strDate);
	            		  totalCount = contentManageDao.getTotalClientAnnounceBoardInfo(param);
	            		  int countPerPage = totalCount / boardCount;
	            		  param.setStartRowNum(countPerPage * boardIndex);
	            		  if(boardCount == 1) {
		            		  param.setPageCount(-1);
		            	  } else {		            		  
		            		  if(boardIndex == boardCount-1) {
		            			  countPerPage+=totalCount % boardCount;
		            		  }
		            		  param.setPageCount(countPerPage);
		            	  }  
	            		  //System.out.println("real Board index :" + clientBoardInfo.getBoardIndex());
	            		  //System.out.println("getBoardMinIndex :" + clientBoardInfo.getBoardMinIndex());
	            		  //System.out.println("getBoardCount :" + clientBoardInfo.getBoardCount());
	            		  //System.out.println(clientBoardInfo.getBoardCount() - clientBoardInfo.getBoardMinIndex());
	            		  //System.out.println(boardCount);
	            		  //System.out.println(param.getPageCount());
	            		  //System.out.println(totalCount);
	            		  //System.out.println(param.getStartRowNum());
//	            		  System.out.println("getClientId : " + param.getClientId());
//	            		  System.out.println("getCurDate : " + param.getCurDate());
//	            		  System.out.println("getBoardIndex : " + param.getBoardIndex());
//	            		  System.out.println("getTemplateType : " + param.getTemplateType());
//	            		  System.out.println("getUrgentYn : " + param.getUrgentYn());
//	            		  System.out.println("boardCount : " + boardCount);
//	            		  System.out.println("getPageCount : " + param.getPageCount());
//	            		  System.out.println("getStartRowNum : " + param.getStartRowNum());
//	            		  System.out.println("getCurDate : " + param.getCurDate());
	            		  param.setPageNum(boardIndex+1);
	            		  list = contentManageDao.getClientNewsletterBoardInfo(param);
	            		  resultMap.put("data",list);
	            	  } else if(boardType.equals("007")) {

							int urgentCount = 0;
							if(param.getUrgentYn() != null && param.getUrgentYn().equals("Y")) {
								urgentCount = contentManageDao.getCountUrgentContents(param);	            					  
								if(urgentCount == 0) {
									param.setUrgentYn("N");
								}
							}

							Map<String,Object> imgBoardInfo = contentManageDao.getImageBoardInfo(param);


							//	            		  param.setContentType("001");
							totalCount = contentManageDao.getTotalClientJuminBoardInfo(param);
							boardCount = Integer.valueOf(imgBoardInfo.get("count").toString());
//							System.out.println("boardCount : "+boardCount);
//							System.out.println("boardType : "+param.getBoardType());
							resultMap.put("bBar", imgBoardInfo.get("bBar"));
							int countPerPage = totalCount / boardCount;
							//int pageNum = (param.getBoardIndex() - (Integer)imgBoardInfo.get("minBoardIndex"))+1;
							int pageNum = Integer.valueOf((String)imgBoardInfo.get("minBoardIndex"));
							param.setStartRowNum(countPerPage * (pageNum-1));
							if(boardCount == 1) {
								param.setPageCount(-1);
							} else {		            		  
								if(pageNum == boardCount) {
									countPerPage+=totalCount % boardCount;
								}
								param.setPageCount(countPerPage);
							}  
							param.setPageNum(pageNum);
							//	            		  System.out.println(pageNum);
							//	            		  System.out.println(boardCount);
							//	            		  System.out.println(param.getPageCount());
							//	            		  System.out.println(param.getStartRowNum());
							Map<String,Object> data = contentManageDao.getClientJuminBoardInfo(param);
							resultMap.put("data",data);
						} 
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "컨텐츠 조회 중 오류가 발생했습니다.";	
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
//		System.out.println("totalCount :"+totalCount);
		System.out.println("=============================");
		return resultMap;
	}

	@Override
	public Map<String, Object> getContentInfoList(ContentInfoVO param) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");  
			String strDate = dateFormat.format(date);  
			param.setCurDate(strDate);
			//		             System.out.println(param.getStartDate());
			//		             System.out.println(param.getEndDate());
			//		             System.out.println(param.getTitle());
			list = contentManageDao.getContentInfoList(param);

			totalCount = contentManageDao.getTotalContentsList(param);
			resultMap.put("totalCount", totalCount);
			resultMap.put("pageNum", param.getPageNum());
			resultMap.put("pageCount", param.getPageCount());
			resultMap.put("list",list);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "컨텐츠 조회 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> getContentInfo(int param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deleteContentInfo(List<ContentInfoVO> param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Integer> announceDataList = new ArrayList<Integer>();
		List<Integer> promoDataList = new ArrayList<Integer>();
		int resultCode = 200;
		String resultMsg = "";
		try {
			for(int i = 0 ; i < param.size();i++) {
				if(param.get(i).getContentType().contentEquals("005")) {
					announceDataList.add(param.get(i).getContentId());
				} else {
					promoDataList.add(param.get(i).getContentId());
				}
			}

			if(announceDataList.size() > 0) {
				announceBoardService.deleteAnnounceBoardInfo(announceDataList);
			}

			if(promoDataList.size() > 0) {
				promoBoardService.deletePromoBoardInfo(promoDataList);
			}
		} catch(Exception e) {
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "컨텐츠 삭제 중 오류가 발생했습니다.";
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> getContentsInfoByMainBoard(ClientBoardDataReqVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		Object[][] dataInMonth = new Object[12][];
		List<List<Map<String,Object>>> monthEventList = new ArrayList<List<Map<String,Object>>>();
		try {
			Map<String,Object> isOffDayData = clientsManageDao.getIsOffDay();
			resultMap.put("isHolidayOff",isOffDayData.get("isHolidayOff"));
			if(isOffDayData.get("isHolidayOff").equals("N")) {
				List<Map<String,Object>> data = contentManageDao.getClientEventBoardInfo(param);
				for (int i = 0; i < 12; i++)  {
					monthEventList.add(new ArrayList<>());
				}
				
				for(int i = 0; i < data.size();i++) {
					int monthNumber = Integer.parseInt(data.get(i).get("postingStart").toString().substring(4, 6));
//					System.out.println("month : "+data.get(i).get("postingStart").toString().substring(4, 6));
					monthEventList.get(monthNumber-1).add(data.get(i));
					
				}

				resultMap.put("data",monthEventList);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "컨텐츠 조회 중 오류가 발생했습니다.";	
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> getClientNoticeInfo(ClientBoardDataReqVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		try {
			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");  
			String strDate = dateFormat.format(date);  
			param.setCurDate(strDate);
//			System.out.println("clientId :"+ param.getClientId());
			List<Map<String,Object>> data = contentManageDao.getClientNoticeInfo(param);
			resultMap.put("data",data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "공지사항 조회 중 오류가 발생했습니다.";	
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getClientEventData(ClientBoardDataReqVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		List<Map<String,Object>> monthEventList = new ArrayList<Map<String,Object>>();
		try {
			Map<String,Object> isOffDayData = clientsManageDao.getIsOffDay();
			resultMap.put("isHolidayOff",isOffDayData.get("isHolidayOff"));
			if(isOffDayData.get("isHolidayOff").equals("N")) {
				List<Map<String,Object>> data = contentManageDao.getClientEventBoardInfo(param);
				
				for(int i = 0; i < data.size();i++) {
					int startMonthNumber = Integer.parseInt(data.get(i).get("postingStart").toString().substring(4, 6));
					int endMonthNumber = Integer.parseInt(data.get(i).get("postingEnd").toString().substring(4, 6));
//					System.out.println(param.getMonthNumber()+" - "+startMonthNumber+" - "+endMonthNumber);
					if(param.getMonthNumber() == startMonthNumber || param.getMonthNumber() == endMonthNumber  ) {
						monthEventList.add(data.get(i));
					}				
				}

				resultMap.put("data",monthEventList);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "컨텐츠 조회 중 오류가 발생했습니다.";	
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getReserveBoardData(ClientBoardDataReqVO param) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int resultCode = 200;
		String resultMsg = "";
		try {
				System.out.println("clientId : "+param.getClientId());
				ReserveRoomInfoVO data = contentManageDao.getReserveBoardData(param);			
				if(data != null) {
					resultMap.put("data",data);
				} else {
					resultCode = 404;
					resultMsg = "예약 정보가 없습니다.";	
				}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "회의실 예약정보 조회 중 오류가 발생했습니다.";	
		}
		resultMap.put("result",resultCode);
		resultMap.put("resultMsg",resultMsg);
		return resultMap;
	}

	@Override
	public Map<String, Object> getDownloadInfo(Map<String, Object> param) {
		return null;
	}

}
