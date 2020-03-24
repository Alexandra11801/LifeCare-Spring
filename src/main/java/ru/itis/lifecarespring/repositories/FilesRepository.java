package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lifecarespring.models.FileInfo;

import java.util.Optional;

public interface FilesRepository extends JpaRepository<FileInfo, Long> {
	Optional<FileInfo> findByStorageName(String storageName);
}
