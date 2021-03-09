package br.com.southsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.dto.VotoNewDTO;
import br.com.southsystem.model.Voto;
import br.com.southsystem.model.service.VotoService;

@RestController
@RequestMapping(value = "/voto")
public class VotoController {

	@Autowired
	VotoService service;

	@RequestMapping(method = RequestMethod.POST)
	public Voto insert(@Valid @RequestBody VotoNewDTO objDTO){
		return service.votar(objDTO);
	} 
	
}
