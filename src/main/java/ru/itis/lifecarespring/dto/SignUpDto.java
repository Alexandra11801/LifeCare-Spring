package ru.itis.lifecarespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

	private String name;
	private String surname;
	private String password;
	private String repeatPassword;
	private String email;
	private MultipartFile avatar;

}
