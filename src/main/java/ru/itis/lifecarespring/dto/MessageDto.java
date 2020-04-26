package ru.itis.lifecarespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.lifecarespring.models.Message;

import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {

	private String senderName;
	private String text;
	private LocalDateTime date;

	public static MessageDto from(Message message){
		return MessageDto.builder()
				.senderName(message.getSenderName())
				.text(message.getText())
				.date(message.getDate()).build();
	}

	public static List<MessageDto> from(List<Message> messages){
		return messages.stream().map(message -> MessageDto.from(message)).collect(Collectors.toList());
	}

}
