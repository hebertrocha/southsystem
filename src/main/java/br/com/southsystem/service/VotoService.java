package br.com.southsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.dto.VotoNewDTO;
import br.com.southsystem.model.Pauta;
import br.com.southsystem.model.Voto;
import br.com.southsystem.model.enuns.StatusPauta;
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
	ValidaCpf validaCpf;
	
	public void votar(VotoNewDTO objDTO) {
		
		String statusCpf = validaCpf.ConsultaCpf(objDTO.getCpf());
		
		if (statusCpf.contentEquals("ABLE_TO_VOTE")) {
			
			Pauta pauta = pautaService.find(objDTO.getIdPauta());
			if (pauta.getStatus()== StatusPauta.ABERTA) {
				Voto voto = new Voto(objDTO.getCpf(), pauta, objDTO.getOpcao());
				votoRepository.save(voto);
			} else {
				throw new InsertVotoException("Pauta não está aberta a votações" + objDTO.getIdPauta() );
			}
		}else {
			throw new InsertVotoException("Cpf não apto a votar: " + objDTO.getCpf() + " - Status: " + statusCpf);
		}
		

		
	}

}
