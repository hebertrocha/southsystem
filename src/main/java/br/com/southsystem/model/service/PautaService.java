package br.com.southsystem.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.controller.exception.ObjectNotFoundException;
import br.com.southsystem.model.Pauta;
import br.com.southsystem.model.service.runable.ManterPauta;
import br.com.southsystem.repository.PautaRepository;

@Service
public class PautaService {
	
	@Autowired
	PautaRepository repo;
	
	public Pauta find(Long id) throws ObjectNotFoundException {
		Optional<Pauta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado: Id:" + id + ", Tipo: " + Pauta.class.getName()));

	}

	public Pauta abrirPauta(Long tempo, Pauta pauta) {
		
		ManterPauta mt = new ManterPauta(tempo, pauta);
		Thread executaPauta = new Thread(mt);
		executaPauta.start();
		return pauta;
		
	}
}
