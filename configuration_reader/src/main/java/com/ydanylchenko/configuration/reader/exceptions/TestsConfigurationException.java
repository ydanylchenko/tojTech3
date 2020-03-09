package com.ydanylchenko.configuration.reader.exceptions;

/**
 * Created by mike-sid on 14.07.15.
 */
public class TestsConfigurationException extends RuntimeException {

    public TestsConfigurationException(String message) {
        super(message);
    }

    public TestsConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
