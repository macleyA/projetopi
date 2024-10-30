package Ifrn.pi.eventos.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Ifrn.pi.eventos.models.Evento;

@Controller
public class EventosController {

    @RequestMapping("/eventos/form")
    public String form() {
        return "formEvento";
    }

    @RequestMapping(value = "/eventos", method = RequestMethod.POST)
    public String receberFormulario(Evento evento) {
        System.out.println("Nome: " + evento.getNome());
        System.out.println("Local: " + evento.getLocal());
        System.out.println("Data: " + evento.getData());
        System.out.println("Horário: " + evento.getHorario());
        return "confirmacao";
    }


}
