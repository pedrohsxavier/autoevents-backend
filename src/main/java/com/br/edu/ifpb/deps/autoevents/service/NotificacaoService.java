package com.br.edu.ifpb.deps.autoevents.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NotificacaoService {
	
	public void sendMessage(String message) throws IOException, TimeoutException {		
		String FILA = "EVENTOS";
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		try (Connection connection = factory.newConnection(); Channel canal = connection.createChannel()) {
			canal.queueDeclare(FILA, true, false, false, null);
						
			canal.basicPublish("", FILA, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
			System.out.println("Enviando para o banco " + FILA);
		}
	}
	
}
