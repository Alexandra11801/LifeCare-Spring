package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.security.UserDetailsImpl;
import ru.itis.lifecarespring.services.RevisionsService;

@Controller
@RequestMapping("/revisions")
public class RevisionsController {

	@Autowired
	private RevisionsService revisionsService;

	@GetMapping
	public String getRevisionsPage(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal() instanceof String)){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			UserDto user = UserDto.from(details.getUser());
			model.addAttribute("current_user", user);
			model.addAttribute("revisions", revisionsService.allRevisions(user.getId()));
		}
		model.addAttribute("authorizated", !(auth.getPrincipal() instanceof String));
		return "revisions_page";
	}

	@GetMapping("/accept")
	@ResponseBody
	public void accept(@RequestParam("revision") long revisionId){
		revisionsService.accept(revisionId);
	}

	@GetMapping("/reject")
	@ResponseBody
	public void reject(@RequestParam("revision") long revisionId){
		revisionsService.reject(revisionId);
	}

}
