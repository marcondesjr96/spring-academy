package com.io.github.vendas.service.impl;

import com.io.github.vendas.domain.entity.Usuario;
import com.io.github.vendas.domain.repository.UsuarioRepositoy;
import com.io.github.vendas.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepositoy usuarioRepositoy;

    @Transactional
    public Usuario salvar (Usuario usuario){
        return usuarioRepositoy.save(usuario);
    }

    public UserDetails autenticar (Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean isPasswordMatch = encoder.matches(usuario.getSenha(), user.getPassword());

        if(isPasswordMatch){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositoy.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

        String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User.builder()
               .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
