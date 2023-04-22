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

import com.ets.SecurePharmacy.tenant.dao.CounterSaleRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.CounterSaleEntity;
import com.ets.SecurePharmacy.service.CounterSaleService;

@Service
public class CounterSaleServiceImpl implements CounterSaleService {

	@Autowired
	CounterSaleRepository counterRepo;
	@Transactional
	public List<CounterSaleEntity> getAllCounterSales() {
		// TODO Auto-generated method stub
		List<CounterSaleEntity> result;
		try {
			result = (List<CounterSaleEntity>) counterRepo.findAll();	
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<CounterSaleEntity>();
		}
	}
	@Transactional
	public CounterSaleEntity getCounterSaleById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CounterSaleEntity> openStock;
		try
		{
			openStock =counterRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(openStock.isPresent()) {
			return openStock.get();
		}
		else {
			throw new RecordNotFoundException("No Counter Sale Record exist for given id");
		}
	}

	@Transactional
	public CounterSaleEntity createOrUpdateCounterSale(CounterSaleEntity entity) {
		// TODO Auto-generated method stub
		
		try {
			entity = counterRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}
	@Override
	public Page<CounterSaleEntity> getAllCounterSaleWithPagination(int offset, int pageSize) {
		Page<CounterSaleEntity> result;
		try {
			result =counterRepo.findAll(PageRequest.of(offset, pageSize));
			
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;	
	}
	@Override
	public Page<CounterSaleEntity> getSearch(String query, Pageable pageable) {
		Page<CounterSaleEntity> result;
		try {
			result =counterRepo.findAllByEntryDateContains(query,pageable);	
			
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
			
		return result;
	}

}
