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
import com.ets.SecurePharmacy.tenant.dao.ReceiptEntryRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.ReceiptEntryEntity;
import com.ets.SecurePharmacy.service.ReceiptEntryService;

@Service
public class ReceiptEntryServiceImpl implements ReceiptEntryService {

	@Autowired
	ReceiptEntryRepository reRepo;

	@Autowired
	AccountLedgerRepository accountLedgerRepository;

	@Override
	@Transactional
	public List<ReceiptEntryEntity> getAll() {
		List<ReceiptEntryEntity> result;
		try {
			result = (List<ReceiptEntryEntity>) reRepo.findAll();
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<ReceiptEntryEntity>();
		}
	}

	@Override
	@Transactional
	public ReceiptEntryEntity getById(Long id) throws RecordNotFoundException {
		Optional<ReceiptEntryEntity> receiptDetails;
		try {
			receiptDetails = reRepo.findById(id);

		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (receiptDetails.isPresent()) {
			return receiptDetails.get();
		} else {
			throw new RecordNotFoundException("No Receipt record exist for given id");
		}
	}

	@Override
	@Transactional
	public ReceiptEntryEntity createOrUpdate(ReceiptEntryEntity receiptEntry) {

		ReceiptEntryEntity entity;
		try {

			entity = reRepo.save(receiptEntry);

			AccountLedgerEntity accountLedgerEntity = new AccountLedgerEntity();
			accountLedgerEntity.setAccountCreationEntity(entity.getAccountEntity());
			accountLedgerEntity.setTransactionId("SE" + entity.getId());
			accountLedgerEntity.setAddAmount(entity.getTotalAmount());
			accountLedgerEntity.setCreatedDate(LocalDateTime.now());
			accountLedgerEntity.setTransactionType("Sales");
			accountLedgerEntity
					.setDescription("Add Net Amount from the Receipt Entry =" + entity.getTotalAmount() + " ");

			accountLedgerRepository.save(accountLedgerEntity);

			entity.getReceiptDetailsEntity().forEach(details -> {

				AccountLedgerEntity acLedgerEntity = new AccountLedgerEntity();
				acLedgerEntity.setAccountCreationEntity(details.getAccountEntity());
				acLedgerEntity.setTransactionId("SE" + entity.getId());
				acLedgerEntity.setLessAmount(details.getNetAmount());
				acLedgerEntity.setCreatedDate(LocalDateTime.now());
				acLedgerEntity.setTransactionType("Receipt Entry");
				acLedgerEntity
						.setDescription("Less Net Amount from the Receipt Entry =" + details.getNetAmount() + " ");
				;

				accountLedgerRepository.save(accountLedgerEntity);

			});

		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return entity;
	}

	@Override
	public Page<ReceiptEntryEntity> getAllReceiptEntryWithPagination(int offset, int pageSize) {
		Page<ReceiptEntryEntity> result;
		try {
			result = reRepo.findAll(PageRequest.of(offset, pageSize));
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;
	}

	@Override
	public Page<ReceiptEntryEntity> getSearch(String query, Pageable pageable) {

		Page<ReceiptEntryEntity> result;
		try {
			result = reRepo.findAllByReferenceNumberContains(query, pageable);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;
	}

}
