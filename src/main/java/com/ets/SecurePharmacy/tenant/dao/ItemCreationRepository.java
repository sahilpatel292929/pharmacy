package com.ets.SecurePharmacy.tenant.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;

@Repository
public interface ItemCreationRepository extends JpaRepository<ItemCreationEntity, Long> {

	List<ItemCreationEntity> findByItemName(String itemName);
	
	@Query("SELECT i FROM ItemCreationEntity i WHERE CONCAT(i.itemName) LIKE %?1%")
	public List<ItemCreationEntity> search(String keyword);
	
    Page<ItemCreationEntity> findAllByItemNameContains(String name, Pageable pageable);
	
}
