package ru.itis.lifecarespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.lifecarespring.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {
	Optional<Category> findByCategory(String category);
	List<Category> findAll();
}
