package com.ets.SecurePharmacy.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
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
import com.ets.SecurePharmacy.tenant.model.OpenStockEntryEntity;
import com.ets.SecurePharmacy.service.OpenStockEntryService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/opening-stock")
public class OpenStockEntryController {

	@Autowired
	OpenStockEntryService openStockService;

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<OpenStockEntryEntity> getAllOpenStockAccounts() {
		System.out.println("Hi Welcome");
		List<OpenStockEntryEntity> listOpensStockAccounts = openStockService.getAllOpenStock();
		return listOpensStockAccounts;
	}

	@GetMapping("/getOpenStockEntryDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<OpenStockEntryEntity>> getOpenStockEntryWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<OpenStockEntryEntity> openStockEntryWithPagination;
		if (keyword.length() != 0) {
			openStockEntryWithPagination = openStockService.getSearch(keyword, pageable);
		} else {
			openStockEntryWithPagination = openStockService.getAllOpenStockEntryWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(openStockEntryWithPagination.getSize(), openStockEntryWithPagination);
	}

	@GetMapping("/getOpenStockEntryDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport10() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<OpenStockEntryEntity> openStockEntry = openStockService.getAllOpenStock();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:openStockEntry.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(openStockEntry);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * s if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\openStockEntry.pdf");
		// }

		return "report generated in path : " + path;
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public OpenStockEntryEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		OpenStockEntryEntity listOpenStockAccounts = openStockService.getOpenStockeById(id);
		if (listOpenStockAccounts == null)
			throw new UserNotFoundException("Purchase Invoice was not Found " + id);
		return listOpenStockAccounts;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<OpenStockEntryEntity> openStockCreate(@RequestBody OpenStockEntryEntity pkgObj) {

		OpenStockEntryEntity entity = openStockService.createOrUpdateOpenStock(pkgObj);
		return new ResponseEntity<>(entity, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<OpenStockEntryEntity> updateOpenStock(@RequestBody OpenStockEntryEntity pkgObj) {

		OpenStockEntryEntity entity = openStockService.createOrUpdateOpenStock(pkgObj);
		return new ResponseEntity<>(entity, HttpStatus.OK);

	}

}
