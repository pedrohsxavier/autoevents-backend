package com.br.edu.ifpb.deps.autoevents.dto.response;

import com.br.edu.ifpb.deps.autoevents.model.Evento;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;

public class InscricaoResponse {
    private Long id;
    private String email;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static InscricaoResponse from(Long id, Usuario usuario){
        InscricaoResponse inscricaoResponse = new InscricaoResponse();
        inscricaoResponse.setEmail(usuario.getEmail());
        inscricaoResponse.setNome(usuario.getNome());
        inscricaoResponse.setId(id);

        return inscricaoResponse;
    }
}
