package com.company.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.api.model.Mensagem;
import com.company.api.webclient.WebClientRabbitMQ;

@Service
public class EnvioMensagemMQService {
	
	@Autowired
	WebClientRabbitMQ webClient;
	
	public void enviarMensagem(Mensagem mensagem) {
		webClient.putMensagemFilaMQ(mensagem);
	}

}
