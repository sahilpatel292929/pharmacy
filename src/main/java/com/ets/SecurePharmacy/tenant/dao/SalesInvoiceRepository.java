package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntity;
@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoiceEntity, Long> {
	@Query("SELECT i FROM SalesInvoiceEntity i WHERE CONCAT(i.customerName) LIKE %?1%")
	public List<SalesInvoiceEntity> search(String keyword);
	
	@Query("SELECT i FROM SalesInvoiceEntity i WHERE CONCAT(i.customerName) LIKE %?1%")
    Page<SalesInvoiceEntity> searchByMobile(Long mobileno, Pageable pageable);
    Page<SalesInvoiceEntity> findAllByCustomerNameContains(String name, Pageable pageable);

}
