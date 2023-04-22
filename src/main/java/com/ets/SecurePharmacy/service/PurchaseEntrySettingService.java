package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.model.PurchaseEntrySettingEntity;

@Service
public interface PurchaseEntrySettingService {

	public List<PurchaseEntrySettingEntity> getAllPurchaseSetting();

	public PurchaseEntrySettingEntity createOrUpdateSetttings(PurchaseEntrySettingEntity entity);

	public Page<PurchaseEntrySettingEntity> getAllPurchaseEntrySettingWithPagination(int offset, int pageSize);

	public Page<PurchaseEntrySettingEntity> getSearch(String query, Pageable pageable);

}
