package br.com.southsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.southsystem.dto.CpfRetornoDTO;

@Service
public class ValidaCpf {

	private static Logger log = LoggerFactory.getLogger(ValidaCpf.class);
	
	public String ConsultaCpf(String cpf) {

		log.info("Starting the call the verification endpoint.");
			
		RestTemplate template = new RestTemplate();
			
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("user-info.herokuapp.com/users/" + cpf)
				.build();
			
		log.info(uri.toUriString());
			
		ResponseEntity<CpfRetornoDTO> resultado =  template.getForEntity(uri.toUriString(), CpfRetornoDTO.class);
			
		log.info("Verification endpoint call completed.");
		if (resultado.getStatusCode() != HttpStatus.NOT_FOUND){
			return resultado.getBody().getStatus();
		}else {
			return "CPF não encontrado";
		}
		

	}
}
