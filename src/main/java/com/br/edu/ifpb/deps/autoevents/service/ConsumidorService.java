package com.br.edu.ifpb.deps.autoevents.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ConsumidorService {
	private String[] mensagem = {};

	public String[] getMensagem() {
		return mensagem;
	}

	public void setMensagem(String[] mensagem) {
		this.mensagem = mensagem;
	}

	public String consumeMessage() throws IOException, TimeoutException {			
		ConnectionFactory connectionFactory = new ConnectionFactory();
	    connectionFactory.setHost("localhost");
	    Connection conexao = connectionFactory.newConnection();        
	    Channel canal = conexao.createChannel();                 
	    String NOME_FILA = "EVENTOS";
	    	    	    
	    System.out.println("OPAAAA");
	    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	    	String m = new String (delivery.getBody(), "UTF-8");
	    	System.out.println("AQUIIIIIIIIIII " +m);
	    	mensagem[mensagem.length] = m;
			canal.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	    	try {
				canal.close();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	   
	    	
	    };
	    	 boolean autoAck = false;  // ack é feito aqui. Como está autoAck, enviará automaticamente        	 
	    	 canal.basicConsume (NOME_FILA, autoAck, deliverCallback, consumerTag -> {});
	    	 return "";
	}	   
}
