package br.edu.ifrn.crud.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.domains.Usuario;

@Controller
@RequestMapping("/usuarios")
public class CadastroUserController {

	@GetMapping("/cadastro")
	public String entrarCadastro(ModelMap modelUser) {
		modelUser.addAttribute("usuario", new Usuario());
		return "/usuario/cadastro";
	}

	@ModelAttribute("profissoes")
	public List<String> getProfissoes() {
		return Arrays.asList("Argricultor", "Engenheiro", "Médico", "Professor", "Outro");
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/salvar")
	public String salvarUser(Usuario usuario, RedirectAttributes rediAttr, HttpSession sessao) {
		Integer uid = (Integer) sessao.getAttribute("uid");
		List<Usuario> listaUsuarios = (List<Usuario>) sessao.getAttribute("listaUsuarios");

		if (uid == null) {
			uid = 1;
		}

		if (listaUsuarios == null) {
			listaUsuarios = new ArrayList<>();
		}

		usuario.setUid(uid);
		listaUsuarios.add(usuario);
		uid++;
		sessao.setAttribute("uid", uid);
		sessao.setAttribute("listaUsuarios", listaUsuarios);

		rediAttr.addFlashAttribute("msgSucesso", "Cadastro de usuário realizado com sucesso!");
		rediAttr.addFlashAttribute("msgErro", "Erro ao tentar cadastrar usuário!");
		return "redirect:/usuario/cadastro";
	}
}
