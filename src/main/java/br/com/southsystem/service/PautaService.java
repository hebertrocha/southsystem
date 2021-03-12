package br.com.southsystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.controller.exception.ObjectNotFoundException;
import br.com.southsystem.controller.exception.PautaEmAbertoException;
import br.com.southsystem.model.Pauta;
import br.com.southsystem.repository.PautaRepository;

@Service
public class PautaService {
	
	private static Logger log = LoggerFactory.getLogger(PautaService.class);
	
	@Autowired
	PautaRepository repo;
	
	@Autowired
	EnviaFila enviaFila;
	
	public Pauta find(Long id) throws ObjectNotFoundException {
		log.info("Iniciando busca de pauta por Id.");
		Optional<Pauta> obj = repo.findById(id);
		log.info("Finalizando busca de pauta por Id.");
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado: Id:" + id + ", Tipo: " + Pauta.class.getName()));

	}

	public void abrirPauta(Long tempo, Long id) {
		log.info("Iniciando processo de abertura de pauta para votação.");
		LocalDateTime horaIni = LocalDateTime.now();
		LocalDateTime horaFim = horaIni.plusMinutes(tempo);
		Pauta obj = find(id);
		obj.setHoraAbertura(horaIni);
		obj.setHoraFechamento(horaFim);
		repo.save(obj);
		log.info("Finalizando processo de abertura de pauta para votação.");
	}
	
	public void enviarMensageria(Long id) {
		
		LocalDateTime hora = LocalDateTime.now();
		log.info("Iniciando busca de pauta por Id.");
		Pauta obj = find(id);
		log.info("Buscando Resultado de votação da pauta.");
		List<Object[]> resultado = repo.findByResultadoVotacao(id);
		if (hora.isAfter(obj.getHoraFechamento())) {
			log.info("Enviando dados para a fila.");
			enviaFila.send(resultado);
		}else {
			throw new PautaEmAbertoException("Pauta não apta para envio " + obj.getAssunto());
		}
		log.info("Finalizando envio de dados para a fila.");
	}

	
}

