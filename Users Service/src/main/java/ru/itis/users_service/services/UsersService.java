package ru.itis.users_service.services;

import ru.itis.users_service.dto.UserDto;

import java.util.List;

public interface UsersService {
	List<UserDto> getUsers();
}
