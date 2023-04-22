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
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.tenant.dao.AccountCreationRepository;
import com.ets.SecurePharmacy.tenant.dao.AccountLedgerRepository;
import com.ets.SecurePharmacy.tenant.dao.PaymentEntryRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.PaymentEntryEntity;
import com.ets.SecurePharmacy.service.PaymentEntryService;

@Service
public class PaymentEntryServiceImpl implements PaymentEntryService {

	@Autowired
	PaymentEntryRepository paymentEntryRepo;
	
	@Autowired
	AccountCreationRepository accountRepo;
	
	@Autowired 
	AccountLedgerRepository accountLedgerRepository;
	

	@Override
	@Transactional
	public List<PaymentEntryEntity> getAll() {
		// TODO Auto-generated method stub
		List<PaymentEntryEntity> result;
		try {
			result = (List<PaymentEntryEntity>) paymentEntryRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<PaymentEntryEntity>();
		}

	}

	@Override
	@Transactional
	public PaymentEntryEntity getById(Long id) throws RecordNotFoundException {
		Optional<PaymentEntryEntity> paymentDetails ;
		
		try {
			paymentDetails= paymentEntryRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (paymentDetails.isPresent()) {
			return paymentDetails.get();
		} else {
			throw new RecordNotFoundException("No Payment Entry record exist for given id");
		}
	}

	@Override
	@Transactional
	public PaymentEntryEntity createOrUpdate(PaymentEntryEntity entitys) {
		PaymentEntryEntity entity;
		try {
			entity = paymentEntryRepo.save(entitys);
			
			AccountLedgerEntity accountLedgerEntity = new AccountLedgerEntity();
			accountLedgerEntity.setAccountCreationEntity(entity.getAccountEntity());
			accountLedgerEntity.setTransactionId("SE" + entity.getId());
			accountLedgerEntity.setLessAmount((double)entity.getNetAmount());
			accountLedgerEntity.setCreatedDate(LocalDateTime.now());
			accountLedgerEntity.setTransactionType("Sales");
			accountLedgerEntity
					.setDescription("Add Net Amount from the Receipt Entry =" + entity.getNetAmount() + " ");

			accountLedgerRepository.save(accountLedgerEntity);

			entity.getPaymentEntryDetails().forEach(details -> {

				AccountLedgerEntity acLedgerEntity = new AccountLedgerEntity();
				acLedgerEntity.setAccountCreationEntity(details.getAccountEntity());
				acLedgerEntity.setTransactionId("SE" + entity.getId());
				acLedgerEntity.setAddAmount(details.getNetAmount());
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
	public Page<PaymentEntryEntity> getAllPaymentEntryWithPagination(int offset, int pageSize) {
		Page<PaymentEntryEntity> result;
		try {
			  result = paymentEntryRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;

	}

	@Override
	public Page<PaymentEntryEntity> getSearch(String query, Pageable pageable) {
		
		Page<PaymentEntryEntity> result;
		try {
			result = paymentEntryRepo.findAllByPaymentDateContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

}
