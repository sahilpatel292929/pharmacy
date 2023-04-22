package com.ets.SecurePharmacy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.tenant.model.AccountEntity;

import com.ets.SecurePharmacy.service.AccountService;

@RestController
@RequestMapping("/account-service")
public class AccountController {
	@Autowired
	AccountService accountService;
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('User') " + "|| hasRole('Admin') ")
	public List<AccountEntity>  getAllOpenStockAccounts() {
		List<AccountEntity> listCashWD = accountService.getAllAccounts();
		return listCashWD;
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('User') " + "|| hasRole('Admin') ")
 	public AccountEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException{
		AccountEntity listCashWD = accountService.getAccountsById(id);
	 	if(listCashWD==null)
			throw new UserNotFoundException("Account was not Found "+ id);
	 	return listCashWD;
		
	}
	
	@PostMapping(value="/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<AccountEntity> openStockCreate(
				 @RequestBody AccountEntity pkgObj) {

		AccountEntity entity = accountService.createOrUpdateAccounts(pkgObj);
		return new ResponseEntity<AccountEntity>(entity,HttpStatus.OK);
		
	}
 
	@PutMapping(value="/update{id}")
	@PreAuthorize("hasRole('User') " + "|| hasRole('Admin') ")
	public ResponseEntity<AccountEntity> updateOpenStock(
				 @RequestBody AccountEntity pkgObj) {
	
		 AccountEntity entity = accountService.createOrUpdateAccounts(pkgObj);
		return new ResponseEntity<AccountEntity>(entity,HttpStatus.OK);
		
	}
	}
