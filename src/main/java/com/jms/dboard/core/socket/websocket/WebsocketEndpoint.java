package com.jms.dboard.core.socket.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.jms.dboard.common.config.CustomSpringConfigurator;

import com.jms.dboard.manage.code.service.CodeManageService;
import com.jms.dboard.manage.version.service.VersionManageService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.jms.dboard.manage.monitoring.service.MonitoringManageService;
import com.jms.dboard.manage.vo.MonitoringInfoVO;
import com.jms.dboard.manage.vo.MonitoringReqInfoVO;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/", decoders = MessageDecoder.class, encoders = MessageEncoder.class,
configurator = CustomSpringConfigurator.class)


public class WebsocketEndpoint {
    private Session session;
    static private Set<WebsocketEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    
    @Autowired
    MonitoringManageService monitoringManageService;

	@Autowired
	VersionManageService versionManageService;
    
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
		System.out.println("onOpen ========================");
        this.session = session;
        chatEndpoints.add(this);
//        Message message = new Message();
//        message.setContent("Connected!");
//        Map<String,Object> echoMessage = new HashMap<String,Object>();
//        echoMessage.put("result",  "Connected!");
//        broadcast(session, message);
    }

    @OnMessage
    public void onMessage(Message message) throws IOException, EncodeException {
    	MonitoringInfoVO monitoringInfoVO = new MonitoringInfoVO();
    	monitoringInfoVO.setClientId(message.getClientId());
	   	System.out.println("============= 소켓 메시지 =============");

//    	System.out.println("client id : "+ message.getClientId());
//    	System.out.println("event type : "+ message.getEventType());
    	MonitoringInfoVO monitoringInfoData = new MonitoringInfoVO();
    	String eventType = message.getEventType();
		System.out.println("eventType : "+eventType);
		try {
			if(eventType.equals("clientInfo")) {
				monitoringInfoData = monitoringManageService.getClientMonitoringConf(monitoringInfoVO);
//				System.out.println("FTP IP:" + monitoringInfoData.getFtpInfo().getIpaddr());
//				System.out.println("FTP PORT:" + monitoringInfoData.getFtpInfo().getPort());
//				System.out.println("FTP ID:" + monitoringInfoData.getFtpInfo().getId());
//				System.out.println("FTP PASS:" + monitoringInfoData.getFtpInfo().getPwd());
				 broadcast(session, monitoringInfoData);
			} else if(eventType.equals("monitorInfo")) {
				
				MonitoringReqInfoVO paramInfo = new MonitoringReqInfoVO();
				paramInfo.setClientId(message.getClientId());
				paramInfo.setClientIp(message.getClientIp());
				paramInfo.setMonitorType(Integer.valueOf(message.getMonitorType()));
				paramInfo.setResource(message.getResource());
				paramInfo.setMonitor(message.getMonitor());
				monitoringManageService.saveClientMonitoring(paramInfo);
				monitoringInfoVO.setFtpType("MONITOR");
				monitoringInfoData = monitoringManageService.getClientMonitoringConf(monitoringInfoVO);
				broadcast(session, monitoringInfoData);
				
			} else if(eventType.equals("EMG_PATCH")){

				Map<String, Object> paramMap = new HashMap<String,Object>();

				paramMap.put("clientId",message.getClientId());
				paramMap.put("pageVersion",message.getPageVersion());
				paramMap.put("resourceVersion",message.getResourceVersion());

				Map<String, Object> returnData = versionManageService.getClientVersionInfo(paramMap);
				broadcast(session, returnData);

			} else if(eventType.equals("CHK_PATCH")){
				Map<String, Object> paramMap = new HashMap<String,Object>();

				System.out.println("client id :"+ message.getClientId());
				paramMap.put("clientId",message.getClientId());
				paramMap.put("pageVersion",message.getPageVersion());
				paramMap.put("resourceVersion",message.getResourceVersion());

				Map<String, Object> returnData = versionManageService.getClientVersionInfo(paramMap);

				broadcast(session, returnData);
			} else {
//				System.out.println("정의되지 않은 이벤트 타입니다.["+eventType+"]");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        message.setFrom(users.get(session.getId()));
    	
       
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        chatEndpoints.remove(this);
//        Message message = new Message();
//        message.setFrom(users.get(session.getId()));
//        message.setContent("Disconnected!");
//        broadcast(session, message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    	throwable.printStackTrace();
    }

	public void broadcast(Session session, Object message) throws IOException, EncodeException {
    	try {        	
	    	synchronized (session) {
	//    		session.
	    		if(message.getClass().equals(Message.class))  {
	    			session.getBasicRemote().sendObject((Message) message);
	    		} else {
	    			ObjectMapper om = new ObjectMapper();
	    			String resultJson = om.writeValueAsString(message);
					System.out.println("resultJson :"+resultJson);
	    			session.getBasicRemote().sendText(resultJson);
	    		}
	    	}
    	} catch (IOException | EncodeException e) {
    		e.printStackTrace();
        }
    	//        chatEndpoints.forEach(endpoint -> {
//            synchronized (endpoint) {
//                try {
//                    endpoint.session.getBasicRemote()
//                        .sendObject(message);
//                } catch (IOException | EncodeException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

	static public void broadcastAll(Map<String, Object> message) throws IOException, EncodeException {
		try {

			chatEndpoints.forEach(endpoint -> {
				synchronized (endpoint) {
					try {
						ObjectMapper om = new ObjectMapper();
						String resultJson = om.writeValueAsString(message);
						System.out.println("resultJson :"+resultJson);
						endpoint.session.getBasicRemote().
								sendText(resultJson);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}