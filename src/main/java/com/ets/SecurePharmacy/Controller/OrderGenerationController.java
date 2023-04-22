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
import com.ets.SecurePharmacy.tenant.model.OrderGenerationEntity;
import com.ets.SecurePharmacy.service.OrderGenerationEntryService;

@RestController
@RequestMapping("order-generation")
public class OrderGenerationController {

	@Autowired
	OrderGenerationEntryService ogService;

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<OrderGenerationEntity> getAll() {

		List<OrderGenerationEntity> listAccounts = ogService.getAll();
		return listAccounts;

	}
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public OrderGenerationEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		OrderGenerationEntity list = ogService.getById(id);
		if (list == null)
			throw new UserNotFoundException("Order was not Found " + id);
		return list;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<OrderGenerationEntity> PackCreate(@RequestBody OrderGenerationEntity pkgObj) {
		OrderGenerationEntity entity = ogService.createOrUpdate(pkgObj);
		return new ResponseEntity<>(entity, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<OrderGenerationEntity> updatePurchase(@RequestBody OrderGenerationEntity pkgObj,
			@PathVariable long id) {

		ogService.createOrUpdate(pkgObj);
		return new ResponseEntity<OrderGenerationEntity>(pkgObj, HttpStatus.OK);

	}

}
