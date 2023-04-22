package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.ManufacturerCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.service.ManufacturerCreationService;

@Service
public class ManufacturerCreationServiceImpl implements ManufacturerCreationService {

	@Autowired
	ManufacturerCreationRepository mRepo;

	public List<ManufacturerCreationEntity> getAllManufactureList() {
		List<ManufacturerCreationEntity> result;
		try {
			result = (List<ManufacturerCreationEntity>) mRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<ManufacturerCreationEntity>();
		}
	}

	public ManufacturerCreationEntity getManufactureById(Long id) throws RecordNotFoundException {
		Optional<ManufacturerCreationEntity> manu;
		try {
			manu = mRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		if (manu.isPresent()) {
			return manu.get();
		} else {
			throw new RecordNotFoundException("No Manufacture record exist for given id");
		}
	}

	public boolean getManufactureByName(String mName) {
		
		List<ManufacturerCreationEntity> mList;
		try {
			mList = mRepo.findByManufacturerName(mName);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (mList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public ManufacturerCreationEntity createOrUpdateManufacture(ManufacturerCreationEntity entity) {
		List<ManufacturerCreationEntity> mList;
		try {
			mList = mRepo.findByManufacturerName(entity.getManufacturerName());
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (mList.size() > 0) {
			throw new RecordAlreadyPresentException("Manufacture Name Already Created");
		} else {
			try {
				entity = mRepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
		}
		
		return entity;
	}

	public void deleteManufactureById(Long id) throws RecordNotFoundException {
		Optional<ManufacturerCreationEntity> mList ;
		try {
			mList = mRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (mList.isPresent()) {
			mRepo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Manufacture record exist for given id");
		}

	}

	@Override
	public void createOrUpdateManufactureList(List<ManufacturerCreationEntity> entity) {
		// TODO Auto-generated method stub
		try {
			mRepo.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
	}

	@Override
	public Page<ManufacturerCreationEntity> getAllManufacturerWithPagination(int offset, int pageSize) {
		Page<ManufacturerCreationEntity> result;
		try {
			
			result = mRepo.findAll(PageRequest.of(offset, pageSize));
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;

	}

	@Override
	public Page<ManufacturerCreationEntity> getSearch(String query, Pageable pageable) {
		
		Page<ManufacturerCreationEntity> result;
		try {
			result = mRepo.findAllByManufacturerNameContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

	@Override
	public ManufacturerCreationEntity updateManufacture(ManufacturerCreationEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = mRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}

}
