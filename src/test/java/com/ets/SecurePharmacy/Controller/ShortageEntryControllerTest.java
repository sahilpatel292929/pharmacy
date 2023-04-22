package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.ShortageEntryService;
import com.ets.SecurePharmacy.tenant.model.ShortageEntryEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ShortageEntryController.class})
@ExtendWith(SpringExtension.class)
class ShortageEntryControllerTest {
    @Autowired
    private ShortageEntryController shortageEntryController;

    @MockBean
    private ShortageEntryService shortageEntryService;

    /**
     * Method under test: {@link ShortageEntryController#createShortage(ShortageEntryEntity)}
     */
    @Test
    void testCreateShortage() throws Exception {
        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(shortageEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ShortageEntry/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(shortageEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ShortageEntryController#createShortage(ShortageEntryEntity)}
     */
    @Test
    void testCreateShortage2() throws Exception {
        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);
        when(shortageEntryService.createOrUpdateShortage((ShortageEntryEntity) any())).thenReturn(shortageEntryEntity);

        ShortageEntryEntity shortageEntryEntity1 = new ShortageEntryEntity();
        shortageEntryEntity1.setEntryDate(null);
        shortageEntryEntity1.setId(123L);
        shortageEntryEntity1.setShortageDetails(new ArrayList<>());
        shortageEntryEntity1.setStatus("Status");
        shortageEntryEntity1.setTotalItems(10.0d);
        shortageEntryEntity1.setTotalQty(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(shortageEntryEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ShortageEntry/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(shortageEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ShortageEntryEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate>"
                                + "<totalItems>10.0</totalItems><totalQty>10.0</totalQty><status>Status</status><shortageDetails/><id>123"
                                + "</id></ShortageEntryEntity>"));
    }

    /**
     * Method under test: {@link ShortageEntryController#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() throws Exception {
        when(shortageEntryService.getAllShortage()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ShortageEntry/getAll");
        MockMvcBuilders.standaloneSetup(shortageEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link ShortageEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);
        when(shortageEntryService.getShortageById((Long) any())).thenReturn(shortageEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ShortageEntry/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(shortageEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ShortageEntryEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate>"
                                + "<totalItems>10.0</totalItems><totalQty>10.0</totalQty><status>Status</status><shortageDetails/><id>123"
                                + "</id></ShortageEntryEntity>"));
    }

    /**
     * Method under test: {@link ShortageEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(shortageEntryService.getShortageById((Long) any()))
                .thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ShortageEntry/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(shortageEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ShortageEntryController#getShortageEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetShortageEntryWithPagination() throws Exception {
        when(shortageEntryService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<ShortageEntryEntity>> response = shortageEntryController.getShortageEntryWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link ShortageEntryController#getShortageEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetShortageEntryWithPagination2() throws Exception {
        when(shortageEntryService.getAllShortageEntryWithPagination(Mockito.anyInt(),(Mockito.anyInt())))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<ShortageEntryEntity>> response = shortageEntryController.getShortageEntryWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link ShortageEntryController#updateShortage(ShortageEntryEntity)}
     */
    @Test
    void testUpdateShortage() throws Exception {
        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(shortageEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/ShortageEntry/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(shortageEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ShortageEntryController#updateShortage(ShortageEntryEntity)}
     */
    @Test
    void testUpdateShortage2() throws Exception {
        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);
        when(shortageEntryService.createOrUpdateShortage((ShortageEntryEntity) any())).thenReturn(shortageEntryEntity);

        ShortageEntryEntity shortageEntryEntity1 = new ShortageEntryEntity();
        shortageEntryEntity1.setEntryDate(null);
        shortageEntryEntity1.setId(123L);
        shortageEntryEntity1.setShortageDetails(new ArrayList<>());
        shortageEntryEntity1.setStatus("Status");
        shortageEntryEntity1.setTotalItems(10.0d);
        shortageEntryEntity1.setTotalQty(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(shortageEntryEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/ShortageEntry/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(shortageEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ShortageEntryEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate>"
                                + "<totalItems>10.0</totalItems><totalQty>10.0</totalQty><status>Status</status><shortageDetails/><id>123"
                                + "</id></ShortageEntryEntity>"));
    }

    /**
     * Method under test: {@link ShortageEntryController#updateShortage(ShortageEntryEntity)}
     */
    @Test
    void testUpdateShortage3() throws Exception {
        when(shortageEntryService.createOrUpdateShortage((ShortageEntryEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        ShortageEntryEntity shortageEntryEntity = new ShortageEntryEntity();
        shortageEntryEntity.setEntryDate(null);
        shortageEntryEntity.setId(123L);
        shortageEntryEntity.setShortageDetails(new ArrayList<>());
        shortageEntryEntity.setStatus("Status");
        shortageEntryEntity.setTotalItems(10.0d);
        shortageEntryEntity.setTotalQty(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(shortageEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/ShortageEntry/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(shortageEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

