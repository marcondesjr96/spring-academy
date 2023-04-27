package com.io.github.vendas.domain.repository;

import com.io.github.vendas.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositoy extends JpaRepository<Usuario, Integer> {


    Optional<Usuario> findByLogin(String login);
}
