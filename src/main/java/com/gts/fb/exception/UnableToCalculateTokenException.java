package com.gts.fb.exception;

/**
 * <p><b> Copyright 2013 (c) Waltz-Soft </b></p>
 *
 * @author zapolski
 */
public class UnableToCalculateTokenException extends RuntimeException {
    public UnableToCalculateTokenException() {
    }

    public UnableToCalculateTokenException(String message) {
        super(message);
    }

    public UnableToCalculateTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToCalculateTokenException(Throwable cause) {
        super(cause);
    }
}
