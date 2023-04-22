package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;

@Repository
public interface StoreTypeCreationRepository extends JpaRepository<StoreTypeCreationEntity, Long> {

	List<StoreTypeCreationEntity> findByStoreTypeName(String storeType);

	@Query("SELECT i FROM StoreTypeCreationEntity i WHERE CONCAT(i.storeTypeName) LIKE %?1%")
	public List<StoreTypeCreationEntity> search(String keyword);

	Page<StoreTypeCreationEntity> findAllByStoreTypeNameContains(String name, Pageable pageable);
}
