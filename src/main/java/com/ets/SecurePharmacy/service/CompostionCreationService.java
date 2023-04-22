package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;

@Service
public interface CompostionCreationService {

	public List<CompostionCreationEntity> getAllCompositionList();
	public CompostionCreationEntity getCompositionById(Long id) throws RecordNotFoundException;
	public boolean getCompositonByName(String area);
	public CompostionCreationEntity createOrUpdateCompostion(CompostionCreationEntity entity);
	public CompostionCreationEntity updateCompostion(CompostionCreationEntity entity);
	public void  createOrUpdateCompostion(List<CompostionCreationEntity> entity);
	public void deleteCompostionById(Long id) throws RecordNotFoundException;
	public Page<CompostionCreationEntity> getAllCompostionWithPagination(int offset,int pageSize);

	public Page<CompostionCreationEntity> getSearch(String query, Pageable pageable);

}
