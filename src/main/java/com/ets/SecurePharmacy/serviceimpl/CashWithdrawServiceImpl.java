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
import com.ets.SecurePharmacy.tenant.dao.CashWithdrawRepository;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.AccountLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.CashWithdrawEntity;
import com.ets.SecurePharmacy.service.CashWithdrawService;

@Service
public class CashWithdrawServiceImpl implements CashWithdrawService {

	@Autowired
	CashWithdrawRepository cashWR;

	@Autowired 
	AccountLedgerRepository accountLedgerRepository;
	
	public List<CashWithdrawEntity> getAllCashWithdraw() {
		// TODO Auto-generated method stub
		List<CashWithdrawEntity> result;
		try {
			result = (List<CashWithdrawEntity>) cashWR.findAll();
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<CashWithdrawEntity>();
		}
	}

	public CashWithdrawEntity getCashWithDrawById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CashWithdrawEntity> openStock;
		try {
			openStock = cashWR.findById(id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (openStock.isPresent()) {
			return openStock.get();
		} else {
			throw new RecordNotFoundException("No Cash Withdraw Record exist for given id");
		}
	}

	public CashWithdrawEntity createOrUpdateCashWithdraw(CashWithdrawEntity entity) {
		// TODO Auto-generated method stub
		try {
			entity = cashWR.save(entity);
			
			AccountLedgerEntity accountLedgerEntity = new AccountLedgerEntity();
			//	accountLedgerEntity.setAccountCreationEntity(entity.getAccountEntity());
				accountLedgerEntity.setTransactionId("SE" + entity.getId());
				accountLedgerEntity.setAddAmount((double)entity.getAmount());
				accountLedgerEntity.setCreatedDate(LocalDateTime.now());
				accountLedgerEntity.setTransactionType("Cash Deposit");
				accountLedgerEntity
						.setDescription("Add Net Amount from the Receipt Entry =" + entity.getAmount() + " ");

				accountLedgerRepository.save(accountLedgerEntity);

		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return entity;
	}

	@Override
	public Page<CashWithdrawEntity> getAllDepositsWithPagination(int offset, int pageSize) {
		Page<CashWithdrawEntity> result;

		try {
			result = cashWR.findAll(PageRequest.of(offset, pageSize));
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;
	}

	@Override
	public Page<CashWithdrawEntity> getSearch(String query, Pageable pageable) {
		Page<CashWithdrawEntity> result;
		try {
			result = cashWR.findAllByBankNameContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

}
