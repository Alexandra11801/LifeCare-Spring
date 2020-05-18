package ru.itis.lifecarespring.services;

import org.springframework.security.core.Authentication;
import ru.itis.lifecarespring.dto.EditDto;
import ru.itis.lifecarespring.dto.UserDto;

public interface EditService {
	void edit(EditDto dto, UserDto user);
}
