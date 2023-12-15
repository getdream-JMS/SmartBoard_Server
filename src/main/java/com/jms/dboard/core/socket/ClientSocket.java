package com.jms.dboard.core.socket;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
 
public class ClientSocket {

    public static void main(String args[]) {
    	
        try {
            
            // 서버 연결
            String serverIp = "127.0.0.1";
            System.out.println("IP :" + serverIp + "연결 중입니다.");
            
            // 소켓 생성
            Socket socket = new Socket(serverIp, 10000);
            
            // InputStram
            BufferedReader  br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            
            // 데이터 출력
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
         // 메세지 전달
 			pw.println("{\"clientId\" : 1, \"clientIp\" : \"192.168.0.100\"}");
 			pw.flush();
 			
 			// 응답 출력
 			InputStream is = socket.getInputStream();
 			byte[] bytes = new byte[100];
 			int readyByteCount = is.read(bytes);
 			//bytes = br.readLine().getBytes("UTF-8");
 			String convertData = new String(bytes,0,readyByteCount,"UTF-8");
 			System.out.println("convertData :" +convertData);
         			
            // 스트림 및 소켓 종료
            br.close();
            pw.close();
            socket.close();
            System.out.println("연결 종료.");
            
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
