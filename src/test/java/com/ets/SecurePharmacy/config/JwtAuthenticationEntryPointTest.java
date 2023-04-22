package com.ets.SecurePharmacy.config;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {JwtAuthenticationEntryPoint.class})
@ExtendWith(SpringExtension.class)
class JwtAuthenticationEntryPointTest {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    /**
     * Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest, HttpServletResponse, AuthenticationException)}
     */
    @Test
    void testCommence2() throws IOException, ServletException {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Act
        jwtAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));
    }

    /**
     * Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest, HttpServletResponse, AuthenticationException)}
     */
    @Test
    void testCommence3() throws IOException, ServletException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = mock(Response.class);
        doNothing().when(response).sendError(anyInt(), (String) any());

        // Act
        jwtAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));

        // Assert
        verify(response).sendError(anyInt(), (String) any());
    }
}

