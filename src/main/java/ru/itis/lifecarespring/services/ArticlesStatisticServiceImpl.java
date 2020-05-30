package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.lifecarespring.dto.ArticlesStatisticDto;
import ru.itis.lifecarespring.models.ArticlesStatistic;
import ru.itis.lifecarespring.repositories.ArticlesStatisticsRepository;

@Service
public class ArticlesStatisticServiceImpl implements ArticlesStatisticService {

	@Autowired
	ArticlesStatisticsRepository repository;

	@Override
	@Transactional
	public void saveStatistic(ArticlesStatisticDto dto) {
		ArticlesStatistic statistic = ArticlesStatistic.builder()
										.period(dto.getPeriod())
										.articlesNumber(dto.getArticlesNumber()).build();
		repository.save(statistic);
	}
}
