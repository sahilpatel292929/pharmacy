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
import com.ets.SecurePharmacy.tenant.model.CustomerReceiptEntryEntity;
import com.ets.SecurePharmacy.service.CustomerReceiptEntryService;

@RestController
@RequestMapping("/customer-receipt")
public class CustomerReceiptEntryController {

	
	@Autowired
	CustomerReceiptEntryService customerReceiptEntryService;
	
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<CustomerReceiptEntryEntity> getAll() {

		List<CustomerReceiptEntryEntity> listAccounts = customerReceiptEntryService.getAll();
		return listAccounts;

	}

	@GetMapping("/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<CustomerReceiptEntryEntity>> getReceiptEntryWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<CustomerReceiptEntryEntity> receiptEntryWithPagination;
		if (keyword.length() != 0) {
			receiptEntryWithPagination = customerReceiptEntryService.getSearch(keyword, pageable);
		} else {
			receiptEntryWithPagination = customerReceiptEntryService.getAllReceiptEntryWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(receiptEntryWithPagination.getSize(), receiptEntryWithPagination);
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public CustomerReceiptEntryEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		CustomerReceiptEntryEntity list = customerReceiptEntryService.getById(id);
		if (list == null)
			throw new UserNotFoundException("Receipt was not Found " + id);
		return list;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CustomerReceiptEntryEntity> PackCreate(@RequestBody CustomerReceiptEntryEntity pkgObj) {
		CustomerReceiptEntryEntity entity = customerReceiptEntryService.createOrUpdate(pkgObj);
		return new ResponseEntity<CustomerReceiptEntryEntity>(entity, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CustomerReceiptEntryEntity> updatePurchase(@RequestBody CustomerReceiptEntryEntity pkgObj,
			@PathVariable long id) {

		CustomerReceiptEntryEntity entity = customerReceiptEntryService.createOrUpdate(pkgObj);
		return new ResponseEntity<CustomerReceiptEntryEntity>(entity, HttpStatus.OK);

	}
}
