package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.EditDto;
import ru.itis.lifecarespring.dto.UserDto;

public interface EditService {
	void edit(EditDto dto, UserDto user);
}
