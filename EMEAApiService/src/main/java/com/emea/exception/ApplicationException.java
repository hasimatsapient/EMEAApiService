package com.emea.exception;

/**
 * Exception class for generic exception.
 * @author hmolla
 *
 */
public class ApplicationException extends Exception{
   
    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

   
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

   
    public ApplicationException(Throwable cause) {
        super(cause);
    }

  
    protected ApplicationException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
