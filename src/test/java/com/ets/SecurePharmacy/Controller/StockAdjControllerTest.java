package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.StockAdjService;
import com.ets.SecurePharmacy.tenant.model.StockAdjEntity;
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

@ContextConfiguration(classes = {StockAdjController.class})
@ExtendWith(SpringExtension.class)
class StockAdjControllerTest {
    @Autowired
    private StockAdjController stockAdjController;

    @MockBean
    private StockAdjService stockAdjService;

    /**
     * Method under test: {@link StockAdjController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts() throws Exception {
        when(stockAdjService.getAllStockAgjDetails()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/StockAdj/getAll");
        MockMvcBuilders.standaloneSetup(stockAdjController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link StockAdjController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        when(stockAdjService.getStockAdjById((Long) any())).thenReturn(stockAdjEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/StockAdj/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(stockAdjController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<StockAdjEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate><totalAdj>Total"
                                        + " Adj</totalAdj><status>Status</status><stockAdjDetails/><id>123</id></StockAdjEntity>"));
    }

    /**
     * Method under test: {@link StockAdjController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice1() throws Exception {
        when(stockAdjService.getStockAdjById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/StockAdj/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(stockAdjController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link StockAdjController#getStockAdjWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetStockAdjWithPagination() throws Exception {
        when(stockAdjService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<StockAdjEntity>> response = stockAdjController.getStockAdjWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link StockAdjController#getStockAdjWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetStockAdjWithPagination2() throws Exception {
        when(stockAdjService.getAllStockAdjEntityWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<StockAdjEntity>> response = stockAdjController.getStockAdjWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link StockAdjController#openStockCreate(StockAdjEntity)}
     */
    @Test
    void testOpenStockCreate() throws Exception {
        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        String content = (new ObjectMapper()).writeValueAsString(stockAdjEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/StockAdj/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(stockAdjController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link StockAdjController#openStockCreate(StockAdjEntity)}
     */
    @Test
    void testOpenStockCreate2() throws Exception {
        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        when(stockAdjService.createOrUpdateStockAdj((StockAdjEntity) any())).thenReturn(stockAdjEntity);

        StockAdjEntity stockAdjEntity1 = new StockAdjEntity();
        stockAdjEntity1.setEntryDate(null);
        stockAdjEntity1.setId(123L);
        stockAdjEntity1.setStatus("Status");
        stockAdjEntity1.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity1.setTotalAdj("Total Adj");
        String content = (new ObjectMapper()).writeValueAsString(stockAdjEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/StockAdj/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(stockAdjController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<StockAdjEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate><totalAdj>Total"
                                        + " Adj</totalAdj><status>Status</status><stockAdjDetails/><id>123</id></StockAdjEntity>"));
    }

    /**
     * Method under test: {@link StockAdjController#openStockCreate(StockAdjEntity)}
     */
    @Test
    void testOpenStockCreate3() throws Exception {
        when(stockAdjService.createOrUpdateStockAdj((StockAdjEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(null);
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        String content = (new ObjectMapper()).writeValueAsString(stockAdjEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/StockAdj/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(stockAdjController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link StockAdjController#updateOpenStock(StockAdjEntity)}
     */
    @Test
    void testUpdateOpenStock() throws Exception {
        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        String content = (new ObjectMapper()).writeValueAsString(stockAdjEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/StockAdj/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(stockAdjController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link StockAdjController#updateOpenStock(StockAdjEntity)}
     */
    @Test
    void testUpdateOpenStock2() throws Exception {
        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        when(stockAdjService.createOrUpdateStockAdj((StockAdjEntity) any())).thenReturn(stockAdjEntity);

        StockAdjEntity stockAdjEntity1 = new StockAdjEntity();
        stockAdjEntity1.setEntryDate(null);
        stockAdjEntity1.setId(123L);
        stockAdjEntity1.setStatus("Status");
        stockAdjEntity1.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity1.setTotalAdj("Total Adj");
        String content = (new ObjectMapper()).writeValueAsString(stockAdjEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/StockAdj/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(stockAdjController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<StockAdjEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate><totalAdj>Total"
                                        + " Adj</totalAdj><status>Status</status><stockAdjDetails/><id>123</id></StockAdjEntity>"));
    }

    /**
     * Method under test: {@link StockAdjController#updateOpenStock(StockAdjEntity)}
     */
    @Test
    void testUpdateOpenStock3() throws Exception {
        when(stockAdjService.createOrUpdateStockAdj((StockAdjEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        StockAdjEntity stockAdjEntity = new StockAdjEntity();
        stockAdjEntity.setEntryDate(null);
        stockAdjEntity.setId(123L);
        stockAdjEntity.setStatus("Status");
        stockAdjEntity.setStockAdjDetails(new ArrayList<>());
        stockAdjEntity.setTotalAdj("Total Adj");
        String content = (new ObjectMapper()).writeValueAsString(stockAdjEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/StockAdj/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(stockAdjController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

