package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.SupplierPaymentEntryService;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierPaymentEntry;
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

@ContextConfiguration(classes = {SupplierPaymentEntryController.class})
@ExtendWith(SpringExtension.class)
class SupplierPaymentEntryControllerTest {
    @Autowired
    private SupplierPaymentEntryController supplierPaymentEntryController;

    @MockBean
    private SupplierPaymentEntryService supplierPaymentEntryService;

    /**
     * Method under test: {@link SupplierPaymentEntryController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(supplierPaymentEntryService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/supplier-payment-entry/getAll");
        MockMvcBuilders.standaloneSetup(supplierPaymentEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link SupplierPaymentEntryController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(supplierPaymentEntryService.getAll()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/supplier-payment-entry/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(supplierPaymentEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryController#getByInvoice(Long)}
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

        SupplierPaymentEntry supplierPaymentEntry = new SupplierPaymentEntry();
        supplierPaymentEntry.setAccountEntity(accountCreationEntity);
        supplierPaymentEntry.setId(123L);
        supplierPaymentEntry.setNetAmount(10.0d);
        supplierPaymentEntry.setPaymentDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceNumber("42");
        supplierPaymentEntry.setRemarks("Remarks");
        supplierPaymentEntry.setStatus("Status");
        supplierPaymentEntry.setSupplierPaymentEntryDetails(new ArrayList<>());
        when(supplierPaymentEntryService.getById((Long) any())).thenReturn(supplierPaymentEntry);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/supplier-payment-entry/get/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(supplierPaymentEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<SupplierPaymentEntry><id>123</id><paymentDate>1970</paymentDate><paymentDate>1</paymentDate><paymentDate"
                                        + ">2</paymentDate><refernceNumber>42</refernceNumber><refernceDate>1970</refernceDate><refernceDate>1"
                                        + "</refernceDate><refernceDate>2</refernceDate><status>Status</status><remarks>Remarks</remarks><netAmount"
                                        + ">10.0</netAmount><accountEntity><id>123</id><actName>Act Name</actName><actType>Act Type</actType>"
                                        + "<actGroup>Act Group</actGroup><openingBal>Opening Bal</openingBal><openingBalDate>1970</openingBalDate"
                                        + "><openingBalDate>1</openingBalDate><openingBalDate>2</openingBalDate><created_by>1</created_by><status"
                                        + ">Status</status><created_date>1970</created_date><created_date>1</created_date><created_date>2</created"
                                        + "_date><updated_by>1</updated_by><updated_date>1970</updated_date><updated_date>1</updated_date><updated"
                                        + "_date>2</updated_date></accountEntity><supplierPaymentEntryDetails/></SupplierPaymentEntry>"));
    }

    /**
     * Method under test: {@link SupplierPaymentEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(supplierPaymentEntryService.getById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/supplier-payment-entry/get/{id}",
                123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(supplierPaymentEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryController#getPaymentEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPaymentEntryWithPagination() throws Exception {
        when(supplierPaymentEntryService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SupplierPaymentEntry>> response = supplierPaymentEntryController.getPaymentEntryWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryController#getPaymentEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetPaymentEntryWithPagination2() throws Exception {
        when(supplierPaymentEntryService.getAllPaymentEntryWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SupplierPaymentEntry>> response = supplierPaymentEntryController.getPaymentEntryWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link SupplierPaymentEntryController#PackCreate(SupplierPaymentEntry)}
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

        SupplierPaymentEntry supplierPaymentEntry = new SupplierPaymentEntry();
        supplierPaymentEntry.setAccountEntity(accountCreationEntity);
        supplierPaymentEntry.setId(123L);
        supplierPaymentEntry.setNetAmount(10.0d);
        supplierPaymentEntry.setPaymentDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceNumber("42");
        supplierPaymentEntry.setRemarks("Remarks");
        supplierPaymentEntry.setStatus("Status");
        supplierPaymentEntry.setSupplierPaymentEntryDetails(new ArrayList<>());
        when(supplierPaymentEntryService.createOrUpdate(supplierPaymentEntry)).thenReturn(supplierPaymentEntry);
        ResponseEntity<SupplierPaymentEntry> response = supplierPaymentEntryController.PackCreate(supplierPaymentEntry);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link SupplierPaymentEntryController#updatePurchase(SupplierPaymentEntry, long)}
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

        SupplierPaymentEntry supplierPaymentEntry = new SupplierPaymentEntry();
        supplierPaymentEntry.setAccountEntity(accountCreationEntity);
        supplierPaymentEntry.setId(123L);
        supplierPaymentEntry.setNetAmount(10.0d);
        supplierPaymentEntry.setPaymentDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceDate(LocalDate.ofEpochDay(1L));
        supplierPaymentEntry.setRefernceNumber("42");
        supplierPaymentEntry.setRemarks("Remarks");
        supplierPaymentEntry.setStatus("Status");
        supplierPaymentEntry.setSupplierPaymentEntryDetails(new ArrayList<>());
        when(supplierPaymentEntryService.createOrUpdate(supplierPaymentEntry)).thenReturn(supplierPaymentEntry);
        ResponseEntity<SupplierPaymentEntry> response = supplierPaymentEntryController.updatePurchase(supplierPaymentEntry, 123L);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(supplierPaymentEntry));
    }
}

