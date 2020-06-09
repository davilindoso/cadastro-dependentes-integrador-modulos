package com.company.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.api.model.Cliente;
import com.company.api.webclient.WebClientCliente;

import reactor.core.publisher.Flux;

@Service
public class ClienteService {

	@Autowired
	WebClientCliente webClientCliente;
	
	public Flux<Cliente> obterClientes() {
		return webClientCliente.obterClientes();
	}
	
	public Cliente cadastrarCliente(Cliente body) {
		return webClientCliente.cadastrarCliente(body);
	}
	
	

}
