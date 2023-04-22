package com.ets.SecurePharmacy.master.dao;

import com.ets.SecurePharmacy.master.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Tenant findByName(String name);

    @Query(nativeQuery = true,value = "SELECT u.id from Tenant t inner join TenantAdminMpg tam on t.id = tam.tenant_id inner join `User` u on u.id = tam.admin_user_id WHERE t.name=?1")
    Integer getUserIdByTenantName(String name);

    @Query(nativeQuery = true, value = "SELECT t.* from Tenant t inner join TenantAdminMpg tam on t.id = tam.tenant_id inner join `User` u on u.id = tam.admin_user_id where u.tenant_user_id = ?1 or tam.admin_user_id = ?1")
    List<Tenant> getTenantDetailsBasedAdminId(Long id);
}
