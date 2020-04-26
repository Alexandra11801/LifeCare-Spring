package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.MessageDto;

import java.util.List;

public interface MessagesService {
	List<MessageDto> getLastMessages();
	void addMessage(MessageDto dto);
}
