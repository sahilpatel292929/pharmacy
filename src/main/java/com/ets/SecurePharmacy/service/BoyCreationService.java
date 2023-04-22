package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.BoyCreationEntity;

@Service("boyService")
public interface BoyCreationService {

	public List<BoyCreationEntity> getAllCutomer();
	public BoyCreationEntity getBoyById(Long id) throws RecordNotFoundException;
	public boolean getBoyName(String bname);
	public BoyCreationEntity createOrUpdateBoy(BoyCreationEntity entity);
	public void deleteBoyById(Long id) throws RecordNotFoundException;
}
