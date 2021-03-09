package br.com.southsystem.model.service.runable;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.southsystem.model.Pauta;
import br.com.southsystem.model.enuns.StatusPauta;
import br.com.southsystem.repository.PautaRepository;

public class ManterPauta implements Runnable{

	private Long tempo;
	private Pauta pauta;	
	
	@Autowired
	PautaRepository pautaRepository;
	
	public ManterPauta(Long tempo, Pauta pauta) {
		this.tempo = tempo;
		this.pauta = pauta;
	}

	@Override
	public void run() {
		
		pauta.setStatus(StatusPauta.ABERTA);
		pautaRepository.save(pauta);
		
		Long tempoFinal = (tempo * (60 * 1000));
		
		try {
			Thread.sleep(tempoFinal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		pauta.setStatus(StatusPauta.FECHADA);
		pautaRepository.save(pauta);

		
	}

}
