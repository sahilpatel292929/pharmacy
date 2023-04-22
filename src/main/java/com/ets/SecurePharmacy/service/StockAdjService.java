package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.StockAdjEntity;

@Service
public interface StockAdjService {

	public List<StockAdjEntity> getAllStockAgjDetails();

	public StockAdjEntity getStockAdjById(Long id) throws RecordNotFoundException;

	public StockAdjEntity createOrUpdateStockAdj(StockAdjEntity entity);

	public Page<StockAdjEntity> getAllStockAdjEntityWithPagination(int offset, int pageSize);

	public Page<StockAdjEntity> getSearch(String query, Pageable pageable);

}
