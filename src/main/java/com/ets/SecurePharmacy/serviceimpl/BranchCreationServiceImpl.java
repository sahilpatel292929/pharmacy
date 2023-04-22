package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.BranchCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;
import com.ets.SecurePharmacy.service.BranchCreationService;

@Service
public class BranchCreationServiceImpl implements BranchCreationService {

	@Autowired
	BranchCreationRepository branchRepo;

	public List<BranchCreationEntity> getAllBranchList() {
		List<BranchCreationEntity> result;
		try {
			result = (List<BranchCreationEntity>) branchRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<BranchCreationEntity>();
		}
	}

	public BranchCreationEntity getBranchById(Long id) throws RecordNotFoundException {
		Optional<BranchCreationEntity> branch;
		try {
			branch = branchRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (branch.isPresent()) {
			return branch.get();
		} else {
			throw new RecordNotFoundException("No Branch record exist for given id");
		}
	}

	public boolean getBranchByName(String branch) {
		List<BranchCreationEntity> branchList;
		try {
			branchList= branchRepo.findByBranchName(branch);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (branchList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public BranchCreationEntity createOrUpdateBranch(BranchCreationEntity entity) {
	
		List<BranchCreationEntity> branchList;
		
		try {
			branchList = branchRepo.findByBranchName(entity.getBranchName());
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (branchList.size() > 0) {
			throw new RecordAlreadyPresentException("Branch Name Already Created");
		}
		else {
			try {
				entity = branchRepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
		}
		
		return entity;
	}

	public void deleteBranchById(Long id) throws RecordNotFoundException {
		Optional<BranchCreationEntity> branch;
		try {
		
			branch= branchRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (branch.isPresent()) {
			branchRepo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Branch record exist for given id");
		}
	}

	@Override
	public Page<BranchCreationEntity> getAllBranchWithPagination(int offset, int pageSize) {
		Page<BranchCreationEntity> result;
		
		try {
			result = branchRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;

	}

	@Override
	public Page<BranchCreationEntity> getSearch(String query, Pageable pageable) {
		Page<BranchCreationEntity> result;
		try {
			result = branchRepo.findAllByBranchContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

	@Override
	public BranchCreationEntity updateBranch(BranchCreationEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = branchRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}

}
