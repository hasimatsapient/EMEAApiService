package com.emea.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception class to indicate user not activated.
 * @author hmolla
 *
 */
public class UserNotActivatedException extends AuthenticationException {

    public UserNotActivatedException(String msg) {
        super(msg);
    }
}
