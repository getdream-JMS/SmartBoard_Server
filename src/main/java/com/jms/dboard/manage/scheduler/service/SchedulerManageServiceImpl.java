package com.jms.dboard.manage.scheduler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.common.vo.CommonCodeVO;
import com.jms.dboard.manage.announce.dao.AnnounceBoardDao;
import com.jms.dboard.manage.content.dao.ContentManageDao;
import com.jms.dboard.manage.promo.dao.PromoBoardDao;
import com.jms.dboard.manage.scheduler.dao.SchedulerManageDao;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.OrganInfo2VO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.PromoBoardVO;
import com.jms.dboard.manage.vo.WeatherInfoVO;
import org.json.simple.parser.ParseException;

@Service("scheduleManageService")
public class SchedulerManageServiceImpl implements SchedulerManageService{

	@Autowired
	SchedulerManageDao schedulerManageDao;


	@Autowired
	CommonCodeDao commonCodeDao;

	@Autowired
	AnnounceBoardDao announceBoardDao;

	@Autowired
	PromoBoardDao promoBoardDao;

	@Autowired
	ContentManageDao contentManageDao;

	@Value("${weather.service_key}")
	private String weatherServiceKey;

	@Value("${weather.data_type}")
	private String weatherDataType;

	@Value("${weather.nx}")
	private String weatherNx;

	@Value("${weather.ny}")
	private String weatherNy;

	@Override
	public Map<String, Object> syncNowonInfo() {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<OrganInfo2VO> deptList = null;
		List<UserInfoVO> userList = null;
		int resultCode = 200;
		String resultMsg = "정상적으로 부서및 사용자 정보 연동이 완료되었습니다.";
		try {
			deptList = schedulerManageDao.getNowonDeptList();
			userList = schedulerManageDao.getNowonUserList();
			if(deptList.size() > 0) {
				schedulerManageDao.deleteNowonDeptInfo();
				/*for(Iterator<Map<String,Object>> it = deptList.iterator() ; it.hasNext() ; ) {
							Map<String,Object> userInfo = it.next();
							  System.out.println("parentId :" + userInfo.get("UPPER_DEPT_ID"));
							  System.out.println("deptName :" + userInfo.get("DEPT_NAME"));
						}*/

				schedulerManageDao.insertNowonDeptInfo(deptList);
			}
			if(userList.size() > 0) {
				schedulerManageDao.deleteNowonUserInfo();

				//	for(Iterator<Map<String,Object>> it = userList.iterator() ; it.hasNext() ; ) {
				//		Map<String,Object> userInfo = it.next();
				//		  if(userInfo.get("display").equals("0")) {
				//			  it.remove();
				//		  }
				//	}
				schedulerManageDao.insertNowonUserInfo(userList);
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("내부 조직도 연동에 실패(DB 연결 상태 확인)");
			resultCode = 500;
			resultMsg = "부서및 사용자 정보 동기화 중 오류가 발생했습니다.";

		}
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}


	@Override
	public void deleteExpiredContents(String curDate) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> expiredList = null;

		int resultCode = 200;
		String resultMsg = "";
		try {
			expiredList = schedulerManageDao.getExpiredContents(curDate);
			if(expiredList.size() > 0) {
				String contentType = "";
				String contentPath = "";
				String realContentPath = "";
				int contentId = 0;
				CommonCodeVO codeVo = new CommonCodeVO();
				for(int i = 0; i < expiredList.size();i++) {
					contentType = (String) expiredList.get(i).get("contentType");
					contentPath = (String) expiredList.get(i).get("contentPath");
					contentId = (int) expiredList.get(i).get("contentId");
					if(contentPath != null) {
						if(contentType.equals("005")) {
							// 공고문							
							codeVo.setCodeName("ANN_PATH"); 
							codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
							realContentPath = codeVo.getCodeInfo()+contentPath.substring(contentPath.lastIndexOf("/"),contentPath.length());
	
							// 데이타 삭제
							AnnounceBoardVO announceBoardVO = new AnnounceBoardVO();
							announceBoardVO.setContentId(contentId);
							announceBoardDao.deleteAnnounceBoardImage(announceBoardVO);
							announceBoardDao.deleteTtsInfo(announceBoardVO);
	
							Map<String ,Object> contClientMap = new HashMap<String,Object>();
							contClientMap.put("contentId", contentId);
							contClientMap.put("boardType", "001");
							contentManageDao.deleteContClients(contClientMap);
							announceBoardDao.deleteAnnounceBoardInfo(announceBoardVO);
						} else {
							// 홍보물			
							codeVo.setCodeName("PRO_PATH"); 
							codeVo = commonCodeDao.getCommonCodeInfoByName(codeVo);
							realContentPath = codeVo.getCodeInfo()+contentPath.substring(contentPath.lastIndexOf("/"),contentPath.length());
	
							// 데이타 삭제
							PromoBoardVO promoBoardVO = new PromoBoardVO();
							promoBoardVO.setContentId(contentId);
	
							Map<String ,Object> contClientMap = new HashMap<String,Object>();
							contClientMap.put("contentId", contentId);
							contClientMap.put("boardType", "002");
							contentManageDao.deleteContClients(contClientMap);								
							promoBoardDao.deletePromoBoardInfo(promoBoardVO);
						}
						// 파일 삭제			
						CommonUtils.deleteFiles(realContentPath, CommonUtils.padLeftZeros(contentId+"",10));
					}
				}
				System.out.println("정상적으로 게시 만료 컨텐츠 삭제되었습니다.");

			} else {
				System.out.println("["+curDate+"]삭제 할 만료 컨텐츠가 없습니다.");
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			System.out.println("게시 만료 컨텐츠 삭제 중 오류가 발생했습니다.");

		}

	}


	@Override
	public void updateClientMntrStatus() {
		// TODO Auto-generated method stub
		try {
			schedulerManageDao.updateClientMntrStatus();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void getWeatherData() {
		// TODO Auto-generated method stub
		HttpURLConnection conn = null;
		BufferedReader rd = null;
		try {
		DateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date nowDate = new Date();
		String tempDate = sdFormat.format(nowDate).substring(0, 8);
//		String currentTime = sdFormat.format(nowDate).substring(8, 10);
		String apiParamTime = getValidProvideTime();
		// JSON데이터를 요청하는 URLstr을 만듭니다.
		CommonCodeVO param = new CommonCodeVO();
		param.setCodeName("EXT_IF_WEATHER");		
			CommonCodeVO codeData = commonCodeDao.getCommonCodeInfoByName(param);
			
			if(codeData != null && !codeData.getCodeInfo().equals("")) {
//				String weatherUrl = codeData.getCodeInfo();
				String apiUrl = codeData.getCodeInfo();
				// 홈페이지에서 받은 키
				String serviceKey = weatherServiceKey;
				String pageNo = "1";
				String numOfRows = "50"; // 한 페이지 결과 수
				String data_type = weatherDataType; // 타입 xml, json 등등 ..
				String baseDate = tempDate; // "20200821"이런식으로 api에서 제공하는 형식으로 입력.
				String baseTime = apiParamTime; // API 제공 시간을 입력하면 됨
				String nx = weatherNx; // 위도
				String ny = weatherNy; // 경도
	//			boolean hasRightTimeData = false;
				WeatherInfoVO weatherInfo = new WeatherInfoVO();
				
				try {
					StringBuilder urlBuilder = new StringBuilder(apiUrl);
					urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
					urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
					urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="+ URLEncoder.encode(numOfRows, "UTF-8")); /* 한 페이지 결과 수 */
					urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(data_type, "UTF-8")); /* 타입 */	
					urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="+ URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜 */
					urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "="+ URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
					urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); // 경도
					urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); // 위도


					/* GET방식으로 전송해서 파라미터 받아오기*/
					URL url = new URL(urlBuilder.toString());
					// 어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제		
					
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Content-type", "application/json");

					if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
						rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
					} else {
						rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
					}
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = rd.readLine()) != null) {
						sb.append(line);
					}
					
					rd.close();
					conn.disconnect();
					String result = sb.toString();
					System.out.println(result);
					// 문자열을 JSON으로 파싱합니다. 마지막 배열형태로 저장된 데이터까지 파싱해냅니다.
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
					JSONObject parse_response = (JSONObject) jsonObj.get("response");
					JSONObject parse_body = (JSONObject) parse_response.get("body");// response 로 부터 body 찾아오기
					if (parse_body != null) {
						JSONObject parse_items = (JSONObject) parse_body.get("items");// body 로 부터 items 받아오기
						// items로 부터 itemlist 를 받아오기 itemlist : 뒤에 [ 로 시작하므로 jsonarray이다.
						JSONArray parse_item = (JSONArray) parse_items.get("item");

						JSONObject obj;
						String category; // 기준 날짜와 기준시간을 VillageWeather 객체에 저장합니다.

						String day = "";
						String time = "";
						String fcstTime = "";
						List<WeatherInfoVO> datalist = new ArrayList<WeatherInfoVO>();
						weatherInfo.setBaseDate(baseDate);
						weatherInfo.setBaseTime(baseTime);
//						System.out.println("currentTime :"+currentTime);
						
						for (int i = 0; i < parse_item.size(); i++) {
							obj = (JSONObject) parse_item.get(i); // 해당 item을 가져옵니다.
							Object fcstValue = obj.get("fcstValue");
							Object tmpBaseDate = obj.get("baseDate");
//							System.out.println("fcstTime :"+obj.get("fcstTime").toString().substring(0, 2));
//							if(!currentTime.equals(obj.get("fcstTime").toString().substring(0, 2))) {
							if(i > 0 && !fcstTime.equals(obj.get("fcstTime").toString())) {
//								System.out.println(obj.get("fcstTime").toString() + ": 시간이 다름");
								weatherInfo.setFcstTime(fcstTime);
//								hasRightTimeData = true;	
								continue;
							}
							fcstTime = obj.get("fcstTime").toString();
							category = (String) obj.get("category"); // item에서 카테고리를 검색해옵니다.
							// 검색한 카테고리와 일치하는 변수에 문자형으로 데이터를 저장합니다.
							// 데이터들이 형태가 달라 문자열로 통일해야 편합니다. 꺼내서 사용할때 다시변환하는게 좋습니다.
//							System.out.println("category : "+category);
							switch (category) {
								case "POP":
									weatherInfo.setPop(fcstValue.toString());
									break;
								case "PTY":
									weatherInfo.setPty(fcstValue.toString());
									break;
								case "PCP":
									weatherInfo.setPcp(fcstValue.toString());
									break;
								case "REH":
									weatherInfo.setReh(fcstValue.toString());
									break;
								case "SNO":
									weatherInfo.setSno(fcstValue.toString());
									break;
								case "SKY":
									weatherInfo.setSky(fcstValue.toString());
									break;
								case "TMP":
									weatherInfo.setTmp (fcstValue.toString());
									break;
								case "TMN":
									weatherInfo.setTmn( fcstValue.toString());
									break;
								case "UUU":
									weatherInfo.setUuu( fcstValue.toString());
									break;
								case "VVV":
									weatherInfo.setVvv( fcstValue.toString());
									break;
								case "WAV":
									weatherInfo.setWav( fcstValue.toString());
									break;
								case "VEC":
									weatherInfo.setVec( fcstValue.toString());
									break;
								case "WSD":
									weatherInfo.setWsd( fcstValue.toString());
									break;
							}

//							if (!day.equals(tmpBaseDate.toString())) {
//								day = tmpBaseDate.toString();
//							}
//							if (!time.equals(fcstTime.toString())) {
//								time = fcstTime.toString();
////								System.out.println("데이: "+ day + "  " +"타임: "+ time);
//							}		

							
//							System.out.print("\tcategory : " + category);
//							System.out.print(", fcst_Value : " + fcstValue);
//							System.out.print(", fcstDate : " + tmpBaseDate);
//							System.out.println(", fcstTime : " + fcstTime);		

								
						}
						
						if( parse_item.size() > 0) {
							schedulerManageDao.deleteWeatherData();
							schedulerManageDao.saveWeatherData(weatherInfo);
						}
						
					}else {
						System.out.println("resultCode : " + ((JSONObject) parse_response.get("header")).get("resultCode"));
						System.out.println("resultMsg : " + ((JSONObject) parse_response.get("header")).get("resultMsg"));
					}
				}catch(IOException e) {
					//e.printStackTrace();
				}catch(ParseException e) {
					//e.printStackTrace();
					System.out.println("날씨 데이타 파싱 오류");
				}
			} else {
				System.out.println("날씨 정보 조회 URL이 없음");
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) conn.disconnect();
			if(rd != null)
				try {
					rd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		/*
         	POP	강수확률	%
			PTY	강수형태	코드값
			PCP	1시간 강수량	범주 (1 mm)
			REH	습도	%
			SNO	1시간 신적설	범주(1 cm)
			SKY	하늘상태	코드값
			TMP	1시간 기온	℃
			TMN	일 최저기온	℃
			TMX	일 최고기온	℃
			UUU	풍속(동서성분)	m/s
			VVV	풍속(남북성분)	m/s
			WAV	파고	M
			VEC	풍향	deg
			WSD	풍속	m/s	         */		
	}

	public String getValidProvideTime () {
		
		Integer [] forecastTime = {210, 510, 810, 1110, 1410, 1710, 2010, 2310};
		Date nowDate = new Date();
		SimpleDateFormat timeformat = new SimpleDateFormat("HHmm"); // 년월일시분초 14자리 포멧
		int properTime = 0;
		boolean findTime = false;
		// 일시 문자열을 읽고 출력하는 실습
		try {
			
			Integer curTime = Integer.valueOf(timeformat.format(nowDate));
		
			int fTime = 0;
			for(int i = 0; i < forecastTime.length;i++) {
				fTime = forecastTime[i];			
				if(fTime > curTime) {
					findTime = true;
				} else {
					properTime = fTime;
				}
				if(findTime) break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CommonUtils.padLeftZeros(properTime+"", 4);
	}


	@Override
	public Map<String, Object> getClientWeatherInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> expiredList = null;

		int resultCode = 200;
		String resultMsg = "";
		try {
			String className = "";
			WeatherInfoVO  weatherInfoVO = schedulerManageDao.getClientWeatherInfo();
			if(weatherInfoVO != null) {
				String category = weatherInfoVO.getCategory();
				String skyValue = weatherInfoVO.getSky();
				String ptyValue = weatherInfoVO.getPty();
				System.out.println("weatherInfoVO.getTmp() :"+weatherInfoVO.getTmp());
				Integer tempValue = 0;
				tempValue = Integer.valueOf(weatherInfoVO.getTmp());
				
                if(skyValue.equals("1") && tempValue < 33) {
                    className = "weather1"; //맑음
                } else if(ptyValue.equals("1")) {
                	className = "weather2"; //비
                } else if(skyValue.equals("3")) {
                	className = "weather3"; //구름많음
                } else if(skyValue.equals("4")) {
                	className = "weather4"; //흐림
                } else if(ptyValue.equals("3")) {
                	className = "weather5"; //눈
                } else if(ptyValue.equals("1") && tempValue >= 33) {
                	className = "weather6"; //눈
                }
                resultMap.put("weatherClass", className);
		        resultMap.put("temp",weatherInfoVO.getTmp()+"℃");
		        resultMap.put("Humidity",weatherInfoVO.getReh()+"%");
			} else {
				resultCode = 404;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMap.put("weatherClass", "");
		    resultMap.put("temp"," - ℃");
		    resultMap.put("Humidity","- %");
			System.out.println("날씨 정보 조회 중 오류가 발생했습니다.");

		}
		resultMap.put("resultCode", resultCode);
		return resultMap;
	}
}
