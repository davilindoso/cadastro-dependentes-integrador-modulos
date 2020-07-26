package com.company.api.controller;

public class WebClientException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WebClientException(String mensagem) {
		super(mensagem);
	}
}
