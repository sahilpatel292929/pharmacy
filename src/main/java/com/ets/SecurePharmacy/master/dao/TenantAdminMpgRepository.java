package com.ets.SecurePharmacy.master.dao;

import com.ets.SecurePharmacy.master.model.TenantAdminMpg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantAdminMpgRepository extends JpaRepository<TenantAdminMpg, Long> {

    @Query(nativeQuery = true, value = "SELECT tam.* from TenantAdminMpg tam inner join Tenant t on tam.tenant_id = t.id where t.name = ?1")
    TenantAdminMpg findByTenantName(String tenantName);
}
