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
import com.ets.SecurePharmacy.tenant.model.CounterSaleEntity;
import com.ets.SecurePharmacy.service.CounterSaleService;

@RestController
@RequestMapping("/counter-sale")
public class CounterSaleController {

	@Autowired
	CounterSaleService counterService;
	

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<CounterSaleEntity>  getAllOpenStockAccounts() {
		List<CounterSaleEntity> listCounterSalesAccounts = counterService.getAllCounterSales();
		return listCounterSalesAccounts;
	}
	@GetMapping("/getCounterSaleDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<CounterSaleEntity>> getCounterSaleWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<CounterSaleEntity> counterSaleWithPagination;
		if (keyword.length() != 0) {
			counterSaleWithPagination =counterService.getSearch(keyword, pageable);
		} else {
			counterSaleWithPagination = counterService.getAllCounterSaleWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(counterSaleWithPagination.getSize(), counterSaleWithPagination);
	}
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
 	public CounterSaleEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException{
		CounterSaleEntity listCounterSaleAccounts = counterService.getCounterSaleById(id);
	 	if(listCounterSaleAccounts==null)
			throw new UserNotFoundException("Counter Sales was not Found "+ id);
	 	return listCounterSaleAccounts;
		
	}
	@PostMapping(value="/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CounterSaleEntity> openStockCreate(
				 @RequestBody CounterSaleEntity pkgObj) {
	
		CounterSaleEntity entity=counterService.createOrUpdateCounterSale(pkgObj);
		return new ResponseEntity<CounterSaleEntity>(entity,HttpStatus.OK);
		
	}
 
	@PutMapping(value="/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CounterSaleEntity> updateOpenStock(
				 @RequestBody CounterSaleEntity pkgObj) {
	
		CounterSaleEntity entity=counterService.createOrUpdateCounterSale(pkgObj);
		return new ResponseEntity<CounterSaleEntity>(entity,HttpStatus.OK);
		
	}
	
}
