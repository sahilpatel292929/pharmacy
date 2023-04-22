package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.CashWithdrawEntity;

@Service
public interface CashWithdrawService {

	public List<CashWithdrawEntity> getAllCashWithdraw();
	public CashWithdrawEntity getCashWithDrawById(Long id) throws RecordNotFoundException;
	public CashWithdrawEntity createOrUpdateCashWithdraw(CashWithdrawEntity entity);
	public Page<CashWithdrawEntity> getAllDepositsWithPagination(int offset, int pageSize);

	public Page<CashWithdrawEntity> getSearch(String query, Pageable pageable);
}
