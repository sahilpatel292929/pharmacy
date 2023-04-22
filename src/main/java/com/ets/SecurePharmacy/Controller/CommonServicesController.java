package com.ets.SecurePharmacy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.service.AccountCreationService;
import com.ets.SecurePharmacy.service.BatchService;

@RestController
@RequestMapping("/common")
public class CommonServicesController {

	@Autowired 
	AccountCreationService accountCreationService;
	
	@Autowired
	BatchService batchService;
	
	@GetMapping(value="/payment_modes")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<List<AccountCreationEntity>>  getPaymentModes(){
		
		List<AccountCreationEntity> results=accountCreationService.getPaymentModes();
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@GetMapping(value="/bank_names")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<List<AccountCreationEntity>>  getBankNames(){
		
		List<AccountCreationEntity> results=accountCreationService.getBankNames();
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@GetMapping(value="/get_MRP/{batch}/{item_id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<BatchEntity> getMRP(@PathVariable String batch,@PathVariable Long item_id ){
		BatchEntity results= batchService.getMRP(batch, item_id);
		
		return  new ResponseEntity<>(results, HttpStatus.OK);
	}
	
}
