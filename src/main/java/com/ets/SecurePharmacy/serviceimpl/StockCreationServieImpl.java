package com.ets.SecurePharmacy.serviceimpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.StockCreationRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.StockCreationEntity;
import com.ets.SecurePharmacy.service.StockCreationService;

@Service
public class StockCreationServieImpl implements StockCreationService {

	@Autowired
	StockCreationRepository stockRepo;
	
	public List<StockCreationEntity> getAllStock() {
		
		List<StockCreationEntity> result;
		try {
			result = (List<StockCreationEntity>) stockRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(result.size() > 0) {
			return result;
		} 
		else {
			return new ArrayList<StockCreationEntity>();
		}
	}

	
	public StockCreationEntity getStockById(Long id) throws RecordNotFoundException {
		Optional<StockCreationEntity> stock;
		try {
			stock =stockRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(stock.isPresent()) {
			return stock.get();
		}
		else {
			throw new RecordNotFoundException("No Stock record exist for given id");
		}
	}

	
	public boolean getStockName(String bname) {
		List<StockCreationEntity> stock;
		try {
			stock=stockRepo.findByStockName(bname);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(stock.size() > 0) {
			return true;
		}else {
		return false;
		}
	}

	
	public StockCreationEntity createOrUpdateStock(StockCreationEntity entity) {
		
		try {
			entity=stockRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

	
	public void deleteStockById(Long id) throws RecordNotFoundException {
		Optional<StockCreationEntity> stock;
		try {
			stock=stockRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(stock.isPresent()) {
			stockRepo.deleteById(id);
		}
		else {
			throw new RecordNotFoundException("No Stock record exist for given id");
		}

	}

}
