package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;

@Service
public interface BranchCreationService {

	public List<BranchCreationEntity> getAllBranchList();

	public BranchCreationEntity getBranchById(Long id) throws RecordNotFoundException;

	public boolean getBranchByName(String area);

	public BranchCreationEntity createOrUpdateBranch(BranchCreationEntity entity);
	
	public BranchCreationEntity updateBranch(BranchCreationEntity entity);

	public void deleteBranchById(Long id) throws RecordNotFoundException;

	public Page<BranchCreationEntity> getAllBranchWithPagination(int offset, int pageSize);

	public Page<BranchCreationEntity> getSearch(String query, Pageable pageable);
	
}
