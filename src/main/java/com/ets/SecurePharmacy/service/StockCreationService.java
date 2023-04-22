package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.StockCreationEntity;

@Service
public interface StockCreationService {

	public List<StockCreationEntity> getAllStock();
	public StockCreationEntity getStockById(Long id) throws RecordNotFoundException;
	public boolean getStockName(String bname);
	public StockCreationEntity createOrUpdateStock(StockCreationEntity entity);
	public void deleteStockById(Long id) throws RecordNotFoundException;
}
