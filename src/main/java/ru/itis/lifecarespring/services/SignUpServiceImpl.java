package ru.itis.lifecarespring.services;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.lifecarespring.dto.SignUpDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.models.CookieValue;
import ru.itis.lifecarespring.models.Role;
import ru.itis.lifecarespring.models.State;
import ru.itis.lifecarespring.models.User;
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
	private CookiesRepository cookiesRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void signUp(SignUpDto dto, HttpServletResponse response) {
		User user = User.builder().name(dto.getName())
				.surname(dto.getSurname())
				.hashPassword(encoder.encode(dto.getPassword()))
				.email(dto.getEmail())
				.imagePath(dto.getImagePath())
				.state(State.NOT_CONFIRMED)
				.confirmationCode(UUID.randomUUID().toString())
				.role(Role.USER).build();
		usersRepository.save(user);
		String value = UUID.randomUUID().toString();
//		CookieValue cookieValue = CookieValue.builder().value(value).user(user).build();
//		cookiesRepository.save(cookieValue);
		response.addCookie(new Cookie("username", dto.getEmail()));
		response.addCookie(new Cookie("password", encoder.encode(dto.getPassword())));
		emailService.sendMail("Confirmation", UserDto.from(user), user.getEmail(), user.getConfirmationCode());
	}

	@Override
	public boolean userExists(String email) {
		return usersRepository.findByEmail(email).isPresent();
	}
}
