package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.CounterSaleEntity;

@Repository
public interface CounterSaleRepository extends JpaRepository<CounterSaleEntity, Long> {
	@Query("SELECT i FROM CounterSaleEntity i WHERE CONCAT(i.entryDate) LIKE %?1%")
	public List<CounterSaleEntity> search(String keyword);
	
    Page<CounterSaleEntity> findAllByEntryDateContains(String name, Pageable pageable);
	
}
