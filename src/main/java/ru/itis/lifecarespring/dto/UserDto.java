package ru.itis.lifecarespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.lifecarespring.models.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private long id;
	private String name;
	private String surname;
	private String email;
	private String imagePath;

	public static UserDto from(User user){
		return UserDto.builder().id(user.getId()).name(user.getName()).surname(user.getSurname()).email(user.getEmail()).imagePath(user.getImagePath()).build();
	}

}
