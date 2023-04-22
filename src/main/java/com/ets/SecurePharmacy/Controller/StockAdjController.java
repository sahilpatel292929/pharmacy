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
import com.ets.SecurePharmacy.tenant.model.StockAdjEntity;
import com.ets.SecurePharmacy.service.StockAdjService;

@RestController
@RequestMapping("/StockAdj")
public class StockAdjController {

	@Autowired
	StockAdjService sas;

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<StockAdjEntity> getAllOpenStockAccounts() {
		List<StockAdjEntity> listStockAdj = sas.getAllStockAgjDetails();
		return listStockAdj;
	}

	@GetMapping("/getStockAdjDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<StockAdjEntity>> getStockAdjWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<StockAdjEntity> stockAdjWithPagination;
		if (keyword.length() != 0) {
			stockAdjWithPagination = sas.getSearch(keyword, pageable);
		} else {
			stockAdjWithPagination = sas.getAllStockAdjEntityWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(stockAdjWithPagination.getSize(), stockAdjWithPagination);
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public StockAdjEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		StockAdjEntity listStockAdj = sas.getStockAdjById(id);
		if (listStockAdj == null)
			throw new UserNotFoundException("Sales Adjustment Account was not Found " + id);
		return listStockAdj;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<StockAdjEntity> openStockCreate(@RequestBody StockAdjEntity pkgObj) {

		StockAdjEntity entity = sas.createOrUpdateStockAdj(pkgObj);
		return new ResponseEntity<StockAdjEntity>(entity, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<StockAdjEntity> updateOpenStock(@RequestBody StockAdjEntity pkgObj) {

		StockAdjEntity entity = sas.createOrUpdateStockAdj(pkgObj);
		return new ResponseEntity<>(entity, HttpStatus.OK);

	}
}













