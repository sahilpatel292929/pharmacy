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

import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.tenant.dao.StockAdjRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.StockAdjEntity;
import com.ets.SecurePharmacy.service.StockAdjService;

@Service
public class StockAdjServiceImpl implements StockAdjService {

	@Autowired
	StockAdjRepository stockAdjRepo;
	@Autowired
	ItemStockRepository itemRepo;
	@Transactional
	public List<StockAdjEntity> getAllStockAgjDetails() {
		// TODO Auto-generated method stub
		List<StockAdjEntity> result ;
		try {
			result = (List<StockAdjEntity>) stockAdjRepo.findAll();	
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
			
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<StockAdjEntity>();
		}
	}

	@Transactional
	public StockAdjEntity getStockAdjById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<StockAdjEntity> stock;
		try {
			stock=stockAdjRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(stock.isPresent()) {
			return stock.get();
		}
		else {
			throw new RecordNotFoundException("No Stock Adjustment Entry record exist for given id");
		}
	}

	@Transactional
	public StockAdjEntity createOrUpdateStockAdj(StockAdjEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = stockAdjRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}

	@Override
	public Page<StockAdjEntity> getAllStockAdjEntityWithPagination(int offset, int pageSize) {
		Page<StockAdjEntity> result;
		try {
			result=stockAdjRepo .findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;

	}

	@Override
	public Page<StockAdjEntity> getSearch(String query, Pageable pageable) {
		Page<StockAdjEntity> result;
		try {
			result = stockAdjRepo .findAllByEntryDateContains(query,pageable);	
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return result;
	}

}
