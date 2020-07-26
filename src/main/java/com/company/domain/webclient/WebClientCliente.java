package com.company.domain.webclient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.company.domain.model.Cliente;
import com.company.exception.WebClientException;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class WebClientCliente {

	WebClient webClient;

	@Autowired
	WebClient.Builder builder;

	@Value("${api.endpoint.cliente}")
	private String endpointCliente;

	@PostConstruct
	public void init() {
		final HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 99 * 1000))
				.tcpConfiguration(client -> client.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(99))
						.addHandlerLast(new WriteTimeoutHandler(99))));
		final ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
		webClient = builder.baseUrl(endpointCliente).clientConnector(connector)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	public Cliente cadastrarCliente(Cliente body) {
		Cliente cliente = webClient.post().contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(body), Cliente.class).retrieve()
				.bodyToMono(Cliente.class).doOnError(this::errorHandler).block();
		return cliente;
	}

	public void errorHandler(Throwable exception) {
		if (exception instanceof WebClientResponseException) {
			throw new WebClientException(((WebClientResponseException) exception).getResponseBodyAsString());
		}
		throw new WebClientException("Ocorreu um erro inesperado ao realizar chamada webclient");
	}

	public Flux<Cliente> obterClientes() {
		return webClient.get().retrieve().bodyToFlux(Cliente.class);
	}

	public WebClient getWebClient() {
		return webClient;
	}

}
