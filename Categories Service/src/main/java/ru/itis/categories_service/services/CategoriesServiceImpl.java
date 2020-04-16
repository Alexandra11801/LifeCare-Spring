package ru.itis.categories_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.categories_service.CategoriesRepository;
import ru.itis.categories_service.dto.CategoryDto;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;


	@Override
	public List<CategoryDto> getCategories() {
		return CategoryDto.from(categoriesRepository.findAll());
	}
}
