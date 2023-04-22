package com.ets.SecurePharmacy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;
import com.ets.SecurePharmacy.service.BatchService;
import com.ets.SecurePharmacy.service.ItemStockService;
import com.ets.SecurePharmacy.service.PurchaseEntryService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	ItemStockService itmService;
	
	@Autowired 
	BatchService batchService;
	@Autowired
	PurchaseEntryService peService;
	
	@GetMapping(value="/getItemStock/{itemID}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	 public ItemStockEntity getItemStockDetails(@PathVariable Long itemID){
		 ItemStockEntity list=itmService.getItemStockDetails(itemID);
		 return list;
	 }
	
	@GetMapping(value="/getItemLatestDetails/{itemID}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<PurchaseEntryEntity> getItemLatestDetails(@PathVariable Long itemID){
		
		List<PurchaseEntryEntity> results = peService.getLastPuchaseDetails(itemID);
		return results;
	}
	
	@GetMapping(value="/getBatchDetails/{itemID}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	 public List<BatchEntity> getBatchNo(@PathVariable Long itemID){
		return batchService.getItemBatchDetails(itemID);
		 
	 }

	@GetMapping(value="/getBatchNumber/{itemID}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	 public List<BatchEntity> getBatchNumbers(@PathVariable Long itemID){
		return batchService.getItemBatchDetails(itemID);
		 
	 }
	
}
