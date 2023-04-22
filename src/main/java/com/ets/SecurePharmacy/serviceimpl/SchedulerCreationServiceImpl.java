package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.SchedulerCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.service.SchedulerCreationService;

@Service
public class SchedulerCreationServiceImpl implements SchedulerCreationService {

	@Autowired
	SchedulerCreationRepository scRepo;

	public List<SchedulerCreationEntity> getAllSchedulerList() {
		List<SchedulerCreationEntity> result;
		try {
			 result = (List<SchedulerCreationEntity>) scRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<SchedulerCreationEntity>();
		}
	}

	public SchedulerCreationEntity getSchedulerById(Long id) throws RecordNotFoundException {
		Optional<SchedulerCreationEntity> sche = scRepo.findById(id);
		if (sche.isPresent()) {
			return sche.get();
		} else {
			throw new RecordNotFoundException("No Scheduler record exist for given id");
		}
	}

	public boolean getSchedulerByName(String scName) {
		List<SchedulerCreationEntity> sus = scRepo.findBySchedulerName(scName);
		if (sus.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public SchedulerCreationEntity createOrUpdateScheduler(SchedulerCreationEntity entity) {
		List<SchedulerCreationEntity> sus = scRepo.findBySchedulerName(entity.getSchedulerName());
		if (sus.size() > 0) {
			throw new RecordAlreadyPresentException("Scheduler Name Already Created");
		} else {
			try {
				entity = scRepo.save(entity);
			}
			catch(Exception e){
				throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
			}
			
		}
		
		return entity;
	}

	public void deleteSchedulerById(Long id) throws RecordNotFoundException {
		Optional<SchedulerCreationEntity> scr = scRepo.findById(id);
		if (scr.isPresent()) {
			scRepo.deleteById(id);
		} else {
			throw new RecordNotFoundException("No Scheduler record exist for given id");
		}

	}

	@Override
	public Page<SchedulerCreationEntity> getAllSchedulerWithPagination(int offset, int pageSize) {
		Page<SchedulerCreationEntity> result = scRepo.findAll(PageRequest.of(offset, pageSize));
		return result;

	}

	@Override
	public Page<SchedulerCreationEntity> getSearch(String query, Pageable pageable) {
		Page<SchedulerCreationEntity> result = scRepo.findAllBySchedulerNameContains(query, pageable);
		return result;
	}

	@Override
	public SchedulerCreationEntity updateScheduler(SchedulerCreationEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = scRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}

}
