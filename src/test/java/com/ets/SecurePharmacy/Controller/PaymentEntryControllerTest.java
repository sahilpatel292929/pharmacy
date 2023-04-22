package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.PaymentEntryService;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.PaymentEntryEntity;
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

@ContextConfiguration(classes = {PaymentEntryController.class})
@ExtendWith(SpringExtension.class)
class PaymentEntryControllerTest {
    @Autowired
    private PaymentEntryController paymentEntryController;

    @MockBean
    private PaymentEntryService paymentEntryService;

    /**
     * Method under test: {@link PaymentEntryController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(paymentEntryService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment-entry/getAll");
        MockMvcBuilders.standaloneSetup(paymentEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link PaymentEntryController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(paymentEntryService.getAll()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment-entry/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PaymentEntryController#exportReport10()}
     */
    @Test
    void testExportReport10() throws Exception {
        when(paymentEntryService.getAll()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PaymentEntryController#exportReport10()}
     */
    @Test
    void testExportReport102() throws Exception {
        when(paymentEntryService.getAll()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/payment-entry/getPaymentEntryDetailsReport");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PaymentEntryController#getByInvoice(Long)}
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

        PaymentEntryEntity paymentEntryEntity = new PaymentEntryEntity();
        paymentEntryEntity.setAccountEntity(accountCreationEntity);
        paymentEntryEntity.setId(123L);
        paymentEntryEntity.setNetAmount(10.0d);
        paymentEntryEntity.setPaymentDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setPaymentEntryDetails(new ArrayList<>());
        paymentEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setRefernceNumber("42");
        paymentEntryEntity.setRemarks("Remarks");
        paymentEntryEntity.setStatus("Status");
        when(paymentEntryService.getById((Long) any())).thenReturn(paymentEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment-entry/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(paymentEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<PaymentEntryEntity><id>123</id><paymentDate>1970</paymentDate><paymentDate>1</paymentDate><paymentDate"
                                        + ">2</paymentDate><refernceNumber>42</refernceNumber><refernceDate>1970</refernceDate><refernceDate>1<"
                                        + "/refernceDate><refernceDate>2</refernceDate><status>Status</status><remarks>Remarks</remarks><netAmount"
                                        + ">10.0</netAmount><accountEntity><id>123</id><actName>Act Name</actName><actType>Act Type</actType>"
                                        + "<actGroup>Act Group</actGroup><openingBal>Opening Bal</openingBal><openingBalDate>1970</openingBalDate"
                                        + "><openingBalDate>1</openingBalDate><openingBalDate>2</openingBalDate><created_by>1</created_by><status"
                                        + ">Status</status><created_date>1970</created_date><created_date>1</created_date><created_date>2</created"
                                        + "_date><updated_by>1</updated_by><updated_date>1970</updated_date><updated_date>1</updated_date><updated"
                                        + "_date>2</updated_date></accountEntity><paymentEntryDetails/></PaymentEntryEntity>"));
    }

    /**
     * Method under test: {@link PaymentEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(paymentEntryService.getById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment-entry/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link PaymentEntryController#getPaymentEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPaymentEntryWithPagination() throws Exception {
        when(paymentEntryService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<PaymentEntryEntity>> response = paymentEntryController.getPaymentEntryWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link PaymentEntryController#getPaymentEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPaymentEntryWithPagination2() throws Exception {
        when(paymentEntryService.getAllPaymentEntryWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<PaymentEntryEntity>> response = paymentEntryController.getPaymentEntryWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link PaymentEntryController#PackCreate(PaymentEntryEntity)}
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

        PaymentEntryEntity paymentEntryEntity = new PaymentEntryEntity();
        paymentEntryEntity.setAccountEntity(accountCreationEntity);
        paymentEntryEntity.setNetAmount(10.0d);
        paymentEntryEntity.setPaymentDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setPaymentEntryDetails(new ArrayList<>());
        paymentEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setRefernceNumber("42");
        paymentEntryEntity.setRemarks("Remarks");
        paymentEntryEntity.setStatus("Status");

        PaymentEntryEntity paymentEntryEntity1 = new PaymentEntryEntity();
        paymentEntryEntity.setAccountEntity(accountCreationEntity);
        paymentEntryEntity.setId(123L);
        paymentEntryEntity.setNetAmount(10.0d);
        paymentEntryEntity.setPaymentDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setPaymentEntryDetails(new ArrayList<>());
        paymentEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setRefernceNumber("42");
        paymentEntryEntity.setRemarks("Remarks");
        paymentEntryEntity.setStatus("Status");
        when(paymentEntryService.createOrUpdate(paymentEntryEntity)).thenReturn(paymentEntryEntity1);
        ResponseEntity<PaymentEntryEntity> response = paymentEntryController.PackCreate(paymentEntryEntity);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link PaymentEntryController#updatePurchase(PaymentEntryEntity, long)}
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

        PaymentEntryEntity paymentEntryEntity = new PaymentEntryEntity();
        paymentEntryEntity.setAccountEntity(accountCreationEntity);
        paymentEntryEntity.setId(123L);
        paymentEntryEntity.setNetAmount(10.0d);
        paymentEntryEntity.setPaymentDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setPaymentEntryDetails(new ArrayList<>());
        paymentEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        paymentEntryEntity.setRefernceNumber("42");
        paymentEntryEntity.setRemarks("Remarks");
        paymentEntryEntity.setStatus("Status");
        when(paymentEntryService.createOrUpdate(paymentEntryEntity)).thenReturn(paymentEntryEntity);
        ResponseEntity<PaymentEntryEntity> response = paymentEntryController.updatePurchase(paymentEntryEntity, 123L);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(paymentEntryEntity));
    }
}

