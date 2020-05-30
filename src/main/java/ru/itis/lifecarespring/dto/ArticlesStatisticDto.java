package ru.itis.lifecarespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.lifecarespring.models.ArticlesStatistic;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticlesStatisticDto {

	private long id;
	private String period;
	private int articlesNumber;

	public ArticlesStatisticDto from(ArticlesStatistic statistic){
		return ArticlesStatisticDto.builder().id(statistic.getId())
				.period(statistic.getPeriod())
				.articlesNumber(statistic.getArticlesNumber()).build();
	}

}
