package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ets.SecurePharmacy.tenant.model.CustomerReceiptEntryEntity;

public interface CustomerReceiptEntryRepository extends JpaRepository<CustomerReceiptEntryEntity, Long> {

	@Query("SELECT i FROM  ReceiptEntryEntity i WHERE CONCAT(i.referenceNumber) LIKE %?1%")
	public List<CustomerReceiptEntryEntity> search(String keyword);

	Page<CustomerReceiptEntryEntity> findAllByReferenceNumberContains(String name, Pageable pageable);
}

