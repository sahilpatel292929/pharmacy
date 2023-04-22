package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;

@Service
public interface ManufacturerCreationService {

	public List<ManufacturerCreationEntity> getAllManufactureList();

	public ManufacturerCreationEntity getManufactureById(Long id) throws RecordNotFoundException;

	public boolean getManufactureByName(String area);

	public ManufacturerCreationEntity createOrUpdateManufacture(ManufacturerCreationEntity entity);
	
	public ManufacturerCreationEntity updateManufacture(ManufacturerCreationEntity entity);

	public void createOrUpdateManufactureList(List<ManufacturerCreationEntity> entity);

	public void deleteManufactureById(Long id) throws RecordNotFoundException;

	public Page<ManufacturerCreationEntity> getAllManufacturerWithPagination(int offset, int pageSize);

	public Page<ManufacturerCreationEntity> getSearch(String query, Pageable pageable);
}
