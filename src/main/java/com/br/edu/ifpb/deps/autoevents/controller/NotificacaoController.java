package com.br.edu.ifpb.deps.autoevents.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NotificacaoController {
	
	public void sendMessage(String message, String bandeira) throws IOException, TimeoutException {
		String EXCHANGE_NAME = "processarCartao";
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel canal = connection.createChannel()) {
			canal.exchangeDeclare(EXCHANGE_NAME, "direct");
						
			canal.basicPublish(EXCHANGE_NAME, bandeira, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
			System.out.println("Enviando para o banco " + bandeira);
		}
	}
	
}
