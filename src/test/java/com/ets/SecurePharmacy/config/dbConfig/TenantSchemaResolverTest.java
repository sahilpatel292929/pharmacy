package com.ets.SecurePharmacy.config.dbConfig;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {TenantSchemaResolver.class})
@ExtendWith(SpringExtension.class)
class TenantSchemaResolverTest {
    @Autowired
    private TenantSchemaResolver tenantSchemaResolver;


    /**
     * Method under test: {@link TenantSchemaResolver#validateExistingCurrentSessions()}
     */
    @Test
    void testValidateExistingCurrentSessions() {
        // Arrange, Act and Assert
        assertTrue(tenantSchemaResolver.validateExistingCurrentSessions());
    }
}

