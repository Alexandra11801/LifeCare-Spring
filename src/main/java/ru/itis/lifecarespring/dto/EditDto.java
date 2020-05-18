package ru.itis.lifecarespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditDto {

	@NotEmpty(message = "{errors.not.null}")
	private String name;

	@NotEmpty(message = "{errors.not.null}")
	private String surname;

	@Email(message = "{errors.incorrect.email}")
	private String email;

	@Pattern(message = "{errors.incorrect.phone}", regexp = "((\\+7)|8)([0-9]){10}")
	private String phone;
	private MultipartFile avatar;

}
