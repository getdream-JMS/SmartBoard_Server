package com.jms.dboard.core.socket;

import java.net.Socket;

//리스너 패턴을 위한 인터페이스
public interface SocketListener {
	public void run(Socket socket, String message);
}


