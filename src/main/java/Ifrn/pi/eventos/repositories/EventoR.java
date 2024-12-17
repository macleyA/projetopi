package Ifrn.pi.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Ifrn.pi.eventos.models.Evento;

public interface EventoR extends JpaRepository<Evento, Long> {
	

}