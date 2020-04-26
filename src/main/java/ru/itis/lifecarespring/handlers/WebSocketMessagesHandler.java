package ru.itis.lifecarespring.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.lifecarespring.dto.MessageDto;
import ru.itis.lifecarespring.services.MessagesService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

	private static final Map<String, WebSocketSession> sessions = new HashMap<>();

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MessagesService messagesService;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.put(session.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		String messageText = (String) message.getPayload();
		MessageDto messageFromJson = objectMapper.readValue(messageText, MessageDto.class);
		messageFromJson.setDate(LocalDateTime.now());
		messagesService.addMessage(messageFromJson);
		for (WebSocketSession currentSession : sessions.values()) {
			currentSession.sendMessage(message);
		}
	}

}
