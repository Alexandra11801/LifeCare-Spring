package ru.itis.lifecarespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.Category;
import ru.itis.lifecarespring.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDto {

	private long id;
	private String title;
	private String text;
	private String category;
	private int likes;
	private int dislikes;
	private User author;

	public static ArticleDto from(Article article){
		return ArticleDto.builder()
				.id(article.getId())
				.title(article.getTitle())
				.text(article.getText())
				.category(article.getCategory().getCategory())
				.likes(article.getLikes())
				.dislikes(article.getDislikes())
				.author(article.getAuthor()).build();
	}

	public static List<ArticleDto> from(List<Article> articles){
		return articles.stream().map(a -> ArticleDto.from(a)).collect(Collectors.toList());
	}

}
