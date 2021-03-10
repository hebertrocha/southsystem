package br.com.southsystem.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.dto.VotoNewDTO;
import br.com.southsystem.service.VotoService;

@RestController
@RequestMapping(value = "/voto")
public class VotoController {

	private static Logger log = LoggerFactory.getLogger(VotoController.class);
	
	@Autowired
	VotoService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody VotoNewDTO objDTO){
		service.votar(objDTO);
		return ResponseEntity.noContent().build();
	} 
	
}
