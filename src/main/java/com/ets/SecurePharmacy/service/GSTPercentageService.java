package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.GSTPercentageEntity;

@Service
@Configurable
public interface GSTPercentageService {

	public List<GSTPercentageEntity> getAllGSTPercentage();

	public GSTPercentageEntity getGSTPercentageById(Long id) throws RecordNotFoundException;

	public GSTPercentageEntity createOrUpdateSGSTPercentage(GSTPercentageEntity entity);

	public void createOrUpdateAccountsBulk(List<GSTPercentageEntity> entity);

	public void deleteGSTPercentageById(Long id) throws RecordNotFoundException;

}
