package com.br.edu.ifpb.deps.autoevents.repository;

import com.br.edu.ifpb.deps.autoevents.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    Optional<Carro> findByNome(String nome);
}
