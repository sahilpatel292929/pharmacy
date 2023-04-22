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
import com.ets.SecurePharmacy.tenant.model.ShortageEntryEntity;
import com.ets.SecurePharmacy.service.ShortageEntryService;

@RestController
@RequestMapping("/ShortageEntry")
public class ShortageEntryController {

	@Autowired
	ShortageEntryService ses;

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<ShortageEntryEntity> getAllAccounts() {
		List<ShortageEntryEntity> listAccounts = ses.getAllShortage();
		// System.out.println(listAccounts);
		return listAccounts;
	}

	@GetMapping("/getShortageEntryDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<ShortageEntryEntity>> getShortageEntryWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<ShortageEntryEntity> shortageEntryWithPagination;
		if (keyword.length() != 0) {
			shortageEntryWithPagination = ses.getSearch(keyword, pageable);
		} else {
			shortageEntryWithPagination = ses.getAllShortageEntryWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(shortageEntryWithPagination.getSize(), shortageEntryWithPagination);
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ShortageEntryEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		ShortageEntryEntity listAccounts = ses.getShortageById(id);
		if (listAccounts == null)
			throw new UserNotFoundException("Shortage Id was not Found " + id);
		return listAccounts;

	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<ShortageEntryEntity> createShortage(@RequestBody ShortageEntryEntity shortObj) {
		ShortageEntryEntity entity = ses.createOrUpdateShortage(shortObj);
		return new ResponseEntity<ShortageEntryEntity>(entity, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<ShortageEntryEntity> updateShortage(@RequestBody ShortageEntryEntity shortObj) {
		ShortageEntryEntity entity = ses.createOrUpdateShortage(shortObj);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

}
