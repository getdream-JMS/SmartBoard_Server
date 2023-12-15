package com.jms.dboard.core.socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Component;




public class MonoTest {
	
	private String authId = "kt";
	private String authPass = "yYZPs4U5ne94SZvq73Fmuj1tvJcCAFRJ";
	private String authKey = "a3Q6eVlaUHM0VTVuZTk0U1p2cTczRm11ajF0dkpjQ0FGUko=";
	
	private MonoTest() {
//		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		
		MonoTest_Key();
		
		MonoTest_ID();

    }
	//Key 사용
	private void MonoTest_Key() {
		Date curDate = new Date();
		SimpleDateFormat tmpDate;
		tmpDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		System.out.println("Before : "+tmpDate.format(curDate));
		System.out.println("==================================================");
		System.out.println("Get Data From MONO With Key");
		System.out.println("==================================================");
		String urlOverHttps = "www.monomax.me";
		try {
			
			URL url = new URL("HTTPS", urlOverHttps, -1, "/api/kt/search?keyword=car");
			URLConnection conn = url.openConnection();
			conn.addRequestProperty("User-Agent", "Mozilla");
			HttpURLConnection http = (HttpURLConnection) conn;
//			HttpURLConnection.setFollowRedirects(false);
			http.setRequestMethod("GET");
			http.setDoOutput(true);
			http.setRequestProperty("Authorization", "Basic "+authKey);
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			int responseCode = http.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(http.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());

			System.out.println("==================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			curDate = new Date();
			System.out.println("After "+tmpDate.format(curDate));
		}
	} 
	
	//ID, PASSWORD 사용
	private void MonoTest_ID() {
		System.out.println("==================================================");
		System.out.println("Get Data From MONO With ID and Password");
		System.out.println("==================================================");
		String urlOverHttps = "www.monomax.me";
		try {
//			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
			URL url = new URL("HTTPS", urlOverHttps, -1, "/api/kt/search?keyword=car");
			URLConnection conn = url.openConnection();
			conn.addRequestProperty("User-Agent", "Mozilla");
			HttpURLConnection http = (HttpURLConnection) conn;
//			HttpURLConnection.setFollowRedirects(false);
			http.setRequestMethod("GET");
			http.setDoOutput(true);
			//방법 1 : sun.misc의 경우 import 할 수 없기 때문에 sun.misc.BASE64Encoder() 전체 경로를 써줘야 함
			//        String encoded = new sun.misc.BASE64Encoder().encode(("kt".concat(":").concat("yYZPs4U5ne94SZvq73Fmuj1tvJcCAFRJ")).getBytes());
			//        http.setRequestProperty("Authorization", "Basic "+encoded);

			//방법 2 : java 8이상을 사용 할 경우 (저의 경우 java 8을 사용하고 있음)
			String encoded = new String(Base64.getMimeEncoder().encode((authId.concat(":").concat(authPass)).getBytes()),
					StandardCharsets.UTF_8);
			http.setRequestProperty("Authorization", "Basic "+encoded);
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			int responseCode = http.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(http.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(StringEscapeUtils.unescapeJava(response.toString()));

			System.out.println("==================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}

	} 
}
