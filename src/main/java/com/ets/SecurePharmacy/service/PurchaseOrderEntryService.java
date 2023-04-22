package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.PurchaseOrderEntity;

@Service
public interface PurchaseOrderEntryService {

	public List<PurchaseOrderEntity> getAll();

	public PurchaseOrderEntity getById(Long id) throws RecordNotFoundException;

	public PurchaseOrderEntity createOrUpdate(PurchaseOrderEntity entity);

	public List<PurchaseOrderEntity> findByCriteria(Long itemsId, Long supplierDetailsId);

	public Page<PurchaseOrderEntity> getAllPurchaseOrderWithPagination(int offset, int pageSize);

	public Page<PurchaseOrderEntity> getSearch(String query, Pageable pageable);

}
