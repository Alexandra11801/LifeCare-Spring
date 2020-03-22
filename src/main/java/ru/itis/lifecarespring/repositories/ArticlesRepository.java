package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.User;

import java.util.List;
import java.util.Optional;

public interface ArticlesRepository extends JpaRepository<Article, Long> {
	Optional<List<Article>> findAllByAuthor(User author);
	Optional<Article> findByTitle(String title);
	Optional<Article> findById(long id);
	Optional<List<Article>> findAllByTitleContainsIgnoreCase(String title);

	@Modifying
	@Query("update Article a set a.likes = ?1 where a.id = ?2")
	int setLikesFor(int likes, long id);

	@Modifying
	@Query("update Article a set a.dislikes = ?1 where a.id = ?2")
	int setDisikesFor(int dislikes, long id);
}
