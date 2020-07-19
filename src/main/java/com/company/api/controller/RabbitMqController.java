package com.company.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.model.MensagemRabbitMq;
import com.company.domain.service.EnvioMensagemMQService;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

	@Autowired
	EnvioMensagemMQService envioMensagemMQService;

	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping("/enviar")
	public void put(@Valid @RequestBody MensagemRabbitMq mensagem) {
		envioMensagemMQService.enviarMensagem(mensagem);
	}

}
