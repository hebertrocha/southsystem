package br.com.southsystem.controller.exception;

public class PautaEmAbertoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PautaEmAbertoException(String msg) {
		super(msg);
		
	}

	public PautaEmAbertoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
