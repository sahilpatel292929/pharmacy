package com.ets.SecurePharmacy.tenant.dao;

import com.ets.SecurePharmacy.tenant.model.SupplierLedgerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierLedgerRepository extends JpaRepository<SupplierLedgerEntity,Long> {
}
