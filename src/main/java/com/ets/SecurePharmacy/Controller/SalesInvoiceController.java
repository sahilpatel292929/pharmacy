package com.ets.SecurePharmacy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.DTO.SalesInvoiceDTO;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceDraftEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntity;
import com.ets.SecurePharmacy.service.SalesInvoiceService;

@RestController
@RequestMapping("/SalesInvoice")
public class SalesInvoiceController {

	@Autowired
	SalesInvoiceService saleInvService;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<SalesInvoiceEntity>  getAllOpenStockAccounts() {
		List<SalesInvoiceEntity> listOpensStockAccounts = saleInvService.getAlStockInvoice();
		return listOpensStockAccounts;
	}
	@GetMapping("/getSalesInvoiceDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<SalesInvoiceEntity>> getSalesInvoiceWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<SalesInvoiceEntity> salesInvoiceWithPagination;
		if (keyword.length() != 0) {
			salesInvoiceWithPagination = saleInvService.getSearch(keyword, pageable);
		} else {
			salesInvoiceWithPagination = saleInvService.getAllSalesInvoiceWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(salesInvoiceWithPagination.getSize(), salesInvoiceWithPagination);
	}
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
 	public SalesInvoiceEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException{
		SalesInvoiceEntity listOpenStockAccounts = saleInvService.getStockInvoiceById(id);
	 	if(listOpenStockAccounts==null)
			throw new UserNotFoundException("Sales Invoice was not Found "+ id);
	 	return listOpenStockAccounts;
		
	}
	@PostMapping(value="/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SalesInvoiceDTO> openStockCreate(
				 @RequestBody SalesInvoiceDTO pkgObj) {
	
		SalesInvoiceDTO entity=saleInvService.createOrUpdateStockInvoice(pkgObj);
		return new ResponseEntity<SalesInvoiceDTO>(entity,HttpStatus.OK);
		
	}
 
	@PutMapping(value="/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SalesInvoiceDTO> updateOpenStock(
				 @RequestBody SalesInvoiceDTO pkgObj) {
	
		SalesInvoiceDTO entity=saleInvService.createOrUpdateStockInvoice(pkgObj);
		return new ResponseEntity<SalesInvoiceDTO>(entity,HttpStatus.OK);
		
	}
	

	@PostMapping(value="/createDraft")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SalesInvoiceDraftEntity> salesInvoicesDraft(
			 @RequestBody SalesInvoiceDTO pkgObj) {
	
		SalesInvoiceDraftEntity entity=saleInvService.createOrUpdateStockInvoiceDraft(pkgObj);
		return new ResponseEntity<SalesInvoiceDraftEntity>(entity,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAll-draft-details")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<SalesInvoiceDraftEntity> getAllSalesInvoiceDraft(
			 @RequestBody SalesInvoiceDTO pkgObj) {
	
		SalesInvoiceDraftEntity entity=saleInvService.createOrUpdateStockInvoiceDraft(pkgObj);
		return new ResponseEntity<SalesInvoiceDraftEntity>(entity,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete-draft-byId")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public Object deleteDraftById(@RequestParam Long id) {
		return saleInvService.deleteDraftById(id);
	}
}
