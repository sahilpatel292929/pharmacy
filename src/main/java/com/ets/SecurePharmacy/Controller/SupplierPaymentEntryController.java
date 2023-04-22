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
import com.ets.SecurePharmacy.tenant.model.SupplierPaymentEntry;
import com.ets.SecurePharmacy.service.SupplierPaymentEntryService;

@RestController
@RequestMapping("/supplier-payment-entry")
public class SupplierPaymentEntryController {

	@Autowired
	SupplierPaymentEntryService supplierPaymentEntryService;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<SupplierPaymentEntry> getAll() {

		List<SupplierPaymentEntry> listAccounts = supplierPaymentEntryService.getAll();
		return listAccounts;

	}

	@GetMapping("/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<SupplierPaymentEntry>> getPaymentEntryWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<SupplierPaymentEntry> paymentEntryWithPagination;
		if (keyword.length() != 0) {
			paymentEntryWithPagination = supplierPaymentEntryService.getSearch(keyword, pageable);
		} else {
			paymentEntryWithPagination = supplierPaymentEntryService.getAllPaymentEntryWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(paymentEntryWithPagination.getSize(), paymentEntryWithPagination);
	}
	
	

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public SupplierPaymentEntry getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		SupplierPaymentEntry listAccounts = supplierPaymentEntryService.getById(id);
		if (listAccounts == null)
			throw new UserNotFoundException("Purchase Invoice was not Found " + id);
		return listAccounts;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SupplierPaymentEntry> PackCreate(@RequestBody SupplierPaymentEntry pkgObj) {
		SupplierPaymentEntry entity = supplierPaymentEntryService.createOrUpdate(pkgObj);
		return new ResponseEntity<SupplierPaymentEntry>(entity, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SupplierPaymentEntry> updatePurchase(@RequestBody SupplierPaymentEntry pkgObj,
			@PathVariable long id) {

		SupplierPaymentEntry entity = supplierPaymentEntryService.createOrUpdate(pkgObj);
		return new ResponseEntity<SupplierPaymentEntry>(entity, HttpStatus.OK);

	}
}
