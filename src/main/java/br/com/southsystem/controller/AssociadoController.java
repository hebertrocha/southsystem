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
import br.com.southsystem.dto.AssociadoNewDTO;
import br.com.southsystem.model.Associado;
import br.com.southsystem.repository.AssociadoRepository;

@RestController
@RequestMapping(value = "/associado")
public class AssociadoController {
	
	@Autowired
	private AssociadoRepository repo;
	
	@RequestMapping(method = RequestMethod.POST)
	public Associado insert(@RequestBody AssociadoNewDTO objDTO){
		Associado obj = new Associado();
		obj.setCpf(objDTO.getCpf());
		obj.setNome(objDTO.getNome());
		return repo.save(obj);	
	} 
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll(){

			List<Associado> list = repo.findAll();
			return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Associado find(@PathVariable Long id){
		
		Optional<Associado> associado = repo.findById(id);
		return associado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado: Id:" + id + ", Tipo: " + Associado.class.getName()));
		
	}

}
