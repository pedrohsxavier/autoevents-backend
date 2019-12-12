package com.br.edu.ifpb.deps.autoevents.dto.response;

import com.br.edu.ifpb.deps.autoevents.model.Carro;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public class CarroResponse {
    private Long id;
    private String nome;
    private int ano;
    private BigDecimal valor;
    private Long montadoraId;

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

    public Long getMontadoraId() {
        return montadoraId;
    }

    public void setMontadoraId(Long montadoraId) {
        this.montadoraId = montadoraId;
    }

    public static CarroResponse from(Carro carro){
        CarroResponse carroResponse = new CarroResponse();

        carroResponse.setAno(carro.getAno());
        carroResponse.setId(carro.getId());
        carroResponse.setMontadoraId(carro.getMontadora().getId());
        carroResponse.setNome(carro.getNome());
        carroResponse.setValor(carro.getValor());

        return carroResponse;
    }

    public static Page<CarroResponse> from (Page<Carro> carros){
        Page<CarroResponse> carroResponses = carros.map(carro -> {
            CarroResponse carroResponse = new CarroResponse();

            carroResponse.setAno(carro.getAno());
            carroResponse.setId(carro.getId());
            carroResponse.setMontadoraId(carro.getMontadora().getId());
            carroResponse.setNome(carro.getNome());
            carroResponse.setValor(carro.getValor());

            return carroResponse;
        });
        return carroResponses;
    }
}
