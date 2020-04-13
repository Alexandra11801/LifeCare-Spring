package ru.itis.lifecarespring.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.security.UserDetailsImpl;

@Controller
@RequestMapping("/")
public class MainPageController {

	@GetMapping
	public String getMainPage(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal() instanceof String)){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			System.out.println(details.getUser().getName());
			model.addAttribute("current_user", UserDto.from(details.getUser()));
		}
		model.addAttribute("authorizated", !(auth.getPrincipal() instanceof String));
		return "main_page";
	}

}
