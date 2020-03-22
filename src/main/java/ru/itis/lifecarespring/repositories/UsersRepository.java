package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.lifecarespring.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
	Optional<User> findById(long id);
	Optional<User> findByEmail(String email);
	Optional<User> findByConfirmationCode(String code);
}
