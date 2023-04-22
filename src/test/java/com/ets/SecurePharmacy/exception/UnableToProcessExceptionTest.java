package com.ets.SecurePharmacy.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnableToProcessExceptionTest {
    /**
     * Method under test: {@link UnableToProcessException#UnableToProcessException(String)}
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        UnableToProcessException actualUnableToProcessException = new UnableToProcessException("An error occurred");

        // Assert
        assertNull(actualUnableToProcessException.getCause());
        assertEquals(0, actualUnableToProcessException.getSuppressed().length);
        assertEquals("An error occurred", actualUnableToProcessException.getMessage());
        assertEquals("An error occurred", actualUnableToProcessException.getLocalizedMessage());
    }
}

