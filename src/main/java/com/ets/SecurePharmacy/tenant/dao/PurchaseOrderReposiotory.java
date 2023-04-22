package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.tenant.model.PurchaseOrderEntity;

@Repository
@Transactional
public interface PurchaseOrderReposiotory extends JpaRepository<PurchaseOrderEntity, Long>, JpaSpecificationExecutor<PurchaseOrderEntity> {


@Query("SELECT i FROM PurchaseOrderEntity i WHERE CONCAT(i.orderDate) LIKE %?1%")
	public List<PurchaseOrderEntity> search(String keyword);
	
    Page<PurchaseOrderEntity> findAllBySupplierNameContains(String name, Pageable pageable);
}
