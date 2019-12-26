package com.br.edu.ifpb.deps.autoevents.dto.response;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class UsuarioResponse {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String senha;

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static UsuarioResponse from (Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setDataNascimento(usuario.getDataNascimento());
        usuarioResponse.setNome(usuario.getNome());
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setSenha(usuario.getSenha());

        return usuarioResponse;
    }

    public static Page<UsuarioResponse> from (Page<Usuario> usuarios) {
        Page<UsuarioResponse> usuariosResponse = usuarios.map(usuario -> {
            UsuarioResponse usuarioResponse = new UsuarioResponse();

            usuarioResponse.setDataNascimento(usuario.getDataNascimento());
            usuarioResponse.setNome(usuario.getNome());
            usuarioResponse.setId(usuario.getId());
            usuarioResponse.setEmail(usuario.getEmail());
            usuarioResponse.setSenha(usuario.getSenha());

            return usuarioResponse;
        });
        return usuariosResponse;
    }
}
