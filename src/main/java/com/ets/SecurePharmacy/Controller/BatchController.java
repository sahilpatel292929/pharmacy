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
import com.ets.SecurePharmacy.tenant.dao.BatchRepository.BatchDetailsDTO;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.service.BatchService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/batch-service")
public class BatchController {

	@Autowired
	BatchService batchService;

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<BatchEntity> getAllOpenStockAccounts() {
		List<BatchEntity> listCashWD = batchService.getAllAccounts();
		return listCashWD;
	}

	@GetMapping("/batchDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<BatchEntity>> getBatchWithPagination(@RequestParam int offset, @RequestParam int pageSize,
			@RequestParam String keyword, Pageable pageable) {

		Page<BatchEntity> batchWithPagination;
		if (keyword.length() != 0) {
			batchWithPagination = batchService.getSearch(keyword, pageable);
		} else {
			batchWithPagination = batchService.getAllBatchWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(batchWithPagination.getSize(), batchWithPagination);
	}

	@GetMapping("/getBatchDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport10() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<BatchEntity> Batch = batchService.getAllAccounts();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath: batch.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Batch);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * s if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Batch.pdf");
		// }

		return "report generated in path : " + path;
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public BatchEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		BatchEntity listCashWD = batchService.getAccountsById(id);
		if (listCashWD == null)
			throw new UserNotFoundException("Batch was not Found " + id);
		return listCashWD;

	}

	@GetMapping("/getBatch/{batch_number}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public BatchEntity getByBatchNo(@PathVariable String batch_number) throws RecordNotFoundException {
		BatchEntity listCashWD;
		
		listCashWD= batchService.getBatchDetails(batch_number);
		if (listCashWD == null)
			throw new UserNotFoundException("Batch was not Found " + batch_number);
		return listCashWD;

	}
	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<BatchEntity> openStockCreate(@RequestBody BatchEntity pkgObj) {

		BatchEntity entity = batchService.createOrUpdateAccounts(pkgObj);
		return new ResponseEntity<>(entity, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<BatchEntity> updateOpenStock(@RequestBody BatchEntity pkgObj) {

		BatchEntity entity = batchService.createOrUpdateAccounts(pkgObj);
		return new ResponseEntity<BatchEntity>(entity, HttpStatus.OK);

	}
	
	

}
