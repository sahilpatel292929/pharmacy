package com.ets.SecurePharmacy.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecordAlreadyPresentExceptionTest {
    /**
     * Method under test: {@link RecordAlreadyPresentException#RecordAlreadyPresentException(String)}
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        RecordAlreadyPresentException actualRecordAlreadyPresentException = new RecordAlreadyPresentException(
                "An error occurred");

        // Assert
        assertNull(actualRecordAlreadyPresentException.getCause());
        assertEquals(0, actualRecordAlreadyPresentException.getSuppressed().length);
        assertEquals("An error occurred", actualRecordAlreadyPresentException.getMessage());
        assertEquals("An error occurred", actualRecordAlreadyPresentException.getLocalizedMessage());
    }
}

