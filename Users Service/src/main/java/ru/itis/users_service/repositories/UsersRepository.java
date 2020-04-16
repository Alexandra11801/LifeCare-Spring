package ru.itis.users_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.users_service.models.User;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
	List<User> findAll();
}
