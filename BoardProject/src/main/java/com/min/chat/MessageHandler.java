package com.min.chat;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

public class MessageHandler {
	
	private static Logger log = LoggerFactory.getLogger(MessageHandler.class);

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	//WebSocketConfig에서 설정한 destinationprefix + 클라이언트가 전송할 mapping주소 
	//클라이언트가 /app/in 을 붙여 서버로 메시지를 전송한다.
	@MessageMapping("/WebSocketEx") 
	public void showUsers(String msg) {
		System.out.println("msg" + msg);
		HashMap<String, Object> payload = new HashMap<>();
		payload.put("name", "yr");
		simpMessagingTemplate.convertAndSend("/topic/a", payload);
	}

}
