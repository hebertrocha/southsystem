package br.com.southsystem.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.dto.VotoNewDTO;
import br.com.southsystem.model.Pauta;
import br.com.southsystem.model.Voto;
import br.com.southsystem.repository.PautaRepository;
import br.com.southsystem.repository.VotoRepository;
import br.com.southsystem.service.exceptions.InsertVotoException;

@Service
public class VotoService {

	private static Logger log = LoggerFactory.getLogger(VotoService.class);
	
	@Autowired
	PautaService pautaService;
	
	@Autowired
	VotoRepository votoRepository;
	
	@Autowired
	PautaRepository PautaRepository;
	
	@Autowired
	ValidaCpf validaCpf;
	
	public void votar(VotoNewDTO objDTO) {
		
		log.info("Verificando se CPF informado é apto a votar.");
		String statusCpf = validaCpf.ConsultaCpf(objDTO.getCpf());
		log.info("Verificando se CPF informado é apto a votar.");
		
		if (statusCpf.contentEquals("ABLE_TO_VOTE")) {
			
			LocalDateTime hora = LocalDateTime.now();
			log.info("Buscando Dados da pauta.");
			Pauta pauta = pautaService.find(objDTO.getIdPauta());
			if (hora.isAfter(pauta.getHoraAbertura()) && hora.isBefore(pauta.getHoraFechamento())) {
				log.info("Gravando voto.");
				Voto voto = new Voto(objDTO.getCpf(), pauta, objDTO.getOpcao());
				pauta.getVotos().add(voto);
				PautaRepository.save(pauta);
				votoRepository.save(voto);
			} else {
				throw new InsertVotoException("Pauta não está aberta a votações" + objDTO.getIdPauta() );
			}
		}else {
			throw new InsertVotoException("Cpf não apto a votar: " + objDTO.getCpf() + " - Status: " + statusCpf);
		}
		
	}

}
