package com.br.edu.ifpb.deps.autoevents.dto.response;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.service.ConsumidorService;

public class LoginResponse {
    private String tipo;
    private String nome;
    private String email;
    private String token;
    private String id;
    private String mensagens;

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
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
	public String getMensagens() {
		return mensagens;
	}

	public void setMensagens(String mensagens) throws InterruptedException, IOException, TimeoutException {
		ConsumidorService cs = new ConsumidorService();
		cs.consumeMessage();
		Thread.sleep(1000);
		this.mensagens+= "[ " ;		
//		for(int i = 0; i < m.length; i++) {
//			this.mensagens+= m[i] + ", "; 
//		}
//		if (this.mensagens.contains(",")) {
//			this.mensagens = this.mensagens.substring(0, this.mensagens.length() - 2);
//		}
		this.mensagens = mensagens;
	}

	public static LoginResponse from (Usuario usuario, String tipo) throws InterruptedException{
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setEmail(usuario.getEmail());
        loginResponse.setNome(usuario.getNome());
        loginResponse.setToken(usuario.getToken());
        loginResponse.setTipo(tipo);
        loginResponse.setId(usuario.getId() + "");
//        loginResponse.setMensagens("");        
//        Thread.sleep(1000);        

        return loginResponse;
    }
}
