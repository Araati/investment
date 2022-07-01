package com.vazgen.investment.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailNotConfirmedException extends AuthenticationException {

    public EmailNotConfirmedException(){
        this("Email not confirmed");
    }
    public EmailNotConfirmedException(final String msg) {
        super(msg);
    }
}
