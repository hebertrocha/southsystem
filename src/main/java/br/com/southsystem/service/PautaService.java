package br.com.southsystem.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.controller.exception.ObjectNotFoundException;
import br.com.southsystem.model.Pauta;
import br.com.southsystem.model.service.runable.ManterPauta;
import br.com.southsystem.repository.PautaRepository;

@Service
public class PautaService {
	
	private static Logger log = LoggerFactory.getLogger(PautaService.class);
	
	@Autowired
	PautaRepository repo;
	
	public Pauta find(Long id) throws ObjectNotFoundException {
		log.info("Iniciando busca de pauta por Id.");
		Optional<Pauta> obj = repo.findById(id);
		log.info("Finalizando busca de pauta por Id.");
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado: Id:" + id + ", Tipo: " + Pauta.class.getName()));

	}

	public Pauta abrirPauta(Long tempo, Pauta pauta) {
		log.info("Iniciando processo de abertura de pauta para votação.");
		ManterPauta mt = new ManterPauta(tempo, pauta);
		Thread executaPauta = new Thread(mt);
		executaPauta.start();
		log.info("Iniciando processo de abertura de pauta para votação.");
		return pauta;
		
	}
	

	
}

