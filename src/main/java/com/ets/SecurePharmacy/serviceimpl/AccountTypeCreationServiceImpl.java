package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.AccountTypeCreationRepository;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountTypeCreationEntity;
import com.ets.SecurePharmacy.service.AccountTypeCreationService;
@Service
public class AccountTypeCreationServiceImpl implements AccountTypeCreationService {

	@Autowired
	AccountTypeCreationRepository accountType;
	
	public List<AccountTypeCreationEntity> getAllAccountTypes() {
		// TODO Auto-generated method stub
		
		List<AccountTypeCreationEntity> accountTypes;
		try {
			accountTypes=(List<AccountTypeCreationEntity>) accountType.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(accountTypes!=null) {
			return accountTypes;
		}
		else {
			return new ArrayList<AccountTypeCreationEntity>();
		}
		
	}
}
