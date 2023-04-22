package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.CounterSaleEntity;

@Service
public interface CounterSaleService {

	public List<CounterSaleEntity> getAllCounterSales();

	public CounterSaleEntity getCounterSaleById(Long id) throws RecordNotFoundException;

	public CounterSaleEntity createOrUpdateCounterSale(CounterSaleEntity entity);

	public Page<CounterSaleEntity> getAllCounterSaleWithPagination(int offset, int pageSize);

	public Page<CounterSaleEntity> getSearch(String query, Pageable pageable);

}
