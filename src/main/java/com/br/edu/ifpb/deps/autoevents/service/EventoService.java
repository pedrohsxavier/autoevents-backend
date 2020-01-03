package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.CarroEventoRequest;
import com.br.edu.ifpb.deps.autoevents.dto.request.EventoRequest;
import com.br.edu.ifpb.deps.autoevents.dto.request.InscricaoRequest;
import com.br.edu.ifpb.deps.autoevents.dto.request.UsuarioRequest;
import com.br.edu.ifpb.deps.autoevents.model.Carro;
import com.br.edu.ifpb.deps.autoevents.model.Evento;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.repository.CarroRepository;
import com.br.edu.ifpb.deps.autoevents.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
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
    private CarroRepository carroRepository;

    public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository, CarroRepository carroRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.carroRepository = carroRepository;
    }

    public Evento cadastrarEvento(EventoRequest request) {
        Evento evento = new Evento();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request,evento);

        return this.eventoRepository.save(evento);
    }

    public Evento atualizarEvento(Long id, EventoRequest request) {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento inexistente"));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request,evento);

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

    public Usuario inscreverUsuario(Long id, InscricaoRequest request) {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento inexistente"));
        Usuario usuario = this.usuarioRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário inexistente"));

        evento.getUsuarios().add(usuario);
        usuario.getEventos().add(evento);

        this.eventoRepository.save(evento);
        return usuario;
    }

    public Carro cadastrarCarroEvento(Long id, CarroEventoRequest request) {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento inexistente"));
        Carro carro = this.carroRepository.findByNome(request.getNome()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe carro com esse nome"));

        evento.getCarros().add(carro);
        carro.getEventos().add(evento);

        this.eventoRepository.save(evento);
        return carro;
    }
}
