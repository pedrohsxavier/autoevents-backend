package com.br.edu.ifpb.deps.autoevents.model;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
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

    @ManyToMany(mappedBy = "carros")
    private Set<Evento> eventos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }
}
