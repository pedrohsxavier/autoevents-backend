package com.br.edu.ifpb.deps.autoevents.repository;

import com.br.edu.ifpb.deps.autoevents.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
