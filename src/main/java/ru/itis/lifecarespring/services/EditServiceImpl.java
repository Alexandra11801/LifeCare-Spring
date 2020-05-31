package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.lifecarespring.dto.EditDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.models.FileInfo;
import ru.itis.lifecarespring.models.User;
import ru.itis.lifecarespring.repositories.UsersRepository;
import ru.itis.lifecarespring.security.UserDetailsImpl;

import java.util.Optional;

@Service
public class EditServiceImpl implements EditService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private FilesService filesService;

	@Override
	public void edit(EditDto dto, UserDto userDto) {
		Optional<User> optional = usersRepository.findById(userDto.getId());
		if(optional.isPresent()){
			User user = optional.get();
			user.setName(dto.getName());
			user.setSurname(dto.getSurname());
			user.setPhone(dto.getPhone());
			user.setEmail(dto.getEmail());
			if(dto.getAvatar() != null) {
				FileInfo avatar = filesService.save(dto.getAvatar());
				user.setAvatar(avatar);
				user.setImageName(avatar.getStorageName());
			}
			usersRepository.save(user);
			Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getHashPassword());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		else{
			throw new UsernameNotFoundException("User not found");
		}
	}
}
