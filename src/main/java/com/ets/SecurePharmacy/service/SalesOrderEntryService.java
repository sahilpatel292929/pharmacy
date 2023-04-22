package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.SalesOrderEntryEntity;

@Service
public interface SalesOrderEntryService {

	public List<SalesOrderEntryEntity> getAll();

	public SalesOrderEntryEntity getById(Long id) throws RecordNotFoundException;

	public SalesOrderEntryEntity createOrUpdate(SalesOrderEntryEntity entity);

	public List<SalesOrderEntryEntity> getByMobileNumber(Long mobileNo) throws RecordNotFoundException;

	public Page<SalesOrderEntryEntity> getAllSalesOrderEntryEntityWithPagination(int offset, int pageSize);

	public Page<SalesOrderEntryEntity> getSearch(String query, Pageable pageable);

}
