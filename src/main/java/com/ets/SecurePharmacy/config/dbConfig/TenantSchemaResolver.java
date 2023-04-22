package com.ets.SecurePharmacy.config.dbConfig;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {

    private String defaultTenant = "db_main";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String t = DBContextHolder.getCurrentDb();
        if (t != null) {
            return t;
        } else {
            return defaultTenant;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}