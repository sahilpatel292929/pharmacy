package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.WarningCreationRepository;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.WarningCreationEntity;
import com.ets.SecurePharmacy.service.WarningCreationService;

@Service
public class WarningCreationServiceImpl implements WarningCreationService {

	@Autowired
	WarningCreationRepository wRepo;
	public List<WarningCreationEntity> getAllWarningList() {
		List<WarningCreationEntity> warning;
		try {
			warning=(List<WarningCreationEntity>) wRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(warning!=null)
		{
			return warning;
		}
		else {
			return new ArrayList<WarningCreationEntity>();
		}
		
	}

}
