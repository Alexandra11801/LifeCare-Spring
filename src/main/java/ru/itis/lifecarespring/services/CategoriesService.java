package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.CategoryDto;

import java.util.List;

public interface CategoriesService {
	CategoryDto getCategory(String category);
	List<CategoryDto> allCategories();
}
