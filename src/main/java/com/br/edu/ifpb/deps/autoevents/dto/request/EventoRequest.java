package com.br.edu.ifpb.deps.autoevents.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoRequest {
    private String nome;
    private String descricao;
    private String cidade;
    private String pais;
    private Long usuarioId;
    //private Long montadoraId;
    private LocalDate dataEvento;
    private double ingressoValor;
}
