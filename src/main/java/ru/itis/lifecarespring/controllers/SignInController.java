package ru.itis.lifecarespring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signin")
public class SignInController {

	@GetMapping
	public String getSignInPage(@RequestParam(value = "error", required = false) String error, Model model){
		model.addAttribute("error", error);
		model.addAttribute("authorizated", false);
		return "signin_page";
	}

}
