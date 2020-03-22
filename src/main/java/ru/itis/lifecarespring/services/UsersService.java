package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.UserDto;

import java.util.List;

public interface UsersService {
	UserDto getUserById(long id);
	List<ArticleDto> getAllArticles(UserDto user);
}
