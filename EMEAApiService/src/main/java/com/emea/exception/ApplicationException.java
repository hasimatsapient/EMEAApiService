package com.emea.exception;

/**
 * Exception class for generic exception.
 * 
 * @author hmolla
 *
 */
public class ApplicationException extends Exception {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Exception e) {
        super(e);
    }

}
