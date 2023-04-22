package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;

@Service
public interface BatchService {

	List<BatchEntity> getAllAccounts();

	BatchEntity getAccountsById(Long id) throws RecordNotFoundException;

	BatchEntity createOrUpdateAccounts(BatchEntity entity);

	void deleteAccountsById(Long id) throws RecordNotFoundException;

	Page<BatchEntity> getAllBatchWithPagination(int offset, int pageSize);

	Page<BatchEntity> getSearch(String query, Pageable pageable);

	BatchEntity getMRP(String batch_no, Long item_id);

	List<BatchEntity> getItemBatchDetails(Long id);
	List<String> getItemBatchNoDetails(Long id);
	
	BatchEntity getBatchDetails(String id) throws RecordNotFoundException;


}
