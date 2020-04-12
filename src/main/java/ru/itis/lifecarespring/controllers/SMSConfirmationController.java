package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.lifecarespring.dto.ConfirmDto;
import ru.itis.lifecarespring.services.ConfirmationService;

@Controller
@RequestMapping("/sms_confirm")
public class SMSConfirmationController {

	@Autowired
	private ConfirmationService confirmationService;

	@GetMapping
	public String getConfirationPage(@RequestParam(value = "error", required = false) String error, Model model){
		model.addAttribute("error", error);
		model.addAttribute("authorizated", false);
		return "sms_confirmation_page";
	}

	@PostMapping
	public String confirm(ConfirmDto form){
		boolean confirmed = confirmationService.smsConfirm(form.getCode());
		if(confirmed){
			return "redirect:/";
		}
		else{
			return "redirect:/sms_confirm?error";
		}
	}
}
