package ru.itis.lifecarespring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.lifecarespring.dto.UserDto;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String surname;
	private String hashPassword;
	private String email;
	private String imagePath;

	@Enumerated(value = EnumType.STRING)
	private State state;
	private String confirmationCode;

	@Enumerated(value=EnumType.STRING)
	private Role role;


}
