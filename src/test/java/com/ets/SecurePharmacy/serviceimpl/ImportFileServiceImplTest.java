package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.service.ItemCreationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ImportFileServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ImportFileServiceImplTest {
    @Autowired
    private ImportFileServiceImpl importFileServiceImpl;

    @MockBean
    private ItemCreationService itemCreationService;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link ImportFileServiceImpl#getMappedItem(MultipartFile)}
     */
    @Test
    void testGetMappedItem() throws UnsupportedEncodingException, RestClientException {
        // Arrange
        when(itemCreationService.getAllItemList()).thenReturn(new ArrayList<>());
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.CONTINUE);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(responseEntity);

        // Act and Assert
        assertSame(responseEntity,
                importFileServiceImpl.getMappedItem(new MockMultipartFile("Name", "AAAAAAAA".getBytes("UTF-8"))));
        verify(itemCreationService).getAllItemList();
        verify(restTemplate).postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any());
    }
}

