package br.com.southsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.controller.exception.ObjectNotFoundException;
import br.com.southsystem.dto.PautaNewDTO;
import br.com.southsystem.model.Pauta;
import br.com.southsystem.model.enuns.StatusPauta;
import br.com.southsystem.repository.PautaRepository;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {
	
	@Autowired
	PautaRepository repo;

	@RequestMapping(method = RequestMethod.POST)
	public Pauta insert(@RequestBody PautaNewDTO objDTO){
		Pauta obj = new Pauta();
		obj.setAssunto(objDTO.getAssunto());
		obj.setStatus(StatusPauta.FECHADA);
		return repo.save(obj);	
	} 
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll(){

			List<Pauta> list = repo.findAll();
			return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Pauta find(@PathVariable Long id){
		
		Optional<Pauta> associado = repo.findById(id);
		return associado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado: Id:" + id + ", Tipo: " + Pauta.class.getName()));
		
	}
	
}
