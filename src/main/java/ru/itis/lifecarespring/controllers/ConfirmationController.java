package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.lifecarespring.services.ConfirmationService;

@Controller
@RequestMapping("/email_confirm")
public class ConfirmationController {

	@Autowired
	private ConfirmationService service;

	@GetMapping("/{confirmation_code}")
	public String getConfirmPage(@PathVariable("confirmation_code") String code, Model model){
		boolean confirmed = service.emailConfirm(code);
		model.addAttribute("confirmed", confirmed);
		return "confirmation_page";
	}

}
