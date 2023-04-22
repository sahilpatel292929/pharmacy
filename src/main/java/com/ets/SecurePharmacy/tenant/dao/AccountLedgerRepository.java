package com.ets.SecurePharmacy.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.AccountLedgerEntity;

@Repository
public interface AccountLedgerRepository extends JpaRepository<AccountLedgerEntity, Long> {

	
}
