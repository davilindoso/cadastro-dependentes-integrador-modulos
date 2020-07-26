package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfiguration implements WebFluxConfigurer {

	private final Environment environment;

	@Autowired
    public WebFluxConfiguration(Environment environment) {
        this.environment = environment;
    }

	@Override
	public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
		boolean fastMode = environment.getProperty("turnOnFastMode", Boolean.class, false);
		if (fastMode) {
			configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder());
			configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder());
		}
	}

}
