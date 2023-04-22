package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.AreaCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;
import com.ets.SecurePharmacy.service.AreaCreationService;
@Service
public class AreaCreationServiceImpl implements AreaCreationService {

	@Autowired
	AreaCreationRepository areaRepo;
	
	public List<AreaCreationEntity> getAllAreaList() {
		List<AreaCreationEntity> result;
		try {
			result= (List<AreaCreationEntity>) areaRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(result.size() > 0) {
			return result;
		} 
		else {
			return new ArrayList<AreaCreationEntity>();
		}
	}

	
	public AreaCreationEntity getAreaById(Long id) throws RecordNotFoundException {
		Optional<AreaCreationEntity> results;
		try {
			results=areaRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(results.isPresent()) {
			return results.get();
		}
		else {
			throw new RecordNotFoundException("No Area record exist for given id");
		}
	}

	
	public boolean getAreaByName(String area) {
		List<AreaCreationEntity> cus;
		try {
			cus=areaRepo.findByAreaName(area);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(cus.size() > 0) {
			return true;
		}else {
		return false;
		}
	}

	
	public AreaCreationEntity createOrUpdateArea(AreaCreationEntity entity) {
		// TODO Auto-generated method stub
		List<AreaCreationEntity> results;
		try {
			results= areaRepo.findByAreaName(entity.getAreaName());
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(results.size()>0) {
			throw new RecordAlreadyPresentException("Area Name Already Created");
		}
		entity=areaRepo.save(entity);
		return entity;
	}

	
	public void deleteAreaById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<AreaCreationEntity> results;
		try {
			results=areaRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(results.isPresent()) {
			areaRepo.deleteById(id);
		}
		else {
			throw new RecordNotFoundException("No Area record exist for given id");
		}
	}


	@Override
	public void createOrUpdateAreaList(List<AreaCreationEntity> entity) {
		// TODO Auto-generated method stub
		try {
		areaRepo.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
	}
		
	
}
