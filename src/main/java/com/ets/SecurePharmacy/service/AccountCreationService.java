package com.ets.SecurePharmacy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
@Service
@Configurable
public interface AccountCreationService {
	
	public List<AccountCreationEntity> getAllAccounts();
	public AccountCreationEntity getAccountsById(Long id) throws RecordNotFoundException;
	public AccountCreationEntity createOrUpdateAccounts(AccountCreationEntity entity);
	public AccountCreationEntity updateAccounts(AccountCreationEntity entity);
	public void createOrUpdateAccountsBulk(List<AccountCreationEntity> entity);
	public void deleteAccountsById(Long id) throws RecordNotFoundException;
	public Page<AccountCreationEntity> getAllAccountsWithPagination(int offset,int pageSize);
	public Page<AccountCreationEntity> getSearch(String query, Pageable pageable);
	
	public List<AccountCreationEntity> getPaymentModes();
	public List<AccountCreationEntity> getBankNames();

}
