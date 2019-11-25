package com.br.edu.ifpb.deps.autoevents.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Past(message="Data inválida")
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;
}
