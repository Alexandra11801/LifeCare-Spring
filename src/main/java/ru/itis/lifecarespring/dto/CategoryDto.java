package ru.itis.lifecarespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.lifecarespring.models.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

	private String category;

	public static CategoryDto from(Category category){
		return CategoryDto.builder().category(category.getCategory()).build();
	}

	public static List<CategoryDto> from(List<Category> categories){
		return categories.stream().map(cat -> CategoryDto.from(cat)).collect(Collectors.toList());
	}

}
