package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.lifecarespring.dto.AddedCommentDto;
import ru.itis.lifecarespring.dto.ArticleDto;
import ru.itis.lifecarespring.dto.CommentDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.security.UserDetailsImpl;
import ru.itis.lifecarespring.services.ArticleService;
import ru.itis.lifecarespring.services.CommentsService;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentsService commentsService;

	@GetMapping("/{title}")
	public String getArticlePage(@PathVariable("title")String title, Model model){
		ArticleDto article = articleService.getArticleByTitle(title);
		model.addAttribute("article", article);
		model.addAttribute("author", UserDto.from(article.getAuthor()));
		List<CommentDto> comments = articleService.getAllComments(article);
		model.addAttribute("comments", comments);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal() instanceof String)){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			model.addAttribute("current_user", UserDto.from(details.getUser()));
		}
		model.addAttribute("authorizated", !(auth.getPrincipal() instanceof String));
		return "article_page";
	}

	@PostMapping("/{title}")
	public String comment(@PathVariable("title")String title, AddedCommentDto form){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String senderName = !(auth.getPrincipal() instanceof String) ? ((UserDetailsImpl)auth.getPrincipal()).getUser().getName() +
				" " + ((UserDetailsImpl)auth.getPrincipal()).getUser().getSurname() : "Anonymous";
		commentsService.addComment(form, senderName);
		return "redirect:/article/" + title;
	}

	@GetMapping("/like")
	@ResponseBody
	public int like(@RequestParam("article") String title){
		ArticleDto article = articleService.getArticleByTitle(title);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDto user = UserDto.from (((UserDetailsImpl) auth.getPrincipal()).getUser());
		return articleService.like(article, user);
	}

	@GetMapping("/dislike")
	@ResponseBody
	public int dislike(@RequestParam("article") String title){
		ArticleDto article = articleService.getArticleByTitle(title);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDto user = UserDto.from (((UserDetailsImpl) auth.getPrincipal()).getUser());
		return articleService.dislike(article, user);
	}
}
