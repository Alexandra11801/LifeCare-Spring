package ru.itis.users_service.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.users_service.dto.UserDto;
import ru.itis.users_service.services.UsersService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping
	public List<UserDto> getUsers(){
		return usersService.getUsers();
	}

}
