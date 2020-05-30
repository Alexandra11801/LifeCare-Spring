package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.CategoryDto;
import ru.itis.lifecarespring.dto.CommentDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.models.Category;

import java.time.LocalDate;
import java.util.List;

public interface ArticleService {
	List<ArticleDto> allArticles(UserDto user);
	ArticleDto getArticleByTitle(String title);
	List<CommentDto> getAllComments(ArticleDto article);
	void addArticle(ArticleDto article, UserDto author);
	List<ArticleDto> getAllArticlesByTitle(String title);
	List<ArticleDto> getAllArticlesByTitleAndCategory(String title, CategoryDto category);
	int like(ArticleDto article, UserDto user);
	int dislike(ArticleDto article, UserDto user);
	List<ArticleDto> getResentArticles(LocalDate date);
}
