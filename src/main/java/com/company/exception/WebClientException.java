package com.company.exception;

public class WebClientException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WebClientException(String mensagem) {
		super(mensagem);
	}
}
