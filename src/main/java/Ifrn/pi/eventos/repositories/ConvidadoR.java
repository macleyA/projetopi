package Ifrn.pi.eventos.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import Ifrn.pi.eventos.models.Convidado;
import Ifrn.pi.eventos.models.Evento;

public interface ConvidadoR extends JpaRepository<Convidado, Long> {

	List<Convidado> findByEvento(Evento evento);
}