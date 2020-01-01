package com.br.edu.ifpb.deps.autoevents.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(nullable = false)
    @Future(message="Data inválida")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

    @Column(nullable = false)
    private double ingressoValor;

    @Column(nullable = false)
    private String descricao;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "evento_usuario",
            joinColumns = { @JoinColumn(name = "usuario_id")},
            inverseJoinColumns = { @JoinColumn(name = "evento_id")})
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "evento_carro",
            joinColumns = { @JoinColumn(name = "carro_id")},
            inverseJoinColumns = { @JoinColumn(name = "evento_id")})
    private Set<Carro> carros = new HashSet<>();

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuario) {
        this.usuarios = usuario;
    }

    public Set<Carro> getCarros() {
        return carros;
    }

    public void setCarros(Set<Carro> carros) {
        this.carros = carros;
    }

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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public double getIngressoValor() {
        return ingressoValor;
    }

    public void setIngressoValor(double ingressoValor) {
        this.ingressoValor = ingressoValor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



}
