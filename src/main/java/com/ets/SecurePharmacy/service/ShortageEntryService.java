package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.ShortageEntryEntity;

@Service
public interface ShortageEntryService {

	public List<ShortageEntryEntity> getAllShortage();
	public ShortageEntryEntity getShortageById(Long id) throws RecordNotFoundException;
	public ShortageEntryEntity createOrUpdateShortage(ShortageEntryEntity entity);
	public Page<ShortageEntryEntity> getAllShortageEntryWithPagination(int offset,int pageSize);
	public Page<ShortageEntryEntity> getSearch(String query, Pageable pageable);

	
}
