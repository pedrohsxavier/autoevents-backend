package com.br.edu.ifpb.deps.autoevents.dto.request;

import lombok.Data;

public class MontadoraRequest {
    private String nome;
    private String pais;
    private Long carroId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
    }
}
