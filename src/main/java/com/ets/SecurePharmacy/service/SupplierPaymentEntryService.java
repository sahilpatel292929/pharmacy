package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.SupplierPaymentEntry;

@Service
public interface SupplierPaymentEntryService {

	public List<SupplierPaymentEntry> getAll();

	public SupplierPaymentEntry getById(Long id) throws RecordNotFoundException;

	public SupplierPaymentEntry createOrUpdate(SupplierPaymentEntry entity);

	public Page<SupplierPaymentEntry> getAllPaymentEntryWithPagination(int offset, int pageSize);

	public Page<SupplierPaymentEntry> getSearch(String query, Pageable pageable);

}
