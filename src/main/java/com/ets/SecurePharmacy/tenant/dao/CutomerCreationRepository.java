package com.ets.SecurePharmacy.tenant.dao;

import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
@Repository
public interface CutomerCreationRepository extends JpaRepository<CustomerCreationEntity, Long> {

	CustomerCreationEntity findByMobileNo(String mobile);
	
	@Query("SELECT i FROM CustomerCreationEntity i WHERE CONCAT(i.customerName) LIKE %?1%")
	public List<CustomerCreationEntity> search(String keyword);
	
    Page<CustomerCreationEntity> findAllByCustomerNameOrMobileNoContains(String name, String mobileno, Pageable pageable);
    
    
	
}
