package com.br.edu.ifpb.deps.autoevents.dto.response;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.br.edu.ifpb.deps.autoevents.model.Carro;
import com.br.edu.ifpb.deps.autoevents.model.Evento;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;

public class EventoResponse {
    private Long id;
    private String nome;
    private String descricao;
    private String cidade;
    private String pais;
    private Long usuarioId;
    private LocalDate dataEvento;
    private double ingressoValor;
    private String usuarios;
    private String carros;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public double getIngressoValor() {
        return ingressoValor;
    }

    public void setIngressoValor(double ingressoValor) {
        this.ingressoValor = ingressoValor;
    }
    
    public String getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = "[";
		for (Usuario usuario : usuarios) {
			this.usuarios+= "{ \"id\": \""+ usuario.getId() + "\", " +"\"nome\": \"" +usuario.getNome() +"\" }, ";
		}
		if (this.usuarios.contains(",")) {
			this.usuarios = this.usuarios.substring(0, this.usuarios.length() - 2);
		}
		this.usuarios += "]";
		
	}
		

	public String getCarros() {
		return carros;
	}

	public void setCarros(Set<Carro> carros) {
		this.carros = "[";
		for (Carro carro: carros) {
			this.carros+= "{ \"nome\": \""+ carro.getNome() + "\", " +"\"valor\": \"" +carro.getValor() +"\" }, ";
		}
		if (this.carros.contains(",")) {
			this.carros = this.carros.substring(0, this.carros.length() - 2);
		}
		this.carros += "]";
		
	}

	public static EventoResponse from (Evento evento) {
        EventoResponse eventoResponse = new EventoResponse();

        eventoResponse.setNome(evento.getNome());
        eventoResponse.setDescricao(evento.getDescricao());
        eventoResponse.setCidade(evento.getCidade());
        eventoResponse.setPais(evento.getPais());
        eventoResponse.setDataEvento(evento.getDataEvento());
        eventoResponse.setId(evento.getId());
        eventoResponse.setIngressoValor(evento.getIngressoValor());
        //eventoResponse.setMontadoraId(evento.getMontadora().getId());
        eventoResponse.setUsuarioId(evento.getUsuarioId());
        eventoResponse.setUsuarios(evento.getUsuarios());
        eventoResponse.setCarros(evento.getCarros());
        
        return eventoResponse;
    }

    public static Page<EventoResponse> from (Page<Evento> eventos) {
        Page<EventoResponse> eventoResponses = eventos.map(evento -> {
            EventoResponse eventoResponse = new EventoResponse();

            eventoResponse.setNome(evento.getNome());
            eventoResponse.setDescricao(evento.getDescricao());
            eventoResponse.setCidade(evento.getCidade());
            eventoResponse.setPais(evento.getPais());
            eventoResponse.setDataEvento(evento.getDataEvento());
            eventoResponse.setId(evento.getId());
            eventoResponse.setIngressoValor(evento.getIngressoValor());
            //eventoResponse.setMontadoraId(evento.getMontadora().getId());
            eventoResponse.setUsuarioId(evento.getUsuarioId());
            eventoResponse.setUsuarios(evento.getUsuarios());
            eventoResponse.setCarros(evento.getCarros());
            
            return eventoResponse;
        });

        return eventoResponses;
    }
}
