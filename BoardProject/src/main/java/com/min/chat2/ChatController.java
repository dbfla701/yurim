package com.min.chat2;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ChatController {
//	
//	@Autowired
//	private SimpMessageSendingOperations messagingTemplate;
//
//    @MessageMapping("/chat/message")
//    public void message(ChatRoom message) {
//    	if (ChatRoom.MessageType.JOIN.equals(message.getType()))
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
//}
