package br.edu.ifrn.crud.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		List<Usuario> usuariosCadastrados = (List<Usuario>) sessao.getAttribute("usuariosCadastrados");

		if (uid == null) {
			uid = 1;
		}

		if (usuariosCadastrados == null) {
			usuariosCadastrados = new ArrayList<>();
		}

		if (usuario.getUid() == 0) {
			usuario.setUid(uid);
			usuariosCadastrados.add(usuario);
			uid++;
			sessao.setAttribute("uid", uid);
			sessao.setAttribute("usuariosCadastrados", usuariosCadastrados);

			rediAttr.addFlashAttribute("msgSucesso", "Cadastro de usuário realizado com sucesso!");
		} else {
			usuariosCadastrados.remove(usuario);
			usuariosCadastrados.add(usuario);

			rediAttr.addFlashAttribute("msgSucesso", "Edição de usuário realizada com sucesso!");
			return "/usuario/busca";
		}

		return "redirect:/usuarios/cadastro";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/editar/{id}")
	public String iniciarEdicao(@PathVariable("id") Integer idUser, ModelMap model, HttpSession sessao) {

		List<Usuario> usuariosCadastrados = (List<Usuario>) sessao.getAttribute("usuariosCadastrados");

		Usuario usuario = new Usuario();
		usuario.setUid(idUser);

		int pos = usuariosCadastrados.indexOf(usuario);
		usuario = usuariosCadastrados.get(pos);

		model.addAttribute("usuario", usuario);

		return "/usuario/cadastro";
	}
}
