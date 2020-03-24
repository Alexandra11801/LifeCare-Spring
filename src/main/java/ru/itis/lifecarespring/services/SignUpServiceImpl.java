package ru.itis.lifecarespring.services;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.lifecarespring.dto.SignUpDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.models.*;
import ru.itis.lifecarespring.repositories.CookiesRepository;
import ru.itis.lifecarespring.repositories.UsersRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private FilesService filesService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void signUp(SignUpDto dto) {
		FileInfo avatar = filesService.save(dto.getAvatar());
		User user = User.builder().name(dto.getName())
				.surname(dto.getSurname())
				.hashPassword(encoder.encode(dto.getPassword()))
				.email(dto.getEmail())
				.avatar(avatar)
				.state(State.NOT_CONFIRMED)
				.confirmationCode(UUID.randomUUID().toString())
				.role(Role.USER).build();
		usersRepository.save(user);
		emailService.sendMail("Confirmation", UserDto.from(user), user.getEmail(), user.getConfirmationCode());
	}

	@Override
	public boolean userExists(String email) {
		return usersRepository.findByEmail(email).isPresent();
	}
}
