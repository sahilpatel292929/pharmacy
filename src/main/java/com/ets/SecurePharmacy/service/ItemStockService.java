package com.ets.SecurePharmacy.service;

import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;

@Service
public interface ItemStockService {

	public ItemStockEntity getItemStockDetails(Long itemId);
}
