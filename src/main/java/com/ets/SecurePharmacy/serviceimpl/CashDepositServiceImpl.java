package com.ets.SecurePharmacy.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.AccountLedgerRepository;
import com.ets.SecurePharmacy.tenant.dao.CashDepositRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.CashDepositEntity;
import com.ets.SecurePharmacy.service.CashDepositService;

@Service
public class CashDepositServiceImpl implements CashDepositService {

	@Autowired
	CashDepositRepository cashDepositRepository;
	@Autowired 
	AccountLedgerRepository accountLedgerRepository;
	@Override
	public List<CashDepositEntity> getAllCashDeposit() {
		// TODO Auto-generated method stub
		List<CashDepositEntity> result;
		try {
			result =  cashDepositRepository.findAll();	
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<CashDepositEntity>();
		}
		
	}

	@Override
	public CashDepositEntity getCashDepositById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CashDepositEntity> results;
		try {
			results=cashDepositRepository.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(results.isPresent()) {
			return results.get();
		}
		else {
			throw new RecordNotFoundException("No Cash Deposit Record exist for given id");
		}
	}

	@Override
	public CashDepositEntity createOrUpdateCashDeposit(CashDepositEntity entity) {
		// TODO Auto-generated method stub
		
		try {
			entity = cashDepositRepository.save(entity);
			AccountLedgerEntity accountLedgerEntity = new AccountLedgerEntity();
		//	accountLedgerEntity.setAccountCreationEntity(entity.getAccountEntity());
			accountLedgerEntity.setTransactionId("SE" + entity.getId());
			accountLedgerEntity.setAddAmount((double)entity.getAmount());
			accountLedgerEntity.setCreatedDate(LocalDateTime.now());
			accountLedgerEntity.setTransactionType("Cash Deposit");
			accountLedgerEntity
					.setDescription("Add Net Amount from the Receipt Entry =" + entity.getAmount() + " ");

			accountLedgerRepository.save(accountLedgerEntity);

		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
		
	}

	@Override
	public CashDepositEntity updateCashDeposit(CashDepositEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = cashDepositRepository.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
		
	}

	@Override
	public Page<CashDepositEntity> getAllDepositsWithPagination(int offset, int pageSize) {
		Page<CashDepositEntity> result;
		
		try {
			result = cashDepositRepository.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

	@Override
	public Page<CashDepositEntity> getSearch(String query, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CashDepositEntity> result;
		try {
			result = cashDepositRepository.findAllByBankNameContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

}
