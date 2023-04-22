package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.OpenStockEntryService;
import com.ets.SecurePharmacy.tenant.model.OpenStockEntryEntity;
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

@ContextConfiguration(classes = {OpenStockEntryController.class})
@ExtendWith(SpringExtension.class)
class OpenStockEntryControllerTest {
    @Autowired
    private OpenStockEntryController openStockEntryController;

    @MockBean
    private OpenStockEntryService openStockEntryService;

    /**
     * Method under test: {@link OpenStockEntryController#exportReport10()}
     */
    @Test
    void testExportReport10() throws Exception {
        when(openStockEntryService.getAllOpenStock()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(openStockEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link OpenStockEntryController#exportReport10()}
     */
    @Test
    void testExportReport102() throws Exception {
        when(openStockEntryService.getAllOpenStock()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/opening-stock/getOpenStockEntryDetailsReport");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(openStockEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link OpenStockEntryController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts() throws Exception {
        when(openStockEntryService.getAllOpenStock()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/opening-stock/getAll");
        MockMvcBuilders.standaloneSetup(openStockEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link OpenStockEntryController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts2() throws Exception {
        when(openStockEntryService.getAllOpenStock()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/opening-stock/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(openStockEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link OpenStockEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        OpenStockEntryEntity openStockEntryEntity = new OpenStockEntryEntity();
        openStockEntryEntity.setDelStatus("Del Status");
        openStockEntryEntity.setId(123L);
        openStockEntryEntity.setMrpAmount(10.0d);
        openStockEntryEntity.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity.setPurchaseVal(10.0d);
        openStockEntryEntity.setSalesVal(10.0d);
        openStockEntryEntity.setSrt(10.0d);
        openStockEntryEntity.setStatus("Status");
        openStockEntryEntity.setTotalItems(1000);
        when(openStockEntryService.getOpenStockeById((Long) any())).thenReturn(openStockEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/opening-stock/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(openStockEntryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<OpenStockEntryEntity><id>123</id><orderDate>1970</orderDate><orderDate>1</orderDate><orderDate>2<"
                                        + "/orderDate><srt>10.0</srt><totalItems>1000</totalItems><mrpAmount>10.0</mrpAmount><purchaseVal>10.0<"
                                        + "/purchaseVal><salesVal>10.0</salesVal><delStatus>Del Status</delStatus><status>Status</status>"
                                        + "<openStockDetails/></OpenStockEntryEntity>"));
    }

    /**
     * Method under test: {@link OpenStockEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(openStockEntryService.getOpenStockeById((Long) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/opening-stock/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(openStockEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link OpenStockEntryController#getOpenStockEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetOpenStockEntryWithPagination() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders
                .get("/opening-stock/getOpenStockEntryDetails/pagination")
                .param("keyword", "foo")
                .param("offset", "https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(openStockEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link OpenStockEntryController#openStockCreate(OpenStockEntryEntity)}
     */
    @Test
    void testOpenStockCreate() throws Exception {
        OpenStockEntryEntity openStockEntryEntity = new OpenStockEntryEntity();
        openStockEntryEntity.setDelStatus("Del Status");
        openStockEntryEntity.setId(123L);
        openStockEntryEntity.setMrpAmount(10.0d);
        openStockEntryEntity.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity.setPurchaseVal(10.0d);
        openStockEntryEntity.setSalesVal(10.0d);
        openStockEntryEntity.setSrt(10.0d);
        openStockEntryEntity.setStatus("Status");
        openStockEntryEntity.setTotalItems(1000);
        String content = (new ObjectMapper()).writeValueAsString(openStockEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/opening-stock/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(openStockEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link OpenStockEntryController#updateOpenStock(OpenStockEntryEntity)}
     */
    @Test
    void testUpdateOpenStock() throws Exception {
        OpenStockEntryEntity openStockEntryEntity = new OpenStockEntryEntity();
        openStockEntryEntity.setDelStatus("Del Status");
        openStockEntryEntity.setId(123L);
        openStockEntryEntity.setMrpAmount(10.0d);
        openStockEntryEntity.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity.setPurchaseVal(10.0d);
        openStockEntryEntity.setSalesVal(10.0d);
        openStockEntryEntity.setSrt(10.0d);
        openStockEntryEntity.setStatus("Status");
        openStockEntryEntity.setTotalItems(1000);
        String content = (new ObjectMapper()).writeValueAsString(openStockEntryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/opening-stock/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(openStockEntryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link OpenStockEntryController#getOpenStockEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetAllOpenStockEntryWithPagination() {
        when(openStockEntryService.getAllOpenStockEntryWithPagination(Mockito.anyInt(), Mockito.anyInt()))
            .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<OpenStockEntryEntity>> response = openStockEntryController.getOpenStockEntryWithPagination(2, 3, "", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link OpenStockEntryController#getOpenStockEntryWithPagination(int, int, String, Pageable)}
     */
    @Test
    void testGetAllOpenStockEntryWithPagination2() {
        when(openStockEntryService.getSearch(Mockito.anyString(), Mockito.any(Pageable.class)))
            .thenReturn(new PageImpl<>(Collections.emptyList()));
        APIResponse<Page<OpenStockEntryEntity>> response = openStockEntryController.getOpenStockEntryWithPagination(2, 3, "fdg", QPageRequest.of(1, 3));
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(response.getResponse().getContent().isEmpty());
    }

    /**
     * Method under test: {@link OpenStockEntryController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice3() throws Exception {
        when(openStockEntryService.getOpenStockeById(Mockito.anyLong()))
            .thenReturn(null);
        Assertions.assertThrows(UserNotFoundException.class, () -> openStockEntryController.getByInvoice(2L));
    }

    @Test
    void testOpenStockCreate2() {
        when(openStockEntryService.createOrUpdateOpenStock(Mockito.any(OpenStockEntryEntity.class)))
            .thenReturn(Mockito.mock(OpenStockEntryEntity.class));
        ResponseEntity<OpenStockEntryEntity>  result =  openStockEntryController.openStockCreate(Mockito.mock(OpenStockEntryEntity.class));
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdateOpenStock2() {
        when(openStockEntryService.createOrUpdateOpenStock(Mockito.any(OpenStockEntryEntity.class)))
            .thenReturn(Mockito.mock(OpenStockEntryEntity.class));
        ResponseEntity<OpenStockEntryEntity>  result =  openStockEntryController.updateOpenStock(Mockito.mock(OpenStockEntryEntity.class));
        Assertions.assertNotNull(result);
    }
}

