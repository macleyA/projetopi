package Ifrn.pi.eventos.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

public class SiteController {

	@GetMapping("/")
	public String index() {
		return "site/index";
	}

	@GetMapping("/administrativo")
	@PreAuthorize("hasRole('CADASTRO')")
	public String administrativo() {
		return "site/administrativo";
	}

	@GetMapping("/estoque")
	@PreAuthorize("hasRole('PEDIDO')")
	public String estoque() {
		return "site/estoque";
	}

	@GetMapping("/vendas")
	@PreAuthorize("hasRole('LISTA')")
	public String vendas() {
		return "site/vendas";
	}

	@GetMapping("/login")
	public String login() {
		return "login/login";
	}

	@GetMapping("/logout")
	public String logout() {
		return "login/logout";
	}

}
