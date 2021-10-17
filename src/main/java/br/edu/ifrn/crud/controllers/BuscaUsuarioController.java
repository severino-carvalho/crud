
package br.edu.ifrn.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.domains.Usuario;
import br.edu.ifrn.crud.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class BuscaUsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/busca")
	public String entrarBusca() {
		return "/usuario/busca";
	}

	@GetMapping("/buscar")
	@Transactional(readOnly = true)
	public String buscar(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "nome", required = false) String email,
			@RequestParam(name = "mostrarTodosDados", required = false) Boolean mostrarTodosDados, ModelMap model) {

		List<Usuario> usuariosEncontrados = usuarioRepository.findByEmailAndNome(email, nome);

		model.addAttribute("usuariosEncontrados", usuariosEncontrados);

		if (mostrarTodosDados != null) {
			model.addAttribute("mostrarTodosDados", true);
		}

		return "/usuario/busca";
	}

	@GetMapping("/remover/{id}")
	@Transactional(readOnly = false)
	public String remover(@PathVariable("id") Integer idUsuario, RedirectAttributes attr) {

		usuarioRepository.deleteById(idUsuario);
		attr.addFlashAttribute("msgSucesso", "Usuário removido com sucesso!");

		return "redirect:/usuarios/buscar";
	}
}
