package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.AccountService;
import com.ets.SecurePharmacy.tenant.model.AccountEntity;
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

import java.util.ArrayList;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    /**
     * Method under test: {@link AccountController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account-service/getAll");
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<List/>"));
    }

    /**
     * Method under test: {@link AccountController#getAllOpenStockAccounts()}
     */
    @Test
    void testGetAllOpenStockAccounts2() throws Exception {
        when(accountService.getAllAccounts()).thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account-service/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice() throws Exception {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");
        when(accountService.getAccountsById((Long) any())).thenReturn(accountEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account-service/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<AccountEntity><id>123</id><userName>janedoe</userName><companyName>Company Name</companyName><address1>42"
                                        + " Main St</address1><address2>42 Main St</address2><postalCode>Postal Code</postalCode><mobileNumber"
                                        + ">42</mobileNumber><emailId>42</emailId><logoUrl>https://example.org/example</logoUrl></AccountEntity"
                                        + ">"));
    }

    /**
     * Method under test: {@link AccountController#getByInvoice(Long)}
     */
    @Test
    void testGetByInvoice2() throws Exception {
        when(accountService.getAccountsById((Long) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account-service/get/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountController#openStockCreate(AccountEntity)}
     */
    @Test
    void testOpenStockCreate() throws Exception {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");
        when(accountService.createOrUpdateAccounts((AccountEntity) any())).thenReturn(accountEntity);

        AccountEntity accountEntity1 = new AccountEntity();
        accountEntity1.setAddress1("42 Main St");
        accountEntity1.setAddress2("42 Main St");
        accountEntity1.setCompanyName("Company Name");
        accountEntity1.setEmailId("42");
        accountEntity1.setId(123L);
        accountEntity1.setLogoUrl("https://example.org/example");
        accountEntity1.setMobileNumber("42");
        accountEntity1.setPostalCode("Postal Code");
        accountEntity1.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(accountEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account-service/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<AccountEntity><id>123</id><userName>janedoe</userName><companyName>Company Name</companyName><address1>42"
                                        + " Main St</address1><address2>42 Main St</address2><postalCode>Postal Code</postalCode><mobileNumber"
                                        + ">42</mobileNumber><emailId>42</emailId><logoUrl>https://example.org/example</logoUrl></AccountEntity"
                                        + ">"));
    }

    /**
     * Method under test: {@link AccountController#openStockCreate(AccountEntity)}
     */
    @Test
    void testOpenStockCreate2() throws Exception {
        when(accountService.createOrUpdateAccounts((AccountEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(accountEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account-service/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountController#updateOpenStock(AccountEntity)}
     */
    @Test
    void testUpdateOpenStock() throws Exception {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");
        when(accountService.createOrUpdateAccounts((AccountEntity) any())).thenReturn(accountEntity);

        AccountEntity accountEntity1 = new AccountEntity();
        accountEntity1.setAddress1("42 Main St");
        accountEntity1.setAddress2("42 Main St");
        accountEntity1.setCompanyName("Company Name");
        accountEntity1.setEmailId("42");
        accountEntity1.setId(123L);
        accountEntity1.setLogoUrl("https://example.org/example");
        accountEntity1.setMobileNumber("42");
        accountEntity1.setPostalCode("Postal Code");
        accountEntity1.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(accountEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/account-service/update{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<AccountEntity><id>123</id><userName>janedoe</userName><companyName>Company Name</companyName><address1>42"
                                        + " Main St</address1><address2>42 Main St</address2><postalCode>Postal Code</postalCode><mobileNumber"
                                        + ">42</mobileNumber><emailId>42</emailId><logoUrl>https://example.org/example</logoUrl></AccountEntity"
                                        + ">"));
    }

    /**
     * Method under test: {@link AccountController#updateOpenStock(AccountEntity)}
     */
    @Test
    void testUpdateOpenStock2() throws Exception {
        when(accountService.createOrUpdateAccounts((AccountEntity) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAddress1("42 Main St");
        accountEntity.setAddress2("42 Main St");
        accountEntity.setCompanyName("Company Name");
        accountEntity.setEmailId("42");
        accountEntity.setId(123L);
        accountEntity.setLogoUrl("https://example.org/example");
        accountEntity.setMobileNumber("42");
        accountEntity.setPostalCode("Postal Code");
        accountEntity.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(accountEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/account-service/update{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}