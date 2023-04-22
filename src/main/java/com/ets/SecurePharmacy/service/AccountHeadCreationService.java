package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.model.AccountHeadCreationEntity;

@Service
public interface AccountHeadCreationService {

	public List<AccountHeadCreationEntity> getAllAccountHead();

}
