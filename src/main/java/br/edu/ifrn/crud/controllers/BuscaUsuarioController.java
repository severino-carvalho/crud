/**
 * 
 */
package br.edu.ifrn.crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping("/buscar")
	public String buscar() {
		return "";
	}

}
