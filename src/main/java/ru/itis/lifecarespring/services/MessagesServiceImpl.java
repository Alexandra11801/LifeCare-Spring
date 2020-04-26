package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.lifecarespring.dto.MessageDto;
import ru.itis.lifecarespring.models.Message;
import ru.itis.lifecarespring.repositories.MessagesRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessagesServiceImpl implements MessagesService {

	@Autowired
	private MessagesRepository messagesRepository;

	@Override
	@Transactional
	public List<MessageDto> getLastMessages() {
		Optional<List<Message>> messages = messagesRepository.findAllByDateAfter(LocalDateTime.now().minusHours(24));
		if(messages.isPresent()){
			return MessageDto.from(messages.get());
		}
		else{
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional
	public void addMessage(MessageDto dto) {
		Message message = Message.builder()
							.senderName(dto.getSenderName())
							.text(dto.getText())
							.date(dto.getDate()).build();
		messagesRepository.save(message);
	}
}
