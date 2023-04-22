package com.ets.SecurePharmacy.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.json.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
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
import com.ets.SecurePharmacy.DTO.AccountCreationDTO;
import com.ets.SecurePharmacy.DTO.AreaCreationDTO;
import com.ets.SecurePharmacy.DTO.BranchCreationDTO;
import com.ets.SecurePharmacy.DTO.CompostionCreationDTO;
import com.ets.SecurePharmacy.DTO.CustomerCreationDTO;
import com.ets.SecurePharmacy.DTO.DiscountSlabCreationDTO;
import com.ets.SecurePharmacy.DTO.GroupCreationDTO;
import com.ets.SecurePharmacy.DTO.HsnSacCreationDTO;
import com.ets.SecurePharmacy.DTO.ItemCreationDTO;
import com.ets.SecurePharmacy.DTO.ManufacturerCreationDTO;
import com.ets.SecurePharmacy.DTO.PackCreationDTO;
import com.ets.SecurePharmacy.DTO.SalesManCreationDTO;
import com.ets.SecurePharmacy.DTO.SchedulerCreationDTO;
import com.ets.SecurePharmacy.DTO.StoreTypeCreationDTO;
import com.ets.SecurePharmacy.DTO.SupplierCreationDTO;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.exception.UserNotFoundException;
import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.AccountHeadCreationEntity;
import com.ets.SecurePharmacy.tenant.model.AccountTypeCreationEntity;
import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;
import com.ets.SecurePharmacy.tenant.model.BestBeforeEntity;
import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GSTPercentageEntity;
import com.ets.SecurePharmacy.tenant.model.GSTTypeCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.MacIdEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.PackCreationEntity;
import com.ets.SecurePharmacy.tenant.model.PaymentModeEntity;
import com.ets.SecurePharmacy.tenant.model.RouteCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SalesManCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StockCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
import com.ets.SecurePharmacy.tenant.model.WarningCreationEntity;
import com.ets.SecurePharmacy.service.AccountCreationService;
import com.ets.SecurePharmacy.service.AccountHeadCreationService;
import com.ets.SecurePharmacy.service.AccountTypeCreationService;
import com.ets.SecurePharmacy.service.AreaCreationService;
import com.ets.SecurePharmacy.service.BestBeforeService;
import com.ets.SecurePharmacy.service.BranchCreationService;
import com.ets.SecurePharmacy.service.CompostionCreationService;
import com.ets.SecurePharmacy.service.CustomerCreationService;
import com.ets.SecurePharmacy.service.DiscountSlabCreationService;
import com.ets.SecurePharmacy.service.GSTPercentageService;
import com.ets.SecurePharmacy.service.GSTTypeCreationService;
import com.ets.SecurePharmacy.service.GroupCreationService;
import com.ets.SecurePharmacy.service.HsnSacCreationService;
import com.ets.SecurePharmacy.service.ItemCreationService;
import com.ets.SecurePharmacy.service.ManufacturerCreationService;
import com.ets.SecurePharmacy.service.PackCreationService;
import com.ets.SecurePharmacy.service.PaymentModeService;
import com.ets.SecurePharmacy.service.RouteCreationService;
import com.ets.SecurePharmacy.service.SalesManCreationService;
import com.ets.SecurePharmacy.service.SchedulerCreationService;
import com.ets.SecurePharmacy.service.StockCreationService;
import com.ets.SecurePharmacy.service.StoreTypeCreationService;
import com.ets.SecurePharmacy.service.SupplierCreationService;
import com.ets.SecurePharmacy.service.WarningCreationService;
import com.ets.SecurePharmacy.transfomers.DTOTransfomers;
import com.ets.SecurePharmacy.util.DateConvertUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/AccountSetup")
public class AccountSetupRestController {

	@Autowired
	AccountCreationService accountService;
	@Autowired
	CustomerCreationService customerService;
	@Autowired
	SupplierCreationService supplierService;
	@Autowired
	PaymentModeService modeService;
	@Autowired
	AccountHeadCreationService accountHead;
	@Autowired
	AccountTypeCreationService accountType;
	@Autowired
	GSTTypeCreationService gstService;
	@Autowired
	AreaCreationService areaService;
	@Autowired
	RouteCreationService routeService;
	@Autowired
	SalesManCreationService salesManService;
	@Autowired
	DiscountSlabCreationService dscService;
	@Autowired
	BranchCreationService branchService;
	@Autowired
	StockCreationService stockService;
	@Autowired
	ManufacturerCreationService maService;
	@Autowired
	GroupCreationService groupService;
	@Autowired
	StoreTypeCreationService storeService;
	@Autowired
	HsnSacCreationService hsnService;
	@Autowired
	CompostionCreationService comService;
	@Autowired
	SchedulerCreationService scService;
	@Autowired
	PackCreationService packService;
	@Autowired
	WarningCreationService warnService;
	@Autowired
	ItemCreationService itemService;
	@Autowired
	BestBeforeService bestSevice;
	@Autowired
	WarningCreationService wService;
	@Autowired
	DateConvertUtils dateUtils;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	GSTPercentageService gstpercentageService;

	@Autowired
	PaymentModeService paymentModeService;

	@Autowired
	DTOTransfomers dtoTransformer;

	@GetMapping("/getAccountDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<AccountCreationDTO> getAllAccounts() {
		List<AccountCreationEntity> listAccounts = accountService.getAllAccounts();
		return listAccounts.stream().map(dtoTransformer::accountCreationConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getAccountDetail/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<AccountCreationEntity>> getAccountsWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<AccountCreationEntity> accountsWithPagination;
		if (keyword.length() != 0) {
			accountsWithPagination = accountService.getSearch(keyword, pageable);
		} else {
			accountsWithPagination = accountService.getAllAccountsWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(accountsWithPagination.getSize(), accountsWithPagination);
	}

	@GetMapping("/getAccountDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<AccountCreationEntity> accountcreation = accountService.getAllAccounts();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCreationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:accountcreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(accountcreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\accountcreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\accountcreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/AccountCreate/Accounts")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> AccountCreate(@RequestBody AccountCreationDTO account) throws ParseException {
		AccountCreationEntity accEntity = this.dtoTransformer.accountCreationConvertToEntity(account);
		AccountCreationEntity accountEntity = accountService.createOrUpdateAccounts(accEntity);
		if (accountEntity != null) {
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/AccountCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> AccountCreateBulk(@RequestBody List<AccountCreationDTO> account)
			throws ParseException {
		List<AccountCreationEntity> accEntity = this.dtoTransformer.accountCreationConvertToEntityList(account);
		try {
			accountService.createOrUpdateAccountsBulk(accEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/getAccount/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public AccountCreationDTO retrieveUser(@PathVariable Long id) throws RecordNotFoundException {
		AccountCreationEntity account = accountService.getAccountsById(id);
		if (account == null)
			throw new UserNotFoundException("id-" + id);
		else {
			return this.dtoTransformer.accountCreationConvertToDto(account);
		}
	}

	@DeleteMapping("/deleteAccount/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public void deleteAccount(@PathVariable(name = "id") Long id) throws RecordNotFoundException {
		accountService.deleteAccountsById(id);

	}

	@PutMapping("/updateAccount/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<AccountCreationDTO> updateAccount(@RequestBody AccountCreationDTO account,
			@PathVariable long id) throws ParseException {

		AccountCreationEntity accountEntitys = this.dtoTransformer.accountCreationConvertToEntity(account);
		AccountCreationEntity accountEntity = accountService.updateAccounts(accountEntitys);
		account = this.dtoTransformer.accountCreationConvertToDto(accountEntity);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@GetMapping("/getCustomerDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<CustomerCreationDTO> getAllCustomers() {
		List<CustomerCreationEntity> listCustomer = customerService.getAllCutomer();

		return listCustomer.stream().map(dtoTransformer::customerCreationConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getCustomerDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<CustomerCreationEntity>> getCustomersWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {
		Page<CustomerCreationEntity> customersWithPagination;
		if (keyword.length() != 0) {
			customersWithPagination = customerService.getSearch(keyword, pageable);
		} else {
			customersWithPagination = customerService.getAllCustomersWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(customersWithPagination.getSize(), customersWithPagination);
	}

	@GetMapping("/getcustomercreationReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport1(String reportFormat) throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<CustomerCreationEntity> customercreation = customerService.getAllCutomer();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCreationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:customercreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customercreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\customercreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\customercreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/CustomerCreate/Customer")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CustomerCreationDTO> customerCreate(@RequestBody CustomerCreationDTO customer)
			throws ParseException {

		CustomerCreationEntity customerEntity = this.dtoTransformer.customerCreationConvertToEntity(customer);
		CustomerCreationEntity customerResEntity = customerService.createOrUpdateCustomer(customerEntity);
		customer = this.dtoTransformer.customerCreationConvertToDto(customerResEntity);
		return new ResponseEntity<>(customer, HttpStatus.OK);

	}

	@GetMapping(value = "/CustomerCreate/{mobileNo}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CustomerCreationDTO> customerCreateMobile(@PathVariable String mobileNo)
			throws ParseException {
		// String val="";
		CustomerCreationEntity s = customerService.getCustomerByMobile(mobileNo);
		CustomerCreationDTO customerDTO = this.dtoTransformer.customerCreationConvertToDto(s);
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);

	}

	@PostMapping(value = "/CustomerCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> customerCreateBulk(@RequestBody List<CustomerCreationDTO> customer)
			throws ParseException {
		List<CustomerCreationEntity> accEntity = this.dtoTransformer.customerCreationConvertToEntityList(customer);
		try {
			customerService.createOrUpdateCustomerList(accEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@GetMapping("/getCustomer/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public CustomerCreationDTO getCustomer(@PathVariable Long id) throws RecordNotFoundException {

		CustomerCreationEntity customer = customerService.getCustomerById(id);
		CustomerCreationDTO customerDTO = this.dtoTransformer.customerCreationConvertToDto(customer);
		if (customer == null)
			throw new UserNotFoundException("User not Found " + id);
		return customerDTO;
	}

	@DeleteMapping("/deleteCustomer/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		customerService.deleteCustomerById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateCustomer/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CustomerCreationDTO> updateCustomer(@RequestBody CustomerCreationDTO customer,
			@PathVariable long id) throws ParseException {
		CustomerCreationEntity customerEntity = this.dtoTransformer.customerCreationConvertToEntity(customer);
		CustomerCreationEntity customeresult = customerService.updateCustomer(customerEntity);
		customer = this.dtoTransformer.customerCreationConvertToDto(customeresult);
		return new ResponseEntity<CustomerCreationDTO>(customer, HttpStatus.OK);
	}

	@GetMapping("/getSuppliers")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<SupplierCreationDTO> getSupplier() {
		List<SupplierCreationEntity> listSuppliers = supplierService.getAllSuppliers();
		return listSuppliers.stream().map(dtoTransformer::supplierConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getSuppliersDetails/pagination")

	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")

	public APIResponse<Page<SupplierCreationEntity>> getSuppliersWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<SupplierCreationEntity> suppliersWithPagination;
		if (keyword.length() != 0) {
			suppliersWithPagination = supplierService.getSearch(keyword, pageable);
		} else {
			suppliersWithPagination = supplierService.getAllSuppliersWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(suppliersWithPagination.getSize(), suppliersWithPagination);
	}

	@GetMapping("/getsuppliercreationReport")
	public String exportReports2() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<SupplierCreationEntity> suppliercreation = supplierService.getAllSuppliers();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCreationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:suppliercreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(suppliercreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\suppliercreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\suppliercreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@GetMapping("/getSupplier/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public SupplierCreationDTO getSupplier(@PathVariable Long id) throws RecordNotFoundException {
		SupplierCreationEntity supplier = supplierService.getSupplierById(id);
		SupplierCreationDTO supplierDto = this.dtoTransformer.supplierConvertToDto(supplier);
		if (supplier == null)
			throw new UserNotFoundException("User not Found " + id);
		return supplierDto;
	}

	@PostMapping(value = "/SupplierCreate/Supplier")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> supplierCreate(@RequestBody SupplierCreationDTO supplier) throws ParseException {
		SupplierCreationEntity sentity = this.dtoTransformer.supplierConvertToEntity(supplier);
		supplierService.createOrUpdateSupplier(sentity);
		supplier = this.dtoTransformer.supplierConvertToDto(sentity);
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@PostMapping(value = "/SupplierCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> supplierCreateList(@RequestBody List<SupplierCreationDTO> supplier)
			throws ParseException {
		List<SupplierCreationEntity> accEntity = this.dtoTransformer.supplierConvertToEntityList(supplier);
		try {
			supplierService.createOrUpdateSupplierList(accEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@DeleteMapping("/deleteSupplier/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteSupplier(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		supplierService.deleteSupplierById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateSupplier/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> updateSupplier(@RequestBody SupplierCreationDTO supplierdto,
			@PathVariable long id) throws ParseException {
		SupplierCreationEntity supplier = this.dtoTransformer.supplierConvertToEntity(supplierdto);
		SupplierCreationEntity supplierEntity = supplierService.updateSupplier(supplier);
		supplierdto = this.dtoTransformer.supplierConvertToDto(supplierEntity);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/getAccountHead")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<AccountHeadCreationEntity> getAccoountHeads() {
		List<AccountHeadCreationEntity> listAccountHeads = accountHead.getAllAccountHead();
		return listAccountHeads;
	}

	@GetMapping("/getAccountType")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<AccountTypeCreationEntity> getAllAccountTypes() {
		List<AccountTypeCreationEntity> listAccountType = accountType.getAllAccountTypes();
		return listAccountType;
	}

	@GetMapping("/getGstType")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<GSTTypeCreationEntity> getAllGSTTypes() {
		List<GSTTypeCreationEntity> listGstType = gstService.getAllGSTType();
		return listGstType;
	}

	@GetMapping("/getAreaDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<AreaCreationDTO> getAllAreaDetails() {
		List<AreaCreationEntity> listArea = areaService.getAllAreaList();
		return listArea.stream().map(dtoTransformer::areaCreationConvertToDto).collect(Collectors.toList());
	}

	@PostMapping(value = "/AreaCreate/Area")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<AreaCreationDTO> areaCreate(@RequestBody AreaCreationDTO areaObj) throws ParseException {
		boolean s = areaService.getAreaByName(areaObj.getAreaName());
		if (s == true)
			throw new RecordAlreadyPresentException("Area Name Already Present");
		AreaCreationEntity areaEntity = this.dtoTransformer.areaCreationConvertToEntity(areaObj);
		AreaCreationEntity areaResp = areaService.createOrUpdateArea(areaEntity);
		areaObj = this.dtoTransformer.areaCreationConvertToDto(areaResp);
		return new ResponseEntity<>(areaObj, HttpStatus.OK);

	}

	@PostMapping(value = "/AreaCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> areaCreateList(@RequestBody List<AreaCreationDTO> areaObj) throws ParseException {
		List<AreaCreationEntity> accEntity = this.dtoTransformer.areaCreationConvertToEntityList(areaObj);
		try {
			areaService.createOrUpdateAreaList(accEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/getArea/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public AreaCreationDTO getArea(@PathVariable Long id) throws RecordNotFoundException {
		AreaCreationEntity area = areaService.getAreaById(id);
		if (area == null)
			throw new UserNotFoundException("User not Found " + id);
		AreaCreationDTO areaDto = this.dtoTransformer.areaCreationConvertToDto(area);
		return areaDto;
	}

	@DeleteMapping("/deleteArea/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteAreaById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		areaService.deleteAreaById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateArea/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> updateArea(@RequestBody AreaCreationDTO areaDto, @PathVariable long id)
			throws ParseException {
		AreaCreationEntity area = this.dtoTransformer.areaCreationConvertToEntity(areaDto);
		AreaCreationEntity areaEntity = areaService.createOrUpdateArea(area);
		areaDto = this.dtoTransformer.areaCreationConvertToDto(areaEntity);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/getRouteDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<RouteCreationEntity> getAllRouteTypes() {
		List<RouteCreationEntity> lisRouteType = routeService.getAllRoutes();
		return lisRouteType;
	}

	@GetMapping("/getRouteCreation/pagination")

	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")

	public APIResponse<Page<RouteCreationEntity>> getAllRouteWithPagination(@PathVariable int offset,
			@PathVariable int pageSize) {

		Page<RouteCreationEntity> routeWithPagination = routeService.getAllRouteWithPagination(offset, pageSize);

		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(routeWithPagination.getSize(), routeWithPagination);
	}

	@GetMapping("/getroutedetailsReport")
	public String exportReport3() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<RouteCreationEntity> areacreation = routeService.getAllRoutes();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCreationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:routecreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(areacreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\routecreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\routecreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@GetMapping("/getSalesBoyDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<SalesManCreationEntity> getAllSalesBoyDetails() {
		List<SalesManCreationEntity> listSalesBoy = salesManService.getAllSalesManDetails();
		return listSalesBoy;
	}

	@GetMapping("/getSalesBoyDetails/pagination")

	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")

	public APIResponse<Page<SalesManCreationEntity>> getSalesManWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<SalesManCreationEntity> salesmanWithPagination;
		if (keyword.length() != 0) {
			salesmanWithPagination = salesManService.getSearch(keyword, pageable);
		} else {
			salesmanWithPagination = salesManService.getAllSalesManWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(salesmanWithPagination.getSize(), salesmanWithPagination);
	}

	@GetMapping("/getsalesboydetailsReport")
	public String exportReport4() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<SalesManCreationEntity> salesmancreation = salesManService.getAllSalesManDetails();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCreationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:salesmancreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(salesmancreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\salesmancreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/SalesBoyCreate/SaleBoy")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> salesBoyCreate(@RequestBody SalesManCreationEntity areaObj) {
		boolean s = salesManService.getSalesManName(areaObj.getSalesManName());
		if (s == true)
			throw new RecordAlreadyPresentException("SalesBoy Name Already Present");
		salesManService.createOrUpdateSalesBoy(areaObj);
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@PostMapping(value = "/SalesBoyCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> salesBoyCreateList(@RequestBody List<SalesManCreationDTO> salesMan)
			throws ParseException {
		List<SalesManCreationEntity> salesEntity = this.dtoTransformer.salesManConvertToEntityList(salesMan);
		try {
			salesManService.createOrUpdateSalesBoyList(salesEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@GetMapping("/getSaleBoy/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public SalesManCreationEntity getSalesBoy(@PathVariable Long id) throws RecordNotFoundException {
		SalesManCreationEntity saleBoy = salesManService.getSalesManById(id);
		if (saleBoy == null)
			throw new UserNotFoundException("User not Found " + id);
		return saleBoy;
	}

	@DeleteMapping("/deleteSalesBoy/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteSalesManById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		salesManService.deleteSalesManById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateSalesBoy/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SalesManCreationEntity> updatSalesMan(@RequestBody SalesManCreationEntity saleBoy,
			@PathVariable long id) {
		SalesManCreationEntity salesEntity = salesManService.updateSalesBoy(saleBoy);
		return new ResponseEntity<>(salesEntity, HttpStatus.OK);
	}

	@GetMapping("/getDiscountDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<DiscountSlabCreationDTO> getAllDiscountSlabDetails() {
		List<DiscountSlabCreationEntity> listDSC = dscService.getAllDisountSlabDetails();
		return listDSC.stream().map(dtoTransformer::discountSlabConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getDiscountDetails/pagination")

	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")

	public APIResponse<Page<DiscountSlabCreationEntity>> getDiscountSlabWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<DiscountSlabCreationEntity> discountslabWithPagination;
		if (keyword.length() != 0) {
			discountslabWithPagination = dscService.getSearch(keyword, pageable);
		} else {
			discountslabWithPagination = dscService.getAllDiscountSlabWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(discountslabWithPagination.getSize(), discountslabWithPagination);

	}

	@GetMapping("/getdiscountslabdetailsReport")
	public String exportReport5() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<DiscountSlabCreationEntity> discountslab = dscService.getAllDisountSlabDetails();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCreationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:discountslabcreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(discountslab);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Discountslabcreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/discountSlabCreate/discountSlab")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<DiscountSlabCreationDTO> discountSlabCreate(@RequestBody DiscountSlabCreationDTO dscDto)
			throws ParseException {
		boolean s = dscService.getDiscountSlabName(dscDto.getDiscountSlabName());
		if (s == true)
			throw new RecordAlreadyPresentException("Dicount Slab Name Already Present");
		DiscountSlabCreationEntity dscEntity = this.dtoTransformer.discountConvertToEntity(dscDto);
		dscService.createOrUpdateDiscountSlab(dscEntity);
		dscDto = this.dtoTransformer.discountSlabConvertToDto(dscEntity);
		return new ResponseEntity<DiscountSlabCreationDTO>(dscDto, HttpStatus.OK);

	}

	@PostMapping(value = "/discountSlabCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> discountSlabCreateList(@RequestBody List<DiscountSlabCreationDTO> dscDto)
			throws ParseException {
		List<DiscountSlabCreationEntity> salesEntity = this.dtoTransformer.discountConvertToEntityList(dscDto);
		try {
			dscService.createOrUpdateDiscountSlabList(salesEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@GetMapping("/getDiscoutSlab/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public DiscountSlabCreationDTO getDisountSale(@PathVariable Long id) throws RecordNotFoundException {
		DiscountSlabCreationEntity dsc = dscService.getDiscountSalbById(id);
		if (dsc == null)
			throw new UserNotFoundException("Discount Slab not Found " + id);
		DiscountSlabCreationDTO dscDTO = this.dtoTransformer.discountSlabConvertToDto(dsc);
		return dscDTO;
	}

	@DeleteMapping("/deletDiscoutSlab/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteDiscountSlabById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		dscService.deleteDiscountSlabById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateDiscoutSlab/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<DiscountSlabCreationDTO> updateDiscountSlab(@RequestBody DiscountSlabCreationDTO dscObj,
			@PathVariable long id) throws ParseException {
		DiscountSlabCreationEntity discountEntity = this.dtoTransformer.discountConvertToEntity(dscObj);
		DiscountSlabCreationEntity discountRes = dscService.updateDiscountSlab(discountEntity);
		dscObj = this.dtoTransformer.discountSlabConvertToDto(discountRes);
		return new ResponseEntity<>(dscObj, HttpStatus.OK);
	}

	@GetMapping("/getBranchDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<BranchCreationDTO> getAllBranchDetails() {
		List<BranchCreationEntity> listArea = branchService.getAllBranchList();
		return listArea.stream().map(dtoTransformer::branchCreationConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getBranchDetails/pagination")

	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")

	public APIResponse<Page<BranchCreationEntity>> getBranchWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<BranchCreationEntity> branchWithPagination;
		if (keyword.length() != 0) {
			branchWithPagination = branchService.getSearch(keyword, pageable);
		} else {
			branchWithPagination = branchService.getAllBranchWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(branchWithPagination.getSize(), branchWithPagination);
	}

	@GetMapping("/getbranchcreationdetailsReport")
	public String exportReport6() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<BranchCreationEntity> branchcreation = branchService.getAllBranchList();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:branchcreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(branchcreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\branchcreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/BranchCreate/Branch")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<BranchCreationDTO> branchCreate(@RequestBody BranchCreationDTO branchObj)
			throws ParseException {
		boolean s = branchService.getBranchByName(branchObj.getBranchName());
		if (s == true)
			throw new RecordAlreadyPresentException("Branch Name Already Present");
		BranchCreationEntity entity = this.dtoTransformer.branchCreationConvertToEntity(branchObj);
		BranchCreationEntity entityres = branchService.createOrUpdateBranch(entity);
		branchObj = this.dtoTransformer.branchCreationConvertToDto(entityres);
		return new ResponseEntity<>(branchObj, HttpStatus.OK);

	}

	@GetMapping("/getBranch/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public BranchCreationDTO getBranch(@PathVariable Long id) throws RecordNotFoundException {
		BranchCreationEntity branch = branchService.getBranchById(id);
		if (branch == null)
			throw new UserNotFoundException("Branch not Found " + id);
		BranchCreationDTO branchDto = this.dtoTransformer.branchCreationConvertToDto(branch);
		return branchDto;
	}

	@DeleteMapping("/deleteBranch/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteBranchById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		branchService.deleteBranchById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateBranch/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<BranchCreationEntity> updateBranch(@RequestBody BranchCreationDTO branch,
			@PathVariable long id) throws ParseException {
		BranchCreationEntity branchEntity = this.dtoTransformer.branchCreationConvertToEntity(branch);
		BranchCreationEntity branchEntityRes = branchService.updateBranch(branchEntity);
		return new ResponseEntity<>(branchEntityRes, HttpStatus.OK);
	}

	@GetMapping("/getStockDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<StockCreationEntity> getAllStockDetails() {
		List<StockCreationEntity> listStock = stockService.getAllStock();
		return listStock;
	}

	@PostMapping(value = "/StockCreate/Stock")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> stockCreate(@RequestBody StockCreationEntity areaObj) {
		boolean s = stockService.getStockName(areaObj.getStockName());
		if (s == true)
			throw new RecordAlreadyPresentException("Stock Name Already Present");
		stockService.createOrUpdateStock(areaObj);
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@GetMapping("/getStock/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public StockCreationEntity getStock(@PathVariable Long id) throws RecordNotFoundException {
		StockCreationEntity stock = stockService.getStockById(id);
		if (stock == null)
			throw new UserNotFoundException("Stock not Found " + id);
		return stock;
	}

	@DeleteMapping("/deleteStock/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteStockById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		stockService.deleteStockById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateStock/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<StockCreationEntity> updateStock(@RequestBody StockCreationEntity stock,
			@PathVariable long id) {
		StockCreationEntity entity = stockService.createOrUpdateStock(stock);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/getManufactureDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<ManufacturerCreationDTO> getAllManufactureDetails() {
		List<ManufacturerCreationEntity> listMan = maService.getAllManufactureList();
		return listMan.stream().map(dtoTransformer::manufactureCreationConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getManufacturerDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<ManufacturerCreationEntity>> getManufacturerWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {
		Page<ManufacturerCreationEntity> manufacturerWithPagination;
		if (keyword.length() != 0) {
			manufacturerWithPagination = maService.getSearch(keyword, pageable);
		} else {
			manufacturerWithPagination = maService.getAllManufacturerWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());
		return new APIResponse<>(manufacturerWithPagination.getSize(), manufacturerWithPagination);
	}

	@GetMapping("/getManufacturerDetailsReport")
	public String exportReport7() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<ManufacturerCreationEntity> manufacturercreation = maService.getAllManufactureList();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:manufacturercreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(manufacturercreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\manufacturercreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/ManfactureCreate/Manufacturer")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<ManufacturerCreationDTO> manufacturerkCreate(
			@RequestBody ManufacturerCreationDTO manufactureObj) throws ParseException {
		boolean s = maService.getManufactureByName(manufactureObj.getManufacturerName());
		if (s == true)
			throw new RecordAlreadyPresentException("Manufacture Name already present");
		ManufacturerCreationEntity entity = this.dtoTransformer.manufactureConvertToEntity(manufactureObj);
		ManufacturerCreationEntity entityRes = maService.createOrUpdateManufacture(entity);
		manufactureObj = this.dtoTransformer.manufactureCreationConvertToDto(entityRes);
		return new ResponseEntity<ManufacturerCreationDTO>(manufactureObj, HttpStatus.OK);
	}

	@PostMapping(value = "/ManfactureCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> manufacturerkCreate(@RequestBody List<ManufacturerCreationDTO> manufactureObj)
			throws ParseException {
		List<ManufacturerCreationEntity> salesEntity = this.dtoTransformer
				.manufactureConvertToEntityList(manufactureObj);
		try {
			maService.createOrUpdateManufactureList(salesEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/getManufacture/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ManufacturerCreationDTO getManufacture(@PathVariable Long id) throws RecordNotFoundException {
		ManufacturerCreationEntity mcreate = maService.getManufactureById(id);
		if (mcreate == null)
			throw new UserNotFoundException("Manufacture  not Found " + id);
		ManufacturerCreationDTO mcreateDTO = this.dtoTransformer.manufactureCreationConvertToDto(mcreate);
		return mcreateDTO;
	}

	@DeleteMapping("/deleteManufacture/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteManfuctureById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		maService.deleteManufactureById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateManufacture/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<ManufacturerCreationDTO> updateManufacture(@RequestBody ManufacturerCreationDTO mce,
			@PathVariable long id) throws ParseException {
		ManufacturerCreationEntity mfcEntity = this.dtoTransformer.manufactureConvertToEntity(mce);
		ManufacturerCreationEntity mfcEntityRes = maService.updateManufacture(mfcEntity);
		mce = this.dtoTransformer.manufactureCreationConvertToDto(mfcEntityRes);
		return new ResponseEntity<ManufacturerCreationDTO>(mce, HttpStatus.OK);
	}

	@GetMapping("/getGroupDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<GroupCreationDTO> getAllGroupDetails() {
		List<GroupCreationEntity> listGroup = groupService.getAllGroupList();
		return listGroup.stream().map(dtoTransformer::groupCreationConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getGroupDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")

	public APIResponse<Page<GroupCreationEntity>> getGroupWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<GroupCreationEntity> groupWithPagination;
		if (keyword.length() != 0) {
			groupWithPagination = groupService.getSearch(keyword, pageable);
		} else {
			groupWithPagination = groupService.getAllGroupWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(groupWithPagination.getSize(), groupWithPagination);
	}

	@GetMapping("/getGroupDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport8() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<GroupCreationEntity> groupcreationEntity = groupService.getAllGroupList();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:groupcreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(groupcreationEntity);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Groupcreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/GroupCreate/Group")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<GroupCreationDTO> groupCreate(@RequestBody GroupCreationDTO groupDTO) throws ParseException {
		boolean s = groupService.getGroupByName(groupDTO.getGroupName());
		if (s == true)
			throw new RecordAlreadyPresentException("Group Name Already Present");
		GroupCreationEntity entity = this.dtoTransformer.groupConvertToEntity(groupDTO);
		GroupCreationEntity entityRes = groupService.createOrUpdateGroup(entity);
		groupDTO = this.dtoTransformer.groupCreationConvertToDto(entityRes);
		return new ResponseEntity<>(groupDTO, HttpStatus.OK);

	}

	@PostMapping(value = "/GroupCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> groupCreate(@RequestBody List<GroupCreationDTO> groupDTO) throws ParseException {
		List<GroupCreationEntity> groupEntity = this.dtoTransformer.groupConvertToEntityList(groupDTO);
		try {
			groupService.createOrUpdateGroup(groupEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@GetMapping("/getGroup/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public GroupCreationDTO getGroup(@PathVariable Long id) throws RecordNotFoundException {
		GroupCreationEntity group = groupService.getGroupById(id);
		if (group == null)
			throw new UserNotFoundException("Group not Found " + id);
		GroupCreationDTO groupDto = this.dtoTransformer.groupCreationConvertToDto(group);
		return groupDto;
	}

	@DeleteMapping("/deleteGroup/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteGroupById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		groupService.deleteGroupById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateGroup/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<GroupCreationDTO> updateGroup(@RequestBody GroupCreationDTO group, @PathVariable long id)
			throws ParseException {
		GroupCreationEntity gEntity = this.dtoTransformer.groupConvertToEntity(group);
		GroupCreationEntity gEntityRes = groupService.updateGroup(gEntity);
		group = this.dtoTransformer.groupCreationConvertToDto(gEntityRes);
		return new ResponseEntity<>(group, HttpStatus.OK);
	}

	@GetMapping("/getStoreTypeDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<StoreTypeCreationDTO> getAllStoreTypeDetails() {
		List<StoreTypeCreationEntity> listStores = storeService.getAllStoreTypeList();
		return listStores.stream().map(dtoTransformer::storeTypeConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getStoreTypeDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<StoreTypeCreationEntity>> getStoreTypeWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<StoreTypeCreationEntity> storetypeWithPagination;
		if (keyword.length() != 0) {
			storetypeWithPagination = storeService.getSearch(keyword, pageable);
		} else {
			storetypeWithPagination = storeService.getAllStoreTypeWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(storetypeWithPagination.getSize(), storetypeWithPagination);
	}

	@GetMapping("/getStoreTypeCreationReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport9() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<StoreTypeCreationEntity> storetypecreation = storeService.getAllStoreTypeList();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:storetypecreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(storetypecreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Storetypecreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/StoreTypeCreate/StoreType")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<StoreTypeCreationDTO> storetypeCreate(@RequestBody StoreTypeCreationDTO storeObj)
			throws ParseException {
		boolean s = storeService.getStoreByName(storeObj.getStoreTypeName());
		if (s == true)
			throw new RecordAlreadyPresentException("Store Name Already Present");
		StoreTypeCreationEntity entity = this.dtoTransformer.storeTypeConvertToEntity(storeObj);
		StoreTypeCreationEntity entityres = storeService.createOrUpdateStoreType(entity);
		storeObj = this.dtoTransformer.storeTypeConvertToDto(entityres);
		return new ResponseEntity<>(storeObj, HttpStatus.OK);

	}

	@GetMapping("/getStoreType/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public StoreTypeCreationDTO getStoreType(@PathVariable Long id) throws RecordNotFoundException {
		StoreTypeCreationEntity storeEntity = storeService.getStoreById(id);
		if (storeEntity == null)
			throw new UserNotFoundException("Store Type  not Found " + id);
		StoreTypeCreationDTO storeDto = this.dtoTransformer.storeTypeConvertToDto(storeEntity);
		return storeDto;
	}

	@DeleteMapping("/deleteStoreType/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteStoreById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		storeService.deleteStoreById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateStoreType/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<StoreTypeCreationDTO> updateStoreType(@RequestBody StoreTypeCreationDTO storeDto,
			@PathVariable long id) throws ParseException {
		StoreTypeCreationEntity sEntity = this.dtoTransformer.storeTypeConvertToEntity(storeDto);
		StoreTypeCreationEntity sEntityRes = storeService.updateStoreType(sEntity);
		storeDto = this.dtoTransformer.storeTypeConvertToDto(sEntityRes);
		return new ResponseEntity<>(storeDto, HttpStatus.OK);
	}

	@GetMapping("/getHSNeDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<HsnSacCreationDTO> getAllHSNeDetails() {
		List<HsnSacCreationEntity> listHsn = hsnService.getAllHSNSACList();
		return listHsn.stream().map(dtoTransformer::hsnCreationConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getHsnSacDetails/pagination")

	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")

	public APIResponse<Page<HsnSacCreationEntity>> getHsnSacWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<HsnSacCreationEntity> hsnsacWithPagination;
		if (keyword.length() != 0) {
			hsnsacWithPagination = hsnService.getSearch(keyword, pageable);
		} else {
			hsnsacWithPagination = hsnService.getAllHsnSacWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(hsnsacWithPagination.getSize(), hsnsacWithPagination);
	}

	@GetMapping("/getHsnSacCreationReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport10() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<HsnSacCreationEntity> hsnsaccreation = hsnService.getAllHSNSACList();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:HsnSacCreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(hsnsaccreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\hsnsaccreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/HSNCreate/HSNSAC")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HsnSacCreationDTO> hsntypeCreate(@RequestBody HsnSacCreationDTO hsnDTO)
			throws ParseException {
		boolean s = hsnService.getHsnByName(hsnDTO.getHsnName());
		if (s == true)
			throw new RecordAlreadyPresentException("Store Name Already Present");
		HsnSacCreationEntity hsnEntity = this.dtoTransformer.hsnConvertToEntity(hsnDTO);
		HsnSacCreationEntity hsnEntityRes = hsnService.createOrUpdateHsn(hsnEntity);
		hsnDTO = this.dtoTransformer.hsnCreationConvertToDto(hsnEntityRes);
		return new ResponseEntity<>(hsnDTO, HttpStatus.OK);

	}

	@PostMapping(value = "/HSNCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> hsntypeCreateList(@RequestBody List<HsnSacCreationDTO> hsnDTO)
			throws ParseException {
		List<HsnSacCreationEntity> hsn = this.dtoTransformer.hsnConvertToEntityList(hsnDTO);
		try {
			hsnService.createOrUpdateHsnList(hsn);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@GetMapping("/getHSNType/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public HsnSacCreationDTO getHsnType(@PathVariable Long id) throws RecordNotFoundException {
		HsnSacCreationEntity hsn = hsnService.getHsnById(id);
		if (hsn == null)
			throw new UserNotFoundException("HSN Type not Found " + id);
		HsnSacCreationDTO hsnDTO = this.dtoTransformer.hsnCreationConvertToDto(hsn);
		return hsnDTO;
	}

	@DeleteMapping("/deleteHSNType/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteSHSNById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		hsnService.deleteHsnById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateHSNType/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HsnSacCreationDTO> updateHsnType(@RequestBody HsnSacCreationDTO hsnDTO, @PathVariable long id)
			throws ParseException {
		HsnSacCreationEntity hEntity = this.dtoTransformer.hsnConvertToEntity(hsnDTO);
		HsnSacCreationEntity hEntityRes = hsnService.updateHsn(hEntity);
		hsnDTO = this.dtoTransformer.hsnCreationConvertToDto(hEntityRes);
		return new ResponseEntity<>(hsnDTO, HttpStatus.OK);
	}

	@GetMapping("/getCompositionDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<CompostionCreationEntity> getAllCompositionDetails() {
		List<CompostionCreationEntity> listCom = comService.getAllCompositionList();
		return listCom;
	}

	@GetMapping("/getCompositionDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<CompostionCreationEntity>> getCompositionWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<CompostionCreationEntity> compostionWithPagination;
		if (keyword.length() != 0) {
			compostionWithPagination = comService.getSearch(keyword, pageable);
		} else {
			compostionWithPagination = comService.getAllCompostionWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(compostionWithPagination.getSize(), compostionWithPagination);
	}

	@GetMapping("/getCompositionDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport11() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<CompostionCreationEntity> compositioncreation = comService.getAllCompositionList();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:compostioncreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(compositioncreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\compositioncreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/CompostionCreate/Composition")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> compostionCreate(@RequestBody CompostionCreationEntity comObj) {
		boolean s = comService.getCompositonByName(comObj.getCompName());
		if (s == true)
			throw new RecordAlreadyPresentException("Composition Name Already Present");
		comService.createOrUpdateCompostion(comObj);
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@PostMapping(value = "/CompostionCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> compostionCreate(@RequestBody List<CompostionCreationDTO> comObj)
			throws ParseException {
		List<CompostionCreationEntity> groupEntity = this.dtoTransformer.compisitionConvertToEntityList(comObj);
		try {
			comService.createOrUpdateCompostion(groupEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@GetMapping("/getCompostion/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public CompostionCreationEntity getCompostionType(@PathVariable Long id) throws RecordNotFoundException {
		CompostionCreationEntity group = comService.getCompositionById(id);
		if (group == null)
			throw new UserNotFoundException("Compostion not Found " + id);
		return group;
	}

	@DeleteMapping("/deleteCompisition/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteCompositionById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		comService.deleteCompostionById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateCompositionType/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<CompostionCreationEntity> updateCompositionType(@RequestBody CompostionCreationEntity store,
			@PathVariable long id) {
		CompostionCreationEntity entity = comService.updateCompostion(store);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/getSchedulerDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<SchedulerCreationDTO> getAllScheduerDetails() {
		List<SchedulerCreationEntity> listSche = scService.getAllSchedulerList();
		return listSche.stream().map(dtoTransformer::schedulerConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getSchedulerDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<SchedulerCreationEntity>> getSchedulerWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<SchedulerCreationEntity> schedulerWithPagination;
		if (keyword.length() != 0) {
			schedulerWithPagination = scService.getSearch(keyword, pageable);
		} else {
			schedulerWithPagination = scService.getAllSchedulerWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(schedulerWithPagination.getSize(), schedulerWithPagination);
	}

	@GetMapping("/getSchedulerDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport12() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<SchedulerCreationEntity> schedulercreation = scService.getAllSchedulerList();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:SchedulerDetailsCreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(schedulercreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\SchedulerDetailscreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/SchedulerCreate/Scheduler")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SchedulerCreationDTO> schedulerCreate(@RequestBody SchedulerCreationDTO scDto)
			throws ParseException {
		boolean s = scService.getSchedulerByName(scDto.getSchedulerName());
		if (s == true)
			throw new RecordAlreadyPresentException("Scheduler Name Already Present");
		SchedulerCreationEntity entity = this.dtoTransformer.schedulerConvertToEntity(scDto);
		SchedulerCreationEntity entityRes = scService.createOrUpdateScheduler(entity);
		scDto = this.dtoTransformer.schedulerConvertToDto(entityRes);
		return new ResponseEntity<>(scDto, HttpStatus.OK);

	}

	@GetMapping("/getSceduler/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public SchedulerCreationDTO getSchedulerType(@PathVariable Long id) throws RecordNotFoundException {
		SchedulerCreationEntity scheduler = scService.getSchedulerById(id);
		if (scheduler == null)
			throw new UserNotFoundException("Scheduler not Found " + id);
		SchedulerCreationDTO dto = this.dtoTransformer.schedulerConvertToDto(scheduler);
		return dto;
	}

	@DeleteMapping("/deleteScheduler/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteSchedulerById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		scService.deleteSchedulerById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateSchedulerType/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<SchedulerCreationDTO> updateSchedulerType(@RequestBody SchedulerCreationDTO scheduleDTO,
			@PathVariable long id) throws ParseException {
		SchedulerCreationEntity entity = this.dtoTransformer.schedulerConvertToEntity(scheduleDTO);
		SchedulerCreationEntity entityRes = scService.updateScheduler(entity);
		scheduleDTO = this.dtoTransformer.schedulerConvertToDto(entityRes);
		return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
	}

	@GetMapping("/getPackDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<PackCreationDTO> getAllPackDetails() {
		List<PackCreationEntity> listpkg = packService.getAllPackList();
		return listpkg.stream().map(dtoTransformer::packCreateConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getPackDetails/pagination")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<PackCreationEntity>> getPackWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {

		Page<PackCreationEntity> packWithPagination;
		if (keyword.length() != 0) {
			packWithPagination = packService.getSearch(keyword, pageable);
		} else {
			packWithPagination = packService.getAllPackWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());

		return new APIResponse<>(packWithPagination.getSize(), packWithPagination);
	}

	@GetMapping("/getPackDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport13() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<PackCreationEntity> packcreation = packService.getAllPackList();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:packcreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(packcreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\packcreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@PostMapping(value = "/PackCreate/Pack")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<PackCreationDTO> PackCreate(@RequestBody PackCreationDTO pkgObj) throws ParseException {
		boolean s = packService.getPackByName(pkgObj.getPackName());
		if (s == true)
			throw new RecordAlreadyPresentException("Package Name Already Present");
		PackCreationEntity pkgEntity = this.dtoTransformer.packCreateConvertToEntity(pkgObj);
		PackCreationEntity pkgRes = packService.createOrUpdatePack(pkgEntity);
		pkgObj = this.dtoTransformer.packCreateConvertToDto(pkgRes);
		return new ResponseEntity<>(pkgObj, HttpStatus.OK);

	}

	@PostMapping(value = "/PackCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> PackCreateList(@RequestBody List<PackCreationDTO> pkgObj) throws ParseException {
		List<PackCreationEntity> packEntity = this.dtoTransformer.packCreateConvertToEntityList(pkgObj);
		try {
			packService.createOrUpdatePack(packEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/getPack/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public PackCreationDTO getPackType(@PathVariable Long id) throws RecordNotFoundException {
		PackCreationEntity pkg = packService.getPakById(id);
		if (pkg == null)
			throw new UserNotFoundException("Package not Found " + id);
		PackCreationDTO pkgObj = this.dtoTransformer.packCreateConvertToDto(pkg);
		return pkgObj;
	}

	@DeleteMapping("/deletePack/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deletePackById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		packService.deletePackById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updatePackType/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<PackCreationDTO> updatePackType(@RequestBody PackCreationDTO pack, @PathVariable long id)
			throws ParseException {
		PackCreationEntity pEntity = this.dtoTransformer.packCreateConvertToEntity(pack);
		PackCreationEntity pEntityRes = packService.createOrUpdatePack(pEntity);
		pack = this.dtoTransformer.packCreateConvertToDto(pEntityRes);
		return new ResponseEntity<>(pack, HttpStatus.OK);
	}

	@PostMapping(value = "/itemCreate/Item")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<ItemCreationDTO> itemCreate(@RequestBody ItemCreationDTO item) throws ParseException {
		/*
		 * boolean s = itemService.getItemName(item.getItemName()); if (s == true) throw
		 * new RecordAlreadyPresentException("Item Name Already Present");
		 */

		ItemCreationEntity itemEntity = this.dtoTransformer.itemConvertToEntity(item);
		ItemCreationEntity itemEntityRes = itemService.createOrUpdateItem(itemEntity);
		item = this.dtoTransformer.itemCreationConvertToDto(itemEntityRes);
		return new ResponseEntity<>(item, HttpStatus.OK);

	}

	@PostMapping(value = "/itemCreate/bulk")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> itemCreate(@RequestBody List<ItemCreationDTO> item) throws ParseException {
		List<ItemCreationEntity> itemEntity = this.dtoTransformer.itemConvertToEntityList(item);
		try {
			itemService.createOrUpdateItem(itemEntity);
		} catch (Exception e) {
			throw new UnableToProcessException("Currently unable to process request");
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/getItemDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<ItemCreationDTO> getAllItemDetails() {
		List<ItemCreationEntity> lisItms = itemService.getAllItemList();
		return lisItms.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());
	}

	@GetMapping("/getItemDetail")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<ItemCreationEntity>> getProductsWithPagination(@RequestParam int offset,
			@RequestParam int pageSize, @RequestParam String keyword, Pageable pageable) {
		Page<ItemCreationEntity> itemsWithPagination;
		if (keyword.length() != 0) {
			itemsWithPagination = itemService.getSearch(keyword, pageable);
		} else {
			itemsWithPagination = itemService.getAllItemWithPagination(offset, pageSize);
		}
		// Page<ItemCreationDTO> itemDTO=(Page<ItemCreationDTO>)
		// itemsWithPagination.stream().map(dtoTransformer::itemCreationConvertToDto).collect(Collectors.toList());
		return new APIResponse<>(itemsWithPagination.getSize(), itemsWithPagination);
	}

	@GetMapping("/getItemDetailsReport")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String exportReport14() throws FileNotFoundException, JRException {
		String path = "C:\\Users\\ADMIN\\Desktop\\Reports";
		List<ItemCreationEntity> itemcreation = itemService.getAllItemList();
		// List<AccountCreationDTO> list=
		// accountcreation.stream().map(dtoTransformer::accountCrSseationConvertToDto).collect(Collectors.toList());
		// load file and compile it
		File file = ResourceUtils.getFile("classpath: itemcreation.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(itemcreation);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Eclatansys");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		/*
		 * if (reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
		 * "\\salesmancreation.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
		 */
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\ itemcreation.pdf");
		// }

		return "report generated in path : " + path;
	}

	@GetMapping("/getItemDetails/Search")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public APIResponse<Page<ItemCreationEntity>> getAllItemDetails(@RequestParam String keyword, Pageable pageable) {
		Page<ItemCreationEntity> lisItms = itemService.getSearch(keyword, pageable);
		return new APIResponse<>(lisItms.getSize(), lisItms);
	}

	@GetMapping("/getItem/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ItemCreationDTO getItemDetails(@PathVariable Long id) throws RecordNotFoundException {
		ItemCreationEntity itm = itemService.getItemById(id);
		if (itm == null)
			throw new UserNotFoundException("Item not Found " + id);
		ItemCreationDTO item = this.dtoTransformer.itemCreationConvertToDto(itm);
		return item;
	}

	@DeleteMapping("/deleteItem/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<HttpStatus> deleteItemById(@PathVariable(name = "id") Long id)
			throws RecordNotFoundException {
		itemService.deleteItemById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/updateItemDetails/{id}")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<ItemCreationDTO> updateItemType(@RequestBody ItemCreationDTO itm, @PathVariable long id)
			throws ParseException {
		ItemCreationEntity entity = this.dtoTransformer.itemConvertToEntity(itm);
		ItemCreationEntity pEntity = itemService.updateItem(entity);
		itm = this.dtoTransformer.itemCreationConvertToDto(pEntity);
		return new ResponseEntity<>(itm, HttpStatus.OK);
	}

	@GetMapping("/getBestBeforeDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<BestBeforeEntity> getAllBestBeforeDetails() {
		List<BestBeforeEntity> lisItms = bestSevice.getAllBestBeforeTypes();
		return lisItms;
	}

	@GetMapping("/getWaringDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<WarningCreationEntity> getWarningDetails() {
		List<WarningCreationEntity> lisItms = wService.getAllWarningList();
		return lisItms;
	}

	@GetMapping("/getPurchaseEntrySettings")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<WarningCreationEntity> getPurchaseEntrySettings() {
		List<WarningCreationEntity> lisItms = wService.getAllWarningList();
		return lisItms;
	}

	@GetMapping("/getPaymentModes")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<PaymentModeEntity> getPaymentModes() {
		List<PaymentModeEntity> lisItms = paymentModeService.getAllPaymentModes();
		return lisItms;
	}

	@GetMapping("/getGSTPercentage")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public List<GSTPercentageEntity> getAllOpenStockGstPercentage() {
		List<GSTPercentageEntity> listCashWD = gstpercentageService.getAllGSTPercentage();
		return listCashWD;
	}

	@GetMapping("/getMacIdDetails")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public MacIdEntity getMackIdDetails() throws SocketException {
		MacIdEntity macDetails = new MacIdEntity();

		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		while (networkInterfaces.hasMoreElements()) {
			NetworkInterface ni = networkInterfaces.nextElement();
			byte[] hardwareAddress = ni.getHardwareAddress();
			if (hardwareAddress != null) {
				String[] hexadecimalFormat = new String[hardwareAddress.length];
				for (int i = 0; i < hardwareAddress.length; i++) {
					hexadecimalFormat[i] = String.format("%02X", hardwareAddress[i]);
				}
				System.out.println(String.join("-", hexadecimalFormat));

			}

		}
		System.getProperties().list(System.out);
		return macDetails;
	}
}
