package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.tenant.dao.OderGenerationEntryRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.OrderGenerationEntity;
import com.ets.SecurePharmacy.service.OrderGenerationEntryService;

@Service
public class OrderGeneratioEntryServiceImpl implements OrderGenerationEntryService {

	@Autowired
	OderGenerationEntryRepository ogRepo;

	@Override
	@Transactional
	public List<OrderGenerationEntity> getAll() {
		List<OrderGenerationEntity> result; 
		try {
			result = (List<OrderGenerationEntity>) ogRepo.findAll();
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<OrderGenerationEntity>();
		}
	}

	@Override
	@Transactional
	public OrderGenerationEntity getById(Long id) throws RecordNotFoundException {
		Optional<OrderGenerationEntity> orderDetails;
		try {
			orderDetails = ogRepo.findById(id);
			
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (orderDetails.isPresent()) {
			return orderDetails.get();
		} else {
			throw new RecordNotFoundException("No Order record exist for given id");
		}
	}

	@Override
	@Transactional
	public OrderGenerationEntity createOrUpdate(OrderGenerationEntity entity) {
		try {
			entity = ogRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		return entity;
	}

}
