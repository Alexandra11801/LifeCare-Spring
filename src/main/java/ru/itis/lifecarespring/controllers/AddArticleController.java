package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.models.Article;
import ru.itis.lifecarespring.models.User;
import ru.itis.lifecarespring.security.UserDetailsImpl;
import ru.itis.lifecarespring.services.ArticleService;

@Controller
@RequestMapping("/add")
public class AddArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping
	public String getAddArticlePage(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal() instanceof String)){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			model.addAttribute("current_user", UserDto.from(details.getUser()));
		}
		model.addAttribute("authorizated", !(auth.getPrincipal() instanceof String));
		return "add_article_page";
	}

	@PostMapping
	public String addArticle(ArticleDto form){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User author = ((UserDetailsImpl)auth.getPrincipal()).getUser();
		articleService.addArticle(form, UserDto.from(author));
		return "redirect:/article/" + form.getTitle();
	}

}
