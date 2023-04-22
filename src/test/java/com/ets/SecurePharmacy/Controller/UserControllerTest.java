package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.RegisterDTO;
import com.ets.SecurePharmacy.DTO.TenantCreationDTO;
import com.ets.SecurePharmacy.serviceimpl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#register(RegisterDTO)}
     */
    @Test
    void testRegister() throws Exception {
        when(userService.register((RegisterDTO) any())).thenReturn("Register");

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setRole("Role");
        registerDTO.setTenantName("Tenant Name");
        registerDTO.setUserFirstName("Jane");
        registerDTO.setUserLastName("Doe");
        registerDTO.setUserName("janedoe");
        registerDTO.setUserPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(registerDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Register"));
    }

    /**
     * Method under test: {@link UserController#getUser()}
     */
    @Test
    void testGetUser() throws Exception {
        when(userService.getUser()).thenReturn("User");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/getUser");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User"));
    }

    /**
     * Method under test: {@link UserController#addTenant(TenantCreationDTO)}
     */
    @Test
    void testAddTenant() throws Exception {
        when(userService.addTenant((TenantCreationDTO) any())).thenReturn("Add Tenant");

        TenantCreationDTO tenantCreationDTO = new TenantCreationDTO();
        tenantCreationDTO.setExistingTenantName("Existing Tenant Name");
        tenantCreationDTO.setNewTenantName("New Tenant Name");
        String content = (new ObjectMapper()).writeValueAsString(tenantCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/addTenant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add Tenant"));
    }

    /**
     * Method under test: {@link UserController#getTenant()}
     */
    @Test
    void testGetTenant() throws Exception {
        when(userService.getTenant()).thenReturn("Tenant");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/getTenant");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Tenant"));
    }
}

