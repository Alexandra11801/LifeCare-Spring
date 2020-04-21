package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lifecarespring.models.Revision;
import ru.itis.lifecarespring.models.User;

import java.util.List;
import java.util.Optional;

public interface RevisionsRepository extends JpaRepository<Revision, Long> {
	Optional<List<Revision>> findAllByArticleAuthor(User articleAuthor);
	Optional<Revision> findById(long id);
}
