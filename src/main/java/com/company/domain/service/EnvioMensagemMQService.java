package com.company.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.model.MensagemRabbitMq;
import com.company.domain.webclient.WebClientRabbitMQ;

@Service
public class EnvioMensagemMQService {
	
	@Autowired
	WebClientRabbitMQ webClient;
	
	public void enviarMensagem(MensagemRabbitMq mensagem) {
		webClient.putMensagemFilaMQ(mensagem);
	}

}
