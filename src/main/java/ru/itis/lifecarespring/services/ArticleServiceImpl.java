package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.CategoryDto;
import ru.itis.lifecarespring.dto.CommentDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.models.*;
import ru.itis.lifecarespring.repositories.*;

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

	@Autowired
	private CategoriesRepository categoriesRepository;

	@Autowired
	private RatesRepository ratesRepository;

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
		Category category = categoriesRepository.findByCategory(dto.getCategory()).get();
		Article article = Article.builder()
							.title(dto.getTitle())
							.category(category)
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
			return new ArrayList<>();
		}
	}

	@Override
	public List<ArticleDto> getAllArticlesByTitleAndCategory(String title, CategoryDto category) {
		Optional<Category> optional = categoriesRepository.findByCategory(category.getCategory());
		if(optional.isPresent()) {
			Optional<List<Article>> articles = articlesRepository.findAllByCategoryAndTitleContainsIgnoreCase(optional.get(), title);
			if (articles.isPresent()) {
				return ArticleDto.from(articles.get());
			} else {
				return new ArrayList<>();
			}
		}
		else{
			throw new RuntimeException("Category not found");
		}
	}

	@Override
	public int like(ArticleDto dto, UserDto userDto) {
		Optional<Article> optionalArticle = articlesRepository.findById(dto.getId());
		Optional<User> optionalUser = usersRepository.findById(userDto.getId());
		if(optionalArticle.isPresent()){
			if(optionalUser.isPresent()) {
				Article article = optionalArticle.get();
				User user = optionalUser.get();
				if(!ratesRepository.findByArticleAndUser(article, user).isPresent()) {
					article.setLikes(article.getLikes() + 1);
					articlesRepository.save(article);
					Rate rate = Rate.builder().article(article).user(user).build();
					ratesRepository.save(rate);
				}
				return article.getLikes();
			}
			else{
				throw new UsernameNotFoundException("User not found");
			}
		}
		else{
			throw new RuntimeException("Article not found");
		}
	}

	@Override
	public int dislike(ArticleDto dto, UserDto userDto) {
		Optional<Article> optionalArticle = articlesRepository.findById(dto.getId());
		Optional<User> optionalUser = usersRepository.findById(userDto.getId());
		if(optionalArticle.isPresent()){
			if(optionalUser.isPresent()) {
				Article article = optionalArticle.get();
				User user = optionalUser.get();
				if(!ratesRepository.findByArticleAndUser(article, user).isPresent()) {
					article.setDislikes(article.getDislikes() + 1);
					articlesRepository.save(article);
					Rate rate = Rate.builder().article(article).user(user).build();
					ratesRepository.save(rate);
				}
				return article.getDislikes();
			}
			else{
				throw new UsernameNotFoundException("User not found");
			}
		}
		else{
			throw new RuntimeException("Article not found");
		}
	}
}
