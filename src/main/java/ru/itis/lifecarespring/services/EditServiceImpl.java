package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.lifecarespring.dto.EditDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.models.FileInfo;
import ru.itis.lifecarespring.models.User;
import ru.itis.lifecarespring.repositories.UsersRepository;

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
			user.setName(userDto.getName());
			user.setSurname(userDto.getSurname());
			user.setPhone(userDto.getPhone());
			user.setEmail(userDto.getEmail());
			FileInfo avatar = filesService.save(dto.getAvatar());
			user.setAvatar(avatar);
			usersRepository.save(user);
		}
		else{
			throw new UsernameNotFoundException("User not found");
		}
	}
}
