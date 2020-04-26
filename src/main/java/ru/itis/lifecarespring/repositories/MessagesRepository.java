package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lifecarespring.models.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MessagesRepository extends JpaRepository<Message, Long> {
	Optional<List<Message>> findAllByDateAfter(LocalDateTime date);
}
