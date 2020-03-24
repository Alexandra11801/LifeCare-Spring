package ru.itis.lifecarespring.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.lifecarespring.models.FileInfo;
import ru.itis.lifecarespring.repositories.FilesRepository;
import ru.itis.lifecarespring.utils.FilesUtil;

import java.io.IOException;

@Service
public class FilesServiceImpl implements FilesService {

	@Autowired
	private FilesRepository filesRepository;

	@Autowired
	private FilesUtil filesUtil;

	@Override
	@SneakyThrows
	public FileInfo save(MultipartFile file){
		FileInfo info = filesUtil.convertToInfo(file);
		filesRepository.save(info);
		filesUtil.copyToStorage(file, info.getStorageName());
		return info;
	}
}
