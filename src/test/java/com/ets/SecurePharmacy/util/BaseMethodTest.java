package com.ets.SecurePharmacy.util;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = {BaseMethod.class})
@ExtendWith(SpringExtension.class)
class BaseMethodTest {
    @Autowired
    private BaseMethod baseMethod;

    @MockBean
    private JwtUtil jwtUtil;

    /**
     * Method under test: {@link BaseMethod#getPrinciple()}
     */
    @Test
    void testGetPrinciple() {
        try (MockedStatic<SecurityContextHolder> securityContextHolderMockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(new User("fdgddfd", "fsfdfdfd", Collections.emptyList()), "dsfd");
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            securityContextHolderMockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            String result = BaseMethod.getPrinciple();
            Assertions.assertNotNull(result);
        }
    }

    /**
     * Method under test: {@link BaseMethod#getPrinciple()}
     */
    @Test
    void testGetPrinciple2() {
        try (MockedStatic<SecurityContextHolder> securityContextHolderMockedStatic = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken("fdgddfd", "dsfd");
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            securityContextHolderMockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            String result = BaseMethod.getPrinciple();
            Assertions.assertNotNull(result);
        }
    }

    /**
     * Method under test: {@link BaseMethod#getUserId()}
     */
    @Test
    void testGetUserId() throws Exception {
        assertThrows(UnableToProcessException.class, () -> baseMethod.getUserId());
    }

    /**
     * Method under test: {@link BaseMethod#getUserId()}
     */
    @Test
    void testGetUserId2() throws Exception {
        try (MockedStatic<RequestContextHolder> requestContextHolderMockedStatic = Mockito.mockStatic(RequestContextHolder.class)) {
            ServletRequestAttributes requestAttributes = Mockito.mock(ServletRequestAttributes.class);
            HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
            requestContextHolderMockedStatic.when(RequestContextHolder::getRequestAttributes).thenReturn(requestAttributes);
            Mockito.when(requestAttributes.getRequest()).thenReturn(httpServletRequest);
            Mockito.when(httpServletRequest.getHeader(Mockito.anyString())).thenReturn("Bearer samplevalue");
            Mockito.when(jwtUtil.getUserId(Mockito.anyString())).thenReturn(25);
            Long result = baseMethod.getUserId();
            Assertions.assertNotNull(result);
        }
    }

    /**
     * Method under test: {@link BaseMethod#getUserId()}
     */
    @Test
    void testGetUserId3() throws Exception {
        try (MockedStatic<RequestContextHolder> requestContextHolderMockedStatic = Mockito.mockStatic(RequestContextHolder.class)) {
            ServletRequestAttributes requestAttributes = Mockito.mock(ServletRequestAttributes.class);
            HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
            requestContextHolderMockedStatic.when(RequestContextHolder::getRequestAttributes).thenReturn(requestAttributes);
            Mockito.when(requestAttributes.getRequest()).thenReturn(httpServletRequest);
            Mockito.when(httpServletRequest.getHeader(Mockito.anyString())).thenReturn("Bearer samplevalue");
            Mockito.when(jwtUtil.getUserId(Mockito.anyString())).thenThrow(IllegalArgumentException.class);
            assertThrows(UnableToProcessException.class, () -> baseMethod.getUserId());
        }
    }

    /**
     * Method under test: {@link BaseMethod#getUserId()}
     */
    @Test
    void testGetUserId4() throws Exception {
        try (MockedStatic<RequestContextHolder> requestContextHolderMockedStatic = Mockito.mockStatic(RequestContextHolder.class)) {
            ServletRequestAttributes requestAttributes = Mockito.mock(ServletRequestAttributes.class);
            HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
            requestContextHolderMockedStatic.when(RequestContextHolder::getRequestAttributes).thenReturn(requestAttributes);
            Mockito.when(requestAttributes.getRequest()).thenReturn(httpServletRequest);
            Mockito.when(httpServletRequest.getHeader(Mockito.anyString())).thenReturn("Bearer samplevalue");
            Mockito.when(jwtUtil.getUserId(Mockito.anyString())).thenThrow(ExpiredJwtException.class);
            assertThrows(UnableToProcessException.class, () -> baseMethod.getUserId());
        }
    }

    /**
     * Method under test: {@link BaseMethod#getUserId()}
     */
    @Test
    void testGetUserId5() throws Exception {
        try (MockedStatic<RequestContextHolder> requestContextHolderMockedStatic = Mockito.mockStatic(RequestContextHolder.class)) {
            ServletRequestAttributes requestAttributes = Mockito.mock(ServletRequestAttributes.class);
            HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
            requestContextHolderMockedStatic.when(RequestContextHolder::getRequestAttributes).thenReturn(requestAttributes);
            Mockito.when(requestAttributes.getRequest()).thenReturn(httpServletRequest);
            Mockito.when(httpServletRequest.getHeader(Mockito.anyString())).thenReturn("samplevalue");
            assertThrows(UnableToProcessException.class, () -> baseMethod.getUserId());
        }
    }
}

