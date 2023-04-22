package com.ets.SecurePharmacy.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class RecordNotFoundExceptionTest {
    /**
     * Method under test: {@link RecordNotFoundException#RecordNotFoundException(String)}
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        RecordNotFoundException actualRecordNotFoundException = new RecordNotFoundException("An error occurred");

        // Assert
        assertNull(actualRecordNotFoundException.getCause());
        assertEquals(0, actualRecordNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualRecordNotFoundException.getMessage());
        assertEquals("An error occurred", actualRecordNotFoundException.getLocalizedMessage());
    }

    /**
     * Method under test: {@link RecordNotFoundException#RecordNotFoundException(String, Throwable)}
     */
    @Test
    void testConstructor2() {
        // Arrange
        Throwable throwable = new Throwable();

        // Act
        RecordNotFoundException actualRecordNotFoundException = new RecordNotFoundException("An error occurred",
                throwable);

        // Assert
        Throwable cause = actualRecordNotFoundException.getCause();
        assertSame(throwable, cause);
        Throwable[] suppressed = actualRecordNotFoundException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertEquals("An error occurred", actualRecordNotFoundException.getLocalizedMessage());
        assertEquals("An error occurred", actualRecordNotFoundException.getMessage());
        assertNull(cause.getLocalizedMessage());
        assertNull(cause.getCause());
        assertNull(cause.getMessage());
        assertSame(suppressed, cause.getSuppressed());
    }
}

