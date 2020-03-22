package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.CommentDto;
import ru.itis.lifecarespring.dto.UserDto;

import java.util.List;

public interface ArticleService {
	List<ArticleDto> allArticles(UserDto user);
	ArticleDto getArticleByTitle(String title);
	List<CommentDto> getAllComments(ArticleDto article);
	void addArticle(ArticleDto article, UserDto author);
	List<ArticleDto> getAllArticlesByTitle(String title);
	void like(ArticleDto article);
	void dislike(ArticleDto article);
}
