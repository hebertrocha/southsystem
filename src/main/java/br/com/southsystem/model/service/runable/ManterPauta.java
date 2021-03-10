package br.com.southsystem.model.service.runable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.southsystem.model.Pauta;
import br.com.southsystem.model.enuns.StatusPauta;
import br.com.southsystem.repository.PautaRepository;
import br.com.southsystem.service.PautaService;

public class ManterPauta implements Runnable{

	private Long tempo;
	private Pauta pauta;	
	private static Logger log = LoggerFactory.getLogger(ManterPauta.class);
	
	
	@Autowired
	PautaRepository pautaRepository;
	
	public ManterPauta(Long tempo, Pauta pauta) {
		this.tempo = tempo;
		this.pauta = pauta;
	}

	@Override
	public void run() {
		
		log.info("Alterando o Status da pauta para aberta.");
		pauta.setStatus(StatusPauta.ABERTA);
		pautaRepository.save(pauta);
		
		log.info("Aguardando tempo de abertura de pauta.");
		Long tempoFinal = (tempo * (60 * 1000));
		
		try {
			Thread.sleep(tempoFinal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		log.info("Encerrando período de votação de pauta.");
		pauta.setStatus(StatusPauta.FECHADA);
		pautaRepository.save(pauta);

		
	}

}
