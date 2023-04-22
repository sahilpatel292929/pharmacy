package com.ets.SecurePharmacy.Controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FileAttachmentController.class})
@ExtendWith(SpringExtension.class)
class FileAttachmentControllerTest {
    @Autowired
    private FileAttachmentController fileAttachmentController;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link FileAttachmentController#testWelcome()}
     */
    @Test
    void testTestWelcome() throws Exception {
        when(restTemplate.exchange((String) any(), (HttpMethod) any(), (HttpEntity<Object>) any(), (Class<Object>) any(),
                (Object[]) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/file/test");
        MockMvcBuilders.standaloneSetup(fileAttachmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FileAttachmentController#uploadFile(MultipartFile)}
     */
    @Test
    void testUploadFile() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/file/upload");
        postResult.accept("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("file", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(fileAttachmentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }
}

