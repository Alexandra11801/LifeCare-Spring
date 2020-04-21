package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.RevisionDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.security.UserDetailsImpl;
import ru.itis.lifecarespring.services.ArticleService;
import ru.itis.lifecarespring.services.RevisionsService;

@Controller
@RequestMapping("/suggest-revision")
public class AddRevisionController {

	@Autowired
	private RevisionsService revisionsService;

	@Autowired
	private ArticleService articleService;

	@GetMapping
	public String getSuggestPage(@RequestParam("title")String articleTitle, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal() instanceof String)){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			model.addAttribute("current_user", UserDto.from(details.getUser()));
		}
		model.addAttribute("authorizated", !(auth.getPrincipal() instanceof String));
		ArticleDto article =  articleService.getArticleByTitle(articleTitle);
		model.addAttribute("article", article);
		return "add_revision_page";
	}

	@PostMapping
	public String SuggestRevision(@RequestParam("title")String articleTitle, RevisionDto dto){
		revisionsService.suggestRevision(dto, articleTitle);
		return "redirect:/article/" + articleTitle;
	}

}
