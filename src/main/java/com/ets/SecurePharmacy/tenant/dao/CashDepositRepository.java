package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.CashDepositEntity;

@Repository
public interface CashDepositRepository extends JpaRepository<CashDepositEntity, Long> {

	@Query("SELECT i FROM CashDepositEntity i WHERE CONCAT(i.bankName) LIKE %?1%")
	public List<CashDepositEntity> search(String keyword);

	@Query("SELECT i FROM CashDepositEntity i WHERE CONCAT(i.bankName) LIKE %?1%")
	Page<CashDepositEntity> findAllByBankNameContains(String name, Pageable pageable);
}
