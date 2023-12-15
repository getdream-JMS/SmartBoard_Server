package com.jms.dboard.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class HttpUtils {
	
		public static String sendGet(String url) throws Exception {

			String result = "{\"result\":\"fail\"}";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream(),"UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			result = response.toString();
	
			return result;

		}

		// HTTP POST request
		public static String sendPost(String url, JSONObject urlParameters) throws Exception{
			String result = "{\"result\":\"fail\"}";
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
				//add reuqest header
				con.setRequestMethod("POST");
				con.setDoOutput(true);
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Accept", "application/json");
				con.connect();
	
				// Send post request
				byte[] outputBytes = urlParameters.toString().getBytes("UTF-8");
				OutputStream os = con.getOutputStream();
				os.write(outputBytes);			
				os.flush();
				os.close();
				outputBytes = null;
				int responseCode = con.getResponseCode();
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream(),"UTF-8"));
				String inputLine;
				StringBuffer response = new StringBuffer();
	
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
	
				//print result
				result = response.toString();	
			return result;	
		}
		
		// HTTP POST request
				public static String sendRequest(String url,String method) throws Exception{
					String result = "{\"result\":\"fail\"}";
						URL obj = new URL(url);
						HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
						//add reuqest header
						con.setRequestMethod(method);
						con.setDoOutput(true);
						con.setRequestProperty("Content-Type", "application/json");
						con.setRequestProperty("Accept", "application/json");
						con.setRequestMethod(method);
						con.connect();

						int responseCode = con.getResponseCode();
//						System.out.println("\nSending 'POST' request to URL : " + url);
//						System.out.println("Post parameters : " + urlParameters);
//						System.out.println("Response Code : " + responseCode);
			
						BufferedReader in = new BufferedReader(
						        new InputStreamReader(con.getInputStream(),"UTF-8"));
						String inputLine;
						StringBuffer response = new StringBuffer();
			
						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						in.close();
			
						//print result
						result = response.toString();
//						System.out.println(response.toString());			
					return result;	
				}

}
