package com.jms.dboard.core.socket;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PreDestroy;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.jms.dboard.common.config.CustomSpringConfigurator;
import com.jms.dboard.manage.clients.service.ClientsManageService;
import com.jms.dboard.manage.monitoring.service.MonitoringManageService;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;

import io.netty.util.internal.InternalThreadLocalMap;

@Component
public class SocketIOServerpDemon {
	public static final int PORT = 10000;
	static Configuration config = new Configuration();

	public static SocketIOClient socketClient = null;
	public static Map<String,SocketIOClient> socketMngMap = new HashMap<String,SocketIOClient>();
	@Autowired
	ClientsManageService clientsManageService;
	
	@Autowired
	MonitoringManageService monitoringManageService;

	private static class Singleton {
		private static SocketIOServer socketIOserver = null;
	}

	private SocketIOServerpDemon() {
		if(Singleton.socketIOserver == null) {
			try{			
				config.setHostname("0.0.0.0");			
				config.setPort(PORT);
				config.setHttpCompression(false);
				config.setWebsocketCompression(false);
				SocketConfig sockConfig = new SocketConfig();
				sockConfig.setReuseAddress(true);
				config.setSocketConfig(sockConfig);
				Singleton.socketIOserver  = new SocketIOServer(config);
				//			socketIOserver.addEventListener("status", LogDataObject.class, new DataListener<LogDataObject>() {
				//				@Override
				//		        public void onData(SocketIOClient client, LogDataObject data, AckRequest ackRequest) {
				//		            // broadcast messages to all clients
				//		        	System.out.println("data :"+data.getId());
				//		        	socketIOserver.getBroadcastOperations().sendEvent("chatevent", data);
				//		        }
				//
				//		    });
				Singleton.socketIOserver.addConnectListener(new ConnectListener()
				{
					@Override
					public void onConnect(SocketIOClient client)
					{
						socketClient = client;
						System.out.println("client socketio count : "+Singleton.socketIOserver.getAllClients().size());
						
						try {
							//						Singleton.socketIOserver.getBroadcastOperations().sendEvent("status", socketData.toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//								e.printStackTrace();
						}

					}
				});


				Singleton.socketIOserver.addDisconnectListener(new DisconnectListener()
				{

					@Override
					public void onDisconnect(SocketIOClient aClient)
					{
						try {
							//System.out.println("onDisconnect " + aClient.getRemoteAddress().toString());
							System.out.println("Disconnect : "+aClient.get("clientId"));
							Map<String,Object> paramMap = new HashMap<String,Object>();
							paramMap.put("clientId", aClient.get("clientId"));
							paramMap.put("status", 0);
					
							monitoringManageService.saveClientStatus(paramMap);
							aClient.disconnect();					
//							Singleton.socketIOserver.del(aClient.get("clients"));
						
						} catch(Exception e) {
							e.printStackTrace();
						}
						socketMngMap.remove("client_"+aClient.get("clientId"), aClient);
					}
				});
				System.out.println("=============================");
				System.out.println("   SOCKET IO SERVER START    ");
				System.out.println("=============================");
				Singleton.socketIOserver.start();	
				
				Singleton.socketIOserver.addEventListener("clientInfo", ClientInfoObject.class, new DataListener<ClientInfoObject>() {
					@Override
					public void onData(SocketIOClient client, ClientInfoObject data, AckRequest ackSender) throws Exception {
						// TODO Auto-generated method stub
						client.set("clientId", data.getClientId());
						client.set("boardIndex", data.getBoardIndex());
//						Map<String,Object> controlData = new HashMap<String,Object>();
						System.out.println("[clientInfo] data.getClientId()) "+data.getClientId());
						System.out.println("[clientInfo] data.getBoardIndex()) "+data.getBoardIndex());
//						client.joinRoom("room_"+data.getClientId());
						Map<String,Object> paramMap = new HashMap<String,Object>();
						paramMap.put("clientId", data.getClientId());
						paramMap.put("status", 1);
//						Set<String> roomList = SocketIOServerpDemon.getSocketIOClient().getAllRooms();
//						socketMngMap.put("client_"+data.getClientId()+"_"+data.getBoardIndex(), client);
//						Singleton.socketIOserver.getAllClients();
//						for (Map.Entry<String, SocketIOClient> room : socketMngMap.entrySet()) {
//							System.out.println(room.getKey()+" - "+room.getValue());
//
//						}
						
						monitoringManageService.saveClientStatus(paramMap);
//						controlData.put("clientId", data.getClientId());
//						controlData.put("operation", "STOP");
						
//						Singleton.socketIOserver.getBroadcastOperations().sendEvent("clientControl", controlData);
					}
		        });
				
				Singleton.socketIOserver.addEventListener("boardStatus", ClientInfoObject.class, new DataListener<ClientInfoObject>() {
					@Override
					public void onData(SocketIOClient client, ClientInfoObject data, AckRequest ackSender) throws Exception {
						// TODO Auto-generated method stub
						client.set("clientId", data.getClientId());
						client.set("boardIndex", data.getBoardIndex());
//						System.out.println("[boardStatus] boardStatus] clientId : "+data.getClientId());
//						System.out.println("[boardStatus] boardIndex : "+data.getBoardIndex());
//						System.out.println("activeYn : "+data.getActiveYn());
//						Map<String,Object> controlData = new HashMap<String,Object>();
						ClientBoardsInfoVO param = new ClientBoardsInfoVO();
						param.setActiveYn(data.getActiveYn());
						param.setClientId(data.getClientId());
						param.setBoardIndex(data.getBoardIndex());
						try {
							clientsManageService.updateClientBoardInfo(param);
							
							ClientsInfoVO clientParam = new ClientsInfoVO();
							clientParam.setClientId( data.getClientId());
							clientParam.setActiveYn(data.getActiveYn());
//							clientsManageService.updateClientsInfo (clientParam);
						} catch(Exception e) {
							e.printStackTrace();
						}
						
						
//						
//						controlData.put("clientId", data.getClientId());
//						controlData.put("operation", "STOP");
						
//						Singleton.socketIOserver.getBroadcastOperations().sendEvent("clientControl", controlData);
					}
		        });
			}catch(Exception e){
				System.out.println("ERROR : "+e);
			}
		}
	}
	
	public static Map<String, SocketIOClient> getSocketClientMap() {
		return socketMngMap;
	}
	
	public static SocketIOServer getInstance () {
		return Singleton.socketIOserver;
	}

	public static SocketIOClient getSocketIOClient() {
		return socketClient;
	}

	//	public static SocketIOServer getSocketIOServer() {
	//		return socketIOserver;
	//	}
	private static String getIpAddress(){
		String hostAddr = "";
		try {

			Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();

			while (nienum.hasMoreElements()) {

				NetworkInterface ni = nienum.nextElement();

				Enumeration<InetAddress> kk= ni.getInetAddresses();

				while (kk.hasMoreElements()) {

					InetAddress inetAddress = kk.nextElement();

					if (!inetAddress.isLoopbackAddress() && 

							!inetAddress.isLinkLocalAddress() && 

							inetAddress.isSiteLocalAddress()) {

						hostAddr = inetAddress.getHostAddress().toString();

					}

				}

			}

		} catch (SocketException e) {

			e.printStackTrace();

		}
		return hostAddr;
	}
	@PreDestroy
	public void stop() {
		System.out.println("stop server");
		Singleton.socketIOserver.stop();
		InternalThreadLocalMap.destroy(); 
	}
}
