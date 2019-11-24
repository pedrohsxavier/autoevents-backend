package com.br.edu.ifpb.deps.autoevents.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequest {
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
}
