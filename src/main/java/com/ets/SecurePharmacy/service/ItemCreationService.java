package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;

@Service
public interface ItemCreationService {

	public List<ItemCreationEntity> getAllItemList();
	public ItemCreationEntity getItemById(Long id) throws RecordNotFoundException;
	public boolean getItemName(String iname);
	public ItemCreationEntity createOrUpdateItem(ItemCreationEntity entity);
	public ItemCreationEntity updateItem(ItemCreationEntity entity);
	public void createOrUpdateItem(List<ItemCreationEntity> entity);
	public void deleteItemById(Long id) throws RecordNotFoundException;
	public Page<ItemCreationEntity> getAllItemWithPagination(int offset,int pageSize);
	public Page<ItemCreationEntity> getSearch(String query, Pageable pageable);
}
