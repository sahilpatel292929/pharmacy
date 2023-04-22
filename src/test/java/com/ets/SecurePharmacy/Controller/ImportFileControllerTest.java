package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.service.ImportFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ImportFileController.class})
@ExtendWith(SpringExtension.class)
class ImportFileControllerTest {

    @MockBean
    private ImportFileService importFileService;

    @Autowired
    ImportFileController importFileController;

    /**
     * Method under test: {@link ImportFileController#getMappedItem(MultipartFile)}
     */
    @Test
    void testGetMappedItem() throws Exception {
        MockMultipartFile sampleFile = new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));
        when(importFileService.getMappedItem(sampleFile))
                .thenReturn(new Object());
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/item-mapped").file(sampleFile);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(importFileController).build()
                .perform(multipartRequest);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

