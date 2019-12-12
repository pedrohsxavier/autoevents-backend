package com.br.edu.ifpb.deps.autoevents.dto.response;

import com.br.edu.ifpb.deps.autoevents.model.Montadora;
import org.springframework.data.domain.Page;

public class MontadoraResponse {
    private Long id;
    private String nome;
    private String pais;

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public static MontadoraResponse from (Montadora montadora){
        MontadoraResponse montadoraResponse = new MontadoraResponse();

        montadoraResponse.setId(montadora.getId());
        montadora.setNome(montadora.getNome());
        montadora.setPais(montadora.getPais());

        return montadoraResponse;
    }

    public static Page<MontadoraResponse> from (Page<Montadora> montadoras){
        Page<MontadoraResponse> montadoraResponses = montadoras.map(montadora -> {
            MontadoraResponse montadoraResponse = new MontadoraResponse();

            montadoraResponse.setId(montadora.getId());
            montadora.setNome(montadora.getNome());
            montadora.setPais(montadora.getPais());

            return montadoraResponse;
        });
        return montadoraResponses;
    }
}
