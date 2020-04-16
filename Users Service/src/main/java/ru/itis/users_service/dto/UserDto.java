package ru.itis.users_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.users_service.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private long id;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String imagePath;

	public static UserDto from(User user){
		return UserDto.builder().id(user.getId())
				.name(user.getName())
				.surname(user.getSurname())
				.email(user.getEmail())
				.phone(user.getPhone())
				.imagePath(user.getAvatar().getUrl()).build();
	}

	public static List<UserDto> from(List<User> users){
		return users.stream().map(user -> UserDto.from(user)).collect(Collectors.toList());
	}

}
