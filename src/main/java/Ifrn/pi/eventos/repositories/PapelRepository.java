package Ifrn.pi.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Ifrn.pi.eventos.models.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long>{
	
	Papel findByNome(String nome);

}