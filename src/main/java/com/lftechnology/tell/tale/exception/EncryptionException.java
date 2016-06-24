package com.lftechnology.tell.tale.exception;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
public class EncryptionException extends RuntimeException{

	private static final long serialVersionUID = -2323930005621850047L;

	public EncryptionException() {
		super("Error occurred while encryption");
	}
	
	public EncryptionException(String message){
		super(message);
	}
	
}
