package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.UserDto;

public interface EmailService {
	void sendMail(String subject, UserDto user, String email, String code);
}
