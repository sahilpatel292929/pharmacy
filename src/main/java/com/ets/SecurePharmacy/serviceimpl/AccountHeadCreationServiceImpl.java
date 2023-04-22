package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.AccountHeadCreationRepository;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountHeadCreationEntity;
import com.ets.SecurePharmacy.service.AccountHeadCreationService;

@Service
public class AccountHeadCreationServiceImpl implements AccountHeadCreationService {

	@Autowired
	AccountHeadCreationRepository accountHead;
	
	public List<AccountHeadCreationEntity> getAllAccountHead() {
		// TODO Auto-generated method stub
		List<AccountHeadCreationEntity> aacountHead;
		try {
			aacountHead=(List<AccountHeadCreationEntity>) accountHead.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(accountHead!=null)
		{
			return aacountHead;
		}
		else {
			return new ArrayList<AccountHeadCreationEntity>();
		}
		
	}
	
}
