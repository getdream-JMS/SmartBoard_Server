package com.jms.dboard.core.socket;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import java.util.Collection;
import com.corundumstudio.socketio.SocketIOClient;


public class SocketIOController {

	static public boolean sendMessageToBoard(Map<String,Object> param) {
		boolean result = false;
//		Set<String> roomList = SocketIOServerpDemon.getSocketIOClient().getAllRooms();
		Collection<SocketIOClient> clientList = SocketIOServerpDemon.getInstance().getAllClients();
		try {
			for (Iterator<SocketIOClient>  iterator = clientList.iterator(); iterator.hasNext();) {
				SocketIOClient client = iterator.next();
//				if(client.get("clientId") == param.get("clientId") && client.get("boardIndex") == param.get("boardIndex")) {
				if(client.get("clientId") == param.get("clientId")) {
       			 client.sendEvent(param.get("action").toString().toLowerCase(), param);
       			 result = true;
				}
		     }
			/*
			for(String room:roomList){
				
				if(room.equals(""))continue;
		        if(room.equals("room_"+param.get("clientId"))) {
		        	Collection<SocketIOClient> clientList = SocketIOServerpDemon.getInstance().getRoomOperations("room_"+param.get("clientId")).getClients();
		        	for (Iterator<SocketIOClient> iterator = clientList.iterator(); iterator.hasNext();) {
		        		SocketIOClient client = iterator.next();
		        		 if(client.get("boardIndex") == param.get("boardIndex")) {
		        			 client.sendEvent(param.get("action").toString().toLowerCase(), param);
		        			 result = true;
		        			 break;
		        		 }
		        	}
		        }
		        if(result) break;
			}*/
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	static public boolean sendMessageToClient(Map<String,Object> param) {
		boolean result = false;
//		Set<String> roomList = SocketIOServerpDemon.getSocketIOClient().getAllRooms();
		
		Map<String, SocketIOClient> socketMngMap = SocketIOServerpDemon.getSocketClientMap();
		try {
//			System.out.println("소켓 데이타 전송 : "+roomList.size());
			Collection<SocketIOClient> clientList = SocketIOServerpDemon.getInstance().getAllClients();
			for (Iterator<SocketIOClient>  iterator = clientList.iterator(); iterator.hasNext();) {
				SocketIOClient client = iterator.next();
				System.out.println(client.get("clientId") +" : "+param.get("clientId"));
				if(client.get("clientId").equals(param.get("clientId"))) {
					System.out.println("action :"+param.get("action"));
					client.sendEvent(param.get("action").toString().toLowerCase(), param);
					result = true;
					break;
				}
		     }
//			for (Map.Entry<String, SocketIOClient> room : socketMngMap.entrySet()) {
//				System.out.println(room.getKey()+" - "+room.getValue());
//				if(room.getKey().equals("client_"+param.get("clientId"))) {
//					room.getValue().sendEvent(param.get("action").toString().toLowerCase(), param);
//					break;
//				}
//			}
			
//			for(String room:roomList){
//				System.out.println(param.get("clientId")+" - "+room);
//		        if(room.equals(""))continue;
//		        
//		        
//		        if(room.equals("room_"+param.get("clientId"))) {
//		        	SocketIOServerpDemon.getInstance().getRoomOperations(room).sendEvent(param.get("action").toString().toLowerCase(), param);
//		        	break;
//		        }
//			}
			result = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
