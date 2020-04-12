package ru.itis.lifecarespring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.lifecarespring.dto.CategoryDto;
import ru.itis.lifecarespring.models.Category;
import ru.itis.lifecarespring.repositories.CategoriesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService{

	@Autowired
	private CategoriesRepository repository;

	@Override
	public CategoryDto getCategory(String category) {
		Optional<Category> optional = repository.findByCategory(category);
		if(optional.isPresent()){
			return CategoryDto.from(optional.get());
		}
		else{
			throw new RuntimeException("Category not found");
		}
	}

	@Override
	public List<CategoryDto> allCategories() {
		return CategoryDto.from(repository.findAll());
	}
}
