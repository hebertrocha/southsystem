package br.com.southsystem.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.southsystem.model.RelatPK;
import br.com.southsystem.model.Voto;

public interface VotoRepository extends CrudRepository<Voto, RelatPK>{

	
}
