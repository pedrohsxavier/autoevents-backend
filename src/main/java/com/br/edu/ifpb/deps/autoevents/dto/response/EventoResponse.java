package com.br.edu.ifpb.deps.autoevents.dto.response;

import lombok.Data;
import com.br.edu.ifpb.deps.autoevents.model.Evento;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EventoResponse {
    private Long id;
    private String nome;
    private String cidade;
    private String pais;
    private Long usuarioId;
    //private Long montadoraId;
    private LocalDate dataEvento;
    private BigDecimal ingressoValor;

    public static EventoResponse from (Evento evento) {
        EventoResponse eventoResponse = new EventoResponse();

        eventoResponse.setNome(evento.getNome());
        eventoResponse.setCidade(evento.getCidade());
        eventoResponse.setPais(evento.getPais());
        eventoResponse.setDataEvento(evento.getDataEvento());
        eventoResponse.setId(evento.getId());
        eventoResponse.setIngressoValor(evento.getIngressoValor());
        //eventoResponse.setMontadoraId(evento.getMontadora().getId());
        eventoResponse.setUsuarioId(evento.getUsuario().getId());

        return eventoResponse;
    }

    public static Page<EventoResponse> from (Page<Evento> eventos) {
        Page<EventoResponse> eventoResponses = eventos.map(evento -> {
            EventoResponse eventoResponse = new EventoResponse();

            eventoResponse.setNome(evento.getNome());
            eventoResponse.setCidade(evento.getCidade());
            eventoResponse.setPais(evento.getPais());
            eventoResponse.setDataEvento(evento.getDataEvento());
            eventoResponse.setId(evento.getId());
            eventoResponse.setIngressoValor(evento.getIngressoValor());
            //eventoResponse.setMontadoraId(evento.getMontadora().getId());
            eventoResponse.setUsuarioId(evento.getUsuario().getId());

            return eventoResponse;
        });

        return eventoResponses;
    }
}
