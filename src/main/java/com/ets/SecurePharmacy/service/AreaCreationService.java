package com.ets.SecurePharmacy.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;

@Service
public interface AreaCreationService {

	public List<AreaCreationEntity> getAllAreaList();
	public AreaCreationEntity getAreaById(Long id) throws RecordNotFoundException;
	public boolean getAreaByName(String area);
	public AreaCreationEntity createOrUpdateArea(AreaCreationEntity entity);
	public void createOrUpdateAreaList(List<AreaCreationEntity> entity);
	public void deleteAreaById(Long id) throws RecordNotFoundException;

	}
