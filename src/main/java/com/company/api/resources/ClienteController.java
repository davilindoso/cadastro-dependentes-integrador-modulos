package com.company.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.api.model.Cliente;
import com.company.api.service.ClienteService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;

	@GetMapping
	public Flux<Cliente> obterClientes() {
		return service.obterClientes();
	}
}
