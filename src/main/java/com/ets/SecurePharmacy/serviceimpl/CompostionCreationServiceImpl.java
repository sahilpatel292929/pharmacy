package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.CompostionCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.service.CompostionCreationService;

@Service
public class CompostionCreationServiceImpl implements CompostionCreationService {

	@Autowired
	CompostionCreationRepository comRepo;

	public List<CompostionCreationEntity> getAllCompositionList() {
		List<CompostionCreationEntity> result;
		try {
			 result= (List<CompostionCreationEntity>) comRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<CompostionCreationEntity>();
		}
	}

	public CompostionCreationEntity getCompositionById(Long id) throws RecordNotFoundException {
		
		Optional<CompostionCreationEntity> com;
		try {
			 com = comRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (com.isPresent()) {
			return com.get();
		} else {
			throw new RecordNotFoundException("No Composition record exist for given id");
		}
	}

	public boolean getCompositonByName(String com) {
		List<CompostionCreationEntity> compos;
		try {
			 compos= comRepo.findByCompName(com);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (compos.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public CompostionCreationEntity createOrUpdateCompostion(CompostionCreationEntity entity) {
		List<CompostionCreationEntity> compos;
		try {
			compos = comRepo.findByCompName(entity.getCompName());
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (compos.size() > 0) {
			throw new RecordAlreadyPresentException("Composition Name Already Created");
		} else {
			try {
				entity = comRepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
		}
		
		return entity;
	}

	public void deleteCompostionById(Long id) throws RecordNotFoundException {
		Optional<CompostionCreationEntity> comos;
		try
		{
			comos = comRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (comos.isPresent()) {
			comRepo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Compository record exist for given id");
		}
	}

	@Override
	public void createOrUpdateCompostion(List<CompostionCreationEntity> entity) {
		// TODO Auto-generated method stub
		
		try {
			comRepo.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		

	}

	@Override
	public Page<CompostionCreationEntity> getAllCompostionWithPagination(int offset, int pageSize) {
		Page<CompostionCreationEntity> result;
		try {
			result = comRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;

	}

	@Override
	public Page<CompostionCreationEntity> getSearch(String query, Pageable pageable) {
		Page<CompostionCreationEntity> result;
		try {
			result = comRepo.findAllByCompNameContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

	@Override
	public CompostionCreationEntity updateCompostion(CompostionCreationEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = comRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}

}
