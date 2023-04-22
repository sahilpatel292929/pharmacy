package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.tenant.dao.ShortageEntryRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.ShortageEntryEntity;
import com.ets.SecurePharmacy.service.ShortageEntryService;
@Service
public class ShortageEntryServiceImpl implements ShortageEntryService {

	@Autowired
	ShortageEntryRepository shortRepo;
	@Transactional
	public List<ShortageEntryEntity> getAllShortage() {
		// TODO Auto-generated method stub
		List<ShortageEntryEntity> result;
		try {
			result = (List<ShortageEntryEntity>) shortRepo.findAll();	
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<ShortageEntryEntity>();
		}

		
	}

	@Transactional
	public ShortageEntryEntity getShortageById(Long id) throws RecordNotFoundException {
		Optional<ShortageEntryEntity> shotage;
		try {
			shotage=shortRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(shotage.isPresent()) {
			return shotage.get();
		}
		else {
			throw new RecordNotFoundException("No Shortage Entry record exist for given id");
		}
	}

	@Transactional
	public ShortageEntryEntity createOrUpdateShortage(ShortageEntryEntity entity) {
		
		try {
			entity = shortRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

	@Override
	public Page<ShortageEntryEntity> getAllShortageEntryWithPagination(int offset, int pageSize) {
		Page<ShortageEntryEntity> result ;
		try {
			result= shortRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return result;

	}
	
	@Override
	public Page<ShortageEntryEntity> getSearch(String query, Pageable pageable) {
		Page<ShortageEntryEntity> result;
		try {
			 result =  shortRepo.findAllByEntryDateContains(query,pageable);	
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
			
		return result;
	}

}
