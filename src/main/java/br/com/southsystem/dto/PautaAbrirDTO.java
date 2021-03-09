package br.com.southsystem.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

public class PautaAbrirDTO {

	@NotEmpty(message = "Preenchimento Obrigatório")
	private Long id;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Range(min=1,max=60,message="O tempo deve ser entre 1 e 60 minutos.")
	private Long tempo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTempo() {
		return tempo;
	}
	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}
	
	
}
