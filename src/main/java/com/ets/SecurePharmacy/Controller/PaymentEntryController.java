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
import com.ets.SecurePharmacy.tenant.model.PaymentEntryEntity;
import com.ets.SecurePharmacy.service.PaymentEntryService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/payment-entry")
public class PaymentEntryController {

	@Autowired
	PaymentEntryService paymentEntryService;

	
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<PaymentEntryEntity> getAll() {

		List<PaymentEntryEntity> listAccounts = paymentEntryService.getAll();
		return listAccounts;

	}

	@GetMapping("/getPaymentEntryDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<PaymentEntryEntity>> getPaymentEntryWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<PaymentEntryEntity> paymentEntryWithPagination;
		if (keyword.length() != 0) {
			paymentEntryWithPagination = paymentEntryService.getSearch(keyword, pageable);
		} else {
			paymentEntryWithPagination = paymentEntryService.getAllPaymentEntryWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(paymentEntryWithPagination.getSize(), paymentEntryWithPagination);
	}
	
	@GetMapping("/getPaymentEntryDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport10() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<PaymentEntryEntity> paymentEntry= paymentEntryService.getAll();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:<PaymentEntry.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paymentEntry);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*s
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\PaymentEntry.pdf");
		// }

		return "report generated in path : " + path;
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public PaymentEntryEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		PaymentEntryEntity listAccounts = paymentEntryService.getById(id);
		if (listAccounts == null)
			throw new UserNotFoundException("Purchase Invoice was not Found " + id);
		return listAccounts;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<PaymentEntryEntity> PackCreate(@RequestBody PaymentEntryEntity pkgObj) {
		PaymentEntryEntity entity = paymentEntryService.createOrUpdate(pkgObj);
		return new ResponseEntity<PaymentEntryEntity>(entity, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<PaymentEntryEntity> updatePurchase(@RequestBody PaymentEntryEntity pkgObj,
			@PathVariable long id) {

		PaymentEntryEntity entity = paymentEntryService.createOrUpdate(pkgObj);
		return new ResponseEntity<PaymentEntryEntity>(entity, HttpStatus.OK);

	}
	
	
}
