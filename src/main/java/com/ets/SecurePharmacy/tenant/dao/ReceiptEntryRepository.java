package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.ReceiptEntryEntity;

@Repository
public interface ReceiptEntryRepository extends JpaRepository<ReceiptEntryEntity, Long> {

	@Query("SELECT i FROM  ReceiptEntryEntity i WHERE CONCAT(i.referenceNumber) LIKE %?1%")
	public List<ReceiptEntryEntity> search(String keyword);

	Page<ReceiptEntryEntity> findAllByReferenceNumberContains(String name, Pageable pageable);
}
