package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.CustomerReceiptEntryService;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerReceiptEntryEntity;
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

@ContextConfiguration(classes = {CustomerReceiptEntryController.class})
@ExtendWith(SpringExtension.class)
class CustomerReceiptEntryControllerTest {
    @Autowired
    private CustomerReceiptEntryController customerReceiptEntryController;

    @MockBean
    private CustomerReceiptEntryService customerReceiptEntryService;

    /**
     * Method under test: {@link CustomerReceiptEntryController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(customerReceiptEntryService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer-receipt/getAll");
        MockMvcBuilders.standaloneSetup(customerReceiptEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link CustomerReceiptEntryController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(customerReceiptEntryService.getAll()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer-receipt/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerReceiptEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryController#getByInvoice(Long)}
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

        CustomerReceiptEntryEntity customerReceiptEntryEntity = new CustomerReceiptEntryEntity();
        customerReceiptEntryEntity.setAccountEntity(accountCreationEntity);
        customerReceiptEntryEntity.setCustomerReceiptDetailsEntity(new ArrayList<>());
        customerReceiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setId(123L);
        customerReceiptEntryEntity.setReferenceNumber("42");
        customerReceiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setRemarks("Remarks");
        customerReceiptEntryEntity.setStatus("Status");
        customerReceiptEntryEntity.setTotalAmount(10L);
        when(customerReceiptEntryService.getById((Long) any())).thenReturn(customerReceiptEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer-receipt/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(customerReceiptEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<CustomerReceiptEntryEntity><id>123</id><referenceNumber>42</referenceNumber><entryDate>1970</entryDate><entryDate>1</entryDate><entryDate>2</entryDate><accountEntity><id>123</id><actName>Act Name</actName><actType>Act Type</actType><actGroup>Act Group</actGroup><openingBal>Opening Bal</openingBal><openingBalDate>1970</openingBalDate><openingBalDate>1</openingBalDate><openingBalDate>2</openingBalDate><created_by>1</created_by><status>Status</status><created_date>1970</created_date><created_date>1</created_date><created_date>2</created_date><updated_by>1</updated_by><updated_date>1970</updated_date><updated_date>1</updated_date><updated_date>2</updated_date></accountEntity><refernceDate>1970</refernceDate><refernceDate>1</refernceDate><refernceDate>2</refernceDate><remarks>Remarks</remarks><totalAmount>10</totalAmount><status>Status</status><customerReceiptDetailsEntity/></CustomerReceiptEntryEntity>"));
    }

    /**
     * Method under test: {@link CustomerReceiptEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(customerReceiptEntryService.getById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer-receipt/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerReceiptEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryController#getReceiptEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetReceiptEntryWithPagination() throws Exception {
        when(customerReceiptEntryService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CustomerReceiptEntryEntity>> response = customerReceiptEntryController.getReceiptEntryWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryController#getReceiptEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetReceiptEntryWithPagination2() throws Exception {
        when(customerReceiptEntryService.getAllReceiptEntryWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<CustomerReceiptEntryEntity>> response = customerReceiptEntryController.getReceiptEntryWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link CustomerReceiptEntryController#PackCreate(CustomerReceiptEntryEntity)}
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

        CustomerReceiptEntryEntity customerReceiptEntryEntity = new CustomerReceiptEntryEntity();
        customerReceiptEntryEntity.setAccountEntity(accountCreationEntity);
        customerReceiptEntryEntity.setCustomerReceiptDetailsEntity(new ArrayList<>());
        customerReceiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setReferenceNumber("42");
        customerReceiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setRemarks("Remarks");
        customerReceiptEntryEntity.setStatus("Status");
        customerReceiptEntryEntity.setTotalAmount(10L);


        when(customerReceiptEntryService.createOrUpdate(customerReceiptEntryEntity)).thenReturn(customerReceiptEntryEntity);
        ResponseEntity<CustomerReceiptEntryEntity> response = customerReceiptEntryController.PackCreate(customerReceiptEntryEntity);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link CustomerReceiptEntryController#updatePurchase(CustomerReceiptEntryEntity, long)}
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

        CustomerReceiptEntryEntity customerReceiptEntryEntity = new CustomerReceiptEntryEntity();
        customerReceiptEntryEntity.setAccountEntity(accountCreationEntity);
        customerReceiptEntryEntity.setCustomerReceiptDetailsEntity(new ArrayList<>());
        customerReceiptEntryEntity.setEntryDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setId(123L);
        customerReceiptEntryEntity.setReferenceNumber("42");
        customerReceiptEntryEntity.setRefernceDate(LocalDate.ofEpochDay(1L));
        customerReceiptEntryEntity.setRemarks("Remarks");
        customerReceiptEntryEntity.setStatus("Status");
        customerReceiptEntryEntity.setTotalAmount(10L);
        when(customerReceiptEntryService.createOrUpdate(customerReceiptEntryEntity)).thenReturn(customerReceiptEntryEntity);
        ResponseEntity<CustomerReceiptEntryEntity> response = customerReceiptEntryController.updatePurchase(customerReceiptEntryEntity, 123L);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(customerReceiptEntryEntity));
    }
}

