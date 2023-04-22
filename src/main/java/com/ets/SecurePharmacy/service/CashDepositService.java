package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.CashDepositEntity;

@Service
public interface CashDepositService {

	public List<CashDepositEntity> getAllCashDeposit();
	public CashDepositEntity getCashDepositById(Long id) throws RecordNotFoundException;
	public CashDepositEntity createOrUpdateCashDeposit(CashDepositEntity entity);
	public CashDepositEntity updateCashDeposit(CashDepositEntity entity);
	public Page<CashDepositEntity> getAllDepositsWithPagination(int offset, int pageSize);

	public Page<CashDepositEntity> getSearch(String query, Pageable pageable);

	
}
