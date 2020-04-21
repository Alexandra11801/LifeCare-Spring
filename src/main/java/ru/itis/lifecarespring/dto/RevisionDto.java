package ru.itis.lifecarespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.Revision;
import ru.itis.lifecarespring.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevisionDto {

	private long id;
	private String text;
	private String description;
	private Article article;
	private String articleTitle;
	private User articleAuthor;

	public static RevisionDto from(Revision revision){
		return RevisionDto.builder()
				.id(revision.getId())
				.text(revision.getText())
				.description(revision.getDescription())
				.article(revision.getArticle())
				.articleTitle(revision.getArticleTitle())
				.articleAuthor(revision.getArticleAuthor()).build();
	}

	public static List<RevisionDto> from(List<Revision> revisions){
		return revisions.stream().map(revision -> RevisionDto.from(revision)).collect(Collectors.toList());
	}

}
