package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.CustomerReceiptEntryEntity;

@Service
public interface CustomerReceiptEntryService {

	public List<CustomerReceiptEntryEntity> getAll();

	public CustomerReceiptEntryEntity getById(Long id) throws RecordNotFoundException;

	public CustomerReceiptEntryEntity createOrUpdate(CustomerReceiptEntryEntity entity);

	public Page<CustomerReceiptEntryEntity> getAllReceiptEntryWithPagination(int offset, int pageSize);

	public Page<CustomerReceiptEntryEntity> getSearch(String query, Pageable pageable);
}
