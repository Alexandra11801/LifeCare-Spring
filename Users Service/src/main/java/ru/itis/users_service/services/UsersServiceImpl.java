package ru.itis.users_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.users_service.dto.UserDto;
import ru.itis.users_service.repositories.UsersRepository;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<UserDto> getUsers() {
		return UserDto.from(usersRepository.findAll());
	}
}
