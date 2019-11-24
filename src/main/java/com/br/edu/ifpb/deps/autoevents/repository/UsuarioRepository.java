package com.br.edu.ifpb.deps.autoevents.repository;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
