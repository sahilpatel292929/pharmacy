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
import com.ets.SecurePharmacy.tenant.dao.CustomerLedgerRepository;
import com.ets.SecurePharmacy.tenant.dao.CustomerReceiptEntryRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerReceiptEntryEntity;
import com.ets.SecurePharmacy.service.CustomerReceiptEntryService;

@Service
public class CustomerReceiptEntryServiceImpl implements CustomerReceiptEntryService
{
	
	@Autowired
	CustomerReceiptEntryRepository customerReceiptEntryRepository;
	
	@Autowired 
	AccountLedgerRepository accountLedgerRepository;
	
	@Autowired 
	CustomerLedgerRepository customerLedgerRepository;

	@Override
	public List<CustomerReceiptEntryEntity> getAll() {
		// TODO Auto-generated method stub
		List<CustomerReceiptEntryEntity> result;
		try {
			result = (List<CustomerReceiptEntryEntity>) customerReceiptEntryRepository.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<CustomerReceiptEntryEntity>();
		}
	}

	@Override
	public CustomerReceiptEntryEntity getById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CustomerReceiptEntryEntity> receiptDetails;
		try {
			receiptDetails = customerReceiptEntryRepository.findById(id);
			
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (receiptDetails.isPresent()) {
			return receiptDetails.get();
		} else {
			throw new RecordNotFoundException("No Receipt record exist for given id");
		}
	}

	@Override
	public CustomerReceiptEntryEntity createOrUpdate(CustomerReceiptEntryEntity entitys) {
		
		CustomerReceiptEntryEntity entity;
		try {
			entity = customerReceiptEntryRepository.save(entitys);
			
			AccountLedgerEntity accountLedgerEntity = new AccountLedgerEntity();
			accountLedgerEntity.setAccountCreationEntity(entity.getAccountEntity());
			accountLedgerEntity.setTransactionId("SE" + entity.getId());
			accountLedgerEntity.setAddAmount((double)entity.getTotalAmount());
			accountLedgerEntity.setCreatedDate(LocalDateTime.now());
			accountLedgerEntity.setTransactionType("Sales");
			accountLedgerEntity
					.setDescription("Add Net Amount from the Receipt Entry =" + entity.getTotalAmount() + " ");

			accountLedgerRepository.save(accountLedgerEntity);

			entity.getCustomerReceiptDetailsEntity().forEach(details -> {

				AccountLedgerEntity acLedgerEntity = new AccountLedgerEntity();
				acLedgerEntity.setCustomerCreationEntity(details.getCustomerCreationEntity());
				acLedgerEntity.setTransactionId("SE" + entity.getId());
				acLedgerEntity.setLessAmount(details.getNetAmount());
				acLedgerEntity.setCreatedDate(LocalDateTime.now());
				acLedgerEntity.setTransactionType("Receipt Entry");
				acLedgerEntity
						.setDescription("Less Net Amount from the Receipt Entry =" + details.getNetAmount() + " ");

				accountLedgerRepository.save(acLedgerEntity);

			});

		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

	@Override
	public Page<CustomerReceiptEntryEntity> getAllReceiptEntryWithPagination(int offset, int pageSize) {
		// TODO Auto-generated method stub
		Page<CustomerReceiptEntryEntity> result;
		try {
			result  = customerReceiptEntryRepository.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

	@Override
	public Page<CustomerReceiptEntryEntity> getSearch(String query, Pageable pageable) {
		// TODO Auto-generated method stub
		
		Page<CustomerReceiptEntryEntity> result;
		try {
			result = customerReceiptEntryRepository.findAllByReferenceNumberContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

}
