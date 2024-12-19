package Ifrn.pi.eventos.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Import correto
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Ifrn.pi.eventos.models.Doce;
import Ifrn.pi.eventos.models.Pedido;
import Ifrn.pi.eventos.repositories.DoceR;
import Ifrn.pi.eventos.repositories.PedidoR;

@Controller
public class PedidoController {

    @Autowired
    private PedidoR pedidoR;

    @Autowired
    private DoceR doceR;

    @GetMapping("/eventos/formPedido")
    public String formPedido(Model model) {
        List<Doce> doces = doceR.findAll();
        model.addAttribute("doces", doces); 
        return "eventos/formPedido";
    }

    @PostMapping("/pedidos/novo")
    public String adicionarPedido(Long idDoce, int quantidade, String formaDePagamento, RedirectAttributes attributes) {
        Optional<Doce> opt = doceR.findById(idDoce);
        if (opt.isEmpty()) {
            attributes.addFlashAttribute("mensagemErro", "Doce não encontrado.");
            return "redirect:/eventos/formPedido";
        }

        Doce doce = opt.get();
        Pedido pedido = new Pedido(doce, quantidade, formaDePagamento); 
        pedidoR.save(pedido);

        attributes.addFlashAttribute("mensagem", "Pedido realizado com sucesso!");
        return "redirect:/eventos/formPedido";
    }



    @GetMapping("/pedidos/listar")
    public ModelAndView listar() {
        List<Pedido> pedidos = pedidoR.findAll(); 
        ModelAndView mv = new ModelAndView("eventos/listaPedidos");
        mv.addObject("pedidos", pedidos); 
        return mv;
    }
    @GetMapping("/pedidos/{id}/remover")
    public String apagarPedido(@PathVariable Long id, RedirectAttributes attributes) {
        Optional<Pedido> optPedido = pedidoR.findById(id);

        if (optPedido.isEmpty()) {
            attributes.addFlashAttribute("mensagemErro", "Pedido não encontrado.");
            return "redirect:/pedidos/listar";
        }

        pedidoR.delete(optPedido.get());
        attributes.addFlashAttribute("mensagem", "Pedido removido com sucesso!");
        return "redirect:/pedidos/listar";
    }

}
