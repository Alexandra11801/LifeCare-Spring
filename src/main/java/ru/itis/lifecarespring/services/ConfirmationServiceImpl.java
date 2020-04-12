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
	public boolean emailConfirm(String confirmCode) {
		Optional<User> userOptional = repository.findByEmailConfirmationCode(confirmCode);
		if(userOptional.isPresent()){
			User user = userOptional.get();
			user.setState(State.CONFIRMED);
			repository.save(user);
			return  true;
		}
		return false;
	}

	@Override
	public boolean smsConfirm(long confirmCode) {
		Optional<User> optional = repository.findBySmsConfirmationCode(confirmCode);
		if(optional.isPresent()){
			User user = optional.get();
			user.setState(State.CONFIRMED);
			repository.save(user);
			return true;
		}
		return false;
	}
}
