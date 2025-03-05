package io.github.italoalcantaraa.gestorclientes.validation;

import org.springframework.stereotype.Component;

import io.github.italoalcantaraa.gestorclientes.model.Usuario;
import io.github.italoalcantaraa.gestorclientes.repository.UsuarioRepository;

@Component
public class UsuarioValidation {
    private UsuarioRepository usuarioRepository;

    public UsuarioValidation(UsuarioRepository usuarioRepository ) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validacao(Usuario usuario) {
        if(usuario.getId() != null)
            return;
        
        if(existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
