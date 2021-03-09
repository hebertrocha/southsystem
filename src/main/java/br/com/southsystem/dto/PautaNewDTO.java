package br.com.southsystem.dto;

import javax.validation.constraints.NotEmpty;

public class PautaNewDTO {

	@NotEmpty(message="O assunto da pauta deve ser preenchido.")
	private String assunto;

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	
}
