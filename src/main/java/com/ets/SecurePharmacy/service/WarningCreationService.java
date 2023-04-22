package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.model.WarningCreationEntity;

@Service
public interface WarningCreationService {

	public List<WarningCreationEntity> getAllWarningList();
}
