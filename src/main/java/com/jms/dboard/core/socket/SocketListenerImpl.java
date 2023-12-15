package com.jms.dboard.core.socket;

import java.net.Socket;

import org.springframework.stereotype.Service;

//리스너 패턴을 위한 인터페이스
@Service("socketListener")
public class SocketListenerImpl implements SocketListener{

	@Override
	public void run(Socket socket, String message) {
		// TODO Auto-generated method stub
		System.out.println("message :"+message);
	}
}


