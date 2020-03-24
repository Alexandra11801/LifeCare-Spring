package ru.itis.lifecarespring.utils;

import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.lifecarespring.models.FileInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Data
public class FilesUtil {

	@Value("${storage.path}")
	private String storagePath;

	public void copyToStorage(MultipartFile file, String storageName) throws IOException {
		Files.copy(file.getInputStream(), Paths.get(storagePath, storageName));
	}

	public FileInfo convertToInfo(MultipartFile file){
		String originalName = file.getOriginalFilename();
		String storageName = createStorageName(originalName);
		return FileInfo.builder()
				.originalName(originalName)
				.storageName(storageName)
				.size(file.getSize())
				.type(file.getContentType())
				.url(getUrl(storageName)).build();
	}

	public String createStorageName(String originalName) {
		return UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalName);
	}

	public String getUrl(String storageName){
		return storagePath + "\\" + storageName;
	}
}
