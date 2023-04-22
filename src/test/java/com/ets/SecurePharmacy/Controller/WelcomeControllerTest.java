package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.UserResponseDTO;
import com.ets.SecurePharmacy.serviceimpl.JwtService;
import com.ets.SecurePharmacy.serviceimpl.UserService;
import com.ets.SecurePharmacy.tenant.model.JwtRequest;
import com.ets.SecurePharmacy.tenant.model.JwtResponse;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {WelcomeController.class})
@ExtendWith(SpringExtension.class)
class WelcomeControllerTest {
    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @Autowired
    private WelcomeController welcomeController;


    /**
     * Method under test: {@link WelcomeController#initRoleAndUser()}
     */
    @Test
    void testInitRoleAndUser() {
        doNothing().when(userService).initRoleAndUser();
        welcomeController.initRoleAndUser();
    }


    /**
     * Method under test: {@link WelcomeController#forAdmin()}
     */
    @Test
    void testForAdmin() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/forAdmin");
        MockMvcBuilders.standaloneSetup(welcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("This URL is only accessible to the admin"));
    }

    /**
     * Method under test: {@link WelcomeController#forAdmin()}
     */
    @Test
    void testForAdmin2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/forAdmin");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(welcomeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("This URL is only accessible to the admin"));
    }

    /**
     * Method under test: {@link WelcomeController#forUser()}
     */
    @Test
    void testForUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/forUser");
        MockMvcBuilders.standaloneSetup(welcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("This URL is only accessible to the user"));
    }

    /**
     * Method under test: {@link WelcomeController#createJwtToken(JwtRequest)}
     */
    @Test
    void testCreateJwtToken() throws Exception {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUserName("user name");
        userResponseDTO.setUserFirstName("first name");
        userResponseDTO.setTenantName("tenant name");
        userResponseDTO.setUserLastName("last name");
        JwtResponse jwtResponse = new JwtResponse(userResponseDTO,"token");

        when(jwtService.createJwtToken((JwtRequest) any())).thenReturn(jwtResponse);
        JwtRequest jwtRequest = new JwtRequest("username","password");

        String content = (new ObjectMapper()).writeValueAsString(jwtRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(welcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<JwtResponse><user><id>1</id><userName>user name</userName><userFirstName>first name</userFirstName><userLastName>last name</userLastName><tenantName>tenant name</tenantName></user><jwtToken>token</jwtToken></JwtResponse>"));
    }
}

