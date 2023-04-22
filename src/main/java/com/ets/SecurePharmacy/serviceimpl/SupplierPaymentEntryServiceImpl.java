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

import com.ets.SecurePharmacy.tenant.dao.AccountLedgerRepository;
import com.ets.SecurePharmacy.tenant.dao.SupplierLedgerRepository;
import com.ets.SecurePharmacy.tenant.dao.SupplierPaymentEntryRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierPaymentEntry;
import com.ets.SecurePharmacy.service.SupplierPaymentEntryService;

@Service
public class SupplierPaymentEntryServiceImpl implements SupplierPaymentEntryService {

	@Autowired
	SupplierPaymentEntryRepository supplierPaymentEntryRepository;
	
	@Autowired
	AccountLedgerRepository accountLedgerRepository;
	
	@Autowired
	SupplierLedgerRepository supplierLedgerRepository;

	@Override
	@Transactional
	public List<SupplierPaymentEntry> getAll() {
		// TODO Auto-generated method stub

		List<SupplierPaymentEntry> result;
		try {
			result = (List<SupplierPaymentEntry>) supplierPaymentEntryRepository.findAll();
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<SupplierPaymentEntry>();
		}

	}

	@Override
	@Transactional
	public SupplierPaymentEntry getById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub

		Optional<SupplierPaymentEntry> paymentDetails;

		try {
			paymentDetails = supplierPaymentEntryRepository.findById(id);
		} catch (Exception e) {
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
	public SupplierPaymentEntry createOrUpdate(SupplierPaymentEntry entitys) {
		// TODO Auto-generated method stub
		SupplierPaymentEntry entity;
		try {
			entity = supplierPaymentEntryRepository.save(entitys);
			AccountLedgerEntity accountLedgerEntity = new AccountLedgerEntity();
			accountLedgerEntity.setAccountCreationEntity(entity.getAccountEntity());
			accountLedgerEntity.setTransactionId("SE" + entity.getId());
			accountLedgerEntity.setLessAmount((double)entity.getNetAmount());
			accountLedgerEntity.setCreatedDate(LocalDateTime.now());
			accountLedgerEntity.setTransactionType("Supplier Payment Entry");
			accountLedgerEntity
					.setDescription("Add Net Amount from the Receipt Entry =" + entity.getNetAmount() + " ");

			accountLedgerRepository.save(accountLedgerEntity);

			entity.getSupplierPaymentEntryDetails().forEach(details -> {

				SupplierLedgerEntity acLedgerEntity = new SupplierLedgerEntity();
				acLedgerEntity.setSupplierCreationEntity(details.getSupplierCreationEntity());
				acLedgerEntity.setTransactionId("SE" + entity.getId());
				acLedgerEntity.setAddAmount(details.getNetAmount());
				acLedgerEntity.setCreatedDate(LocalDateTime.now());
				acLedgerEntity.setTransactionType("Supplier Payment Entry");
				acLedgerEntity
						.setDescription("Add Net Amount from the Supplier Payment Entry =" + details.getNetAmount() + " ");

				supplierLedgerRepository.save(acLedgerEntity);
		});
			
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
		
	}

	@Override
	@Transactional
	public Page<SupplierPaymentEntry> getAllPaymentEntryWithPagination(int offset, int pageSize) {
		// TODO Auto-generated method stub
		
		Page<SupplierPaymentEntry> result;
		try {
			  result = supplierPaymentEntryRepository.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
		
	}

	@Override
	@Transactional
	public Page<SupplierPaymentEntry> getSearch(String query, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<SupplierPaymentEntry> result;
		try {
			result = supplierPaymentEntryRepository.findAllByPaymentDateContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

}
