package ru.itis.lifecarespring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cookieValues")
public class CookieValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String value;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
