package br.com.southsystem.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.dto.VotoNewDTO;
import br.com.southsystem.model.Pauta;
import br.com.southsystem.model.Voto;
import br.com.southsystem.model.enuns.StatusPauta;
import br.com.southsystem.repository.VotoRepository;

@Service
public class VotoService {

	@Autowired
	PautaService pautaService;
	
	@Autowired
	VotoRepository votoRepository;
	
	@Autowired
	ValidaCpf validaCpf;
	
	public Voto votar(VotoNewDTO objDTO) {
		//validar o cpf
		String statusCpf = validaCpf.ConsultaCpf(objDTO.getCpf());
		
		if (statusCpf.contentEquals("ABLE_TO_VOTE")) {
			//verificar se a pauta está aberta
			Pauta pauta = pautaService.find(objDTO.getIdPauta());
			if (pauta.getStatus()== StatusPauta.ABERTA) {
				Voto voto = new Voto(objDTO.getCpf(), pauta, objDTO.getOpcao());
				return votoRepository.save(voto);
				
			}
			
		}
		return null;

		
	}

}
