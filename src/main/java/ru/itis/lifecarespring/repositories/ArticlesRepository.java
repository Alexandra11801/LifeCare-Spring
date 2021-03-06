package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.Category;
import ru.itis.lifecarespring.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArticlesRepository extends JpaRepository<Article, Long> {
	Optional<List<Article>> findAllByAuthor(User author);
	Optional<Article> findByTitle(String title);
	Optional<Article> findById(long id);
	Optional<List<Article>> findAllByTitleContainsIgnoreCase(String title);
	Optional<List<Article>> findAllByCategoryAndTitleContainsIgnoreCase(Category category, String title);
	Optional<List<Article>> findAllByCreatedAtAfter(LocalDate date);
}
