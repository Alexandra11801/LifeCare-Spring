package ru.itis.lifecarespring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.lifecarespring.handlers.AuthHandshakeHandler;
import ru.itis.lifecarespring.handlers.WebSocketMessagesHandler;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private WebSocketMessagesHandler webSocketMessagesHandler;

	@Autowired
	private AuthHandshakeHandler handshakeHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
		webSocketHandlerRegistry.addHandler(webSocketMessagesHandler, "/sockets").setHandshakeHandler(handshakeHandler);
	}
}
