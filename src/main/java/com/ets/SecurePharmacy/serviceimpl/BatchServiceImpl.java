package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.BatchRepository;
import com.ets.SecurePharmacy.tenant.dao.BatchRepository.BatchDetailsDTO;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.service.BatchService;

@Service
public class BatchServiceImpl implements BatchService {

	@Autowired 
	BatchRepository batchRepo;
	
	
	public List<BatchEntity> getAllAccounts() {
		List<BatchEntity> result;
		try {
			result= (List<BatchEntity>) batchRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(result.size() > 0) {
			return result;
		} 
		else {
			return new ArrayList<BatchEntity>();
		}
	}

	
	public BatchEntity getAccountsById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<BatchEntity> batch;
		try {
			batch=batchRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(batch.isPresent()) {
			return batch.get();
		}
		else {
			throw new RecordNotFoundException("No Batch record exist for given id");
		}
	}

	
	public BatchEntity createOrUpdateAccounts(BatchEntity entity) {
		
		try {
			entity=batchRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

	
	public void deleteAccountsById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<BatchEntity> batch;
		try {
			batch=batchRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(batch.isPresent()) {
			batchRepo.deleteById(id);
		}
		else {
			throw new RecordNotFoundException("No Batch record exist for given id");
		}
	}
	
	@Override
	public Page<BatchEntity> getAllBatchWithPagination(int offset, int pageSize) {
		Page<BatchEntity> result;
		try
		{
			result =batchRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;	
	}

	@Override
	public Page<BatchEntity> getSearch(String query, Pageable pageable) {
		
		Page<BatchEntity> result;
		try {
			result = batchRepo.findAllByIdContains(query,pageable);	
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
			
		return result;
	}


	@Override
	public BatchEntity getMRP(String batch_no, Long item_id) {
		BatchEntity entity;
		try {
			entity=batchRepo.getMRPand(batch_no, item_id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}


	@Override
	public List<BatchEntity> getItemBatchDetails(Long id) {
		// TODO Auto-generated method stub
		List<BatchEntity> list;
		try {
			list=batchRepo.getListBatches(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return list;
	}


	@Override
	public BatchEntity getBatchDetails(String id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		BatchEntity entity;
		try {
			entity=batchRepo.getBatchDetails(id);

		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}


	@Override
	public List<String> getItemBatchNoDetails(Long id) {
		// TODO Auto-generated method stub
		List<String> list;
		try {
			list=batchRepo.getListBatchNos(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return list;

	}

}
