package com.ets.SecurePharmacy.tenant.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;

@Repository
public interface SupplierCreationRepository extends JpaRepository<SupplierCreationEntity, Long> {

	List<SupplierCreationEntity> findBySupplierNameOrMobileNo(String supplierName,String mobileNo);
	@Query("SELECT i FROM SupplierCreationEntity i WHERE CONCAT(i.supplierName) LIKE %?1%")
	public List<SupplierCreationEntity> search(String keyword);
	
    Page<SupplierCreationEntity> findAllBySupplierNameOrMobileNoContains(String name, String mobileNo, Pageable pageable);
	
}
