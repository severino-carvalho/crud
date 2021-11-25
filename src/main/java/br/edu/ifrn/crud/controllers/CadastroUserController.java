package br.edu.ifrn.crud.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.domains.Profissao;
import br.edu.ifrn.crud.domains.Usuario;
import br.edu.ifrn.crud.dto.AutocompleteDTO;
import br.edu.ifrn.crud.repository.ProfissaoRepository;
import br.edu.ifrn.crud.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class CadastroUserController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProfissaoRepository profissaoRepository;

	@GetMapping("/cadastro")
	public String entrarCadastro(ModelMap modelUser) {
		modelUser.addAttribute("usuario", new Usuario());
		return "/usuario/cadastro";
	}

	/*
	 * @ModelAttribute("profissoes") public List<String> getProfissoes() { return
	 * Arrays.asList("Argricultor", "Engenheiro", "Médico", "Professor", "Outro"); }
	 */

	@PostMapping("/salvar")
	@Transactional(readOnly = false)
	public String salvarUser(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr, ModelMap modelo) {

		// Verifica se ele selecionou uma profissão válida
		if (usuario.getProfissao().getId() == 0) {
			result.addError(new ObjectError("ProfissaoNUll", "Profissão válida não informada!"));
		}

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
			attr.addFlashAttribute("msgSucesso", "Usuário cadastrado com sucesso!");
		} catch (Exception e) {
			modelo.addAttribute("msgErro", "ERRO INTERNO NO SERVIDOR");
			return "usuario/cadastro";
		}

		return "redirect:/usuarios/cadastro";
	}

	@GetMapping("/editar/{id}")
	@Transactional(readOnly = true)
	public String iniciarEdicao(@PathVariable("id") Integer idUser, ModelMap model) {

		Usuario u = usuarioRepository.findById(idUser).get();

		model.addAttribute("usuario", u);

		return "/usuario/cadastro";
	}

	@GetMapping("/autocompleteProfissoes")
	@Transactional(readOnly = true)
	@ResponseBody
	public List<AutocompleteDTO> autocompleteProfissao(@RequestParam("term") String termo) {
		List<Profissao> profissoes = profissaoRepository.findByNome(termo);

		List<AutocompleteDTO> resultados = new ArrayList<>();

		profissoes.forEach(p -> resultados.add(new AutocompleteDTO(p.getNome(), p.getId())));

		return resultados;
	}
}
