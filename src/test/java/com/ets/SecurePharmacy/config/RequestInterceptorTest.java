package com.ets.SecurePharmacy.config;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ContextConfiguration(classes = {RequestInterceptor.class})
@ExtendWith(SpringExtension.class)
class RequestInterceptorTest {
    @Autowired
    private RequestInterceptor requestInterceptor;

    /**
     * Method under test: {@link RequestInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}
     */
    @Test
    void testPostHandle() throws Exception {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();

        // Act
        requestInterceptor.postHandle(request, response, "Handler", new ModelAndView("View Name"));
    }
}

