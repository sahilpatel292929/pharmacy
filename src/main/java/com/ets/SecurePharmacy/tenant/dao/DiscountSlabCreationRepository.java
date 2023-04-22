package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;

@Repository
public interface DiscountSlabCreationRepository extends JpaRepository<DiscountSlabCreationEntity, Long> {

	List<DiscountSlabCreationEntity> findByDiscountSlabName(String dName);
	@Query("SELECT i FROM DiscountSlabCreationEntity i WHERE CONCAT(i.discountSlabName) LIKE %?1%")
	public List<DiscountSlabCreationEntity> search(String keyword);
	
    Page<DiscountSlabCreationEntity> findAllByDiscountSlabNameContains(String name, Pageable pageable);
	
}
