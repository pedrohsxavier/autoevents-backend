package com.br.edu.ifpb.deps.autoevents.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;

@Entity
@Data
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    @Future(message="Data inválida")
    private LocalDate dataEvento;

    @Column(nullable = false)
    private double ingressoValor;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_evento_usuario_id"))
    private Usuario usuario;

//    @ManyToOne
//    @JoinColumn(name = "montadora_id", foreignKey = @ForeignKey(name = "fk_montadora_usuario_id"))
//    private Montadora montadora;
}
