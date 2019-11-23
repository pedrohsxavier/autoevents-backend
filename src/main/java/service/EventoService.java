package service;

import dto.request.EventoRequest;
import model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.EventoRepository;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento criarEvento(EventoRequest request) {
        Evento evento = new Evento();

        evento.setCidade(request.getCidade());
        evento.setPais(request.getPais());
        evento.setNome(request.getNome());
        evento.setDataEvento(request.getDataEvento());
        evento.setIngressoValor(request.getIngressoValor());

        return evento;
    }

    public Evento atualizarEvento(Long id, EventoRequest request) {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(
                () -> new HandlerException("Evento inexistente"));

        evento.setCidade(request.getCidade());
        evento.setPais(request.getPais());
        evento.setNome(request.getNome());
        evento.setDataEvento(request.getDataEvento());
        evento.setIngressoValor(request.getIngressoValor());

        return evento;
    }

    public Page<Evento> listarEventos(Pageable pageable) {
        return this.eventoRepository.findAll(pageable);
    }

    public Evento buscarEvento(Long id) {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(
                () -> new HandlerException("Evento inexistente"));

        return evento;
    }

    public void removerEvento(Long id) {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(
                () -> new HandlerException("Evento inexistente"));
        this.eventoRepository.delete(evento);
    }
}
