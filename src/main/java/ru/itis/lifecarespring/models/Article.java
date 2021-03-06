package ru.itis.lifecarespring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;

	@Lob
	private String text;

	@ManyToOne
	@JoinColumn(name="cathegory_id")
	private Category category;
	private int likes;
	private int dislikes;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;
	private LocalDate createdAt;
}
