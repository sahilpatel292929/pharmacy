package com.ets.SecurePharmacy.Controller;

import com.ets.SecurePharmacy.DTO.APIResponse;
import com.ets.SecurePharmacy.DTO.PurchaseReportDto;
import com.ets.SecurePharmacy.DTO.PurchaseReportFilterDTO;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.service.BatchService;
import com.ets.SecurePharmacy.service.PurchaseEntryService;
import com.ets.SecurePharmacy.service.PurchaseEntrySettingService;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntityDraft;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntrySettingEntity;
import com.ets.SecurePharmacy.transfomers.DTOTransfomers;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.json.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchase-entry")
public class PurchaseEntryController {

	@Autowired
	PurchaseEntrySettingService pesSettings;
	@Autowired
	PurchaseEntryService peService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	DTOTransfomers dtoTransformer;
	
	@Autowired
	BatchService batchService;

	@GetMapping("/getSettingDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<PurchaseEntrySettingEntity> getAllAccounts() {
		List<PurchaseEntrySettingEntity> listAccounts = pesSettings.getAllPurchaseSetting();
		return listAccounts;
	}

	@GetMapping("/getPurchaseEntryDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<PurchaseEntryEntity>> getPurchaseEntrySettingWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<PurchaseEntryEntity> purchaseentryPagination;
		if (keyword.length() != 0) {
			purchaseentryPagination = peService.getSearch(keyword, pageable);
		} else {
			purchaseentryPagination = peService.getAllPurchaseOrderWithPagination(offset, pageSize);
		}

		return new APIResponse<>(purchaseentryPagination.getSize(), purchaseentryPagination);
	}

	@GetMapping("/getPurchaseEntryDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport10() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<PurchaseEntrySettingEntity> purchaseEntrySetting = pesSettings.getAllPurchaseSetting();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:<PurchaseEntrySetting.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(purchaseEntrySetting);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * s if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\PurchaseEntrySetting.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PutMapping("/updateSetting/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<PurchaseEntrySettingEntity> updateAccount(@RequestBody PurchaseEntrySettingEntity account,
			@PathVariable long id) {

		PurchaseEntrySettingEntity accountEntity = pesSettings.createOrUpdateSetttings(account);
		return new ResponseEntity<>(accountEntity, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<PurchaseEntryEntity> getAll() {

		List<PurchaseEntryEntity> listAccounts = peService.getAllAccounts();
		// return
		// listAccounts.stream().map(dtoTransformer::purchaseEntryConvertToDto).collect(Collectors.toList());
		return listAccounts;
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public PurchaseEntryEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		PurchaseEntryEntity listAccounts = peService.getPurchaseById(id);
		if (listAccounts == null)
			throw new UserNotFoundException("Purchase Invoice was not Found " + id);
		// PurchaseEntryDTO
		// dto=this.dtoTransformer.purchaseEntryConvertToDto(listAccounts);
		return listAccounts;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<PurchaseEntryEntity> PackCreate(@RequestBody PurchaseEntryEntity pkgObj)
			throws ParseException {

		// PurchaseEntryEntity
		// entity=this.dtoTransformer.purchaseEntryConvertToEntity(pkgObj);
		// System.out.println(pkgObj);
		PurchaseEntryEntity entityres;

		entityres = peService.createOrUpdateAccounts(pkgObj);

		// pkgObj=this.dtoTransformer.purchaseEntryConvertToDto(entityres);
		return new ResponseEntity<>(entityres, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<PurchaseEntryEntity> updatePurchase(@RequestBody PurchaseEntryEntity pkgObj,
			@PathVariable long id) throws ParseException {
		// PurchaseEntryEntity
		// entity=this.dtoTransformer.purchaseEntryConvertToEntity(pkgObj);
		PurchaseEntryEntity entityres = peService.createOrUpdateAccounts(pkgObj);
		// pkgObj=this.dtoTransformer.purchaseEntryConvertToDto(entityres);
		// return new ResponseEntity<PurchaseEntryDTO>(pkgObj, HttpStatus.OK);
		return new ResponseEntity<PurchaseEntryEntity>(entityres, HttpStatus.OK);
	}
	
	@GetMapping(value="/invoice/{invoice_num}/{supplier_id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<String> getByInvoiceNumber(@PathVariable String invoice_num, @PathVariable Long supplier_id){
		
		String results = peService.getPurchaseDetailByInvoiceAndSupplier(invoice_num,supplier_id);
		return new ResponseEntity<String>(results, HttpStatus.OK);
		
	}

	@GetMapping("/report")
	public ResponseEntity<Page<PurchaseReportDto>> getPurchaseReport(@RequestBody PurchaseReportFilterDTO filter, Pageable pageable) {
		return ResponseEntity.ok(peService.getPurchaseReport(filter, pageable));
	}

	//	make the draft API
	@PostMapping(value = "/create-draft")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<PurchaseEntryEntityDraft> createDraft(@RequestBody PurchaseEntryEntityDraft pkgObj)
			throws ParseException {

		PurchaseEntryEntityDraft entityres;

		entityres = peService.createOrUpdateDraft(pkgObj);
		return new ResponseEntity<>(entityres, HttpStatus.OK);
	}

	@GetMapping("/getAll-draft-details")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public List<PurchaseEntryEntityDraft> getDraftDetails() {

		List<PurchaseEntryEntityDraft> listAccounts = peService.getDraftDetails();

		return listAccounts;
	}

	@DeleteMapping("/delete-draft-byId")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public Object deleteDraftById(@RequestParam Long id) {
		return peService.deleteDraftById(id);
	}


}
