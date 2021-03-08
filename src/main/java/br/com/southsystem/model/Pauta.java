package br.com.southsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.southsystem.model.enuns.StatusPauta;

@Entity
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String assunto;
	private StatusPauta status;
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public StatusPauta getStatus() {
		return status;
	}
	public void setStatus(StatusPauta fechada) {
		this.status = fechada;
	}
	public Long getId() {
		return id;
	}

}
