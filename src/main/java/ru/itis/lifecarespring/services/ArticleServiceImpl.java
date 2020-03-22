package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.CommentDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.Comment;
import ru.itis.lifecarespring.models.User;
import ru.itis.lifecarespring.repositories.ArticlesRepository;
import ru.itis.lifecarespring.repositories.CommentsRepository;
import ru.itis.lifecarespring.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticlesRepository articlesRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private CommentsRepository commentsRepository;

	@Override
	public List<ArticleDto> allArticles(UserDto dto) {
		Optional<User> user = usersRepository.findById(dto.getId());
		if(user.isPresent()){
			Optional<List<Article>> articles = articlesRepository.findAllByAuthor(user.get());
			if(articles.isPresent()){
				return ArticleDto.from(articles.get());
			}
			else{
				return new ArrayList<ArticleDto>() ;
			}
		}
		else{
			throw new UsernameNotFoundException("User not found");
		}
	}

	@Override
	public ArticleDto getArticleByTitle(String title) {
		Optional<Article> article = articlesRepository.findByTitle(title);
		if(article.isPresent()){
			return ArticleDto.from(article.get());
		}
		else {
			throw new RuntimeException("Article not found");
		}
	}

	@Override
	public List<CommentDto> getAllComments(ArticleDto dto) {
		Optional<Article> article = articlesRepository.findByTitle(dto.getTitle());
		if(article.isPresent()){
			Optional<List<Comment>> comments = commentsRepository.findAllByArticle(article.get());
			if(comments.isPresent()){
				return CommentDto.from(comments.get());
			}
			else{
				return new ArrayList<CommentDto>();
			}
		}
		else{
			throw new RuntimeException("Article not found");
		}
	}

	@Override
	public void addArticle(ArticleDto dto, UserDto author) {
		Article article = Article.builder()
							.title(dto.getTitle())
							.category(dto.getCategory())
							.text(dto.getText())
							.author(usersRepository.findById(author.getId()).get())
							.likes(0).dislikes(0).build();
		articlesRepository.save(article);
	}

	@Override
	public List<ArticleDto> getAllArticlesByTitle(String title) {
		Optional<List<Article>> articles = articlesRepository.findAllByTitleContainsIgnoreCase(title);
		if(articles.isPresent()){
			return ArticleDto.from(articles.get());
		}
		else{
			return new ArrayList<ArticleDto>();
		}
	}

	@Override
	public void like(ArticleDto article) {
		articlesRepository.setLikesFor(article.getLikes() + 1, article.getId());
	}

	@Override
	public void dislike(ArticleDto article) {
		articlesRepository.setDisikesFor(article.getDislikes() + 1, article.getId());
	}
}
