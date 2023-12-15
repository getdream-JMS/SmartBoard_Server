package com.jms.dboard.common.utils;

import java.io.File;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

 

public class FileExtFilter {

 
	private static String[] IMG_BAD_EXTENSION = { "jpg", "png", "gif" }; 
	private static String[] MOV_BAD_EXTENSION = { "mp4"}; 
	private static String[] PDF_BAD_EXTENSION = { "pdf"}; 
	
    /**

     * 파일의 확장자를 체크하여 필터링된 확장자를 포함한 파일인 경우에 예외를 발생한다.

     * @param file

     * */

    public static void badFileExtIsReturnException(File file,String filterType) {

        String fileName = file.getName().toLowerCase();

        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,

                fileName.length());
        
        String[] BAD_EXTENSION = { "jsp", "php", "asp", "html", "perl" };

 

        try {

            int len = BAD_EXTENSION.length;

            for (int i = 0; i < len; i++) {

                if (ext.equalsIgnoreCase(BAD_EXTENSION[i])) {

                    // 불량 확장자가 존재할떄 IOExepction 발생

                    throw new IOException("BAD EXTENSION FILE UPLOAD");

                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

 

    /**

     * 파일의 확장자를 체크하여 필터링된 확장자를 포함한 파일인 경우에 true를 리턴한다.

     * @param file

     * */

    public static boolean fileExtIsReturnBoolean(MultipartFile file,String filterType) {

        String fileName = file.getOriginalFilename().toLowerCase();

        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
        		
                fileName.length());
        String[] BAD_EXTENSION = null;
        if (filterType.equals("IMG")){
        	BAD_EXTENSION = IMG_BAD_EXTENSION;
        } else if (filterType.equals("MOV")){
        	BAD_EXTENSION = MOV_BAD_EXTENSION;
        } else if (filterType.equals("PDF")){
        	BAD_EXTENSION = PDF_BAD_EXTENSION;
        } 

        int len = BAD_EXTENSION.length;
        
        for (int i = 0; i < len; i++) {
            if (ext.equalsIgnoreCase(BAD_EXTENSION[i])) {
            	System.out.println("정상 확장자!!!!");
                return true; // 정상 확장자가 존재할때..

            }

        }

        return false;
    }
}

