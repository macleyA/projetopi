package Ifrn.pi.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Ifrn.pi.eventos.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);

}