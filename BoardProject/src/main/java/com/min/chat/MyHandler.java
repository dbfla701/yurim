package com.min.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyHandler extends TextWebSocketHandler {

	private static final Logger log = LoggerFactory.getLogger(MyHandler.class);

	// 방법일 일대일챗팅 map사용
    private Map<String, WebSocketSession> sessions
    = new HashMap<String, WebSocketSession>();

	private List<WebSocketSession> users;
	private Map<String, WebSocketSession> userMap;
//	private Map<String, Object> userMap;
//	private Logger log = LoggerFactory.getLogger(this.getClass());

	public MyHandler() {
		users = new ArrayList<WebSocketSession>();
		userMap = new ConcurrentHashMap<>();
	}

	@Override
	// 소켓 연결 생성 후 실행 메서드
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("TextWebSocketHandler : 연결 생성!");
		users.add(session);
		// Map사용시
        sessions.put(session.getId(), session);
        log.info("{} 연결됨",session.getId());

	}

	@Override
	// 메시지 수신 후 실행 메서드
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("TextWebSocketHandler : 메시지 수신!");
		log.info("메시지 : " + message.getPayload());
		JSONObject object = new JSONObject(message.getPayload());
		String senderId = object.getString("id");
		String sendMsg = object.getString("msg");
		String sendTargetId = object.getString("targetId");

		log.info("id : {}, msg : {}, targetId : {}", senderId, sendMsg, sendTargetId);

		users.stream().forEach(v -> {
			try {
				v.sendMessage(message);
			} catch (IOException e) {
				log.error("handleTextMessage", e);
			}
		});
	
//		for(WebSocketSession sess : users) { //현재 접속된 모든 사람이 여기에 저장되어 있다.
//			sess.sendMessage(new TextMessage("echo:"+message.getPayload()));
//		}
		
//		Iterator<String> sessionIds = sessions.keySet().iterator();
//        String sessionId="";
//        while(sessionIds.hasNext()){
//            sessionId = sessionIds.next();
//            sessions.get(sessionId).sendMessage
//            (new TextMessage("echo:" + message.getPayload()));
//            
//        }
//			if(sess.getName().equals(senderId)) {
//				sess.sendMessage(new TextMessage(session.getName()+"|"+sendMsg));
//				//for문 도는 로그인한 리스트 중에 if문에 해당되는 아이디에게 메시지를 보냄.
//			}else if(sess.getName().equals(session.getPrincipal().getName())){
//				sess.sendMessage(new TextMessage(session.getName()+"|"+sendMsg));
//			}
//		}
		
//for문 도는 로그인한 리스트 중에 if문에 해당되는 아이디에게 메시지를 보냄 .
        }

	@Override
	// 연결 해제 후 실행 메서드
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("TextWebSocketHandler : 연결 종료!");
		users.remove(session);
		// map
		sessions.remove("{} 연결 끊김", session.getId());
	}
}
