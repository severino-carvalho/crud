package br.edu.ifrn.crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

	@GetMapping("/")
	public String inicio() {
		return "inicio";
	}

	@GetMapping("/login")
	public String login() {
		return "/authentication/login";
	}

	@GetMapping("/login-error")
	public String loginError(ModelMap modelo) {
		modelo.addAttribute("msgErro", "Login ou senha incorretos. Tente novamente.");
		return "/authentication/login";
	}
}
