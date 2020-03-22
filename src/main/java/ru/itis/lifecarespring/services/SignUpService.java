package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.SignUpDto;

import javax.servlet.http.HttpServletResponse;

public interface SignUpService {
	void signUp(SignUpDto dto, HttpServletResponse response);
	boolean userExists(String email);
}
