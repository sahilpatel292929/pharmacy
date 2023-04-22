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
import com.ets.SecurePharmacy.tenant.model.CashWithdrawEntity;
import com.ets.SecurePharmacy.service.CashWithdrawService;

@RestController
@RequestMapping("/cash-withdraw")
public class CashWithdrawController {

	@Autowired
	CashWithdrawService cashWS;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<CashWithdrawEntity>  getAllOpenStockAccounts() {
		List<CashWithdrawEntity> listCashWD = cashWS.getAllCashWithdraw();
		return listCashWD;
	}
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
 	public CashWithdrawEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException{
		CashWithdrawEntity listCashWD = cashWS.getCashWithDrawById(id);
	 	if(listCashWD==null)
			throw new UserNotFoundException("Cash Withdraw was not Found "+ id);
	 	return listCashWD;
		
	}
	@PostMapping(value="/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CashWithdrawEntity> openStockCreate(
				 @RequestBody CashWithdrawEntity pkgObj) {
	
		CashWithdrawEntity entity=cashWS.createOrUpdateCashWithdraw(pkgObj);
		return new ResponseEntity<>(entity,HttpStatus.OK);
		
	}
 
	@PutMapping(value="/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CashWithdrawEntity> updateOpenStock(
				 @RequestBody CashWithdrawEntity pkgObj) {
	
		CashWithdrawEntity entity=cashWS.createOrUpdateCashWithdraw(pkgObj);
		return new ResponseEntity<CashWithdrawEntity>(entity,HttpStatus.OK);
		
	}
	

	@GetMapping("/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<CashWithdrawEntity>> getBatchWithPagination(@RequestParam int offset, @RequestParam int pageSize,
			@RequestParam String keyword, Pageable pageable) {

		Page<CashWithdrawEntity> results;
		if (keyword.length() != 0) {
			results = cashWS.getSearch(keyword, pageable);
		} else {
			results = cashWS.getAllDepositsWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(results.getSize(), results);
	}
}
