package com.ets.SecurePharmacy.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.tenant.dao.SalesOrderEntryRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.SalesOrderEntryEntity;
import com.ets.SecurePharmacy.service.SalesOrderEntryService;

@Service
public class SalesOrderServiceImpl implements SalesOrderEntryService {

	@Autowired
	SalesOrderEntryRepository soRepo;
	@Override
	@Transactional
	public List<SalesOrderEntryEntity> getAll() {
		List<SalesOrderEntryEntity> result;
		try {
			result = (List<SalesOrderEntryEntity>) soRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
				
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<SalesOrderEntryEntity>();
		}
	}
	

	@Override
	@Transactional
	public SalesOrderEntryEntity getById(Long id) throws RecordNotFoundException {
		Optional<SalesOrderEntryEntity> salesDetails;
		try {
			salesDetails=soRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(salesDetails.isPresent()) {
			return salesDetails.get();
		}
		else {
			throw new RecordNotFoundException("No Sales Order record exist for given id");
		}
	}

	@Override
	@Transactional
	public SalesOrderEntryEntity createOrUpdate(SalesOrderEntryEntity entity) {
		
		try {
			entity = soRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

	@Override
	public List<SalesOrderEntryEntity> getByMobileNumber(Long mobileNo) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		List<SalesOrderEntryEntity> salesDetails;
		try {
			salesDetails=soRepo.findByCustomerMobileNo(mobileNo);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		System.out.println("local date curerent"+LocalDateTime.now());
		if(salesDetails.size() > 0) {
			return salesDetails;
		} else {
			return new ArrayList<SalesOrderEntryEntity>();
		}
		
	}


	@Override
	public Page<SalesOrderEntryEntity> getAllSalesOrderEntryEntityWithPagination(int offset, int pageSize) {
		Page<SalesOrderEntryEntity> result;
		try {
			result = soRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}
	
	@Override
	public Page<SalesOrderEntryEntity> getSearch(String query, Pageable pageable) {
		boolean numeric = true;
		Page<SalesOrderEntryEntity> result;
		 Long num = 1l ;
		try {
	            num = Long.parseLong(query);
	            
	        } catch (NumberFormatException e) {
	            numeric = false;
	        }
			try {
					 if(numeric) {
		        	 result =  soRepo.findAllByCustomerMobileNo(num,pageable);	
		        }
		        else {
		        	result =  soRepo. findAllByCustomerNameContains(query,pageable);
		        }
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
				
		return result;
	}

}
