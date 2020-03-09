package com.company.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.api.model.Cliente;
import com.company.api.webclient.Client;

import reactor.core.publisher.Flux;

@Service
public class ClienteService {

	@Autowired
	Client clienteService;

	public Flux<Cliente> obterClientes() {
		return clienteService.obterClientes();
	}

}
