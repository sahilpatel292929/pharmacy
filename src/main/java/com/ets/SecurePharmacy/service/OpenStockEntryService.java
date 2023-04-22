package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.OpenStockEntryEntity;

@Service
public interface OpenStockEntryService {

	public List<OpenStockEntryEntity> getAllOpenStock();

	public OpenStockEntryEntity getOpenStockeById(Long id) throws RecordNotFoundException;

	public OpenStockEntryEntity createOrUpdateOpenStock(OpenStockEntryEntity entity);

	// public boolean getPurchaseDetailByInvoice(String invoice);
	public Page<OpenStockEntryEntity> getAllOpenStockEntryWithPagination(int offset, int pageSize);

	public Page<OpenStockEntryEntity> getSearch(String query, Pageable pageable);

}
