package br.com.southsystem.dto;

import br.com.southsystem.model.Associado;

public class AssociadoNewDTO {


	private String cpf;
	
	private String Nome;

	public AssociadoNewDTO(Associado obj) {
		this.cpf = obj.getCpf();
		this.Nome= obj.getNome();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
	
	
}
