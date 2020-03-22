package ru.itis.lifecarespring.services;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import ru.itis.lifecarespring.dto.UserDto;

import java.io.StringWriter;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String userName;

	@Override
	public void sendMail(String subject, UserDto user, String email, String code) {
		VelocityEngine velocity = new VelocityEngine();
		velocity.init();
		VelocityContext context = new VelocityContext();
		context.put("user", user);
		context.put("confirmation_code", code);
		StringWriter writer = new StringWriter();
		velocity.mergeTemplate("src/main/resources/templates/confirmation_mail.ftlh", context, writer);
		MimeMessagePreparator preparator = mimeMessage -> {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setFrom(userName);
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(writer.toString(), true);
		};
		sender.send(preparator);
	}

}
