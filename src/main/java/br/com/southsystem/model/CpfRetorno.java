package br.com.southsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CpfRetorno {

	@JsonProperty("status")
	private String status;

	public CpfRetorno(String status) {
		super();
		this.status = status;
	}

	public CpfRetorno() {
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CpfRetorno [status=" + status + "]";
	}
	
	
	
	
}
