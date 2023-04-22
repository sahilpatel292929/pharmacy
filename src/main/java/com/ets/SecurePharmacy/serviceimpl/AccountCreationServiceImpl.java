package com.ets.SecurePharmacy.serviceimpl;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.tenant.dao.AccountCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.service.AccountCreationService;

@Service
@Configurable
public class AccountCreationServiceImpl implements AccountCreationService {

	@Autowired
	AccountCreationRepository accountRepository;

	@Transactional
	public List<AccountCreationEntity> getAllAccounts() {
		// TODO Auto-generated method stub
		List<AccountCreationEntity> result;
		try {
			result = (List<AccountCreationEntity>) accountRepository.findAll();
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<AccountCreationEntity>();
		}

	}

	@Transactional
	public AccountCreationEntity getAccountsById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<AccountCreationEntity> employee;
		try {
			employee = accountRepository.findById(id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	@Transactional
	public AccountCreationEntity createOrUpdateAccounts(AccountCreationEntity entity) {
		// TODO Auto-generated method stub

		/*
		 * entity.setCreated_date(new LocalDate()); entity.setCreated_by(0);
		 * entity.setUpdated_date(new LacalDate()); entity.setUpdated_by(0);
		 */
		List<AccountCreationEntity> results;
		try {
			results = accountRepository.findByActName(entity.getActName());
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (results.size() > 0) {
			throw new RecordAlreadyPresentException("Account Name Already Created");
		} else {
			entity = accountRepository.save(entity);
		}

		return entity;

	}

	@Transactional
	public void createOrUpdateAccountsBulk(List<AccountCreationEntity> entity) {
		// TODO Auto-generated method stub
		try {
			accountRepository.saveAll(entity);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		// return entity;

	}

	@Transactional
	public void deleteAccountsById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<AccountCreationEntity> employee;
		try {
			employee = accountRepository.findById(id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (employee.isPresent()) {
			accountRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	@Override
	public Page<AccountCreationEntity> getAllAccountsWithPagination(int offset, int pageSize) {
		Page<AccountCreationEntity> result;
		try {
			result = accountRepository.findAll(PageRequest.of(offset, pageSize));

		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return result;

	}

	@Override
	public Page<AccountCreationEntity> getSearch(String query, Pageable pageable) {
		Page<AccountCreationEntity> result;
		try {
			result = accountRepository.findAllByActNameContains(query, pageable);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;
	}

	@Override
	public AccountCreationEntity updateAccounts(AccountCreationEntity entity) {
		try {
			entity = accountRepository.save(entity);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}

	@Override
	public List<AccountCreationEntity> getPaymentModes() {
		// TODO Auto-generated method stub
		List<AccountCreationEntity> results;

		try {
			results = accountRepository.paymentModes();
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return results;
	}

	@Override
	public List<AccountCreationEntity> getBankNames() {
		// TODO Auto-generated method stub

		List<AccountCreationEntity> results;

		try {
			results = accountRepository.bankNames();
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return results;
		
	}

}
