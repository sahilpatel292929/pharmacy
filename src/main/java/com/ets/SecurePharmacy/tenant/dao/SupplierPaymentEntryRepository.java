package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.SupplierPaymentEntry;

@Repository
public interface SupplierPaymentEntryRepository extends JpaRepository<SupplierPaymentEntry, Long> {

	@Query("SELECT i FROM PaymentEntryEntity i WHERE CONCAT(i.paymentDate) LIKE %?1%")
	public List<SupplierPaymentEntry> search(String keyword);

	Page<SupplierPaymentEntry> findAllByPaymentDateContains(String name, Pageable pageable);
}