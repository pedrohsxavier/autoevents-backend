package com.br.edu.ifpb.deps.autoevents.dto.response;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.service.ConsumidorService;

public class UsuarioResponse {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
    private String mensagens = "";

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
    
    public String getMensagens() {
		return mensagens;
	}

	public void setMensagens(String mensagens) {
		ConsumidorService cs = new ConsumidorService();
		this.mensagens+= "[ " ;
		for(int i = 0; i < cs.getMensagem().length; i++) {
			this.mensagens+= cs.getMensagem()[i] + ", "; 
		}
		if (this.mensagens.contains(",")) {
			this.mensagens = this.mensagens.substring(0, this.mensagens.length() - 2);
		}
		this.mensagens = mensagens;
	}

	public static UsuarioResponse from (Usuario usuario) throws InterruptedException {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setDataNascimento(usuario.getDataNascimento());
        usuarioResponse.setNome(usuario.getNome());
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setSenha(usuario.getSenha());
        usuarioResponse.setMensagens("");        
        Thread.sleep(2000);        

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
            usuarioResponse.setMensagens("");        
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return usuarioResponse;
        });
        return usuariosResponse;
    }
}
