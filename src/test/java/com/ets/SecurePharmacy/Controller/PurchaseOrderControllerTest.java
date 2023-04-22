package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.PurchaseOrderEntryService;
import com.ets.SecurePharmacy.tenant.model.PurchaseOrderEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
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
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
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

@ContextConfiguration(classes = {PurchaseOrderController.class})
@ExtendWith(SpringExtension.class)
class PurchaseOrderControllerTest {
    @Autowired
    private PurchaseOrderController purchaseOrderController;

    @MockBean
    private PurchaseOrderEntryService purchaseOrderEntryService;

    /**
     * Method under test: {@link PurchaseOrderController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(purchaseOrderEntryService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-order/getAll");
        MockMvcBuilders.standaloneSetup(purchaseOrderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link PurchaseOrderController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(purchaseOrderEntryService.getAll()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-order/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseOrderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseOrderController#exportReport10()}
     */
    @Test
    void testExportReport10() throws Exception {
        when(purchaseOrderEntryService.getAll()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseOrderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseOrderController#exportReport10()}
     */
    @Test
    void testExportReport102() throws Exception {
        when(purchaseOrderEntryService.getAll()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/purchase-order/getPurchaseOrderDetailsReport");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseOrderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseOrderController#getAllReports(Long, Long)}
     */
    @Test
    void testGetAllReports() throws Exception {
        when(purchaseOrderEntryService.findByCriteria((Long) any(), (Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/purchase-order/get_reports/{itemsId}/{supplierId}", 123L, 123L);
        MockMvcBuilders.standaloneSetup(purchaseOrderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link PurchaseOrderController#getAllReports(Long, Long)}
     */
    @Test
    void testGetAllReports2() throws Exception {
        when(purchaseOrderEntryService.findByCriteria((Long) any(), (Long) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/purchase-order/get_reports/{itemsId}/{supplierId}", 123L, 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseOrderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseOrderController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
        purchaseOrderEntity.setId(123L);
        purchaseOrderEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        purchaseOrderEntity.setOrderQty(1L);
        purchaseOrderEntity.setPurchaseOrderDetails(new ArrayList<>());
        purchaseOrderEntity.setStatus("Status");
        purchaseOrderEntity.setSupplierDetails(supplierCreationEntity);
        purchaseOrderEntity.setSupplierName("Supplier Name");
        purchaseOrderEntity.setTotalCount(3L);
        when(purchaseOrderEntryService.getById((Long) any())).thenReturn(purchaseOrderEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-order/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(purchaseOrderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<PurchaseOrderEntity><id>123</id><supplierName>Supplier Name</supplierName><orderDate>1970</orderDate"
                                        + "><orderDate>1</orderDate><orderDate>2</orderDate><totalCount>3</totalCount><orderQty>1</orderQty>"
                                        + "<status>Status</status><supplierDetails><id>123</id><supplierName>Supplier Name</supplierName><mobileNo"
                                        + ">Mobile No</mobileNo><address1>42 Main St</address1><address2>42 Main St</address2><gstNo>Gst"
                                        + " No</gstNo><gstType>Gst Type</gstType><credit_days>1</credit_days><discount>3</discount><rateSlab>Rate"
                                        + " Slab</rateSlab><modeOfPayment>Mode Of Payment</modeOfPayment><openingBal>1</openingBal><openingBalDate"
                                        + ">1970</openingBalDate><openingBalDate>1</openingBalDate><openingBalDate>2</openingBalDate><created_by>Jan"
                                        + " 1, 2020 8:00am GMT+0100</created_by><created_date>1970</created_date><created_date>1</created_date>"
                                        + "<created_date>2</created_date><updated_by>1</updated_by><updated_date>1970</updated_date><updated_date"
                                        + ">1</updated_date><updated_date>2</updated_date><status>Status</status></supplierDetails><purchaseOrderDetails"
                                        + "/><supplierDetailsId>123</supplierDetailsId></PurchaseOrderEntity>"));
    }

    /**
     * Method under test: {@link PurchaseOrderController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(purchaseOrderEntryService.getById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-order/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseOrderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseOrderController#getPurchaseOrderWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPurchaseOrderWithPagination() throws Exception {
        when(purchaseOrderEntryService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<PurchaseOrderEntity>> response = purchaseOrderController.getPurchaseOrderWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link PurchaseOrderController#getPurchaseOrderWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPurchaseOrderWithPagination2() throws Exception {
        when(purchaseOrderEntryService.getAllPurchaseOrderWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<PurchaseOrderEntity>> response = purchaseOrderController.getPurchaseOrderWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link PurchaseOrderController#PackCreate(PurchaseOrderEntity)}
     */
    @Test
    void testPackCreate() throws Exception {
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
        purchaseOrderEntity.setId(123L);
        purchaseOrderEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        purchaseOrderEntity.setOrderQty(1L);
        purchaseOrderEntity.setPurchaseOrderDetails(new ArrayList<>());
        purchaseOrderEntity.setStatus("Status");
        purchaseOrderEntity.setSupplierDetails(supplierCreationEntity);
        purchaseOrderEntity.setSupplierName("Supplier Name");
        purchaseOrderEntity.setTotalCount(3L);
        when(purchaseOrderEntryService.createOrUpdate(purchaseOrderEntity)).thenReturn(purchaseOrderEntity);
        ResponseEntity<PurchaseOrderEntity> response = purchaseOrderController.PackCreate(purchaseOrderEntity);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link PurchaseOrderController#updatePurchase(PurchaseOrderEntity, long)}
     */
    @Test
    void testUpdatePurchase() throws Exception {
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
        purchaseOrderEntity.setId(123L);
        purchaseOrderEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        purchaseOrderEntity.setOrderQty(1L);
        purchaseOrderEntity.setPurchaseOrderDetails(new ArrayList<>());
        purchaseOrderEntity.setStatus("Status");
        purchaseOrderEntity.setSupplierDetails(supplierCreationEntity);
        purchaseOrderEntity.setSupplierName("Supplier Name");
        purchaseOrderEntity.setTotalCount(3L);
        when(purchaseOrderEntryService.createOrUpdate(purchaseOrderEntity)).thenReturn(purchaseOrderEntity);
        ResponseEntity<PurchaseOrderEntity> response = purchaseOrderController.updatePurchase(purchaseOrderEntity, 123L);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(purchaseOrderEntity));
    }
}

