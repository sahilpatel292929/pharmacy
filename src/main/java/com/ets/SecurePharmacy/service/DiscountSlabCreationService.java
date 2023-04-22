package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;

@Service
public interface DiscountSlabCreationService {

	public List<DiscountSlabCreationEntity> getAllDisountSlabDetails();
	public DiscountSlabCreationEntity getDiscountSalbById(Long id) throws RecordNotFoundException;
	public boolean getDiscountSlabName(String dname);
	public DiscountSlabCreationEntity createOrUpdateDiscountSlab(DiscountSlabCreationEntity entity);
	public DiscountSlabCreationEntity updateDiscountSlab(DiscountSlabCreationEntity entity);

	public void createOrUpdateDiscountSlabList(List<DiscountSlabCreationEntity> entity);
	public void deleteDiscountSlabById(Long id) throws RecordNotFoundException;
	public Page<DiscountSlabCreationEntity> getAllDiscountSlabWithPagination(int offset,int pageSize);
	public Page<DiscountSlabCreationEntity> getSearch(String query, Pageable pageable);
}
