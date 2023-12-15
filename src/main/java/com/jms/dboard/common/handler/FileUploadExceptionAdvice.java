package com.jms.dboard.common.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice

public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
     
    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    @ResponseStatus(reason = "File too large")
    public ResponseEntity<?> handleMaxSizeException(
      MaxUploadSizeExceededException exc, 
      HttpServletRequest request,
      HttpServletResponse response) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Content-Type","application/json;charset=UTF-8");
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultCode", 500);
    	map.put("resultMsg", "동영상 용량은 500Mb를 초과할 수 없습니다.");
    	System.out.println("File too large");
    	return new ResponseEntity<>(map, headers, HttpStatus.OK);

//        headers.setContentType(MediaType.IMAGE_PNG);
    }
}