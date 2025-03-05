package io.github.italoalcantaraa.gestorclientes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.italoalcantaraa.gestorclientes.model.Usuario;
import io.github.italoalcantaraa.gestorclientes.service.UsuarioService;

@Controller
public class UserController {

    private UsuarioService usuarioService;
    private List<Usuario> usuariosList;

    public UserController(UsuarioService ususUsuarioService) {
        this.usuarioService = ususUsuarioService;
        usuariosList = new ArrayList<>();
    }
    
    @GetMapping
    public ModelAndView home(Usuario usuario) {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("usuario", new Usuario());

        return mv;
    }

    @GetMapping("/lista") 
    public ModelAndView listaClientes() {
        ModelAndView mv = new ModelAndView("lista");

        try {
            usuariosList = usuarioService.buscarTodos();
            mv.addObject("usuarios", usuariosList);
            
        } catch (Exception e) {
            mv.addObject("erro", e.getCause());
        }
        
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastro(Usuario usuario) throws Exception {
        ModelAndView mv = new ModelAndView();

        try {
            usuarioService.salvarUsuario(usuario);
            mv.setViewName("redirect:/lista");
        } catch (Exception e) {
            mv.setViewName("home");
            mv.addObject("erro", e.getMessage());
        }

        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") UUID id) {
        
        usuarioService.excluirUsuario(id);

        return "redirect:/lista";
    }

    @GetMapping("/editar/{id}") 
    public ModelAndView editar(@PathVariable UUID id) {
        ModelAndView mv = new ModelAndView("home");

        Usuario usuario = usuariosList.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst()
            .get();

        mv.addObject("usuario", usuario);
        return mv;
    }
}
