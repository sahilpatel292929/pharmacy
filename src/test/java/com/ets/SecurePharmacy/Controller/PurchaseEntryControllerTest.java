package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.DTO.PurchaseReportDto;
import com.ets.SecurePharmacy.DTO.PurchaseReportFilterDTO;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.BatchService;
import com.ets.SecurePharmacy.service.PurchaseEntryService;
import com.ets.SecurePharmacy.service.PurchaseEntrySettingService;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntityDraft;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntrySettingEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
import com.ets.SecurePharmacy.transfomers.DTOTransfomers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
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

@ContextConfiguration(classes = {PurchaseEntryController.class})
@ExtendWith(SpringExtension.class)
class PurchaseEntryControllerTest {
    @MockBean
    private BatchService batchService;

    @MockBean
    private DTOTransfomers dTOTransfomers;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private PurchaseEntryController purchaseEntryController;

    @MockBean
    private PurchaseEntryService purchaseEntryService;

    @MockBean
    private PurchaseEntrySettingService purchaseEntrySettingService;

    /**
     * Method under test: {@link PurchaseEntryController#getDraftDetails()}
     */
    @Test
    void testGetDraftDetails() throws Exception {
        when(purchaseEntryService.getDraftDetails()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-entry/getAll-draft-details");
        MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link PurchaseEntryController#getDraftDetails()}
     */
    @Test
    void testGetDraftDetails2() throws Exception {
        when(purchaseEntryService.getDraftDetails()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-entry/getAll-draft-details");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseEntryController#deleteDraftById(Long)}
     */
    @Test
    void testDeleteDraftById() throws Exception {
        when(purchaseEntryService.deleteDraftById((Long) any())).thenReturn("Delete Draft By Id");
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/purchase-entry/delete-draft-byId");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Draft By Id"));
    }

    /**
     * Method under test: {@link PurchaseEntryController#deleteDraftById(Long)}
     */
    @Test
    void testDeleteDraftById2() throws Exception {
        when(purchaseEntryService.deleteDraftById((Long) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/purchase-entry/delete-draft-byId");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseEntryController#createDraft(PurchaseEntryEntityDraft)}
     */
    @Test
    void testCreateDraft() throws Exception {
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

        PurchaseEntryEntityDraft purchaseEntryEntityDraft = new PurchaseEntryEntityDraft();
        purchaseEntryEntityDraft.setCreatedUser(1L);
        purchaseEntryEntityDraft.setDisPercentage(1L);
        purchaseEntryEntityDraft.setDiscAmount(10.0d);
        purchaseEntryEntityDraft.setDiscount(3L);
        purchaseEntryEntityDraft.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setDueday(1);
        purchaseEntryEntityDraft.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setGrossAmounts(1L);
        purchaseEntryEntityDraft.setGstamount(10L);
        purchaseEntryEntityDraft.setId(123L);
        purchaseEntryEntityDraft.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setInvoiceNo("Invoice No");
        purchaseEntryEntityDraft.setNetAmount(1L);
        purchaseEntryEntityDraft.setNoItems(1000);
        purchaseEntryEntityDraft.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntityDraft.setRoundAmount(1L);
        purchaseEntryEntityDraft.setSrtMargin("Srt Margin");
        purchaseEntryEntityDraft.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntityDraft.setSupplierName("Supplier Name");
        when(purchaseEntryService.createOrUpdateDraft(purchaseEntryEntityDraft)).thenReturn(purchaseEntryEntityDraft);
        ResponseEntity<PurchaseEntryEntityDraft> response = purchaseEntryController.createDraft(purchaseEntryEntityDraft);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link PurchaseEntryController#exportReport10()}
     */
    @Test
    void testExportReport10() throws Exception {
        when(purchaseEntrySettingService.getAllPurchaseSetting()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseEntryController#exportReport10()}
     */
    @Test
    void testExportReport102() throws Exception {
        when(purchaseEntrySettingService.getAllPurchaseSetting())
                .thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/purchase-entry/getPurchaseEntryDetailsReport");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseEntryController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(purchaseEntryService.getAllAccounts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-entry/getAll");
        MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link PurchaseEntryController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(purchaseEntryService.getAllAccounts()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-entry/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseEntryController#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() throws Exception {
        when(purchaseEntrySettingService.getAllPurchaseSetting()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-entry/getSettingDetails");
        MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link PurchaseEntryController#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts2() throws Exception {
        when(purchaseEntrySettingService.getAllPurchaseSetting())
                .thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-entry/getSettingDetails");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseEntryController#getByInvoice(Long)}
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

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");
        when(purchaseEntryService.getPurchaseById((Long) any())).thenReturn(purchaseEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-entry/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<PurchaseEntryEntity><id>123</id><invoiceNo>Invoice No</invoiceNo><supplierName>Supplier Name</supplierName><invoiceDate>1970</invoiceDate><invoiceDate>1</invoiceDate><invoiceDate>2</invoiceDate><entrydate>1970</entrydate><entrydate>1</entrydate><entrydate>2</entrydate><discount>3</discount><dueday>1</dueday><duedate>1970</duedate><duedate>1</duedate><duedate>2</duedate><srtMargin>Srt Margin</srtMargin><grossAmounts>1</grossAmounts><discAmount>10.0</discAmount><noItems>1000</noItems><gstamount>10</gstamount><roundAmount>1</roundAmount><footerDate>1970</footerDate><footerDate>1</footerDate><footerDate>2</footerDate><disPercentage>1</disPercentage><otherExpenses/><netAmount>1</netAmount><supplierDetails><id>123</id><supplierName>Supplier Name</supplierName><mobileNo>Mobile No</mobileNo><address1>42 Main St</address1><address2>42 Main St</address2><gstNo>Gst No</gstNo><gstType>Gst Type</gstType><credit_days>1</credit_days><discount>3</discount><rateSlab>Rate Slab</rateSlab><modeOfPayment>Mode Of Payment</modeOfPayment><openingBal>1</openingBal><openingBalDate>1970</openingBalDate><openingBalDate>1</openingBalDate><openingBalDate>2</openingBalDate><created_by>Jan 1, 2020 8:00am GMT+0100</created_by><created_date>1970</created_date><created_date>1</created_date><created_date>2</created_date><updated_by>1</updated_by><updated_date>1970</updated_date><updated_date>1</updated_date><updated_date>2</updated_date><status>Status</status></supplierDetails><purchaseDetails/></PurchaseEntryEntity>"));
    }

    /**
     * Method under test: {@link PurchaseEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(purchaseEntryService.getPurchaseById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase-entry/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PurchaseEntryController#getByInvoiceNumber(String, Long)}
     */
    @Test
    void testGetByInvoiceNumber() throws Exception {
        when(purchaseEntryService.getPurchaseDetailByInvoiceAndSupplier((String) any(), (Long) any()))
                .thenReturn("Purchase Detail By Invoice And Supplier");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/purchase-entry/invoice/{invoice_num}/{supplier_id}", "Invoice num", 1L);
        MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Purchase Detail By Invoice And Supplier"));
    }

    /**
     * Method under test: {@link PurchaseEntryController#getByInvoiceNumber(String, Long)}
     */
    @Test
    void testGetByInvoiceNumber2() throws Exception {
        when(purchaseEntryService.getPurchaseDetailByInvoiceAndSupplier((String) any(), (Long) any()))
                .thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/purchase-entry/invoice/{invoice_num}/{supplier_id}", "Invoice num", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PurchaseEntryController#getPurchaseEntrySettingWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPurchaseEntrySettingWithPagination() throws Exception {
        when(purchaseEntryService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<PurchaseEntryEntity>> response = purchaseEntryController.getPurchaseEntrySettingWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link PurchaseEntryController#getPurchaseEntrySettingWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPurchaseEntrySettingWithPagination2() throws Exception {
        when(purchaseEntryService.getAllPurchaseOrderWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<PurchaseEntryEntity>> response = purchaseEntryController.getPurchaseEntrySettingWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link PurchaseEntryController#getPurchaseReport(PurchaseReportFilterDTO, Pageable)}
     */
    @Test
    void testGetPurchaseReport() throws Exception {
        when(purchaseEntryService.getPurchaseReport(Mockito.any(PurchaseReportFilterDTO.class), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        ResponseEntity<Page<PurchaseReportDto>>  response = purchaseEntryController.getPurchaseReport(Mockito.any(PurchaseReportFilterDTO.class), Mockito.any(Pageable.class));
        Assertions.assertNotNull(response);
    }

    /**
     * Method under test: {@link PurchaseEntryController#PackCreate(PurchaseEntryEntity)}
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

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");

        PurchaseEntryEntity purchaseEntryEntity1 = new PurchaseEntryEntity();
        purchaseEntryEntity1.setDisPercentage(1L);
        purchaseEntryEntity1.setDiscAmount(10.0d);
        purchaseEntryEntity1.setDiscount(3L);
        purchaseEntryEntity1.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setDueday(1);
        purchaseEntryEntity1.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setGrossAmounts(1L);
        purchaseEntryEntity1.setGstamount(10L);
        purchaseEntryEntity1.setId(123L);
        purchaseEntryEntity1.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setInvoiceNo("Invoice No");
        purchaseEntryEntity1.setNetAmount(1L);
        purchaseEntryEntity1.setNoItems(1000);
        purchaseEntryEntity1.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity1.setRoundAmount(1L);
        purchaseEntryEntity1.setSrtMargin("Srt Margin");
        purchaseEntryEntity1.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity1.setSupplierName("Supplier Name");
        when(purchaseEntryService.createOrUpdateAccounts(purchaseEntryEntity)).thenReturn(purchaseEntryEntity1);
        ResponseEntity<PurchaseEntryEntity> response = purchaseEntryController.PackCreate(purchaseEntryEntity);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link PurchaseEntryController#updateAccount(PurchaseEntrySettingEntity, long)}
     */
    @Test
    void testUpdateAccount() throws Exception {
        PurchaseEntrySettingEntity purchaseEntrySettingEntity = new PurchaseEntrySettingEntity();
        purchaseEntrySettingEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        purchaseEntrySettingEntity.setId(123L);
        purchaseEntrySettingEntity.setSettingName("Setting Name");
        purchaseEntrySettingEntity.setStatus("Status");
        purchaseEntrySettingEntity.setUpdated_by(1);
        purchaseEntrySettingEntity.setUpdated_date(LocalDate.ofEpochDay(1L));
        when(purchaseEntrySettingService.createOrUpdateSetttings(purchaseEntrySettingEntity)).thenReturn(purchaseEntrySettingEntity);
        ResponseEntity<PurchaseEntrySettingEntity> response = purchaseEntryController.updateAccount(purchaseEntrySettingEntity, 123L);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(purchaseEntrySettingEntity));
    }

    /**
     * Method under test: {@link PurchaseEntryController#updatePurchase(PurchaseEntryEntity, long)}
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

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");
        when(purchaseEntryService.createOrUpdateAccounts(purchaseEntryEntity)).thenReturn(purchaseEntryEntity);
        ResponseEntity<PurchaseEntryEntity> response = purchaseEntryController.updatePurchase(purchaseEntryEntity, 123L);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(purchaseEntryEntity));
    }
}

