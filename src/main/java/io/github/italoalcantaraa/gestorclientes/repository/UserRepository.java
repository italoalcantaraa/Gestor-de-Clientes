package io.github.italoalcantaraa.gestorclientes.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.italoalcantaraa.gestorclientes.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, UUID> {

}
