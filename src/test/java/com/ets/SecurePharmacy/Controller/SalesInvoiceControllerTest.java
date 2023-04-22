package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.DTO.SalesInvoiceDTO;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.SalesInvoiceService;
import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntity;
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

@ContextConfiguration(classes = {SalesInvoiceController.class})
@ExtendWith(SpringExtension.class)
class SalesInvoiceControllerTest {
    @Autowired
    private SalesInvoiceController salesInvoiceController;

    @MockBean
    private SalesInvoiceService salesInvoiceService;

    /**
     * Method under test: {@link SalesInvoiceController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts() throws Exception {
        when(salesInvoiceService.getAlStockInvoice()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/SalesInvoice/getAll");
        MockMvcBuilders.standaloneSetup(salesInvoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link SalesInvoiceController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts2() throws Exception {
        when(salesInvoiceService.getAlStockInvoice()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/SalesInvoice/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(salesInvoiceController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SalesInvoiceController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
        salesInvoiceEntity.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceEntity.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setCustomerAddress("42 Main St");
        salesInvoiceEntity.setCustomerName("Customer Name");
        salesInvoiceEntity.setDiscountAmt(10.00);
        salesInvoiceEntity.setDiscount(10.00);
        salesInvoiceEntity.setDoctor_name("Doctor");
        salesInvoiceEntity.setDoctorAddress("42 Main St");
        salesInvoiceEntity.setExpenses(10.00);
        salesInvoiceEntity.setGrossAmount(10.999);
        salesInvoiceEntity.setGstAmt(10.999);
        salesInvoiceEntity.setHomeDelivery("Home Delivery");
        salesInvoiceEntity.setId(123l);
        salesInvoiceEntity.setNetAmt(10.99);
        salesInvoiceEntity.setOtherExpenses("Other Expenses");
        salesInvoiceEntity.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setRemainderDays(1);
        salesInvoiceEntity.setRoundOffAmt(10.9999);
        salesInvoiceEntity.setMargin(100.000);
        salesInvoiceEntity.setMarginOn("margin on");
        salesInvoiceEntity.setStatus("Status");
        salesInvoiceEntity.setStockinvoiceDetails(new ArrayList<>());
        when(salesInvoiceService.getStockInvoiceById((Long) any())).thenReturn(salesInvoiceEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/SalesInvoice/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(salesInvoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<SalesInvoiceEntity><id>123</id><customerName>Customer Name</customerName><customerAddress>42 Main St</customerAddress><doctor_name>Doctor</doctor_name><doctorAddress>42 Main St</doctorAddress><discount>10.0</discount><remainderDate>1970</remainderDate><remainderDate>1</remainderDate><remainderDate>2</remainderDate><homeDelivery>Home Delivery</homeDelivery><billdate>1970</billdate><billdate>1</billdate><billdate>2</billdate><discountAmt>10.0</discountAmt><margin>100.0</margin><marginOn>margin on</marginOn><grossAmount>10.999</grossAmount><gstAmt>10.999</gstAmt><roundOffAmt>10.9999</roundOffAmt><netAmt>10.99</netAmt><otherExpenses>Other Expenses</otherExpenses><expenses>10.0</expenses><status>Status</status><paymentMode/><paymentStatus/><customerCreationEntity><customerName>Customer Name</customerName><mobileNo>Mobile No</mobileNo><address1>42 Main St</address1><address2>42 Main St</address2><gstType>Gst Type</gstType><gstNo>Gst No</gstNo><discount>3</discount><rateSlab>1</rateSlab><modeOfPayment>Mode Of Payment</modeOfPayment><creditLimit>1</creditLimit><dueDays>1</dueDays><openingBal>1</openingBal><openingBalDate>1970</openingBalDate><openingBalDate>1</openingBalDate><openingBalDate>2</openingBalDate><created_by>Jan 1, 2020 8:00am GMT+0100</created_by><created_date>1970</created_date><created_date>1</created_date><created_date>2</created_date><updated_by>2020-03-01</updated_by><updated_date>1970</updated_date><updated_date>1</updated_date><updated_date>2</updated_date><status>Status</status><id>123</id></customerCreationEntity><stockinvoiceDetails/><remainderDays>1</remainderDays></SalesInvoiceEntity>"));
    }

    /**
     * Method under test: {@link SalesInvoiceController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(salesInvoiceService.getStockInvoiceById((Long) any()))
                .thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/SalesInvoice/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(salesInvoiceController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SalesInvoiceController#getSalesInvoiceWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSalesInvoiceWithPagination() throws Exception {
        when(salesInvoiceService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SalesInvoiceEntity>> response = salesInvoiceController.getSalesInvoiceWithPagination(2, 3, "Keyword", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link SalesInvoiceController#getSalesInvoiceWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetSalesInvoiceWithPagination2() throws Exception {
        when(salesInvoiceService.getAllSalesInvoiceWithPagination(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<SalesInvoiceEntity>> response = salesInvoiceController.getSalesInvoiceWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link SalesInvoiceController#openStockCreate(SalesInvoiceDTO)}
     */
    @Test
    void testOpenStockCreate() throws Exception {
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SalesInvoiceDTO salesInvoiceDTO = new SalesInvoiceDTO();
        salesInvoiceDTO.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceDTO.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceDTO.setCustomerAddress("42 Main St");
        salesInvoiceDTO.setCustomerName("Customer Name");
        salesInvoiceDTO.setDiscountAmt(10.000);
        salesInvoiceDTO.setDiscount(10.888);
        salesInvoiceDTO.setDoctor_name("Doctor");
        salesInvoiceDTO.setDoctorAddress("42 Main St");
        salesInvoiceDTO.setExpenses(10.9990);
        salesInvoiceDTO.setGrossAmount(10.900);
        salesInvoiceDTO.setGstAmt(10.99);
        salesInvoiceDTO.setHomeDelivery("Home Delivery");
        salesInvoiceDTO.setId(123L);
        salesInvoiceDTO.setNetAmt(10.999);
        salesInvoiceDTO.setOtherExpenses("Other Expenses");
        salesInvoiceDTO.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceDTO.setRemainderDays(1);
        salesInvoiceDTO.setRoundOffAmt(10.999);
        salesInvoiceDTO.setMargin(10.8899);
        salesInvoiceDTO.setMarginOn("margin on");
        salesInvoiceDTO.setStatus("Status");
        salesInvoiceDTO.setStockinvoiceDetails(new ArrayList<>());


        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
        salesInvoiceEntity.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceEntity.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setCustomerAddress("42 Main St");
        salesInvoiceEntity.setCustomerName("Customer Name");
        salesInvoiceEntity.setDiscountAmt(10.000);
        salesInvoiceEntity.setDiscount(10.888);
        salesInvoiceEntity.setDoctor_name("Doctor");
        salesInvoiceEntity.setDoctorAddress("42 Main St");
        salesInvoiceEntity.setExpenses(10.999);
        salesInvoiceEntity.setGrossAmount(10.999);
        salesInvoiceEntity.setGstAmt(10.788);
        salesInvoiceEntity.setHomeDelivery("Home Delivery");
        salesInvoiceEntity.setId(123l);
        salesInvoiceEntity.setNetAmt(10.8899);
        salesInvoiceEntity.setOtherExpenses("Other Expenses");
        salesInvoiceEntity.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setRemainderDays(1);
        salesInvoiceEntity.setRoundOffAmt(10.888);
        salesInvoiceEntity.setMargin(10.8888);
        salesInvoiceEntity.setMarginOn("margin on");
        salesInvoiceEntity.setStatus("Status");
        salesInvoiceEntity.setStockinvoiceDetails(new ArrayList<>());

        when(salesInvoiceService.createOrUpdateStockInvoice(salesInvoiceDTO)).thenReturn(salesInvoiceDTO);
        ResponseEntity<SalesInvoiceDTO> response = salesInvoiceController.openStockCreate(salesInvoiceDTO);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    /**
     * Method under test: {@link SalesInvoiceController#updateOpenStock(SalesInvoiceDTO)}
     */
    @Test
    void testUpdateOpenStock() throws Exception {
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
        salesInvoiceEntity.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceEntity.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setCustomerAddress("42 Main St");
        salesInvoiceEntity.setCustomerName("Customer Name");
        salesInvoiceEntity.setDiscountAmt(10.899);
        salesInvoiceEntity.setDiscount(10.999);
        salesInvoiceEntity.setDoctor_name("Doctor");
        salesInvoiceEntity.setDoctorAddress("42 Main St");
        salesInvoiceEntity.setExpenses(10.999);
        salesInvoiceEntity.setGrossAmount(10.8883);
        salesInvoiceEntity.setGstAmt(10.999);
        salesInvoiceEntity.setHomeDelivery("Home Delivery");
        salesInvoiceEntity.setId(123l);
        salesInvoiceEntity.setNetAmt(10.9999);
        salesInvoiceEntity.setOtherExpenses("Other Expenses");
        salesInvoiceEntity.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setRemainderDays(1);
        salesInvoiceEntity.setRoundOffAmt(10.9999);
        salesInvoiceEntity.setMargin(10.8999);
        salesInvoiceEntity.setMarginOn("margin on");
        salesInvoiceEntity.setStatus("Status");
        salesInvoiceEntity.setStockinvoiceDetails(new ArrayList<>());

        SalesInvoiceDTO salesInvoiceDTO = new SalesInvoiceDTO();
        salesInvoiceDTO.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceDTO.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceDTO.setCustomerAddress("42 Main St");
        salesInvoiceDTO.setCustomerName("Customer Name");
        salesInvoiceDTO.setDiscountAmt(10.900);
        salesInvoiceDTO.setDiscount(10.899);
        salesInvoiceDTO.setDoctor_name("Doctor");
        salesInvoiceDTO.setDoctorAddress("42 Main St");
        salesInvoiceDTO.setExpenses(10.899);
        salesInvoiceDTO.setGrossAmount(10.374);
        salesInvoiceDTO.setGstAmt(10.758);
        salesInvoiceDTO.setHomeDelivery("Home Delivery");
        salesInvoiceDTO.setId(123L);
        salesInvoiceDTO.setNetAmt(1.900);
        salesInvoiceDTO.setOtherExpenses("Other Expenses");
        salesInvoiceDTO.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceDTO.setRemainderDays(1);
        salesInvoiceDTO.setRoundOffAmt(10.888);
        salesInvoiceDTO.setMargin(10.899);
        salesInvoiceDTO.setMarginOn("margin on");
        salesInvoiceDTO.setStatus("Status");
        salesInvoiceDTO.setStockinvoiceDetails(new ArrayList<>());

        when(salesInvoiceService.createOrUpdateStockInvoice(salesInvoiceDTO)).thenReturn(salesInvoiceDTO);
        ResponseEntity<SalesInvoiceDTO> response = salesInvoiceController.updateOpenStock(salesInvoiceDTO);
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue(response.getBody().equals(salesInvoiceDTO));
    }
}

