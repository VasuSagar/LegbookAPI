package com.itm.legbook.exception;

import org.springframework.mail.MailException;

public class LegBookException extends RuntimeException {
    public LegBookException(String exceptionMessage,Exception exception)
    {
        super(exceptionMessage,exception);
    }

    public LegBookException(String exceptionMessage)
    {
        super(exceptionMessage);
    }
}
