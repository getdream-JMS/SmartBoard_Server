package com.jms.dboard.common.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.HttpUtils;

@Component
public class CommonUtils {

	
	@Autowired
	private Properties propConf;
		
    public static String getRandomString(){
        return UUID.randomUUID().toString();
    }
    
    public static String formatFileSize(float size) {
	    String hrSize = null;
	    double denominator = 1024.0;
	    double k = size;
	    double m = size/denominator;
	    double g = ((size/denominator)/denominator);
	    double t = (((size/denominator)/denominator)/denominator);
//	    double t = ((((size/1024.0)/1024.0)/1024.0)/1024.0);

	    DecimalFormat dec1 = new DecimalFormat("0.00");
        DecimalFormat dec2 = new DecimalFormat("0");
        if (t>1) {
            hrSize = isDouble(t) ? dec1.format(t).concat(" TB") : dec2.format(t).concat(" TB");
        } else if (g>1) {
            hrSize = isDouble(g) ? dec1.format(g).concat(" GB") : dec2.format(g).concat(" GB");
        } else if (m>1) {
            hrSize = isDouble(m) ? dec1.format(m).concat(" MB") : dec2.format(m).concat(" MB");
//        } else if (k>1) {
//            hrSize = isDouble(k) ? dec1.format(k).concat(" KB") : dec2.format(k).concat(" KB");
        } else {
            hrSize = isDouble(k) ? dec1.format(k).concat(" KB") : dec2.format(k).concat(" KB");
        }
        return hrSize;

	}
    private static boolean isDouble(double value) {
        if (value % 1 == 0) {
           //value is not double;
            return false;
        } else {
        	//value is double;
            return true;
        }
    }
    
	public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
   }
	
	public static String convertDateToStr(String dateFormat) {
		try {
			SimpleDateFormat sdf =
					new SimpleDateFormat(dateFormat);
	    	Calendar c1 = Calendar.getInstance(); // today
	    	return sdf.format(c1.getTime());
		} catch (Exception e) {
			return "";
		}
    }
	public static boolean createFolder(String dirPath) {
		File files = new File(dirPath);
		try {
		    if (!files.exists()) {
		        if (files.mkdirs()) {
		            System.out.println("Multiple directories are created!");
		        } else {
		            System.out.println("Failed to create multiple directories!");
		        }
		    }
		    return true;
		} catch(Exception e) {
			return false;
		}
	}
	public static String padLeftZeros(String inputString, int length) {
	    if (inputString.length() >= length) {
	        return inputString;
	    }
	    StringBuilder sb = new StringBuilder();
	    while (sb.length() < length - inputString.length()) {
	        sb.append('0');
	    }
	    sb.append(inputString);
	 
	    return sb.toString();
	}
	
	public static File uploadFile(MultipartFile inputFile, String destinationDir,String fileName,String contentId) {
		File newCreateFile = null;
		/* 폴더 경로가 없으면 생성 */
		createFolder(destinationDir);
		newCreateFile = new File(destinationDir+File.separator+fileName);
	//		byte[] data = param.getFile().getBytes();
	//		FileOutputStream fos = new FileOutputStream(destinationDir+File.separator+newFileName);
	//		fos.write(data);
	//		fos.close();
	
			try {
				for (File f : (new File(destinationDir)).listFiles()) {
			        if (f.getName().startsWith(contentId)) {
			            f.delete();
			        }
			    }
				inputFile.transferTo(newCreateFile);
				
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return newCreateFile;
	}
	/**
	 * 주어진 범위내에서 랜덤 integer 가져오기
	 * 
	 * */
	public static int getRandomNumberInRange(int min, int max) {

//		if (min >= max) {
//			throw new IllegalArgumentException("max must be greater than min");
//		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static  int toMB(long size)
	{
		return (int)(size/(1024*1024));
	}
	
	public static  int toGB(long size)
	{
		return Math.round((float)size/(1024*1024*1024));
	}
	
	public static  int toGbps(long size)
	{
		return Math.round((float)size/(1000*1000));
	}
	
	public static String getClientIP() {
		String ip = null;
        HttpServletRequest request = 
        ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        ip = request.getHeader("X-Forwarded-For");
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }
		
		return ip;
	}
	public static void deleteFiles(String destinationDir,String contentId) {
		String path = destinationDir;
		File folder = new File(path);
//		System.out.println("path :"+path);
		try {
		    if(folder.exists()) {
				File[] folder_list = folder.listFiles(); //파일리스트 얻어오기
						
				for (int j = 0; j < folder_list.length; j++) {
					if(folder_list[j].getName().indexOf(contentId) > -1) folder_list[j].delete(); //파일 삭제 							
				}
						
				if(folder_list.length == 0 && folder.isDirectory()){ 
					folder.delete(); //대상폴더 삭제
				}
		    }
		 } catch (Exception e) {
			e.getStackTrace();
		}
	}
}
