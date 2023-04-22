package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.model.BestBeforeEntity;

@Service
public interface BestBeforeService {

	public List<BestBeforeEntity> getAllBestBeforeTypes();
}
