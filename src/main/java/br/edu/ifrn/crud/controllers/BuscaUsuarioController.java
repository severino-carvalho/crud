/**
 * 
 */
package br.edu.ifrn.crud.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifrn.crud.domains.Usuario;

/**
 * @author netin
 *
 */
@Controller
@RequestMapping("/usuarios")
public class BuscaUsuarioController {

	@GetMapping("/busca")
	public String entrarBusca() {
		return "/usuario/busca";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/buscar")
	public String buscar(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "mostrarTodosDados", required = false) Boolean mostrarTodosDados, HttpSession sessao,
			ModelMap model) {

		List<Usuario> usuariosCadastrados = (List<Usuario>) sessao.getAttribute("usuariosCadastrados");

		List<Usuario> usuariosEncontrados = new ArrayList<>();

		if (nome == null || nome.isEmpty()) {
			usuariosEncontrados = usuariosCadastrados;
		} else {
			if (usuariosCadastrados != null) {
				usuariosEncontrados = usuariosCadastrados.stream()
						.filter(u -> u.getNome().toLowerCase().contains(nome.toLowerCase()))
						.collect(Collectors.toList());
			}
		}

		model.addAttribute("usuariosEncontrados", usuariosEncontrados);

		if (mostrarTodosDados != null) {
			model.addAttribute("mostrarTodosDados", true);
		}

		return "/usuario/busca";
	}
}
