package ru.itis.categories_service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.categories_service.models.Category;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Category, Integer>{
	List<Category> findAll();
}
