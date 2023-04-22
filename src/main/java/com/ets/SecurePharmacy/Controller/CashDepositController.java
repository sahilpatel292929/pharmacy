package com.ets.SecurePharmacy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.tenant.model.CashDepositEntity;
import com.ets.SecurePharmacy.service.CashDepositService;

@RestController
@RequestMapping("/cash-deposit")
public class CashDepositController {

	@Autowired
	CashDepositService cashDepositService;
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<CashDepositEntity>  getAllOpenStockAccounts() {
		List<CashDepositEntity> listCashWD = cashDepositService.getAllCashDeposit();
		return listCashWD;
	}
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
 	public CashDepositEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException{
		CashDepositEntity listCashWD = cashDepositService.getCashDepositById(id);
	 	if(listCashWD==null)
			throw new UserNotFoundException("Cash Deposit was not Found "+ id);
	 	return listCashWD;
		
	}
	@PostMapping(value="/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CashDepositEntity> openStockCreate(
				 @RequestBody CashDepositEntity pkgObj) {
	
		CashDepositEntity entity=cashDepositService.createOrUpdateCashDeposit(pkgObj);
		return new ResponseEntity<>(entity,HttpStatus.OK);
		
	}
 

	@GetMapping("/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<CashDepositEntity>> getBatchWithPagination(@RequestParam int offset, @RequestParam int pageSize,
			@RequestParam String keyword, Pageable pageable) {

		Page<CashDepositEntity> results;
		if (keyword.length() != 0) {
			results = cashDepositService.getSearch(keyword, pageable);
		} else {
			results = cashDepositService.getAllDepositsWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(results.getSize(), results);
	}

	@PutMapping(value="/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CashDepositEntity> updateOpenStock(
				 @RequestBody CashDepositEntity pkgObj) {
	
		CashDepositEntity entity=cashDepositService.updateCashDeposit(pkgObj);
		return new ResponseEntity<CashDepositEntity>(entity,HttpStatus.OK);
		
	}
}
