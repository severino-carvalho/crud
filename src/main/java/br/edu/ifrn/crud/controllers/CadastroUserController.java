package br.edu.ifrn.crud.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.domains.Usuario;
import br.edu.ifrn.crud.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class CadastroUserController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/cadastro")
	public String entrarCadastro(ModelMap modelUser) {
		modelUser.addAttribute("usuario", new Usuario());
		return "/usuario/cadastro";
	}

	@ModelAttribute("profissoes")
	public List<String> getProfissoes() {
		return Arrays.asList("Argricultor", "Engenheiro", "Médico", "Professor", "Outro");
	}

	@PostMapping("/salvar")
	public String salvarUser(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr, ModelMap modelo) {

		// Se houver erros no objeto usuário preenchido ele vai ser capturado no IF e
		// não vai ser realizado o cadastro
		if (result.hasErrors()) {
			// É retornada para a mesma página para os erros serem mostrados
			return "/usuario/cadastro";
		}

		/**
		 * IMPLEMENTAÇÃO DO CADASTRO DE USUÁRIOS NO BANCO DE DADOS
		 */

		try {
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			modelo.addAttribute("msgErro", "ERRO INTERNO NO SERVIDOR");
			return "usuario/cadastro";
		}

		return "redirect:/usuarios/cadastro";
	}

	@GetMapping("/editar/{id}")
	public String iniciarEdicao(@PathVariable("id") Integer idUser, ModelMap model) {

		Usuario u = usuarioRepository.findById(idUser).get();

		model.addAttribute("usuario", u);

		return "/usuario/cadastro";
	}
}
