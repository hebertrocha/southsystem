package br.com.southsystem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.southsystem.model.enuns.OpcaoVoto;

@Entity

public class Voto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String cpf;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pauta_id")
	private Pauta pauta;
	
	
	private OpcaoVoto opcao;
	

	public Voto() {

	}
	
	public Voto(String cpf, Pauta pauta, OpcaoVoto opcao) {
		this.cpf = cpf;
		this.pauta = pauta;
		this.opcao = opcao;
	}
	public Pauta getPauta() {
		return pauta;
	}
	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public OpcaoVoto getOpcao() {
		return opcao;
	}
	public void setOpcao(OpcaoVoto opcao) {
		this.opcao = opcao;
	}
	
	

}
