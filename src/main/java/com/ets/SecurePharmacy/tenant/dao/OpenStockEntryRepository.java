package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.OpenStockEntryEntity;

@Repository
public interface OpenStockEntryRepository extends JpaRepository<OpenStockEntryEntity, Long> {
	@Query("SELECT i FROM OpenStockEntryEntity i WHERE CONCAT(i.orderDate) LIKE %?1%")
	public List<OpenStockEntryEntity> search(String keyword);

	Page<OpenStockEntryEntity> findAllByOrderDateContains(String name, Pageable pageable);

}
