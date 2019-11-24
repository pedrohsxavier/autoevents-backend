package com.br.edu.ifpb.deps.autoevents.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false)
    private BigDecimal valor;

    @OneToOne
    @JoinColumn(name = "montadora_id", nullable = false, foreignKey = @ForeignKey(name = "fk_montadora_carro_id"))
    private Montadora montadora;
}
