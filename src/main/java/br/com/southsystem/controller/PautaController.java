package br.com.southsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.controller.exception.ObjectNotFoundException;
import br.com.southsystem.controller.exception.PautaEmAbertoException;
import br.com.southsystem.dto.PautaAbrirDTO;
import br.com.southsystem.dto.PautaNewDTO;
import br.com.southsystem.model.Pauta;
import br.com.southsystem.repository.PautaRepository;
import br.com.southsystem.service.PautaService;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {
	
	private String msg;
	
	
	@Autowired
	PautaRepository repo;
	
	@Autowired
	PautaService ps;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PautaNewDTO objDTO){
		Pauta obj = new Pauta();
		obj.setAssunto(objDTO.getAssunto());
		repo.save(obj);	
		return ResponseEntity.noContent().build();
	} 
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll(){

			List<Pauta> list = repo.findAll();
			return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id){
		try {
		Pauta obj = ps.find(id);
		return ResponseEntity.ok().body(obj);
		}catch (ObjectNotFoundException e) {
			msg = "{ \"statusCode\" : \"404\", \"msg\" : \"Pauta não encontrada.\" }";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);	
		}
	}
	
	@RequestMapping(value = "/abrir", method=RequestMethod.POST)
	public ResponseEntity<Void> abrePauta(@Valid @RequestBody PautaAbrirDTO pautaDTO) {

		ps.abrirPauta(pautaDTO.getTempo(), pautaDTO.getId());
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/enviar/{id}", method=RequestMethod.POST)
	public ResponseEntity<?> EnviaFila(@PathVariable Long id) {
		try {
			ps.enviarMensageria(id);
			return ResponseEntity.noContent().build();
		}catch(PautaEmAbertoException e){
			msg = "{ \"statusCode\" : \"204\", \"msg\" : \"Pauta não apta para envio Teste de votação.\" }";
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(msg);			
		}
		
	}
}
