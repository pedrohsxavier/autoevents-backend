package com.br.edu.ifpb.deps.autoevents.model;

import lombok.Data;

import javax.persistence.*;
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
    private LocalDate dataEvento;

    @Column(nullable = false)
    private double ingressoValor;

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_evento_usuario_id"))
    private Usuario usuario;

//    @ManyToOne
//    @JoinColumn(name = "montadora_id", foreignKey = @ForeignKey(name = "fk_montadora_usuario_id"))
//    private Montadora montadora;
}
