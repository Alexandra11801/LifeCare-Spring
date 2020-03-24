package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.ArticleTitleDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.security.UserDetailsImpl;
import ru.itis.lifecarespring.services.ArticleService;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("/{title}")
	public String getSearchPage(@PathVariable("title")String title, Model model){
		List<ArticleDto> articles = articleService.getAllArticlesByTitle(title);
		model.addAttribute("articles", articles);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal() instanceof String)){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			model.addAttribute("current_user", UserDto.from(details.getUser()));
		}
		model.addAttribute("authorizated", !(auth.getPrincipal() instanceof String));
		return "search";
	}

	@PostMapping
	public String search(ArticleTitleDto form){
		return "redirect:/search/" + form.getTitle();
	}

}
