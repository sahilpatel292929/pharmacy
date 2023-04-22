package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;

@Service
public interface StoreTypeCreationService {

	public List<StoreTypeCreationEntity> getAllStoreTypeList();

	public StoreTypeCreationEntity getStoreById(Long id) throws RecordNotFoundException;

	public boolean getStoreByName(String area);

	public StoreTypeCreationEntity createOrUpdateStoreType(StoreTypeCreationEntity entity);
	
	public StoreTypeCreationEntity updateStoreType(StoreTypeCreationEntity entity);

	public void deleteStoreById(Long id) throws RecordNotFoundException;

	public Page<StoreTypeCreationEntity> getAllStoreTypeWithPagination(int offset, int pageSize);

	public Page<StoreTypeCreationEntity> getSearch(String query, Pageable pageable);

}
