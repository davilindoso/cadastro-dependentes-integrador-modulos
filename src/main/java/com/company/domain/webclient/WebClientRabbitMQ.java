package com.company.domain.webclient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.company.domain.model.Mensagem;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class WebClientRabbitMQ {

	WebClient webClient;

	@Autowired
	WebClient.Builder builder;

	@Value("${api.endpoint.rabbitMQ}")
	private String endpointRabbitMQ;

	@PostConstruct
	public void init() {
		final HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 99 * 1000))
				.tcpConfiguration(client -> client.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(99))
						.addHandlerLast(new WriteTimeoutHandler(99))));
		final ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
		webClient = builder.baseUrl(endpointRabbitMQ).clientConnector(connector).build();
	}
	
	public void putMensagemFilaMQ(Mensagem mensagem) {
		webClient.post().uri("/send").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(Mono.just(mensagem), Mensagem.class).retrieve().bodyToMono(Mensagem.class).block();
	}

}
