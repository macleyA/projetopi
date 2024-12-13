package Ifrn.pi.eventos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Ifrn.pi.eventos.models.Doce;
import Ifrn.pi.eventos.models.Pedido;


public interface PedidoR extends JpaRepository<Pedido, Long> {
	
	List<Pedido> findByDoce(Doce doce);

}
