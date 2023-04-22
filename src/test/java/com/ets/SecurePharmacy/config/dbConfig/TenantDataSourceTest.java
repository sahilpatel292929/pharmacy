package com.ets.SecurePharmacy.config.dbConfig;

import com.ets.SecurePharmacy.master.dao.TenantRepository;
import com.ets.SecurePharmacy.master.model.Tenant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TenantDataSource.class})
@ExtendWith(SpringExtension.class)
class TenantDataSourceTest {
    @MockBean(name = "masterDB")
    private DataSource dataSource;

    @Autowired
    private TenantDataSource tenantDataSource;

    @MockBean
    private TenantRepository tenantRepository;

    /**
     * Method under test: {@link TenantDataSource#getDataSource(String)}
     */
    @Test
    void testGetDataSource() {
        // Arrange and Act
        tenantDataSource.getDataSource("Name");
    }

    /**
     * Method under test: {@link TenantDataSource#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(tenantRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(tenantDataSource.getAll().isEmpty());
        verify(tenantRepository).findAll();
    }

    /**
     * Method under test: {@link TenantDataSource#getAll()}
     */
    @Test
    void testGetAll2() {
        // Arrange
        Tenant tenant = new Tenant();
        tenant.setDriverClassName("Driver Class Name");
        tenant.setId(123L);
        tenant.setName("Name");
        tenant.setPassword("iloveyou");
        tenant.setUrl("https://example.org/example");
        tenant.setUsername("janedoe");

        ArrayList<Tenant> tenantList = new ArrayList<>();
        tenantList.add(tenant);

        Tenant tenant1 = new Tenant();
        tenant1.setDriverClassName("Driver Class Name");
        tenant1.setId(123L);
        tenant1.setName("Name");
        tenant1.setPassword("iloveyou");
        tenant1.setUrl("https://example.org/example");
        tenant1.setUsername("janedoe");
        when(tenantRepository.findByName((String) any())).thenReturn(tenant1);
        when(tenantRepository.findAll()).thenReturn(tenantList);

        // Act and Assert
        assertEquals(1, tenantDataSource.getAll().size());
        verify(tenantRepository).findAll();
    }
}

