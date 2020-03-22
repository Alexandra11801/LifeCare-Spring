package ru.itis.lifecarespring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.lifecarespring.models.User;
import ru.itis.lifecarespring.repositories.UsersRepository;

import java.util.Optional;

@Service(value="customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(email);
		if(user.isPresent()){
			return new UserDetailsImpl(user.get());
		}
		throw new UsernameNotFoundException("User not found");
	}
}
