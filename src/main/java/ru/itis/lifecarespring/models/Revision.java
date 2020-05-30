package ru.itis.lifecarespring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "revisions")
@Where(clause = "handled = false")
public class Revision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Lob
	private String text;

	@Lob
	private String description;

	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;
	private String articleTitle;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User articleAuthor;
	private boolean handled;

}
