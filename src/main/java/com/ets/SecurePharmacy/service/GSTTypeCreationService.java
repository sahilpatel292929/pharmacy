package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.model.GSTTypeCreationEntity;

@Service
public interface GSTTypeCreationService {

	public List<GSTTypeCreationEntity> getAllGSTType();
	public Page<GSTTypeCreationEntity> getAllGSTTypeWithPagination(int offset,int pageSize);
	
}
