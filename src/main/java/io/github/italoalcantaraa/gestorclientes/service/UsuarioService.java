package io.github.italoalcantaraa.gestorclientes.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.italoalcantaraa.gestorclientes.model.Usuario;
import io.github.italoalcantaraa.gestorclientes.repository.UsuarioRepository;
import io.github.italoalcantaraa.gestorclientes.validation.UsuarioValidation;

@Service
public class UsuarioService {
    
    UsuarioRepository usuarioRepository;
    UsuarioValidation usuarioValidation;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioValidation usuarioValidation) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioValidation = usuarioValidation;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        usuarioValidation.validacao(usuario);
        return usuarioRepository.save(usuario);
    }

    public void excluirUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
