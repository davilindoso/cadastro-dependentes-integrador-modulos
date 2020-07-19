package com.company.api.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.model.Mensagem;
import com.company.domain.service.EnvioMensagemMQService;

@RestController
@RequestMapping("/rabbitmq")
public class EnvioMensagemFilaController {

	@Autowired
	EnvioMensagemMQService envioMensagemMQService;

	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping("/put")
	public void put(@Valid @RequestBody Mensagem mensagem) {
		envioMensagemMQService.enviarMensagem(mensagem);
	}

}
