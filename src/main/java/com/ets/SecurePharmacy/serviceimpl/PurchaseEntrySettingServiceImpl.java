package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.PurchaseEntrySettingRepo;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntrySettingEntity;
import com.ets.SecurePharmacy.service.PurchaseEntrySettingService;

@Service
public class PurchaseEntrySettingServiceImpl implements PurchaseEntrySettingService {

	@Autowired
	PurchaseEntrySettingRepo pesRepository;
	
	public List<PurchaseEntrySettingEntity> getAllPurchaseSetting() {
		List<PurchaseEntrySettingEntity> result;
		try {
			result = (List<PurchaseEntrySettingEntity>) pesRepository.findAll();	
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
			
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<PurchaseEntrySettingEntity>();
		}
	}

	public PurchaseEntrySettingEntity createOrUpdateSetttings(PurchaseEntrySettingEntity entity) {
		
		try {
			entity = pesRepository.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

	@Override
	public Page<PurchaseEntrySettingEntity> getAllPurchaseEntrySettingWithPagination(int offset, int pageSize) {
		Page<PurchaseEntrySettingEntity> result =pesRepository.findAll(PageRequest.of(offset, pageSize));
		return result;

	}

	@Override
	public Page<PurchaseEntrySettingEntity> getSearch(String query, Pageable pageable) {
		Page<PurchaseEntrySettingEntity> result =  pesRepository.findAllBySettingNameContains(query,pageable);		
		return result;
	}
	
	
}
