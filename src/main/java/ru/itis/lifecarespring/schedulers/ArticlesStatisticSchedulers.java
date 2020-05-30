package ru.itis.lifecarespring.schedulers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.ArticlesStatisticDto;
import ru.itis.lifecarespring.services.ArticleService;
import ru.itis.lifecarespring.services.ArticlesStatisticService;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
@Slf4j
public class ArticlesStatisticSchedulers {

	@Autowired
	private ArticlesStatisticService statisticService;

	@Autowired
	private ArticleService articleService;

	@Transactional
	@Scheduled(cron = "0 0 10 20 * ?")
	public void run(){
		List<ArticleDto> resentArticles = articleService.getResentArticles(LocalDate.now());
		String period = LocalDate.now().minusMonths(1).toString() + " - " + LocalDate.now().toString();
		ArticlesStatisticDto statistic = ArticlesStatisticDto.builder()
										.period(period).articlesNumber(resentArticles.size()).build();
		statisticService.saveStatistic(statistic);
	}

}
