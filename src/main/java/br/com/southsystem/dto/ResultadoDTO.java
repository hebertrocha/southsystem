package br.com.southsystem.dto;

public class ResultadoDTO {
	
	private String assunto;
	private Long qtdsim;
	private Long qtdnao;
	
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public Long getQtdsim() {
		return qtdsim;
	}
	public void setQtdsim(Long qtdsim) {
		this.qtdsim = qtdsim;
	}
	public Long getQtdnao() {
		return qtdnao;
	}
	public void setQtdnao(Long qtdnao) {
		this.qtdnao = qtdnao;
	}


	
}
