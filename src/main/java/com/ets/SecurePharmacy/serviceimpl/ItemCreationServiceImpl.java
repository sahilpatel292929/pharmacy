package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.ItemCreationRepository;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.service.ItemCreationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ItemCreationServiceImpl implements ItemCreationService {

	@Autowired
	ItemCreationRepository itemRepo;
	
	public List<ItemCreationEntity> getAllItemList() {
		List<ItemCreationEntity> result ;
		try {
			result = (List<ItemCreationEntity>) itemRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
				
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<ItemCreationEntity>();
		}

	}

	public List<ItemCreationEntity> getAllItemSortedList(String field) {
		List<ItemCreationEntity> result;
		try {
			result = (List<ItemCreationEntity>) itemRepo.findAll(Sort.by(Sort.Direction.ASC,field));	
			
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
			
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<ItemCreationEntity>();
		}

	}
	public Page<ItemCreationEntity> getAllItemWithPagination(int offset,int pageSize) {
			Page<ItemCreationEntity> result;
		try {
			result = itemRepo.findAll(PageRequest.of(offset, pageSize));		
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		return result;

	}
	public ItemCreationEntity getItemById(Long id) throws RecordNotFoundException {
		Optional<ItemCreationEntity> items;
		try {
			items  = itemRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(items.isPresent()) {
			return items.get();
		} else {
			throw new RecordNotFoundException("No Item record exist for given id");
		}
	}

	public boolean getItemName(String iname) {
		List<ItemCreationEntity> item;
		try {
			item=itemRepo.findByItemName(iname);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(item.size() > 0) {
			return true;
		}else {
		return false;
		}
	}

	public ItemCreationEntity createOrUpdateItem(ItemCreationEntity entity) {
		List<ItemCreationEntity> list;
		try {
			
			list=itemRepo.findByItemName(entity.getItemName());
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(list.size()>0) {
			throw new RecordAlreadyPresentException("Item Name Already Created");
		}
		else
		{
			entity=itemRepo.save(entity);
		}
		
		return entity;
	}

	public void deleteItemById(Long id) throws RecordNotFoundException {
		Optional<ItemCreationEntity> itm;
		try
		{
			itm=itemRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(itm.isPresent()) {
			itemRepo.deleteById(id);
		}
		else {
			throw new RecordNotFoundException("No Item record exist for given id");
		}
	}

	@Override
	public void createOrUpdateItem(List<ItemCreationEntity> entity) {
		// TODO Auto-generated method stub
		
		try{
			itemRepo.saveAll(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
	}

	@Override
	public Page<ItemCreationEntity> getSearch(String query, Pageable pageable) {
		Page<ItemCreationEntity> result;
		try {
			result  =  itemRepo.findAllByItemNameContains(query,pageable);
			
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
				
		return result;
	}

	@Override
	public ItemCreationEntity updateItem(ItemCreationEntity entity) {
		
		try {
			entity=itemRepo.save(entity);
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return entity;
	}

}
