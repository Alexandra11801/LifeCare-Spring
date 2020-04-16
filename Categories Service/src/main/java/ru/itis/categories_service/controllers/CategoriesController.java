package ru.itis.categories_service.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.categories_service.dto.CategoryDto;
import ru.itis.categories_service.services.CategoriesService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;

	@GetMapping
	public List<CategoryDto> getCategories(){
		return categoriesService.getCategories();
	}

}
