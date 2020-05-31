package ru.itis.lifecarespring.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.lifecarespring.models.FileInfo;

import javax.servlet.http.HttpServletResponse;

public interface FilesService {
	FileInfo save(MultipartFile file);
	void writeToResponse(String fileName, HttpServletResponse response);
}
