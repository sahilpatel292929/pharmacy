package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.BestBeforeRepository;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.BestBeforeEntity;
import com.ets.SecurePharmacy.service.BestBeforeService;

@Service
public class BestBeforeServiceImpl implements BestBeforeService {

	@Autowired
	BestBeforeRepository bestRepo;
	public List<BestBeforeEntity> getAllBestBeforeTypes() {
		// TODO Auto-generated method stub
		List<BestBeforeEntity> bestTypes;
		try {
			bestTypes=(List<BestBeforeEntity>) bestRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(bestTypes!=null) {
			return bestTypes;
		}
		else {
			return new ArrayList<BestBeforeEntity>();
		}
	}

}
