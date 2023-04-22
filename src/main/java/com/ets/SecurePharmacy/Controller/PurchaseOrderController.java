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
import com.ets.SecurePharmacy.tenant.model.PurchaseOrderEntity;
import com.ets.SecurePharmacy.service.PurchaseOrderEntryService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderEntryService poService;

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<PurchaseOrderEntity> getAll() {

		List<PurchaseOrderEntity> listAccounts = poService.getAll();
		return listAccounts;
	}

	@GetMapping("/getPurchaseOrderDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<PurchaseOrderEntity>> getPurchaseOrderWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<PurchaseOrderEntity> purchaseOrderWithPagination;
		if (keyword.length() != 0) {
			purchaseOrderWithPagination = poService.getSearch(keyword, pageable);
		} else {
			purchaseOrderWithPagination = poService.getAllPurchaseOrderWithPagination(offset, pageSize);
		}
		
		return new APIResponse<>(purchaseOrderWithPagination.getSize(),purchaseOrderWithPagination);
	}
	
	@GetMapping("/getPurchaseOrderDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport10() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<PurchaseOrderEntity> purchaseOrder=poService.getAll();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:PurchaseOrder.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(purchaseOrder);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*s
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\PurchaseOrder.pdf");
		// }

		return "report generated in path : " + path;
	}


	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public PurchaseOrderEntity getByInvoice(@PathVariable Long id) throws RecordNotFoundException {
		PurchaseOrderEntity list = poService.getById(id);
		if (list == null)
			throw new UserNotFoundException("Purchase Order was not Found " + id);
		return list;

	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<PurchaseOrderEntity> PackCreate(@RequestBody PurchaseOrderEntity pkgObj) {
		PurchaseOrderEntity entity = poService.createOrUpdate(pkgObj);
		return new ResponseEntity<>(entity, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<PurchaseOrderEntity> updatePurchase(@RequestBody PurchaseOrderEntity pkgObj,
			@PathVariable long id) {

		PurchaseOrderEntity entity = poService.createOrUpdate(pkgObj);
		return new ResponseEntity<PurchaseOrderEntity>(entity, HttpStatus.OK);

	}

	@GetMapping(value = "/get_reports/{itemsId}/{supplierId}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<PurchaseOrderEntity> getAllReports(@PathVariable Long itemsId, @PathVariable Long supplierId) {

		List<PurchaseOrderEntity> listAccounts = poService.findByCriteria(itemsId, supplierId);
		return listAccounts;
	}

}
