package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.ReceiptEntryEntity;

@Service
public interface ReceiptEntryService {

	public List<ReceiptEntryEntity> getAll();

	public ReceiptEntryEntity getById(Long id) throws RecordNotFoundException;

	public ReceiptEntryEntity createOrUpdate(ReceiptEntryEntity entity);

	public Page<ReceiptEntryEntity> getAllReceiptEntryWithPagination(int offset, int pageSize);

	public Page<ReceiptEntryEntity> getSearch(String query, Pageable pageable);

}
