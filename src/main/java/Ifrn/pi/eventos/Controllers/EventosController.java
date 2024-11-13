package Ifrn.pi.eventos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Ifrn.pi.eventos.models.Evento;
import Ifrn.pi.eventos.repositories.EventoR;

@Controller
public class EventosController {
	
	@Autowired
	private EventoR er;

    @RequestMapping("/eventos/form")
    public String form() {
        return "eventos/formEvento";
    }

    @RequestMapping(value = "/eventos", method = RequestMethod.POST)
    public String receberFormulario(Evento evento) {
        System.out.println("Nome: " + evento.getNome());
        System.out.println("Local: " + evento.getLocal());
        System.out.println("Data: " + evento.getData());
        System.out.println("Horário: " + evento.getHorario());
        return "confirmacao";
    }
    @PostMapping("/eventos")
        public String adicionar(Evento evento) {
    	System.out.println(evento);
    	er.save(evento);
    	return "eventos/evento-adicionado"; 
    }


}
