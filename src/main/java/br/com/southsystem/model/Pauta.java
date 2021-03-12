package br.com.southsystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String assunto;
	private LocalDateTime horaAbertura;
	private LocalDateTime horaFechamento;
	
	@JsonIgnore
	@OneToMany( mappedBy = "pauta", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Voto> votos = new ArrayList<>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public LocalDateTime getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(LocalDateTime horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public LocalDateTime getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(LocalDateTime horaFechamento) {
		this.horaFechamento = horaFechamento;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}
	
	
}
