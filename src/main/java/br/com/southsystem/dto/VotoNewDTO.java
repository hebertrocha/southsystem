package br.com.southsystem.dto;

import br.com.southsystem.model.enuns.OpcaoVoto;

public class VotoNewDTO {

	private String Cpf;
	private Long idPauta;
	private OpcaoVoto opcao;
	
	public String getCpf() {
		return Cpf;
	}
	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public Long getIdPauta() {
		return idPauta;
	}
	public void setIdPauta(Long idPauta) {
		this.idPauta = idPauta;
	}
	
	public OpcaoVoto getOpcao() {
		return opcao;
	}
	public void setOpcao(OpcaoVoto opcao) {
		this.opcao = opcao;
	}
	
	
	
}
