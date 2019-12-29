package com.br.edu.ifpb.deps.autoevents.dto.response;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;

public class LoginResponse {
    private String tipo;
    private String nome;
    private String email;
    private String token;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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

    public static LoginResponse from (Usuario usuario, String tipo){
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setEmail(usuario.getEmail());
        loginResponse.setNome(usuario.getNome());
        loginResponse.setToken(usuario.getToken());
        loginResponse.setTipo(tipo);

        return loginResponse;
    }
}
