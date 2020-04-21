package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.Rate;
import ru.itis.lifecarespring.models.User;

import java.util.Optional;

public interface RatesRepository extends JpaRepository<Rate, Long> {
	Optional<Rate> findByArticleAndUser(Article article, User user);
}
