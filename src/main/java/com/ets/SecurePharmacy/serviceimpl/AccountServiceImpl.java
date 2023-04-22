package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.AccountRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountEntity;
import com.ets.SecurePharmacy.service.AccountService;

@Service
@Configurable
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<AccountEntity> getAllAccounts() {
		List<AccountEntity> result;
		try {
			result= (List<AccountEntity>) accountRepository.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<AccountEntity>();
		}

	}

	@Override
	public AccountEntity getAccountsById(Long id) throws RecordNotFoundException {
		Optional<AccountEntity> accounts;
		try {
		 accounts= accountRepository.findById(id);
		}catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		if (accounts.isPresent()) {
			return accounts.get();
		} else {
			throw new RecordNotFoundException("No account record exist for given id");
		}
	}

	@Override
	public AccountEntity createOrUpdateAccounts(AccountEntity entity) {
	
		try {
			entity = accountRepository.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;

	}

	@Override
	public void createOrUpdateAccountsBulk(List<AccountEntity> entity) {
		try {
			accountRepository.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
	}

	@Override
	public void deleteAccountsById(Long id) throws RecordNotFoundException {
		
		Optional<AccountEntity> accounts;
		try {
			accounts = accountRepository.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (accounts.isPresent()) {
			accountRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No account record exist for given id");
		}
	}

}
