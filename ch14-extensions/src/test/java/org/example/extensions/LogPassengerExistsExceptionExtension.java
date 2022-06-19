package org.example.extensions;

import org.example.jdbc.PassengerExistsException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

/*
 * Test that tries to insert the same passenger twice.
 * Because he expects this test to throw an exception, he implements an exception-handling extension to log it
 * */
public class LogPassengerExistsExceptionExtension implements TestExecutionExceptionHandler {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /*
     * He checks whether the thrown exception is an instance of PassengerExistsException; if so, he simply logs it and returns from the method e.
     * Otherwise, he rethrows the exception so it can be handled elsewhere
     * */
    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof PassengerExistsException) {
            logger.severe("Passenger exists: " + throwable.getMessage());
            return;
        }
        throw throwable;
    }
}
