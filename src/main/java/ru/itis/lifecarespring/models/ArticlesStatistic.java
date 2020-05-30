package ru.itis.lifecarespring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "articles_statistics")
public class ArticlesStatistic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String period;
	private int articlesNumber;

}
