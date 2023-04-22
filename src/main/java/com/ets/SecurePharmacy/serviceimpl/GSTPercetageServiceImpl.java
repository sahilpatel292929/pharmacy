package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.GSTPercentageRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.GSTPercentageEntity;
import com.ets.SecurePharmacy.service.GSTPercentageService;

@Service
@Configurable
public class GSTPercetageServiceImpl implements GSTPercentageService {
	@Autowired
	GSTPercentageRepository gstpercentageRepository;

	@Override
	public List<GSTPercentageEntity> getAllGSTPercentage() {
		
		List<GSTPercentageEntity> result;
		try {
			result = (List<GSTPercentageEntity>) gstpercentageRepository.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<GSTPercentageEntity>();
		}

	}

	@Override
	public GSTPercentageEntity getGSTPercentageById(Long id) throws RecordNotFoundException {
		Optional<GSTPercentageEntity> employee;
		try {
			 employee = gstpercentageRepository.findById(id);
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No GSTPercentage record exist for given id");
		}
	}

	@Override
	public GSTPercentageEntity createOrUpdateSGSTPercentage(GSTPercentageEntity entity) {
		try {
			entity = gstpercentageRepository.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;

	}

	@Override
	public void createOrUpdateAccountsBulk(List<GSTPercentageEntity> entity) {
		
		try {
			gstpercentageRepository.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
	}

	@Override
	public void deleteGSTPercentageById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<GSTPercentageEntity> employee;
		try {
			 employee = gstpercentageRepository.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (employee.isPresent()) {
			gstpercentageRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No GST Percentage record exist for given id");
		}
	}

}
