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
import com.ets.SecurePharmacy.tenant.model.ReceiptEntryEntity;
import com.ets.SecurePharmacy.service.ReceiptEntryService;

@RestController
@RequestMapping("receipt-entry")
public class ReceiptEntryController {

	@Autowired
	ReceiptEntryService reService;

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<ReceiptEntryEntity> getAll() {

		List<ReceiptEntryEntity> listAccounts = reService.getAll();
		return listAccounts;

	}

	@GetMapping("/getReceiptEntryDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<ReceiptEntryEntity>> getReceiptEntryWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<ReceiptEntryEntity> receiptEntryWithPagination;
		if (keyword.length() != 0) {
			receiptEntryWithPagination = reService.getSearch(keyword, pageable);
		} else {
			receiptEntryWithPagination = reService.getAllReceiptEntryWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(receiptEntryWithPagination.getSize(), receiptEntryWithPagination);
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ReceiptEntryEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		ReceiptEntryEntity list = reService.getById(id);
		if (list == null)
			throw new UserNotFoundException("Receipt was not Found " + id);
		return list;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<ReceiptEntryEntity> PackCreate(@RequestBody ReceiptEntryEntity pkgObj) {
		ReceiptEntryEntity entity = reService.createOrUpdate(pkgObj);
		return new ResponseEntity<ReceiptEntryEntity>(entity, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<ReceiptEntryEntity> updatePurchase(@RequestBody ReceiptEntryEntity pkgObj,
			@PathVariable long id) {

		ReceiptEntryEntity entity = reService.createOrUpdate(pkgObj);
		return new ResponseEntity<ReceiptEntryEntity>(entity, HttpStatus.OK);

	}
}
