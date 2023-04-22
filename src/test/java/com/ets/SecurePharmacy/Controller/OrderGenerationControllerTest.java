package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.OrderGenerationEntryService;
import com.ets.SecurePharmacy.tenant.model.OrderGenerationEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderGenerationController.class})
@ExtendWith(SpringExtension.class)
class OrderGenerationControllerTest {
    @Autowired
    private OrderGenerationController orderGenerationController;

    @MockBean
    private OrderGenerationEntryService orderGenerationEntryService;

    /**
     * Method under test: {@link OrderGenerationController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(orderGenerationEntryService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order-generation/getAll");
        MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link OrderGenerationController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(orderGenerationEntryService.getAll()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order-generation/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link OrderGenerationController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        when(orderGenerationEntryService.getById((Long) any())).thenReturn(orderGenerationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order-generation/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<OrderGenerationEntity><id>123</id><orderDate>1970</orderDate><orderDate>1</orderDate><orderDate>2<"
                                        + "/orderDate><totalCount>3</totalCount><orderQty>1</orderQty><status>Status</status><orderDetails/><"
                                        + "/OrderGenerationEntity>"));
    }

    /**
     * Method under test: {@link OrderGenerationController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(orderGenerationEntryService.getById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order-generation/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link OrderGenerationController#PackCreate(OrderGenerationEntity)}
     */
    @Test
    void testPackCreate() throws Exception {
        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        String content = (new ObjectMapper()).writeValueAsString(orderGenerationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order-generation/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link OrderGenerationController#PackCreate(OrderGenerationEntity)}
     */
    @Test
    void testPackCreate2() throws Exception {
        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        when(orderGenerationEntryService.createOrUpdate((OrderGenerationEntity) any())).thenReturn(orderGenerationEntity);

        OrderGenerationEntity orderGenerationEntity1 = new OrderGenerationEntity();
        orderGenerationEntity1.setId(123L);
        orderGenerationEntity1.setOrderDate(null);
        orderGenerationEntity1.setOrderDetails(new ArrayList<>());
        orderGenerationEntity1.setOrderQty(1L);
        orderGenerationEntity1.setStatus("Status");
        orderGenerationEntity1.setTotalCount(3L);
        String content = (new ObjectMapper()).writeValueAsString(orderGenerationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order-generation/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<OrderGenerationEntity><id>123</id><orderDate>1970</orderDate><orderDate>1</orderDate><orderDate>2<"
                                        + "/orderDate><totalCount>3</totalCount><orderQty>1</orderQty><status>Status</status><orderDetails/><"
                                        + "/OrderGenerationEntity>"));
    }

    /**
     * Method under test: {@link OrderGenerationController#PackCreate(OrderGenerationEntity)}
     */
    @Test
    void testPackCreate3() throws Exception {
        when(orderGenerationEntryService.createOrUpdate((OrderGenerationEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(null);
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        String content = (new ObjectMapper()).writeValueAsString(orderGenerationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order-generation/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link OrderGenerationController#updatePurchase(OrderGenerationEntity, long)}
     */
    @Test
    void testUpdatePurchase() throws Exception {
        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        String content = (new ObjectMapper()).writeValueAsString(orderGenerationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/order-generation/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link OrderGenerationController#updatePurchase(OrderGenerationEntity, long)}
     */
    @Test
    void testUpdatePurchase2() throws Exception {
        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        when(orderGenerationEntryService.createOrUpdate((OrderGenerationEntity) any())).thenReturn(orderGenerationEntity);

        OrderGenerationEntity orderGenerationEntity1 = new OrderGenerationEntity();
        orderGenerationEntity1.setId(123L);
        orderGenerationEntity1.setOrderDate(null);
        orderGenerationEntity1.setOrderDetails(new ArrayList<>());
        orderGenerationEntity1.setOrderQty(1L);
        orderGenerationEntity1.setStatus("Status");
        orderGenerationEntity1.setTotalCount(3L);
        String content = (new ObjectMapper()).writeValueAsString(orderGenerationEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/order-generation/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<OrderGenerationEntity><id>123</id><orderDate/><totalCount>3</totalCount><orderQty>1</orderQty><status"
                                        + ">Status</status><orderDetails/></OrderGenerationEntity>"));
    }

    /**
     * Method under test: {@link OrderGenerationController#updatePurchase(OrderGenerationEntity, long)}
     */
    @Test
    void testUpdatePurchase3() throws Exception {
        when(orderGenerationEntryService.createOrUpdate((OrderGenerationEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        OrderGenerationEntity orderGenerationEntity = new OrderGenerationEntity();
        orderGenerationEntity.setId(123L);
        orderGenerationEntity.setOrderDate(null);
        orderGenerationEntity.setOrderDetails(new ArrayList<>());
        orderGenerationEntity.setOrderQty(1L);
        orderGenerationEntity.setStatus("Status");
        orderGenerationEntity.setTotalCount(3L);
        String content = (new ObjectMapper()).writeValueAsString(orderGenerationEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/order-generation/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderGenerationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

