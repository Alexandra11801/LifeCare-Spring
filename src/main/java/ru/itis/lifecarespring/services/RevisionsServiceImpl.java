package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.lifecarespring.dto.RevisionDto;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.Revision;
import ru.itis.lifecarespring.models.User;
import ru.itis.lifecarespring.repositories.ArticlesRepository;
import ru.itis.lifecarespring.repositories.RevisionsRepository;
import ru.itis.lifecarespring.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RevisionsServiceImpl implements RevisionsService {

	@Autowired
	private RevisionsRepository revisionsRepository;

	@Autowired
	private ArticlesRepository articlesRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public void suggestRevision(RevisionDto dto, String articleTitle) {
		Optional<Article> optional = articlesRepository.findByTitle(articleTitle);
		if(optional.isPresent()) {
			Article article = optional.get();
			Revision revision = Revision.builder()
					.text(dto.getText())
					.description(dto.getDescription())
					.article(article)
					.articleTitle(articleTitle)
					.articleAuthor(article.getAuthor()).build();
			revisionsRepository.save(revision);
		}
		else{
			throw new RuntimeException("Article not found");
		}
	}

	@Override
	public List<RevisionDto> allRevisions(long userId) {
		Optional<User> user = usersRepository.findById(userId);
		if(user.isPresent()){
			Optional<List<Revision>> revisions = revisionsRepository.findAllByArticleAuthor(user.get());
			if(revisions.isPresent()){
				return RevisionDto.from(revisions.get());
			}
			else{
				return new ArrayList<>();
			}
		}
		else{
			throw new UsernameNotFoundException("User not found");
		}
	}

	@Override
	public void accept(long revisionId) {
		Optional<Revision> optional = revisionsRepository.findById(revisionId);
		if(optional.isPresent()){
			Revision revision = optional.get();
			Article article = revision.getArticle();
			article.setText(revision.getText());
			articlesRepository.save(article);
			revisionsRepository.delete(revision);
		}
	}

	@Override
	public void reject(long revisionId) {
		Optional<Revision> revision = revisionsRepository.findById(revisionId);
		if(revision.isPresent()){
			revisionsRepository.delete(revision.get());
		}
	}
}
