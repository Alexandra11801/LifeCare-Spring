package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
	Optional<List<Comment>> findAllByArticle(Article article);
}
