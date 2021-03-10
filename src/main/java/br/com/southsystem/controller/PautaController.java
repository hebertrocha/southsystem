package br.com.southsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.dto.PautaAbrirDTO;
import br.com.southsystem.dto.PautaNewDTO;
import br.com.southsystem.model.Pauta;
import br.com.southsystem.model.enuns.StatusPauta;
import br.com.southsystem.repository.PautaRepository;
import br.com.southsystem.service.PautaService;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {
	
	
	private static Logger log = LoggerFactory.getLogger(PautaController.class);
	
	@Autowired
	PautaRepository repo;
	
	@Autowired
	PautaService ps;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PautaNewDTO objDTO){
		Pauta obj = new Pauta();
		obj.setAssunto(objDTO.getAssunto());
		obj.setStatus(StatusPauta.FECHADA);
		repo.save(obj);	
		return ResponseEntity.noContent().build();
	} 
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll(){

			List<Pauta> list = repo.findAll();
			return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pauta> find(@PathVariable Long id){
		
		Pauta obj = ps.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(value = "/abrir", method=RequestMethod.POST)
	public ResponseEntity<Void> abrePauta(@Valid @RequestBody PautaAbrirDTO pautaDTO) {
		Pauta pauta = ps.find(pautaDTO.getId());
		ps.abrirPauta(pautaDTO.getTempo(), pauta);
		return ResponseEntity.noContent().build();
		
	}
	
}
