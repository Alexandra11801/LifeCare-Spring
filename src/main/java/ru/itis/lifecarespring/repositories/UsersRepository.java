package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itis.lifecarespring.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
	Optional<User> findById(long id);
	Optional<User> findByEmail(String email);
	Optional<User> findByEmailConfirmationCode(String code);
	Optional<User> findBySmsConfirmationCode(long code);
}
