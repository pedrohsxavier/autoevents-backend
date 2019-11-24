package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.EventoRequest;
import com.br.edu.ifpb.deps.autoevents.model.Evento;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.br.edu.ifpb.deps.autoevents.repository.EventoRepository;

@Service
public class EventoService {
    private EventoRepository eventoRepository;
    private UsuarioRepository usuarioRepository;

    public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
    }

    public Evento cadastrarEvento(EventoRequest request) {
        Evento evento = new Evento();

        Usuario usuario = this.usuarioRepository.findById(request.getUsuarioId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário inexistente"));

        evento.setNome(request.getNome());
        evento.setCidade(request.getCidade());
        evento.setPais(request.getPais());
        evento.setDataEvento(request.getDataEvento());
        evento.setIngressoValor(request.getIngressoValor());
        evento.setUsuario(usuario);

        return this.eventoRepository.save(evento);
    }

    public Evento atualizarEvento(Long id, EventoRequest request) {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento inexistente"));

        Usuario usuario = this.usuarioRepository.findById(request.getUsuarioId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário inexistente"));

        evento.setCidade(request.getCidade());
        evento.setPais(request.getPais());
        evento.setNome(request.getNome());
        evento.setDataEvento(request.getDataEvento());
        evento.setIngressoValor(request.getIngressoValor());
        evento.setUsuario(usuario);

        return this.eventoRepository.save(evento);
    }

    public Page<Evento> listarEventos(Pageable pageable) {
        return this.eventoRepository.findAll(pageable);
    }

    public Evento buscarEvento(Long id) {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento inexistente"));

        return evento;
    }

    public void removerEvento(Long id) {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento inexistente"));
        this.eventoRepository.delete(evento);
    }
}
