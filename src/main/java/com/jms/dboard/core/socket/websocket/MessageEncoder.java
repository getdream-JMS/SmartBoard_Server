package com.jms.dboard.core.socket.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import com.jms.dboard.manage.vo.MonitoringInfoVO;

public class MessageEncoder implements Encoder.Text<Message> {

    private static Gson gson = new Gson();

    @Override
    public String encode(Message message) throws EncodeException {
        String json = gson.toJson(message);
        return json;
    }
    
    public String encode(MonitoringInfoVO message) throws EncodeException {
    	System.out.println("encode :"+message);
        String json = gson.toJson(message);
        return json;
    }
    
    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
