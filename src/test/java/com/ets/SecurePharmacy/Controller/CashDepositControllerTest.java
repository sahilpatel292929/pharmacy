package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.CashDepositService;
import com.ets.SecurePharmacy.tenant.model.CashDepositEntity;
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

@ContextConfiguration(classes = {CashDepositController.class})
@ExtendWith(SpringExtension.class)
class CashDepositControllerTest {
    @Autowired
    private CashDepositController cashDepositController;

    @MockBean
    private CashDepositService cashDepositService;

    /**
     * Method under test: {@link CashDepositController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts() throws Exception {
        when(cashDepositService.getAllCashDeposit()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cash-deposit/getAll");
        MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link CashDepositController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts2() throws Exception {
        when(cashDepositService.getAllCashDeposit()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cash-deposit/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CashDepositController#getBatchWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetBatchWithPagination() throws Exception {
        when(cashDepositService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CashDepositEntity>> response = cashDepositController.getBatchWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link CashDepositController#getBatchWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetBatchWithPagination2() throws Exception {
        when(cashDepositService.getAllDepositsWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CashDepositEntity>> response = cashDepositController.getBatchWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link CashDepositController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        when(cashDepositService.getCashDepositById((Long) any())).thenReturn(cashDepositEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cash-deposit/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<CashDepositEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate>"
                                + "<bankName>Bank Name</bankName><amount>10.0</amount><status>Status</status><id>123</id><"
                                + "/CashDepositEntity>"));
    }

    /**
     * Method under test: {@link CashDepositController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(cashDepositService.getCashDepositById((Long) any()))
                .thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cash-deposit/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CashDepositController#openStockCreate(CashDepositEntity)}
     */
    @Test
    void testOpenStockCreate() throws Exception {
        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashDepositEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cash-deposit/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CashDepositController#openStockCreate(CashDepositEntity)}
     */
    @Test
    void testOpenStockCreate2() throws Exception {
        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        when(cashDepositService.createOrUpdateCashDeposit((CashDepositEntity) any())).thenReturn(cashDepositEntity);

        CashDepositEntity cashDepositEntity1 = new CashDepositEntity();
        cashDepositEntity1.setAmount(10.0d);
        cashDepositEntity1.setBankName("Bank Name");
        cashDepositEntity1.setEntryDate(null);
        cashDepositEntity1.setId(123L);
        cashDepositEntity1.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashDepositEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cash-deposit/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<CashDepositEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate>"
                                + "<bankName>Bank Name</bankName><amount>10.0</amount><status>Status</status><id>123</id><"
                                + "/CashDepositEntity>"));
    }

    /**
     * Method under test: {@link CashDepositController#openStockCreate(CashDepositEntity)}
     */
    @Test
    void testOpenStockCreate3() throws Exception {
        when(cashDepositService.createOrUpdateCashDeposit((CashDepositEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(null);
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashDepositEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cash-deposit/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CashDepositController#updateOpenStock(CashDepositEntity)}
     */
    @Test
    void testUpdateOpenStock() throws Exception {
        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashDepositEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cash-deposit/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CashDepositController#updateOpenStock(CashDepositEntity)}
     */
    @Test
    void testUpdateOpenStock2() throws Exception {
        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        when(cashDepositService.updateCashDeposit((CashDepositEntity) any())).thenReturn(cashDepositEntity);

        CashDepositEntity cashDepositEntity1 = new CashDepositEntity();
        cashDepositEntity1.setAmount(10.0d);
        cashDepositEntity1.setBankName("Bank Name");
        cashDepositEntity1.setEntryDate(null);
        cashDepositEntity1.setId(123L);
        cashDepositEntity1.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashDepositEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cash-deposit/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<CashDepositEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate>"
                                + "<bankName>Bank Name</bankName><amount>10.0</amount><status>Status</status><id>123</id><"
                                + "/CashDepositEntity>"));
    }

    /**
     * Method under test: {@link CashDepositController#updateOpenStock(CashDepositEntity)}
     */
    @Test
    void testUpdateOpenStock3() throws Exception {
        when(cashDepositService.updateCashDeposit((CashDepositEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        CashDepositEntity cashDepositEntity = new CashDepositEntity();
        cashDepositEntity.setAmount(10.0d);
        cashDepositEntity.setBankName("Bank Name");
        cashDepositEntity.setEntryDate(null);
        cashDepositEntity.setId(123L);
        cashDepositEntity.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashDepositEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cash-deposit/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashDepositController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

