package com.ets.SecurePharmacy.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {MvcConfiguration.class})
@ExtendWith(SpringExtension.class)
class MvcConfigurationTest {
    @Autowired
    private MvcConfiguration mvcConfiguration;

    @MockBean
    private RequestInterceptor requestInterceptor;

    /**
     * Method under test: {@link MvcConfiguration#addInterceptors(InterceptorRegistry)}
     */
    @Test
    void testAddInterceptors() {
        // Arrange and Act
        mvcConfiguration.addInterceptors(new InterceptorRegistry());
    }

    /**
     * Method under test: {@link MvcConfiguration#addInterceptors(InterceptorRegistry)}
     */
    @Test
    void testAddInterceptors3() {
        // Arrange
        InterceptorRegistry interceptorRegistry = mock(InterceptorRegistry.class);
        when(interceptorRegistry.addInterceptor((HandlerInterceptor) any()))
                .thenReturn(new InterceptorRegistration(new RequestInterceptor()));

        // Act
        mvcConfiguration.addInterceptors(interceptorRegistry);

        // Assert
        verify(interceptorRegistry).addInterceptor((HandlerInterceptor) any());
    }
}

