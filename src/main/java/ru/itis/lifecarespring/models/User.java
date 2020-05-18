package ru.itis.lifecarespring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.lifecarespring.dto.UserDto;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String surname;
	private String hashPassword;
	private String email;
	private String phone;
	private String imageName;

	@Enumerated(value = EnumType.STRING)
	private State state;
	private String emailConfirmationCode;
	private long smsConfirmationCode;

	@Enumerated(value=EnumType.STRING)
	private Role role;

	@OneToOne
	@JoinColumn(name = "file_id")
	private FileInfo avatar;

}
