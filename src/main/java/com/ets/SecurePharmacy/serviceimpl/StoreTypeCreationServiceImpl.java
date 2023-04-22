package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.StoreTypeCreationRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
import com.ets.SecurePharmacy.service.StoreTypeCreationService;

@Service
public class StoreTypeCreationServiceImpl implements StoreTypeCreationService {

	@Autowired
	StoreTypeCreationRepository storeRepo;

	public List<StoreTypeCreationEntity> getAllStoreTypeList() {
		List<StoreTypeCreationEntity> result;
		try {
			result = (List<StoreTypeCreationEntity>) storeRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<StoreTypeCreationEntity>();
		}
	}

	public StoreTypeCreationEntity getStoreById(Long id) throws RecordNotFoundException {
		Optional<StoreTypeCreationEntity> store;
		try {
			store = storeRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (store.isPresent()) {
			return store.get();
		} else {
			throw new RecordNotFoundException("No Store Type record exist for given id");
		}
	}

	public boolean getStoreByName(String store) {
		List<StoreTypeCreationEntity> str ;
		try {
			str = storeRepo.findByStoreTypeName(store);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (str.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public StoreTypeCreationEntity createOrUpdateStoreType(StoreTypeCreationEntity entity) {
		try {
			entity = storeRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}

	public void deleteStoreById(Long id) throws RecordNotFoundException {
		Optional<StoreTypeCreationEntity> store;
		try {
			store = storeRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (store.isPresent()) {
			storeRepo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Store record exist for given id");
		}

	}

	@Override
	public Page<StoreTypeCreationEntity> getAllStoreTypeWithPagination(int offset, int pageSize) {
		Page<StoreTypeCreationEntity> result;
		
		try {
			result = storeRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;

	}
	
	@Override
	public Page<StoreTypeCreationEntity> getSearch(String query, Pageable pageable) {
		
		Page<StoreTypeCreationEntity> result;
		try {
			result = storeRepo.findAllByStoreTypeNameContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return result;
	}

	@Override
	public StoreTypeCreationEntity updateStoreType(StoreTypeCreationEntity entity) {
		try {
			entity = storeRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}
}
