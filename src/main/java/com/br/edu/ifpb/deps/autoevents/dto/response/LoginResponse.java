package com.br.edu.ifpb.deps.autoevents.dto.response;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;

public class LoginResponse {
    private String nome;
    private String email;
    private String token;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static LoginResponse from (Usuario usuario){
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setEmail(usuario.getEmail());
        loginResponse.setNome(usuario.getNome());
        loginResponse.setToken(usuario.getToken());

        return loginResponse;
    }
}
