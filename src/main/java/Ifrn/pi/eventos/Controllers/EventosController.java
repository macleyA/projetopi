package Ifrn.pi.eventos.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Ifrn.pi.eventos.models.Evento;
import Ifrn.pi.eventos.repositories.EventoR;

@Controller
public class EventosController {
    
    @Autowired
    private EventoR er;

    @GetMapping("/eventos/form")
    public String form() {
        return "eventos/formEvento";
    }


    @PostMapping("/eventos/form")
    public String receberFormulario(Evento evento) {
        System.out.println("Nome: " + evento.getNome());
        System.out.println("Local: " + evento.getLocal());
        System.out.println("Data: " + evento.getData());
        System.out.println("Horário: " + evento.getHorario());
        return "confirmacao";
    }

    @PostMapping("/eventos")
    public String adicionar(Evento evento) {
        System.out.println("Nome: " + evento.getNome());
        System.out.println("Local: " + evento.getLocal());
        System.out.println("Data: " + evento.getData());
        System.out.println("Horário: " + evento.getHorario());
        er.save(evento);
        return "redirect:/eventos";
    }


    @GetMapping("/eventos")
    public ModelAndView listar() {
        List<Evento> eventos = er.findAll();
        ModelAndView mv = new ModelAndView("eventos/lista");
        mv.addObject("eventos", eventos);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id) {
        ModelAndView md = new ModelAndView();
        Optional<Evento> opt = er.findById(id);

        if (opt.isEmpty()) {
            md.setViewName("redirect:/eventos");
            return md;
        }

        md.setViewName("eventos/detalhes");
        Evento evento = opt.get();
        md.addObject("evento", evento);

        return md;
    }
}
