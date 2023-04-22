package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;

@Service
public interface HsnSacCreationService {

	public List<HsnSacCreationEntity> getAllHSNSACList();
	public HsnSacCreationEntity getHsnById(Long id) throws RecordNotFoundException;
	public boolean getHsnByName(String area);
	public HsnSacCreationEntity createOrUpdateHsn(HsnSacCreationEntity entity);
	public HsnSacCreationEntity updateHsn(HsnSacCreationEntity entity);
	public void  createOrUpdateHsnList(List<HsnSacCreationEntity> entity);
	public void deleteHsnById(Long id) throws RecordNotFoundException;
	public Page<HsnSacCreationEntity> getAllHsnSacWithPagination(int offset,int pageSize);

	public Page<HsnSacCreationEntity> getSearch(String query, Pageable pageable);

}
