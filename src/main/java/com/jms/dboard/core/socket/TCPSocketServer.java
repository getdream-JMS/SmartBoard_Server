package com.jms.dboard.core.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonParser;
import com.jms.dboard.manage.monitoring.service.MonitoringManageService;
import com.jms.dboard.manage.vo.MonitoringInfoVO;

import java.text.SimpleDateFormat;

//@Component
public class TCPSocketServer extends ServerSocket {
	@Autowired
	MonitoringManageService monitoringManageService;
	// 싱글톤 패턴으로 구현한다.
	private static TCPSocketServer instance = null;
	public static TCPSocketServer getInstance() throws IOException {
		if (instance == null) {
			instance = new TCPSocketServer();
		}
		return instance;
	}
	// 다중 접속을 위한 client 관리를 한다.
	private final List<Socket> clients = new ArrayList<>();
	// 소켓 메시지 대기를 위한 스레드 풀이다.
	private final ExecutorService receivePool = Executors.newCachedThreadPool();
	// 외부에서 방문자 패턴으로 리스너를 등록할 수 있게 한다.
	private final List<SocketListener> listeners = new ArrayList<>();
	private TCPSocketServer() throws IOException {
		super();
		System.out.println("====== Start Socket Server ======");
		// 소켓은 10000 포트로 오픈한다.(만약 10000 포트를 사용하게 된다면 다른 포트로 수정하면 된다.)
		InetSocketAddress ipep = new InetSocketAddress(10000);
		super.bind(ipep);
		// 싱글 스레드 풀이다. 서버는 하나이기 때문에 싱글 스레드 풀을 사용한다.
		Executors.newSingleThreadExecutor().execute(() -> {
			try {
				// 무한 루프로 클라이언트를 대기를 한다.
				while(true) {
					Socket client = super.accept();
					System.out.println("연결 성공");
					clients.add(client);
					// 메시지 대기를 한다.
					receive(client);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		});
	}
	private void receive(Socket client) {
		// 위 캐쉬 스레드 풀을 사용한다.
		receivePool.execute(() -> {
			// 클라이언트와 메시지 구조은 먼저 4byte의 문자사이즈를 받고 그 크기만큼 문자 메시지가 오는 것이다.
			
			/*
			 * 접근한 소켓 계정의 ip를 체크한다. KTOA 연동 모듈인지 체크 
			 * 정상이면 먼저 정상 접근되었음을 알린다.
			 **/
			BufferedReader br = null;
			PrintWriter pw =null;
			try{
			br = new BufferedReader(
			        new InputStreamReader(client.getInputStream()));
			
			pw = new PrintWriter(client.getOutputStream());
			
			JSONObject jsonObject = new JSONObject(br.readLine().toString());
			//JSONObject message =  (JSONObject) jp.parse(br.readLine().toString());
			// 클라이언트에서 보낸 문자열 출력
			System.out.println("receive : "+br.readLine().toString());
			MonitoringInfoVO param = new MonitoringInfoVO();
			param.setClientId(jsonObject.getInt("clientId"));
			MonitoringInfoVO monitoringInfoVO = monitoringManageService.getClientMonitoringConf(param);
			ObjectMapper mapper = new ObjectMapper();
			String returnData = mapper.writeValueAsString(monitoringInfoVO);
			System.out.println("returnData :"+returnData);
			// 클라이언트에 문자열 전송
				pw.println(returnData);
				pw.flush();	
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				try{
					if(pw != null) { pw.close();}
					if(br != null) { br.close();}
//					if(socket != null){socket.close();}
				}catch(IOException e){
					e.printStackTrace();
				}
			}
				
		});
	}
	// 방문자 패턴
	public void addListener(SocketListener listener) {
		listeners.add(listener);
	}
	// 전체 클라이언트에게 메시지를 보낸다.
	public void send(String msg) {
		for (Socket client : clients) {
			send(client, msg);
		}
	}
	// socket 특정 클라이언트에게 메시지를 보낸다.
	public void send(Socket client, String msg) {
		byte[] data = msg.getBytes();
		ByteBuffer length = ByteBuffer.allocate(4);
		length.putInt(data.length);
		try (OutputStream sender = client.getOutputStream()){
			// 문자 길이를 보내고 데이터를 보낸다. (리틀 엔디언)
			sender.write(length.array());
			sender.write(data);
		} catch (Throwable e) {
			try {
				client.close();
			} catch (IOException x) {
				x.printStackTrace();
			}
			clients.remove(client);
		}
	}
}
