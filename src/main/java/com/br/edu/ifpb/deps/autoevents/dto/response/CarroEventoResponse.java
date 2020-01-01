package com.br.edu.ifpb.deps.autoevents.dto.response;

import com.br.edu.ifpb.deps.autoevents.model.Carro;

public class CarroEventoResponse {
    private Long id;
    private String nomeCarro;
    private int ano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCarro() {
        return nomeCarro;
    }

    public void setNomeCarro(String nomeCarro) {
        this.nomeCarro = nomeCarro;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public static CarroEventoResponse from(Long id, Carro carro){
        CarroEventoResponse response = new CarroEventoResponse();

        response.setAno(carro.getAno());
        response.setId(id);
        response.setNomeCarro(carro.getNome());

        return response;
    }
}
