package com.company.api.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.api.model.Cliente;
import com.company.api.service.ClienteService;
import com.company.api.service.EnvioMensagemMQService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;

	@Autowired
	EnvioMensagemMQService serviceMQ;

	@GetMapping
	public ResponseEntity<Flux<Cliente>> obterClientes() {
		return ResponseEntity.status(HttpStatus.OK).body(service.obterClientes());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody Cliente clienteBody) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarCliente(clienteBody));
	}

}
