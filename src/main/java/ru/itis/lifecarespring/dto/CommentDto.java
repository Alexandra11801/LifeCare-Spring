package ru.itis.lifecarespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.Comment;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

	private long id;
	private Article article;
	private String senderName;
	private String text;
	private LocalDateTime date;

	public static CommentDto from(Comment comment){
		return CommentDto.builder()
				.id(comment.getId())
				.article(comment.getArticle())
				.senderName(comment.getSenderName())
				.text(comment.getText())
				.date(comment.getDate()).build();
	}

	public static List<CommentDto> from(List<Comment> comments){
		return comments.stream().map(c -> CommentDto.from(c)).collect(Collectors.toList());
	}

}
