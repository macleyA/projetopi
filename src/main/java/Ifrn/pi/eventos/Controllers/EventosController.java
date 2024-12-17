package Ifrn.pi.eventos.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Ifrn.pi.eventos.models.Convidado;
import Ifrn.pi.eventos.models.Evento;
import Ifrn.pi.eventos.repositories.ConvidadoR;
import Ifrn.pi.eventos.repositories.EventoR;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/eventos")
public class EventosController {

    @Autowired
    private EventoR er;

    @Autowired
    private ConvidadoR cr;

    @GetMapping("/form")
    public String form(Evento evento) {
        return "eventos/formEvento";
    }

    @PostMapping
    public String salvar(@Valid Evento evento, BindingResult result, RedirectAttributes attributes ) {
        if(result.hasErrors()) {
        	return form(evento);
        }
    	
    	System.out.println(evento);
        er.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento salvo com sucesso");
        return "redirect:/eventos";
    }

    @GetMapping
    public ModelAndView listar() {
        List<Evento> eventos = er.findAll();
        ModelAndView mv = new ModelAndView("eventos/lista");
        mv.addObject("eventos", eventos);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id, Convidado convidado) {
        ModelAndView md = new ModelAndView();
        Optional<Evento> opt = er.findById(id);

        if (opt.isEmpty()) {
            md.setViewName("redirect:/eventos");
            return md;
        }

        md.setViewName("eventos/detalhes");
        Evento evento = opt.get();
        md.addObject("evento", evento);

        List<Convidado> convidados = cr.findByEvento(evento);
        md.addObject("convidados", convidados);

        return md;
    }

    @PostMapping("/{idEvento}")
    public String salvarConvidado(@PathVariable Long idEvento, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
        System.out.println("id do evento: " + idEvento);
        System.out.println(convidado);

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao salvar o convidado. Verifique os campos.");
            return "redirect:/eventos/" + idEvento; 
        }

        Optional<Evento> opt = er.findById(idEvento);

        if (opt.isEmpty()) {
            return "redirect:/eventos";
        }

        Evento evento = opt.get();
        convidado.setEvento(evento);
        cr.save(convidado);

        attributes.addFlashAttribute("mensagem", "Convidado salvo com sucesso!");
        return "redirect:/eventos/" + idEvento; 
    }

    @GetMapping("/{id}/selecionar")
    public ModelAndView selecionarEvento(@PathVariable long id) {
    	ModelAndView md = new ModelAndView();
    	Optional<Evento> opt = er.findById(id);
    	if(opt.isEmpty()) {
    		md.setViewName("redirect:/eventos");
    		return md;
    	}
    	Evento evento = opt.get();
    	md.setViewName("eventos/formEvento");
        md.addObject("evento", evento);
    	return md; 
    	
    }

    @GetMapping("/{idEvento}/convidados/{idConvidado}/selecionar")
    public ModelAndView selecionarConvidado(@PathVariable long idEvento, @PathVariable long idConvidado) {
    	ModelAndView md = new ModelAndView(); 
    	
    	Optional<Evento> optEvento = er.findById(idEvento);
    	Optional<Convidado> optConvidado = cr.findById(idConvidado);
    	
    	if(optEvento.isEmpty() || optConvidado.isEmpty()) {
    		md.setViewName("redirect:/eventos");
    		return md;
    	}
    	
    	Evento evento = optEvento.get();
        Convidado convidado = optConvidado.get();
        
        if(evento.getId() != convidado.getEvento().getId()) {
        	md.setViewName("redirect:/eventos");
    		return md;
        }
        md.setViewName("eventos/detalhes");
        md.addObject("convidado", convidado);
        md.addObject("evento", evento);
        md.addObject("convidados", cr.findByEvento(evento));
    	
    	return md; 
    }

    @GetMapping("/{id}/remover")
    public String apagarEvento(@PathVariable long id, RedirectAttributes attributes) {
        Optional<Evento> opt = er.findById(id);

        if (opt.isPresent()) {
            Evento evento = opt.get();

            List<Convidado> convidados = cr.findByEvento(evento);
            cr.deleteAll(convidados);

            er.delete(evento);
            attributes.addFlashAttribute("mensagem", "Evento removido com sucesso");
        }
        return "redirect:/eventos";
    }
  
    @GetMapping("/{idEvento}/convidados/{idConvidado}/remover")
    public String removerConvidado(@PathVariable long idEvento, @PathVariable long idConvidado, RedirectAttributes attributes) {
        Optional<Evento> optEvento = er.findById(idEvento);

        if (optEvento.isEmpty()) {
            attributes.addFlashAttribute("mensagemErro", "Evento não encontrado.");
            return "redirect:/eventos";
        }

        Evento evento = optEvento.get();

        Optional<Convidado> optConvidado = cr.findById(idConvidado);

        if (optConvidado.isEmpty()) {
            attributes.addFlashAttribute("mensagemErro", "Convidado não encontrado.");
            return "redirect:/eventos/" + idEvento;
        }

        Convidado convidado = optConvidado.get();

        if (convidado.getEvento().getId() != evento.getId()) {
            attributes.addFlashAttribute("mensagemErro", "O convidado não pertence a este evento.");
            return "redirect:/eventos/" + idEvento;
        }

        cr.delete(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado removido com sucesso!");

        return "redirect:/eventos/" + idEvento;
    }

}
