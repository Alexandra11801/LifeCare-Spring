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
import java.util.Random;
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
	private SMSService smsService;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void signUp(SignUpDto dto) {
		FileInfo avatar = filesService.save(dto.getAvatar());
		User user = User.builder().name(dto.getName())
				.surname(dto.getSurname())
				.hashPassword(encoder.encode(dto.getPassword()))
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.avatar(avatar)
				.imageName(avatar.getStorageName())
				.state(State.NOT_CONFIRMED)
				.emailConfirmationCode(UUID.randomUUID().toString())
				.smsConfirmationCode((new Random(89999)).nextInt() + 100000)
				.role(Role.USER).build();
		usersRepository.save(user);
		if(dto.getConfirmation().equals("Confirmation.EMAIL")) {
			emailService.sendMail("Confirmation", UserDto.from(user), user.getEmail(), user.getEmailConfirmationCode());
		}
		else{
			String text = "Your confirmation code: " + user.getSmsConfirmationCode();
			smsService.sendMessage(user.getPhone(), text);
		}
	}

	@Override
	public boolean userExists(String email) {
		return usersRepository.findByEmail(email).isPresent();
	}
}
