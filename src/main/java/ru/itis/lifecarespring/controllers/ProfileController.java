package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.security.UserDetailsImpl;
import ru.itis.lifecarespring.services.UsersService;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private UsersService usersService;

	@GetMapping("/{id}")
	public String getProfilePage(@PathVariable("id")long id, Model model){
		UserDto user = usersService.getUserById(id);
		model.addAttribute("user", user);
		List<ArticleDto> articles = usersService.getAllArticles(user);
		model.addAttribute("articles", articles);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated()){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			model.addAttribute("current_user", details.getUser());
		}
		model.addAttribute("authorizated", auth.isAuthenticated());
		return "profile_page";
	}

}
