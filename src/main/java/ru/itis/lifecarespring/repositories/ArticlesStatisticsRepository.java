package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lifecarespring.models.ArticlesStatistic;

public interface ArticlesStatisticsRepository extends JpaRepository<ArticlesStatistic, Long> {
}
