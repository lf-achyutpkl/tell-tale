package com.lftechnology.tell.tale.exception;
/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7213834010317372624L;

    public ObjectNotFoundException() {
        super("No object found");
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
