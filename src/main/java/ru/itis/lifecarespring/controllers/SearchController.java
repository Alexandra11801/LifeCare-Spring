package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.ArticleTitleDto;
import ru.itis.lifecarespring.dto.CategoryDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.security.UserDetailsImpl;
import ru.itis.lifecarespring.services.ArticleService;
import ru.itis.lifecarespring.services.CategoriesService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoriesService categoriesService;

	@GetMapping
	public String getSearchPage(Model model){
		model.addAttribute("articles", new ArrayList<ArticleDto>());
		List<CategoryDto> categories = categoriesService.allCategories();
		model.addAttribute("categories", categories);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal() instanceof String)){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			model.addAttribute("current_user", UserDto.from(details.getUser()));
		}
		model.addAttribute("authorizated", !(auth.getPrincipal() instanceof String));
		return "search_page";
	}

	@GetMapping("/{title}")
	public String getSearchResultsPage(@PathVariable(value = "title")String title,
	                                   @RequestParam(value="category", required = false) String category, Model model){
		List<ArticleDto> articles;
		if(category == null) {
			articles = articleService.getAllArticlesByTitle(title);
		}
		else{
			CategoryDto categoryDto = categoriesService.getCategory(category);
			articles = articleService.getAllArticlesByTitleAndCategory(title, categoryDto);
		}
		List<CategoryDto> categories = categoriesService.allCategories();
		model.addAttribute("articles", articles);
		model.addAttribute("categories", categories);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal() instanceof String)){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			model.addAttribute("current_user", UserDto.from(details.getUser()));
		}
		model.addAttribute("authorizated", !(auth.getPrincipal() instanceof String));
		return "search_page";
	}

	@PostMapping("/*")
	public String search(ArticleTitleDto form){
		List<String> categories = Arrays.asList(form.getCategory().split(","));
		String category = categories.get(categories.size() - 1);
		if(category.equals("All categories")) {
			return "redirect:/search/" + form.getTitle();
		}
		else{
			return "redirect:/search/" + form.getTitle() + "?category=" + category;
		}
	}

}
