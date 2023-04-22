package com.ets.SecurePharmacy.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.model.AccountTypeCreationEntity;

@Service
public interface AccountTypeCreationService {

	public List<AccountTypeCreationEntity> getAllAccountTypes();

}
