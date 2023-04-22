package com.ets.SecurePharmacy.service;

import java.util.List;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.AccountEntity;

public interface AccountService {
	public List< AccountEntity > getAllAccounts();
	public AccountEntity getAccountsById(Long id) throws RecordNotFoundException;
	public AccountEntity createOrUpdateAccounts(AccountEntity entity);
	public void createOrUpdateAccountsBulk(List<AccountEntity> entity);
	public void deleteAccountsById(Long id) throws RecordNotFoundException;

}