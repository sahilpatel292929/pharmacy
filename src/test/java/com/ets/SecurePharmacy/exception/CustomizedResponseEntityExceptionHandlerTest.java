package com.ets.SecurePharmacy.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomizedResponseEntityExceptionHandler.class})
@ExtendWith(SpringExtension.class)
class CustomizedResponseEntityExceptionHandlerTest {
    @Autowired
    private CustomizedResponseEntityExceptionHandler customizedResponseEntityExceptionHandler;

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleAllExceptions(Exception, WebRequest)}
     */
    @Test
    void testHandleAllExceptions() {
        // Arrange
        Exception ex = new Exception("foo");

        // Act
        ResponseEntity<Object> actualHandleAllExceptionsResult = customizedResponseEntityExceptionHandler
                .handleAllExceptions(ex, new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("foo", actualHandleAllExceptionsResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualHandleAllExceptionsResult.getStatusCode());
        assertTrue(actualHandleAllExceptionsResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleUserNotFoundException(UserNotFoundException, WebRequest)}
     */
    @Test
    void testHandleUserNotFoundException() {
        // Arrange
        UserNotFoundException ex = new UserNotFoundException("An error occurred");

        // Act
        ResponseEntity<Object> actualHandleUserNotFoundExceptionResult = customizedResponseEntityExceptionHandler
                .handleUserNotFoundException(ex, new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("An error occurred", actualHandleUserNotFoundExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleUserNotFoundExceptionResult.getStatusCode());
        assertTrue(actualHandleUserNotFoundExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleUserNotFoundException(UserNotFoundException, WebRequest)}
     */
    @Test
    void testHandleUserNotFoundException3() {
        // Arrange
        UserNotFoundException userNotFoundException = mock(UserNotFoundException.class);
        when(userNotFoundException.getMessage()).thenReturn("Not all who wander are lost");

        // Act
        ResponseEntity<Object> actualHandleUserNotFoundExceptionResult = customizedResponseEntityExceptionHandler
                .handleUserNotFoundException(userNotFoundException, new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("Not all who wander are lost", actualHandleUserNotFoundExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleUserNotFoundExceptionResult.getStatusCode());
        assertTrue(actualHandleUserNotFoundExceptionResult.getHeaders().isEmpty());
        verify(userNotFoundException).getMessage();
    }

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleUserUnableToProcessException(UnableToProcessException, WebRequest)}
     */
    @Test
    void testHandleUserUnableToProcessException() {
        // Arrange
        UnableToProcessException ex = new UnableToProcessException("An error occurred");

        // Act
        ResponseEntity<Object> actualHandleUserUnableToProcessExceptionResult = customizedResponseEntityExceptionHandler
                .handleUserUnableToProcessException(ex, new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("An error occurred", actualHandleUserUnableToProcessExceptionResult.getBody());
        assertEquals(HttpStatus.I_AM_A_TEAPOT, actualHandleUserUnableToProcessExceptionResult.getStatusCode());
        assertTrue(actualHandleUserUnableToProcessExceptionResult.getHeaders().isEmpty());
    }


    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleUserUnableToProcessException(UnableToProcessException, WebRequest)}
     */
    @Test
    void testHandleUserUnableToProcessException3() {
        // Arrange
        UnableToProcessException unableToProcessException = mock(UnableToProcessException.class);
        when(unableToProcessException.getMessage()).thenReturn("Not all who wander are lost");

        // Act
        ResponseEntity<Object> actualHandleUserUnableToProcessExceptionResult = customizedResponseEntityExceptionHandler
                .handleUserUnableToProcessException(unableToProcessException,
                        new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("Not all who wander are lost", actualHandleUserUnableToProcessExceptionResult.getBody());
        assertEquals(HttpStatus.I_AM_A_TEAPOT, actualHandleUserUnableToProcessExceptionResult.getStatusCode());
        assertTrue(actualHandleUserUnableToProcessExceptionResult.getHeaders().isEmpty());
        verify(unableToProcessException).getMessage();
    }


    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMethodArgumentNotValid3() {
        // Arrange
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        when(methodArgumentNotValidException.getMessage()).thenReturn("An error occurred");
        HttpHeaders headers = new HttpHeaders();

        // Act
        ResponseEntity<Object> actualHandleMethodArgumentNotValidResult = customizedResponseEntityExceptionHandler
                .handleMethodArgumentNotValid(methodArgumentNotValidException, headers, HttpStatus.CONTINUE,
                        new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("An error occurred", actualHandleMethodArgumentNotValidResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
        assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
        verify(methodArgumentNotValidException).getMessage();
    }

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMethodArgumentNotValid4() {
        // Arrange
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        when(methodArgumentNotValidException.getMessage()).thenReturn("An error occurred");
        HttpHeaders headers = new HttpHeaders();

        // Act
        ResponseEntity<Object> actualHandleMethodArgumentNotValidResult = customizedResponseEntityExceptionHandler
                .handleMethodArgumentNotValid(methodArgumentNotValidException, headers, HttpStatus.SWITCHING_PROTOCOLS,
                        new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("An error occurred", actualHandleMethodArgumentNotValidResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
        assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
        verify(methodArgumentNotValidException).getMessage();
    }

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMethodArgumentNotValid5() {
        // Arrange
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        when(methodArgumentNotValidException.getMessage()).thenReturn("An error occurred");
        HttpHeaders headers = new HttpHeaders();

        // Act
        ResponseEntity<Object> actualHandleMethodArgumentNotValidResult = customizedResponseEntityExceptionHandler
                .handleMethodArgumentNotValid(methodArgumentNotValidException, headers, HttpStatus.PROCESSING,
                        new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("An error occurred", actualHandleMethodArgumentNotValidResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
        assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
        verify(methodArgumentNotValidException).getMessage();
    }

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMethodArgumentNotValid6() {
        // Arrange
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        when(methodArgumentNotValidException.getMessage()).thenReturn("An error occurred");
        HttpHeaders headers = new HttpHeaders();

        // Act
        ResponseEntity<Object> actualHandleMethodArgumentNotValidResult = customizedResponseEntityExceptionHandler
                .handleMethodArgumentNotValid(methodArgumentNotValidException, headers, HttpStatus.CHECKPOINT,
                        new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("An error occurred", actualHandleMethodArgumentNotValidResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
        assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
        verify(methodArgumentNotValidException).getMessage();
    }

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
     */
    @Test
    void testHandleMethodArgumentNotValid7() {
        // Arrange
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        when(methodArgumentNotValidException.getMessage()).thenReturn("An error occurred");
        HttpHeaders headers = new HttpHeaders();

        // Act
        ResponseEntity<Object> actualHandleMethodArgumentNotValidResult = customizedResponseEntityExceptionHandler
                .handleMethodArgumentNotValid(methodArgumentNotValidException, headers, HttpStatus.OK,
                        new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("An error occurred", actualHandleMethodArgumentNotValidResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMethodArgumentNotValidResult.getStatusCode());
        assertTrue(actualHandleMethodArgumentNotValidResult.getHeaders().isEmpty());
        verify(methodArgumentNotValidException).getMessage();
    }

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleBlogAlreadyExistsException(RecordAlreadyPresentException)}
     */
    @Test
    void testHandleBlogAlreadyExistsException() {
        // Arrange and Act
        ResponseEntity<Object> actualHandleBlogAlreadyExistsExceptionResult = customizedResponseEntityExceptionHandler
                .handleBlogAlreadyExistsException(new RecordAlreadyPresentException("An error occurred"));

        // Assert
        assertEquals("An error occurred", actualHandleBlogAlreadyExistsExceptionResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualHandleBlogAlreadyExistsExceptionResult.getStatusCode());
        assertTrue(actualHandleBlogAlreadyExistsExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CustomizedResponseEntityExceptionHandler#handleBlogAlreadyExistsException(RecordAlreadyPresentException)}
     */
    @Test
    void testHandleBlogAlreadyExistsException3() {
        // Arrange
        RecordAlreadyPresentException recordAlreadyPresentException = mock(RecordAlreadyPresentException.class);
        when(recordAlreadyPresentException.getMessage()).thenReturn("Not all who wander are lost");

        // Act
        ResponseEntity<Object> actualHandleBlogAlreadyExistsExceptionResult = customizedResponseEntityExceptionHandler
                .handleBlogAlreadyExistsException(recordAlreadyPresentException);

        // Assert
        assertEquals("Not all who wander are lost", actualHandleBlogAlreadyExistsExceptionResult.getBody());
        assertEquals(HttpStatus.CONFLICT, actualHandleBlogAlreadyExistsExceptionResult.getStatusCode());
        assertTrue(actualHandleBlogAlreadyExistsExceptionResult.getHeaders().isEmpty());
        verify(recordAlreadyPresentException).getMessage();
    }
}

