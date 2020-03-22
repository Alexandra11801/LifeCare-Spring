package ru.itis.lifecarespring.controllers;

import com.sun.deploy.net.HttpResponse;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.lifecarespring.dto.SignUpDto;
import ru.itis.lifecarespring.services.SignUpService;

import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	private SignUpService service;

	@GetMapping
	public String getSignUpPage(@RequestParam(value = "incorrect_password", required = false) String incorrect_password,
	                            @RequestParam(value = "password_invalid", required = false) String password_invalid,
	                            @RequestParam(value = "user_exists", required = false) String user_exists,
	                            @RequestParam(value = "email_invalid", required = false) String email_invalid, Model model){
		if(password_invalid != null){
			model.addAttribute("password_invalid", true);
		}
		if(incorrect_password != null){
			model.addAttribute("incorrect_password", true);
		}
		if(user_exists != null){
			model.addAttribute("user_exists", true);
		}
		if(email_invalid != null){
			model.addAttribute("incorrect_email", true);
		}
		model.addAttribute("authorizated", false);
		return "signup_page";
	}

	@PostMapping
	public String signUp(SignUpDto form, HttpServletResponse response){
		if(!passwordValid(form.getPassword())){
			return "redirect:/signup?password_invalid";
		}
		if(!form.getPassword().equals(form.getRepeatPassword())){
			return "redirect:/signup?incorrect_password";
		}
		if(!emailValid(form.getEmail())){
			return "redirect:/signup?email_invalid";
		}
		if(service.userExists(form.getEmail())){
			return "redirect:/signup?user_exists";
		}
		service.signUp(form, response);
		return "redirect:/";
	}

	public boolean emailValid(String email){
		Pattern pattern = Pattern.compile("([0-9]|[a-z]|[A-Z]|_|-|\\.)+@[a-z]+\\.[a-z]+");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public boolean passwordValid(String password){
		Pattern pattern = Pattern.compile("([0-9]|[a-z]|[A-Z]){8,}");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

}
