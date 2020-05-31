package ru.itis.lifecarespring.services;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.lifecarespring.models.FileInfo;
import ru.itis.lifecarespring.repositories.FilesRepository;
import ru.itis.lifecarespring.utils.FilesUtil;

import javax.servlet.http.HttpServletResponse;
import java.awt.im.InputContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

	@Override
	@SneakyThrows
	public void writeToResponse(String fileName, HttpServletResponse response) {
		FileInfo file = filesRepository.findByStorageName(fileName).get();
		response.setContentType(file.getType());
		InputStream stream = new FileInputStream(new File(file.getUrl()));
		IOUtils.copy(stream, response.getOutputStream());
		response.flushBuffer();
	}
}
