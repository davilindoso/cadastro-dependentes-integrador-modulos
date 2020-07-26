/*
 * Programa Auto 2.0 
 * Copyright (C) 2018-2022 Porto Seguro Seguros S.A., Inc. All Rights Reserved.
 *
 * Este software contém informações confidenciais e de propriedade da Porto
 * Seguro Seguros S.A. ("Informações Confidenciais"). Você não deve divulgar
 * qualquer tipo de informações confidenciais e deve usá-las apenas, de acordo
 * com os termos do contrato de licença firmado com a Porto Seguro.
 *
 * A Porto Seguro não faz declarações ou garantias sobre a adequação do
 * software, expressa ou implicitamente, incluindo, mas não se limitando, a
 * garantias de comercialização, adequação para um determinado fim ou qualquer
 * tipo de violação.
 *
 * A PORTO SEGURO NÃO SERÁ RESPONSÁVEL POR QUAISQUER DANOS SOFRIDOS PELO
 * LICENCIADO EM DECORRÊNCIA DO USO, MODIFICAÇÃO OU DISTRIBUIÇÃO DESTE SOFTWARE
 * OU SEUS DERIVADOS.
 */
 package com.company.domain.webclient;

import java.util.Arrays;
import java.util.Optional;

/*
  Guarda enuns responsaveis do nome das properties exceptionMessages.properties
  Responsavel por setar a BussinesException 
*/
public enum ExceptionConfigurationEnum {

	TIMEOUT(408, "error.timout"), SERVICOINDISPONIVEL(503, "error.servicoindisponivel"),
	SERVERERROR(500, "error.technical.webapplicationexception"), NOTFOUND(404, "error.notfound"),
	BADREQUEST(400, "error.badrequest"),
	UNAUTHORIZED(401, "error.autorizacao");

	private final int value;
	private final String propertieName;

	ExceptionConfigurationEnum(int value, String propertieName) {
		this.value = value;
		this.propertieName = propertieName;
	}

	public static Optional<ExceptionConfigurationEnum> valueOf(int value) {
		return Arrays.stream(values()).filter(configuration -> configuration.value == value).map(configuration -> 
			configuration != null ? configuration : ExceptionConfigurationEnum.SERVERERROR
		).findFirst();
	}

	public String getPropertieName() {
		return propertieName;
	}

	public static String propertieName(int value) {
		Optional<String> retVal = valueOf(value).map(ExceptionConfigurationEnum::getPropertieName);
		return retVal.orElse("error.technical.webapplicationexception");
	}

}