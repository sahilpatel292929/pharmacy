package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.SalesManCreationEntity;

@Repository
public interface SalesManCreationRepository extends JpaRepository<SalesManCreationEntity, Long> {

	List<SalesManCreationEntity> findBySalesManName(String sales);
	

	@Query("SELECT i FROM SalesManCreationEntity i WHERE CONCAT(i.salesManName) LIKE %?1%")
	public List<SalesManCreationEntity> search(String keyword);
	
    Page<SalesManCreationEntity> findAllBySalesManNameContains(String name, Pageable pageable);
	
}
