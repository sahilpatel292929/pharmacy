package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.ReceiptEntryService;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ReceiptEntryEntity;
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

@ContextConfiguration(classes = {ReceiptEntryController.class})
@ExtendWith(SpringExtension.class)
class ReceiptEntryControllerTest {
    @Autowired
    private ReceiptEntryController receiptEntryController;

    @MockBean
    private ReceiptEntryService receiptEntryService;

    /**
     * Method under test: {@link ReceiptEntryController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(receiptEntryService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/receipt-entry/getAll");
        MockMvcBuilders.standaloneSetup(receiptEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link ReceiptEntryController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(receiptEntryService.getAll()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/receipt-entry/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(receiptEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ReceiptEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        ReceiptEntryEntity receiptEntryEntity = new ReceiptEntryEntity();
        receiptEntryEntity.setAccountEntity(accountCreationEntity);
        receiptEntryEntity.setAmount(10.0d);
        receiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setId(123L);
        receiptEntryEntity.setReceiptDetailsEntity(new ArrayList<>());
        receiptEntryEntity.setReferenceNumber("42");
        receiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setRemarks("Remarks");
        receiptEntryEntity.setStatus("Status");
        receiptEntryEntity.setTotalAmount(10.0d);
        when(receiptEntryService.getById((Long) any())).thenReturn(receiptEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/receipt-entry/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(receiptEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<ReceiptEntryEntity><id>123</id><referenceNumber>42</referenceNumber><entryDate>1970</entryDate>"
                                + "<entryDate>1</entryDate><entryDate>2</entryDate><accountEntity><id>123</id><actName>Act Name</actName"
                                + "><actType>Act Type</actType><actGroup>Act Group</actGroup><openingBal>Opening Bal</openingBal>"
                                + "<openingBalDate>1970</openingBalDate><openingBalDate>1</openingBalDate><openingBalDate>2</openingBalDate"
                                + "><created_by>1</created_by><status>Status</status><created_date>1970</created_date><created_date>1<"
                                + "/created_date><created_date>2</created_date><updated_by>1</updated_by><updated_date>1970</updated_date"
                                + "><updated_date>1</updated_date><updated_date>2</updated_date></accountEntity><refernceDate>1970<"
                                + "/refernceDate><refernceDate>1</refernceDate><refernceDate>2</refernceDate><amount>10.0</amount><remarks"
                                + ">Remarks</remarks><totalAmount>10.0</totalAmount><status>Status</status><receiptDetailsEntity/><"
                                + "/ReceiptEntryEntity>"));
    }

    /**
     * Method under test: {@link ReceiptEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(receiptEntryService.getById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/receipt-entry/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(receiptEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ReceiptEntryController#getReceiptEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetReceiptEntryWithPagination() throws Exception {
        when(receiptEntryService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<ReceiptEntryEntity>> response = receiptEntryController.getReceiptEntryWithPagination(2, 3, "keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link ReceiptEntryController#getReceiptEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetReceiptEntryWithPagination2() throws Exception {
        when(receiptEntryService.getAllReceiptEntryWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<ReceiptEntryEntity>> response = receiptEntryController.getReceiptEntryWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link ReceiptEntryController#PackCreate(ReceiptEntryEntity)}
     */
    @Test
    void testPackCreate() throws Exception {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        ReceiptEntryEntity receiptEntryEntity = new ReceiptEntryEntity();
        receiptEntryEntity.setAccountEntity(accountCreationEntity);
        receiptEntryEntity.setAmount(10.0d);
        receiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setId(123L);
        receiptEntryEntity.setReceiptDetailsEntity(new ArrayList<>());
        receiptEntryEntity.setReferenceNumber("42");
        receiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setRemarks("Remarks");
        receiptEntryEntity.setStatus("Status");
        receiptEntryEntity.setTotalAmount(10.0d);
        when(receiptEntryService.createOrUpdate(receiptEntryEntity)).thenReturn(receiptEntryEntity);
        ResponseEntity<ReceiptEntryEntity> response = receiptEntryController.PackCreate(receiptEntryEntity);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link ReceiptEntryController#updatePurchase(ReceiptEntryEntity, long)}
     */
    @Test
    void testUpdatePurchase() throws Exception {
        AccountCreationEntity accountCreationEntity = new AccountCreationEntity();
        accountCreationEntity.setActGroup("Act Group");
        accountCreationEntity.setActName("Act Name");
        accountCreationEntity.setActType("Act Type");
        accountCreationEntity.setCreated_by(1);
        accountCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setId(123L);
        accountCreationEntity.setOpeningBal("Opening Bal");
        accountCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        accountCreationEntity.setStatus("Status");
        accountCreationEntity.setUpdated_by(1);
        accountCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        ReceiptEntryEntity receiptEntryEntity = new ReceiptEntryEntity();
        receiptEntryEntity.setAccountEntity(accountCreationEntity);
        receiptEntryEntity.setAmount(10.0d);
        receiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setId(123L);
        receiptEntryEntity.setReceiptDetailsEntity(new ArrayList<>());
        receiptEntryEntity.setReferenceNumber("42");
        receiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        receiptEntryEntity.setRemarks("Remarks");
        receiptEntryEntity.setStatus("Status");
        receiptEntryEntity.setTotalAmount(10.0d);
        when(receiptEntryService.createOrUpdate(receiptEntryEntity)).thenReturn(receiptEntryEntity);
        ResponseEntity<ReceiptEntryEntity> response = receiptEntryController.updatePurchase(receiptEntryEntity, 123L);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(receiptEntryEntity));
    }
}

