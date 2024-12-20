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

import Ifrn.pi.eventos.models.Doce;
import Ifrn.pi.eventos.repositories.DoceR;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/doces")
public class DocesController {

    @Autowired
    private DoceR doceR;

    @GetMapping("/form")
    public String formDoce(Doce doce) {
        return "eventos/cadastroDoce"; 
    }

    @PostMapping
    public String salvar(@Valid Doce doce, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return formDoce(doce);
        }

        doceR.save(doce); 
        attributes.addFlashAttribute("mensagem", "Doce salvo com sucesso");
        return "redirect:/doces"; 
    }

    @GetMapping
    public ModelAndView listar() {
        List<Doce> doces = doceR.findAll(); 
        ModelAndView mv = new ModelAndView("eventos/listaDoces");
        mv.addObject("doces", doces); 
        return mv;
    }

    @GetMapping("/{id}/remover")
    public String apagarDoce(@PathVariable Long id, RedirectAttributes attributes) {
        Optional<Doce> optDoce = doceR.findById(id);

        if (optDoce.isEmpty()) {
            attributes.addFlashAttribute("mensagemErro", "Doce não encontrado.");
            return "redirect:/doces";
        }

        Doce doce = optDoce.get();
        doceR.delete(doce);
        attributes.addFlashAttribute("mensagem", "Doce removido com sucesso!");
        return "redirect:/doces";
    }
}
