package com.ets.SecurePharmacy.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ExceptionResponseTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExceptionResponse#ExceptionResponse(Date, String, String)}
     *   <li>{@link ExceptionResponse#getDetails()}
     *   <li>{@link ExceptionResponse#getMessage()}
     *   <li>{@link ExceptionResponse#getTimestamp()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

        // Act
        ExceptionResponse actualExceptionResponse = new ExceptionResponse(fromResult, "An error occurred", "Details");

        // Assert
        assertEquals("Details", actualExceptionResponse.getDetails());
        assertEquals("An error occurred", actualExceptionResponse.getMessage());
        assertSame(fromResult, actualExceptionResponse.getTimestamp());
    }
}

