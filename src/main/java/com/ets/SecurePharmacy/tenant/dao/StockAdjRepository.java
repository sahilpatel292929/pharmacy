package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.StockAdjEntity;

@Repository
public interface StockAdjRepository extends JpaRepository<StockAdjEntity, Long> {
	@Query("SELECT i FROM  StockAdjEntity i WHERE CONCAT(i.entryDate) LIKE %?1%")
	public List< StockAdjEntity> search(String keyword);
	
    Page< StockAdjEntity> findAllByEntryDateContains(String name, Pageable pageable);
	
}
