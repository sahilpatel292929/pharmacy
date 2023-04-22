package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.PaymentEntryEntity;

@Service
public interface PaymentEntryService {

	public List<PaymentEntryEntity> getAll();

	public PaymentEntryEntity getById(Long id) throws RecordNotFoundException;

	public PaymentEntryEntity createOrUpdate(PaymentEntryEntity entity);

	public Page<PaymentEntryEntity> getAllPaymentEntryWithPagination(int offset, int pageSize);

	public Page<PaymentEntryEntity> getSearch(String query, Pageable pageable);

}
