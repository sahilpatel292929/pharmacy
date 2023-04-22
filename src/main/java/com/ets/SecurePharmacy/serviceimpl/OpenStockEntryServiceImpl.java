package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.tenant.dao.OpenStockEntryRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;
import com.ets.SecurePharmacy.tenant.model.OpenStockEntryEntity;
import com.ets.SecurePharmacy.service.OpenStockEntryService;

@Service
public class OpenStockEntryServiceImpl implements OpenStockEntryService {

	@Autowired
	OpenStockEntryRepository openStockRepo;

	@Autowired
	ItemStockRepository itemRepo;

	public List<OpenStockEntryEntity> getAllOpenStock() {
		
		List<OpenStockEntryEntity> result;
		try {
			result = (List<OpenStockEntryEntity>) openStockRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		System.out.println("Service iMpla");
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<OpenStockEntryEntity>();
		}
	}

	public OpenStockEntryEntity getOpenStockeById(Long id) throws RecordNotFoundException {
		Optional<OpenStockEntryEntity> openStock ;
		
		try {
			openStock= openStockRepo.findById(id);
			
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (openStock.isPresent()) {
			return openStock.get();
		} else {
			throw new RecordNotFoundException("No OpenStock Record exist for given id");
		}
	}

	public OpenStockEntryEntity createOrUpdateOpenStock(OpenStockEntryEntity entity) {
		
		try {
			entity = openStockRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		ItemStockEntity itmEntity = itemRepo.findByItemEntityId(1l);
		if (itmEntity != null) {

		}

		return entity;
	}

	@Override
	public Page<OpenStockEntryEntity> getAllOpenStockEntryWithPagination(int offset, int pageSize) {
		Page<OpenStockEntryEntity> result;
		try {
			result = openStockRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

	@Override
	public Page<OpenStockEntryEntity> getSearch(String query, Pageable pageable) {
		Page<OpenStockEntryEntity> result;
		try {
			result = openStockRepo.findAllByOrderDateContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return result;
	}

}
