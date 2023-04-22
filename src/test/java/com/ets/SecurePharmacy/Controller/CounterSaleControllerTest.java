package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.CounterSaleService;
import com.ets.SecurePharmacy.tenant.model.CounterSaleEntity;
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

@ContextConfiguration(classes = {CounterSaleController.class})
@ExtendWith(SpringExtension.class)
class CounterSaleControllerTest {
    @Autowired
    private CounterSaleController counterSaleController;

    @MockBean
    private CounterSaleService counterSaleService;

    /**
     * Method under test: {@link CounterSaleController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts() throws Exception {
        when(counterSaleService.getAllCounterSales()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counter-sale/getAll");
        MockMvcBuilders.standaloneSetup(counterSaleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link CounterSaleController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts2() throws Exception {
        when(counterSaleService.getAllCounterSales()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counter-sale/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(counterSaleController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CounterSaleController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        when(counterSaleService.getCounterSaleById((Long) any())).thenReturn(counterSaleEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counter-sale/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(counterSaleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<CounterSaleEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate><totalItems"
                                        + ">1000</totalItems><totalVal>10.0</totalVal><status>Status</status><counteSaleDetails/><id>123</id><"
                                        + "/CounterSaleEntity>"));
    }

    /**
     * Method under test: {@link CounterSaleController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(counterSaleService.getCounterSaleById((Long) any()))
                .thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counter-sale/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(counterSaleController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CounterSaleController#getCounterSaleWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetCounterSaleWithPagination() throws Exception {
        when(counterSaleService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CounterSaleEntity>> response = counterSaleController.getCounterSaleWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link CounterSaleController#getCounterSaleWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetCounterSaleWithPagination2() throws Exception {
        when(counterSaleService.getAllCounterSaleWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CounterSaleEntity>> response = counterSaleController.getCounterSaleWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link CounterSaleController#openStockCreate(CounterSaleEntity)}
     */
    @Test
    void testOpenStockCreate() throws Exception {
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(counterSaleEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/counter-sale/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(counterSaleController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CounterSaleController#openStockCreate(CounterSaleEntity)}
     */
    @Test
    void testOpenStockCreate2() throws Exception {
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        when(counterSaleService.createOrUpdateCounterSale((CounterSaleEntity) any())).thenReturn(counterSaleEntity);

        CounterSaleEntity counterSaleEntity1 = new CounterSaleEntity();
        counterSaleEntity1.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity1.setEntryDate(null);
        counterSaleEntity1.setId(123L);
        counterSaleEntity1.setStatus("Status");
        counterSaleEntity1.setTotalItems(1000);
        counterSaleEntity1.setTotalVal(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(counterSaleEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/counter-sale/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(counterSaleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<CounterSaleEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate><totalItems"
                                        + ">1000</totalItems><totalVal>10.0</totalVal><status>Status</status><counteSaleDetails/><id>123</id><"
                                        + "/CounterSaleEntity>"));
    }

    /**
     * Method under test: {@link CounterSaleController#updateOpenStock(CounterSaleEntity)}
     */
    @Test
    void testUpdateOpenStock() throws Exception {
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(counterSaleEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/counter-sale/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(counterSaleController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CounterSaleController#updateOpenStock(CounterSaleEntity)}
     */
    @Test
    void testUpdateOpenStock2() throws Exception {
        CounterSaleEntity counterSaleEntity = new CounterSaleEntity();
        counterSaleEntity.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        counterSaleEntity.setId(123L);
        counterSaleEntity.setStatus("Status");
        counterSaleEntity.setTotalItems(1000);
        counterSaleEntity.setTotalVal(10.0d);
        when(counterSaleService.createOrUpdateCounterSale((CounterSaleEntity) any())).thenReturn(counterSaleEntity);

        CounterSaleEntity counterSaleEntity1 = new CounterSaleEntity();
        counterSaleEntity1.setCounteSaleDetails(new ArrayList<>());
        counterSaleEntity1.setEntryDate(null);
        counterSaleEntity1.setId(123L);
        counterSaleEntity1.setStatus("Status");
        counterSaleEntity1.setTotalItems(1000);
        counterSaleEntity1.setTotalVal(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(counterSaleEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/counter-sale/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(counterSaleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<CounterSaleEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate><totalItems"
                                        + ">1000</totalItems><totalVal>10.0</totalVal><status>Status</status><counteSaleDetails/><id>123</id><"
                                        + "/CounterSaleEntity>"));
    }
}

