package br.com.southsystem.service.exceptions;

public class InsertVotoException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	public InsertVotoException(String msg) {
		super(msg);
		
	}

	public InsertVotoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
