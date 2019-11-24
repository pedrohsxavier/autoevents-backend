package com.br.edu.ifpb.deps.autoevents.dto.request;

import lombok.Data;

@Data
public class MontadoraRequest {
    private String nome;
    private String pais;
    private Long carroId;
}
