package br.edu.ifrn.crud.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
	public String salvarUser(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr, HttpSession sessao) {

		// Execulta o método de validação e se houver erros coloca o seu retorno dentro da variável validação
		List<String> validacao = validacao(usuario);
		
		// Se houver erro então vai ser mandado para a página.
		if (!validacao.isEmpty()) {
			attr.addFlashAttribute("msgErro", validacao);
			return "redirect:/usuarios/cadastro";
		}
		
		// Se houver erros no objeto usuário preenchido ele vai ser capturado no IF e não vai ser realizado o cadastro
		if (result.hasErrors()) {
			// É retornada para a mesma página para os erros serem mostrados
			return "/usuario/cadastro"; 
		}
		
		Integer id = (Integer) sessao.getAttribute("id");
		List<Usuario> usuariosCadastrados = (List<Usuario>) sessao.getAttribute("usuariosCadastrados");

		if (id == null) {
			id = 1;
		}

		if (usuariosCadastrados == null) {
			usuariosCadastrados = new ArrayList<>();
		}

		if (usuario.getId() == 0) {

			usuario.setId(id);
			usuariosCadastrados.add(usuario);

			id++;

			sessao.setAttribute("id", id);
			sessao.setAttribute("usuariosCadastrados", usuariosCadastrados);

			attr.addFlashAttribute("msgSucesso", "Cadastro de usuário realizado com sucesso!");

		} else {

			usuariosCadastrados.remove(usuario);
			usuariosCadastrados.add(usuario);

			attr.addFlashAttribute("msgSucesso", "Edição de usuário realizada com sucesso!");

		}

		return "redirect:/usuarios/cadastro";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/editar/{id}")
	public String iniciarEdicao(@PathVariable("id") Integer idUser, ModelMap model, HttpSession sessao) {

		List<Usuario> usuariosCadastrados = (List<Usuario>) sessao.getAttribute("usuariosCadastrados");

		Usuario usuario = new Usuario();
		usuario.setId(idUser);

		int pos = usuariosCadastrados.indexOf(usuario);
		usuario = usuariosCadastrados.get(pos);

		model.addAttribute("usuario", usuario);

		return "/usuario/cadastro";
	}
	
	public List<String> validacao(Usuario u) {
		List<String> validation = new ArrayList<>();
		
		if (u.getNome() == null || u.getNome().isEmpty()) {
			validation.add("Campo 'Nome' é obrigatório.");
		}
		if (u.getNome().length() < 3) {
			validation.add("O campo 'Nome' deve conter ao menos 3 caracteres.");
		}
		if (u.getEmail() == null || u.getEmail().isEmpty()) {
			validation.add("Campo 'Email' é obrigatório.");
		}
		if (u.getTelefone() == null || u.getTelefone().isEmpty()) {
			validation.add("Campo 'Telefone' é obrigatório.");
		}
		if (u.getTelefone().length() != 9) {
			validation.add("O campo 'Telefone' deve conter somente 9 dígitos.");
		}
		if (u.getSenha() == null || u.getSenha().isEmpty()) {
			validation.add("Campo 'Senha' é obrigatório.");
		}
		if (u.getSenha().length() < 6 || u.getSenha().length() > 15) {
			validation.add("A senha deve conter dentre 6 a 15 caractéres");
		}
		
		if (u.getSexo() == null || u.getSexo().isEmpty()) {
			validation.add("Campo 'Sexo' é obrigatório.");
		}
		
		if (u.getProfissao() == null || u.getProfissao().isEmpty()) {
			validation.add("Campo 'Profissao' é obrigatório.");
		}
		
		return validation;
	}
}
