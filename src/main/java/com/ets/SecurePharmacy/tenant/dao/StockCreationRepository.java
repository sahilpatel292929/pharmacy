package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.StockCreationEntity;

@Repository
public interface StockCreationRepository  extends CrudRepository<StockCreationEntity, Long>{

	List<StockCreationEntity> findByStockName(String stockName);
}
