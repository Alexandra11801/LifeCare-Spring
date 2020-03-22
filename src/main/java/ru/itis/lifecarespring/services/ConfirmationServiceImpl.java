package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.lifecarespring.models.State;
import ru.itis.lifecarespring.models.User;
import ru.itis.lifecarespring.repositories.UsersRepository;

import java.util.Optional;

@Component
public class ConfirmationServiceImpl implements ConfirmationService{

	@Autowired
	private UsersRepository repository;

	@Override
	public boolean confirm(String confirmCode) {
		Optional<User> userOptional = repository.findByConfirmationCode(confirmCode);
		if(userOptional.isPresent()){
			User user = userOptional.get();
			user.setState(State.CONFIRMED);
			repository.save(user);
			return  true;
		}
		return false;
	}
}
