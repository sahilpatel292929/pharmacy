package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.config.dbConfig.AsynceService;
import com.ets.SecurePharmacy.master.dao.TenantRepository;
import com.ets.SecurePharmacy.master.model.Tenant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ReloadConfigurationController.class})
@ExtendWith(SpringExtension.class)
class ReloadConfigurationControllerTest {

    @MockBean
    private TenantRepository tenantRepository;

    @MockBean
    private AsynceService asynceService;

    @Autowired
    private ReloadConfigurationController reloadConfigurationController;

    /**
     * Method under test: {@link ReloadConfigurationController#reloadConfiguration()}
     */
    @Test
    void testReloadConfiguration() throws Exception {
        Tenant tenant = new Tenant();
        tenant.setId(1l);
        tenant.setName("name");
        tenant.setPassword("password");
        tenant.setUrl("url");
        tenant.setUsername("username");
        tenant.setDriverClassName("class name");

        when(tenantRepository.findAll()).thenReturn(Collections.singletonList(tenant));
        doNothing().when(asynceService).updateTenant((String) any());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reload/tenant-configuration");
        MockMvcBuilders.standaloneSetup(reloadConfigurationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(tenantRepository).findAll();
        verify(asynceService).updateTenant((String) any());
    }
}