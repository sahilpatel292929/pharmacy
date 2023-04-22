package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.SalesOrderEntryEntity;

@Repository
public interface SalesOrderEntryRepository extends JpaRepository<SalesOrderEntryEntity, Long> {

	public List<SalesOrderEntryEntity>  findByCustomerMobileNo(Long mobileNo);

	@Query("SELECT i FROM SalesOrderEntryEntity i WHERE CONCAT(i.customerMobileNo) LIKE %?1%")
	public List<SalesOrderEntryEntity> search(String keyword);
	@Query("SELECT i FROM SalesOrderEntryEntity i WHERE CONCAT(i.customerMobileNo) LIKE %?1%")
	Page<SalesOrderEntryEntity> findAllByCustomerMobileNo(Long mobileNo, Pageable pageable);
	Page<SalesOrderEntryEntity> findAllByCustomerNameContains(String name1,Pageable pageable);

}
