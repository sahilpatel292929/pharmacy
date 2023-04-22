package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.CashWithdrawService;
import com.ets.SecurePharmacy.tenant.model.CashWithdrawEntity;
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

@ContextConfiguration(classes = {CashWithdrawController.class})
@ExtendWith(SpringExtension.class)
class CashWithdrawControllerTest {
    @Autowired
    private CashWithdrawController cashWithdrawController;

    @MockBean
    private CashWithdrawService cashWithdrawService;

    /**
     * Method under test: {@link CashWithdrawController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts() throws Exception {
        when(cashWithdrawService.getAllCashWithdraw()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cash-withdraw/getAll");
        MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link CashWithdrawController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts2() throws Exception {
        when(cashWithdrawService.getAllCashWithdraw()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cash-withdraw/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CashWithdrawController#getBatchWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetBatchWithPagination() throws Exception {
        when(cashWithdrawService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CashWithdrawEntity>> response = cashWithdrawController.getBatchWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link CashWithdrawController#getBatchWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetBatchWithPagination2() throws Exception {
        when(cashWithdrawService.getAllDepositsWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CashWithdrawEntity>> response = cashWithdrawController.getBatchWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link CashWithdrawController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        when(cashWithdrawService.getCashWithDrawById((Long) any())).thenReturn(cashWithdrawEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cash-withdraw/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<CashWithdrawEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate>"
                                + "<bankName>Bank Name</bankName><amount>10.0</amount><status>Status</status><id>123</id><"
                                + "/CashWithdrawEntity>"));
    }

    /**
     * Method under test: {@link CashWithdrawController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(cashWithdrawService.getCashWithDrawById((Long) any()))
                .thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cash-withdraw/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CashWithdrawController#openStockCreate(CashWithdrawEntity)}
     */
    @Test
    void testOpenStockCreate() throws Exception {
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashWithdrawEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cash-withdraw/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CashWithdrawController#openStockCreate(CashWithdrawEntity)}
     */
    @Test
    void testOpenStockCreate2() throws Exception {
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        when(cashWithdrawService.createOrUpdateCashWithdraw((CashWithdrawEntity) any())).thenReturn(cashWithdrawEntity);

        CashWithdrawEntity cashWithdrawEntity1 = new CashWithdrawEntity();
        cashWithdrawEntity1.setAmount(10.0d);
        cashWithdrawEntity1.setBankName("Bank Name");
        cashWithdrawEntity1.setEntryDate(null);
        cashWithdrawEntity1.setId(123L);
        cashWithdrawEntity1.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashWithdrawEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cash-withdraw/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<CashWithdrawEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate>"
                                + "<bankName>Bank Name</bankName><amount>10.0</amount><status>Status</status><id>123</id><"
                                + "/CashWithdrawEntity>"));
    }

    /**
     * Method under test: {@link CashWithdrawController#openStockCreate(CashWithdrawEntity)}
     */
    @Test
    void testOpenStockCreate3() throws Exception {
        when(cashWithdrawService.createOrUpdateCashWithdraw((CashWithdrawEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(null);
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashWithdrawEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cash-withdraw/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CashWithdrawController#updateOpenStock(CashWithdrawEntity)}
     */
    @Test
    void testUpdateOpenStock() throws Exception {
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashWithdrawEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cash-withdraw/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CashWithdrawController#updateOpenStock(CashWithdrawEntity)}
     */
    @Test
    void testUpdateOpenStock2() throws Exception {
        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        when(cashWithdrawService.createOrUpdateCashWithdraw((CashWithdrawEntity) any())).thenReturn(cashWithdrawEntity);

        CashWithdrawEntity cashWithdrawEntity1 = new CashWithdrawEntity();
        cashWithdrawEntity1.setAmount(10.0d);
        cashWithdrawEntity1.setBankName("Bank Name");
        cashWithdrawEntity1.setEntryDate(null);
        cashWithdrawEntity1.setId(123L);
        cashWithdrawEntity1.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashWithdrawEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cash-withdraw/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<CashWithdrawEntity><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate>"
                                + "<bankName>Bank Name</bankName><amount>10.0</amount><status>Status</status><id>123</id><"
                                + "/CashWithdrawEntity>"));
    }

    /**
     * Method under test: {@link CashWithdrawController#updateOpenStock(CashWithdrawEntity)}
     */
    @Test
    void testUpdateOpenStock3() throws Exception {
        when(cashWithdrawService.createOrUpdateCashWithdraw((CashWithdrawEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        CashWithdrawEntity cashWithdrawEntity = new CashWithdrawEntity();
        cashWithdrawEntity.setAmount(10.0d);
        cashWithdrawEntity.setBankName("Bank Name");
        cashWithdrawEntity.setEntryDate(null);
        cashWithdrawEntity.setId(123L);
        cashWithdrawEntity.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(cashWithdrawEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cash-withdraw/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cashWithdrawController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

