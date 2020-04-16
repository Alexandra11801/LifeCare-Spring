package ru.itis.categories_service.services;

import ru.itis.categories_service.dto.CategoryDto;
import ru.itis.categories_service.models.Category;

import java.util.List;

public interface CategoriesService {
	List<CategoryDto> getCategories();
}
