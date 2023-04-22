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
import com.ets.SecurePharmacy.tenant.model.SalesOrderEntryEntity;
import com.ets.SecurePharmacy.service.SalesOrderEntryService;

@RestController
@RequestMapping("sales-order")
public class SalesOrderController {

	@Autowired
	SalesOrderEntryService soService;

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<SalesOrderEntryEntity> getAll() {

		List<SalesOrderEntryEntity> listAccounts = soService.getAll();
		return listAccounts;

	}
	@GetMapping("/getSalesOrderEntryDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<SalesOrderEntryEntity>> getSalesOrderEntryWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<SalesOrderEntryEntity> salesOrderEntryWithPagination;
		if (keyword.length() != 0) {
			
			salesOrderEntryWithPagination = soService.getSearch(keyword, pageable);
		} else {
			salesOrderEntryWithPagination = soService.getAllSalesOrderEntryEntityWithPagination(offset, pageSize);
		}

		return new APIResponse<>(salesOrderEntryWithPagination.getSize(), salesOrderEntryWithPagination);
	}

	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public SalesOrderEntryEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		SalesOrderEntryEntity list = soService.getById(id);
		if (list == null)
			throw new UserNotFoundException("Order was not Found " + id);
		return list;

	}

	@GetMapping("/getByMobileNumber/{mobileNo}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<SalesOrderEntryEntity> getByMobileNumber(@PathVariable Long mobileNo) throws RecordNotFoundException {
		List<SalesOrderEntryEntity> list = soService.getByMobileNumber(mobileNo);
		if (list == null)
			throw new UserNotFoundException("Mobile was not Found " + mobileNo);
		return list;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SalesOrderEntryEntity> PackCreate(@RequestBody SalesOrderEntryEntity pkgObj) {
		SalesOrderEntryEntity entity= soService.createOrUpdate(pkgObj);
		return new ResponseEntity<>(entity,HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SalesOrderEntryEntity> updatePurchase(@RequestBody SalesOrderEntryEntity pkgObj, @PathVariable long id) {

		SalesOrderEntryEntity entity=soService.createOrUpdate(pkgObj);
		return new ResponseEntity<SalesOrderEntryEntity>(entity, HttpStatus.OK);

	}
	
	

}
