package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
@Service("supplierService")
@Configurable
public interface SupplierCreationService {
	public List<SupplierCreationEntity> getAllSuppliers();
	public SupplierCreationEntity getSupplierById(Long id) throws RecordNotFoundException;
	public boolean getSupplierNameOrMobile(String sname, String mobile);
	public SupplierCreationEntity createOrUpdateSupplier(SupplierCreationEntity entity);
	public SupplierCreationEntity updateSupplier(SupplierCreationEntity entity);
	public void createOrUpdateSupplierList(List<SupplierCreationEntity> entity);
	public void deleteSupplierById(Long id) throws RecordNotFoundException;
	public Page<SupplierCreationEntity> getAllSuppliersWithPagination(int offset,int pageSize);
	public Page<SupplierCreationEntity> getSearch(String query, Pageable pageable);
}
