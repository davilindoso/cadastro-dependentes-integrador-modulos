package com.company.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.model.Mensagem;
import com.company.domain.webclient.WebClientRabbitMQ;

@Service
public class EnvioMensagemMQService {
	
	@Autowired
	WebClientRabbitMQ webClient;
	
	public void enviarMensagem(Mensagem mensagem) {
		webClient.putMensagemFilaMQ(mensagem);
	}

}
