package com.br.edu.ifpb.deps.autoevents.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarroRequest {
    private String nome;
    private int ano;
    private BigDecimal valor;
    private Long montadoraId;
}
