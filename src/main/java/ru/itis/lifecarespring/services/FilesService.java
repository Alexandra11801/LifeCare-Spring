package ru.itis.lifecarespring.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.lifecarespring.models.FileInfo;

public interface FilesService {
	FileInfo save(MultipartFile file);
}
