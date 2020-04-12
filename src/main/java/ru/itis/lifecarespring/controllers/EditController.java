package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.lifecarespring.dto.EditDto;
import ru.itis.lifecarespring.dto.UserDto;
import ru.itis.lifecarespring.security.UserDetailsImpl;
import ru.itis.lifecarespring.services.EditService;

@Controller
@RequestMapping("/edit")
public class EditController {

	@Autowired
	private EditService editService;

	@GetMapping
	public String getEditPage(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal() instanceof String)){
			UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
			model.addAttribute("current_user", UserDto.from(details.getUser()));
		}
		model.addAttribute("authorizated", !(auth.getPrincipal() instanceof String));
		return "edit_page";
	}

	@PostMapping
	public String edit(EditDto form){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDto user = UserDto.from(((UserDetailsImpl) auth.getPrincipal()).getUser());
		editService.edit(form, user);
		return "redirect:/profile/" + user.getId();
	}

}
