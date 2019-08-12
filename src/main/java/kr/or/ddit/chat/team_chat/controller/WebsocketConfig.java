package kr.or.ddit.chat.team_chat.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer{
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

		 registry.addHandler(socketHandler(), "/socketChat") // /socketChat url을 처리해줄 handler 등록
 			.setAllowedOrigins("*")			 // 어떤 도메인이든 상관없이 처리(cross domain)
 			.addInterceptors(new HttpSessionHandshakeInterceptor())	// 다음pt 설명
 			.withSockJS();
	}

	@Bean
	public SocketChatHandler socketHandler(){
		return new SocketChatHandler();	// socketHandler
	}
}
