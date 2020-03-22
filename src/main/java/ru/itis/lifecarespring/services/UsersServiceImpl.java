package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.models.User;
import ru.itis.lifecarespring.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ArticleService articleService;

	@Override
	public UserDto getUserById(long id) {
		Optional<User> user = usersRepository.findById(id);
		if(user.isPresent()){
			return UserDto.from(user.get());
		}
		else{
			throw new UsernameNotFoundException("User not found");
		}
	}

	@Override
	public List<ArticleDto> getAllArticles(UserDto user) {
		return articleService.allArticles(user);
	}
}
