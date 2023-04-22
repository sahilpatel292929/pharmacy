package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;
@Repository
public interface PurchaseEntryRepository extends JpaRepository<PurchaseEntryEntity, Long>, QuerydslPredicateExecutor<PurchaseEntryEntity> {

	List<PurchaseEntryEntity> findByInvoiceNo(String invoice);
	
	@Query(value=" SELECT * FROM (SELECT pe.* FROM TBL_PURCHASE_ENTRY_DETAILS ped inner join TBL_PURCHASE_ENTRY  pe on pe.id=ped.purchaseEntryEntity_id  where items_id=?1  ORDER BY invoice_date DESC LIMIT 3) sub ORDER BY id ASC", nativeQuery = true)
	List<PurchaseEntryEntity> findItemRecentDetails(Long itemId);
	
	Page<PurchaseEntryEntity> findAllBySupplierNameContains(String name, Pageable pageable);
	
	@Query(value="SELECT * FROM TBL_PURCHASE_ENTRY where  invoice_num =?1 and supplierDetails_id =?2 ", nativeQuery = true)
	List<PurchaseEntryEntity> findByinvoiceNoAndSupplierId(String invoice,Long id);
	
}
