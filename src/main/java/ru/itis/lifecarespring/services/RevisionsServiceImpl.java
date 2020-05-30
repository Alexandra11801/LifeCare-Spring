package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	@Transactional
	public void suggestRevision(RevisionDto dto, String articleTitle) {
		Optional<Article> optional = articlesRepository.findByTitle(articleTitle);
		if(optional.isPresent()) {
			Article article = optional.get();
			Revision revision = Revision.builder()
					.text(dto.getText())
					.description(dto.getDescription())
					.article(article)
					.articleTitle(articleTitle)
					.articleAuthor(article.getAuthor())
					.handled(false).build();
			revisionsRepository.save(revision);
		}
		else{
			throw new RuntimeException("Article not found");
		}
	}

	@Override
	@Transactional
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
	@Transactional
	public void accept(long revisionId) {
		Optional<Revision> optional = revisionsRepository.findById(revisionId);
		if(optional.isPresent()){
			Revision revision = optional.get();
			Article article = revision.getArticle();
			article.setText(revision.getText());
			articlesRepository.save(article);
			revision.setHandled(true);
			revisionsRepository.save(revision);
		}
	}

	@Override
	@Transactional
	public void reject(long revisionId) {
		Optional<Revision> optional = revisionsRepository.findById(revisionId);
		if(optional.isPresent()){
			Revision revision = optional.get();
			revision.setHandled(true);
			revisionsRepository.save(revision);
		}
	}
}
