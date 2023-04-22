package com.ets.SecurePharmacy.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;
import com.ets.SecurePharmacy.service.ItemStockService;

@Service
public class ItemStockServiceImpl implements ItemStockService {

	@Autowired
	ItemStockRepository itemStockRepo;
	
	@Override
	public ItemStockEntity getItemStockDetails(Long itemId) {
		// TODO Auto-generated method stub
		System.out.println("Localdate time now " + LocalDateTime.now());
		ItemStockEntity result;
		
		try {
			result = itemStockRepo.findByItemEntityId(itemId);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

}
