package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.PackCreationEntity;

@Service
public interface PackCreationService {

	public List<PackCreationEntity> getAllPackList();

	public PackCreationEntity getPakById(Long id) throws RecordNotFoundException;

	public boolean getPackByName(String packs);

	public PackCreationEntity createOrUpdatePack(PackCreationEntity entity);

	public PackCreationEntity updatePack(PackCreationEntity entity);
	public void createOrUpdatePack(List<PackCreationEntity> entity);

	public void deletePackById(Long id) throws RecordNotFoundException;

	public Page<PackCreationEntity> getAllPackWithPagination(int offset, int pageSize);

	public Page<PackCreationEntity> getSearch(String query, Pageable pageable);
}
