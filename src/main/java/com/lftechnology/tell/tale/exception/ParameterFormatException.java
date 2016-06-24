package com.lftechnology.tell.tale.exception;
/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public class ParameterFormatException extends RuntimeException {

    private static final long serialVersionUID = 7213834010317372624L;

    public ParameterFormatException() {
        super("Parameter Unsupported");
    }

    public ParameterFormatException(String message) {
        super(message);
    }
}
