package br.com.southsystem.model;

import java.io.Serializable;

public class RelatPK implements Serializable{


	private static final long serialVersionUID = 1L;
	private String cpf;
	private Pauta pauta;
	
	public RelatPK(String cpf, Pauta pauta) {
		this.cpf = cpf;
		this.pauta = pauta;
	}

	public RelatPK() {
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((pauta == null) ? 0 : pauta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatPK other = (RelatPK) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (pauta == null) {
			if (other.pauta != null)
				return false;
		} else if (!pauta.equals(other.pauta))
			return false;
		return true;
	}
	
		
}
