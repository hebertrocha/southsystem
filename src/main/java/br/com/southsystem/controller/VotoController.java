package br.com.southsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.dto.VotoNewDTO;
import br.com.southsystem.service.VotoService;
import br.com.southsystem.service.exceptions.InsertVotoException;

@RestController
@RequestMapping(value = "/voto")
public class VotoController {


	
	@Autowired
	VotoService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@Valid @RequestBody VotoNewDTO objDTO){
		
		try {
			service.votar(objDTO);
			return ResponseEntity.noContent().build();
		}catch(InsertVotoException e) {
			String msg = "{ \"statusCode\" : \"406\", \"msg\" : \"erro ao inserir voto.\" }";
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(msg);
		}
	} 
	
}
