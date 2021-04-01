package com.min.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	private final static Logger log = LoggerFactory.getLogger(WebSocketConfig.class);
	

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/WebSocketEx");
		// /user라고 설정해주어서 클라이언트가 connet보낸것을 받을수 있게됨
		registry.addEndpoint("/WebSocketEx").withSockJS();
	}

	// prefix를 /topic으로 주면 컨트롤러를 안거치고 직접 접근하겠다.
	//app은 메세지를 처리할 수 있도록 서버측의 annotated method 로 흐르도록 하기 위한 라우팅이다
	//topic , /queue 는 broker로 흐르도록하기 위한 라우팅이다
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "queue");
		config.setApplicationDestinationPrefixes("/app");
	}
	
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		log.error("webSocketHander : {}", webSocketHandler);
//		
//		registry.addHandler(webSocketHandler,"/ws")
//		.addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*")
////		.withSockJS()
//		;
//	}
	
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		System.out.println("웹소켓 핸들러");
//		registry.addHandler(webSocketHandler,"/chat")
//		.addInterceptors(new HttpSessionHandshakeInterceptor());
//	}	
	
}
