package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.lifecarespring.dto.AddedCommentDto;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.Comment;
import ru.itis.lifecarespring.repositories.ArticlesRepository;
import ru.itis.lifecarespring.repositories.CommentsRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private ArticlesRepository articlesRepository;

	@Override
	@Transactional
	public void addComment(AddedCommentDto form, String senderName) {
		Optional<Article> article = articlesRepository.findById(form.getArticleId());
		if(article.isPresent()){
			commentsRepository.save(Comment.builder()
									.article(article.get())
									.senderName(senderName)
									.text(form.getText())
									.date(LocalDateTime.now()).build());
		}
		else{
			throw new RuntimeException("Article not found");
		}
	}
}
