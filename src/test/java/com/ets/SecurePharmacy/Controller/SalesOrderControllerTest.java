package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.SalesOrderEntryService;
import com.ets.SecurePharmacy.tenant.model.SalesOrderEntryEntity;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@ContextConfiguration(classes = {SalesOrderController.class})
@ExtendWith(SpringExtension.class)
class SalesOrderControllerTest {
    @Autowired
    private SalesOrderController salesOrderController;

    @MockBean
    private SalesOrderEntryService salesOrderEntryService;

    /**
     * Method under test: {@link SalesOrderController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(salesOrderEntryService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sales-order/getAll");
        MockMvcBuilders.standaloneSetup(salesOrderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link SalesOrderController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(salesOrderEntryService.getAll()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sales-order/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(salesOrderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SalesOrderController#getByMobileNumber(Long)}
     */
    @Test
    void testGetByMobileNumber() throws Exception {
        when(salesOrderEntryService.getByMobileNumber((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/sales-order/getByMobileNumber/{mobileNo}", 1L);
        MockMvcBuilders.standaloneSetup(salesOrderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link SalesOrderController#getByMobileNumber(Long)}
     */
    @Test
    void testGetByMobileNumber2() throws Exception {
        when(salesOrderEntryService.getByMobileNumber((Long) any()))
                .thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/sales-order/getByMobileNumber/{mobileNo}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(salesOrderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SalesOrderController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        SalesOrderEntryEntity salesOrderEntryEntity = new SalesOrderEntryEntity();
        salesOrderEntryEntity.setCustomerAddress("42 Main St");
        salesOrderEntryEntity.setCustomerMobileNo(1L);
        salesOrderEntryEntity.setCustomerName("Customer Name");
        salesOrderEntryEntity.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setId(123L);
        salesOrderEntryEntity.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setStatus("Status");
        salesOrderEntryEntity.setTotalCount(3L);
        salesOrderEntryEntity.setTotalQty(1L);
        when(salesOrderEntryService.getById((Long) any())).thenReturn(salesOrderEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sales-order/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(salesOrderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<SalesOrderEntryEntity><id>123</id><customerMobileNo>1</customerMobileNo><customerName>Customer"
                                + " Name</customerName><customerAddress>42 Main St</customerAddress><soDate>1970</soDate><soDate>1</soDate"
                                + "><soDate>2</soDate><deliveryDate>1970</deliveryDate><deliveryDate>1</deliveryDate><deliveryDate>2<"
                                + "/deliveryDate><totalCount>3</totalCount><totalQty>1</totalQty><status>Status</status><salesOrderDetailsEntity"
                                + "/></SalesOrderEntryEntity>"));
    }

    /**
     * Method under test: {@link SalesOrderController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(salesOrderEntryService.getById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sales-order/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(salesOrderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SalesOrderController#getSalesOrderEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSalesOrderEntryWithPagination() throws Exception {
        when(salesOrderEntryService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SalesOrderEntryEntity>> response = salesOrderController.getSalesOrderEntryWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link SalesOrderController#getSalesOrderEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSalesOrderEntryWithPagination2() throws Exception {
        when(salesOrderEntryService.getAllSalesOrderEntryEntityWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SalesOrderEntryEntity>> response = salesOrderController.getSalesOrderEntryWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link SalesOrderController#PackCreate(SalesOrderEntryEntity)}
     */
    @Test
    void testPackCreate() throws Exception {
        SalesOrderEntryEntity salesOrderEntryEntity = new SalesOrderEntryEntity();
        salesOrderEntryEntity.setCustomerAddress("42 Main St");
        salesOrderEntryEntity.setCustomerMobileNo(1L);
        salesOrderEntryEntity.setCustomerName("Customer Name");
        salesOrderEntryEntity.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setStatus("Status");
        salesOrderEntryEntity.setTotalCount(3L);
        salesOrderEntryEntity.setTotalQty(1L);
        SalesOrderEntryEntity salesOrderEntryEntity1 = new SalesOrderEntryEntity();
        salesOrderEntryEntity1.setCustomerAddress("42 Main St");
        salesOrderEntryEntity1.setCustomerMobileNo(1L);
        salesOrderEntryEntity1.setCustomerName("Customer Name");
        salesOrderEntryEntity1.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity1.setId(123L);
        salesOrderEntryEntity1.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity1.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity1.setStatus("Status");
        salesOrderEntryEntity1.setTotalCount(3L);
        salesOrderEntryEntity1.setTotalQty(1L);
        when(salesOrderEntryService.createOrUpdate(salesOrderEntryEntity)).thenReturn(salesOrderEntryEntity1);
        ResponseEntity<SalesOrderEntryEntity> response = salesOrderController.PackCreate(salesOrderEntryEntity);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link SalesOrderController#updatePurchase(SalesOrderEntryEntity, long)}
     */
    @Test
    void testUpdatePurchase() throws Exception {
        SalesOrderEntryEntity salesOrderEntryEntity = new SalesOrderEntryEntity();
        salesOrderEntryEntity.setCustomerAddress("42 Main St");
        salesOrderEntryEntity.setCustomerMobileNo(1L);
        salesOrderEntryEntity.setCustomerName("Customer Name");
        salesOrderEntryEntity.setDeliveryDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setId(123L);
        salesOrderEntryEntity.setSalesOrderDetailsEntity(new ArrayList<>());
        salesOrderEntryEntity.setSoDate(LocalDate.ofEpochDay(1L));
        salesOrderEntryEntity.setStatus("Status");
        salesOrderEntryEntity.setTotalCount(3L);
        salesOrderEntryEntity.setTotalQty(1L);
        when(salesOrderEntryService.createOrUpdate(salesOrderEntryEntity)).thenReturn(salesOrderEntryEntity);
        ResponseEntity<SalesOrderEntryEntity> response = salesOrderController.updatePurchase(salesOrderEntryEntity, 123L);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(salesOrderEntryEntity));
    }
}

