package com.lftechnology.tell.tale.exception;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public class InvalidUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String message) {
        super(message);
    }
}
