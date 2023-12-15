package com.jms.dboard.manage.scheduler.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.jms.dboard.manage.scheduler.service.SchedulerManageService;

@Controller
public class WeatherController {
	
	@Autowired
	SchedulerManageService schedulerManageService;
	/*
	 * 주기적으로 공공 데이타 날씨 정보 조회
	 * */
//	@Scheduled(cron = "0 0/1 * * * *")
//	@Scheduled(cron = "0 30/15 9-20 * * ?")
//	@Scheduled(cron = "0 10 2-23/1 * * ?") //테스트
//	@Scheduled(cron = "0 0/30 * * * ?") //상용
	
	@Scheduled(cron = "0 0/30 * * * *") //상용
	/* 1시간 마다 실행 ex) 01:00, 02:00, 03:00.... 
	cron = "0 0 0/1 * * *"
*/
	public void getWeatherData() {
		Date nowDate = new Date();
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		schedulerManageService.getWeatherData();
		    
//		try {
//			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
//			JSONObject jsonParam = new JSONObject();
//			jsonParam.put("educode", "edukvma");
//			jsonParam.put("branch", "");
//			jsonParam.put("licno", "90018");	
//			jsonParam.put("name", "회비용");
//			jsonParam.put("phone", "");
//			jsonParam.put("certificateType", "1");
//			
//			//String jsonMessage = "{\"educode\": \"edukvma\",\"branch\": \"\",\"licno\": \"90018\",\"name\": \"회비용\",\"phone\": \"\",\"certificateType\": \"1\"}";
//			HttpPost postRequest = new HttpPost("https://devapi.veterflix.com/api/veter"); //POST 메소드 URL 새성 
////			HttpPost postRequest = new HttpPost("http://localhost/cms/api"); //POST 메소드 URL 새성 
//			postRequest.setHeader("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
//			postRequest.setHeader("Connection", "keep-alive");
//			postRequest.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
//			System.out.println("jsonMessage.length() :"+jsonParam.toJSONString());
////			postRequest.setHeader("Content-Length", jsonMessage.length()+"");
////			postRequest.setHeader("HOST" ,"https://devapi.veterflix.com/api/veter");
//			postRequest.setHeader("apiKey", "kV0eA3JolUm1OlBow9pM4w=="); //KEY 입력 
//
//			
//			postRequest.setEntity(new StringEntity(jsonParam.toJSONString(),"UTF-8")); //json 메시지 입력 
//
//			HttpResponse response = client.execute(postRequest);
//
//			//Response 출력
//			if (response.getStatusLine().getStatusCode() == 200) {
//				ResponseHandler<String> handler = new BasicResponseHandler();
//				String body = handler.handleResponse(response);
//				System.out.println(body);
//			} else {
//				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
//			}
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		try {
//			   System.setProperty("jsse.enableSNIExtension", "false");
//
//			   HttpHeaders headers = new HttpHeaders();
//			   headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
//			   headers.set("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
//			   headers.add("apikey", "kV0eA3JolUm1OlBow9pM4w==");
//			//   headers.set("Content-Length", String.valueOf(test.length()));
//			//   headers.set("Host", "devapi.veterflix.com");
//
//			   HttpEntity request = new HttpEntity(jsonParam.toJSONString(), headers);
//			   
////			   RestfulConfig config = new RestfulConfig();
////			   RestTemplate rest = config.getRestTemplate();
//			   RestTemplate rest = new RestTemplate();
////			   rest.getMessageConverters()
////		        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//			   //한글인코딩
//			   rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//
//			//   String res = rest.postForObject("https://devapi.veterflix.com/api/veter", new HttpEntity<>(test, headers), String.class);
//			//   System.out.println(res);
//			   ResponseEntity<String> res
//			     = rest.postForEntity(
////			     = rest.exchange(
//			     "http://localhost/cms/api",
////			     HttpMethod.POST,
//			     request,
////			     new HttpEntity<>(data, headers),
//			     String.class);
//
//			   System.out.println(res.getStatusCode());
//			   System.out.println(res.getBody());
//
//
//			  } catch (Exception e) {
//			   e.printStackTrace();
////			   logger.error("send list request error : "+e.getMessage());
//			  }
	}
			
}
