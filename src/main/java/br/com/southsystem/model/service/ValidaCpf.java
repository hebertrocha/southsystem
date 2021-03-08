package br.com.southsystem.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class ValidaCpf {

	private static Logger log = LoggerFactory.getLogger(ValidaCpf.class);
	
	public String ConsultaCpf(String cpf) {

		log.info("Starting the call the verification endpoint.");
			
		RestTemplate template = new RestTemplate();
			
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("https://user-info.herokuapp.com/users/" + cpf)
				.build();
			
		log.info(uri.toUriString());
			
		ResponseEntity<String> resultado =  template.getForEntity(uri.toUriString(), String.class);
			
		log.info("Verification endpoint call completed.");
		return resultado.getBody();

	}
}
