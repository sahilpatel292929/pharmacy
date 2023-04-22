package com.ets.SecurePharmacy.transfomers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ets.SecurePharmacy.DTO.*;
import org.apache.tomcat.util.json.ParseException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ets.SecurePharmacy.tenant.model.AccountCreationEntity;
import com.ets.SecurePharmacy.tenant.model.AreaCreationEntity;
import com.ets.SecurePharmacy.tenant.model.BoyCreationEntity;
import com.ets.SecurePharmacy.tenant.model.BranchCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CashWithdrawEntity;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CounterSaleEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GSTTypeCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.OpenStockEntryEntity;
import com.ets.SecurePharmacy.tenant.model.OrderGenerationEntity;
import com.ets.SecurePharmacy.tenant.model.PackCreationEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryDetailsEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseOrderEntity;
import com.ets.SecurePharmacy.tenant.model.ReceiptEntryEntity;
import com.ets.SecurePharmacy.tenant.model.RouteCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntryDetailEntity;
import com.ets.SecurePharmacy.tenant.model.SalesManCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ShortageEntryEntity;
import com.ets.SecurePharmacy.tenant.model.StockAdjEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
import com.ets.SecurePharmacy.tenant.model.WarningCreationEntity;
import com.ets.SecurePharmacy.util.DateConvertUtils;

@Component
public class DTOTransfomers {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	DateConvertUtils dateUtils;

	public <S,D> D dtoToEntity(S dto, Class<D> entity) {
		return modelMapper.map(dto, entity);
	}

	public <S,D> D entityToDto(S entity, Class<D> dto) {
		return modelMapper.map(entity, dto);
	}
	
	public AccountCreationDTO accountCreationConvertToDto(AccountCreationEntity post) {
		AccountCreationDTO accountDto = modelMapper.map(post, AccountCreationDTO.class);
		return accountDto;
	}
	
	public AccountCreationEntity accountCreationConvertToEntity(AccountCreationDTO accountDTO) throws ParseException {
		AccountCreationEntity accountEntity = modelMapper.map(accountDTO, AccountCreationEntity.class);
		accountEntity.setOpeningBalDate(this.dateUtils.convertStringToDate(accountDTO.getOpeningBalDate()));
		return accountEntity;
	}
	
	public List<AccountCreationEntity> accountCreationConvertToEntityList(List<AccountCreationDTO> accountDTO) throws ParseException {
		List<AccountCreationEntity> entity = accountDTO
				  .stream()
				  .map(account -> modelMapper.map(account, AccountCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	/* No Date field */
	public AreaCreationDTO areaCreationConvertToDto(AreaCreationEntity post) {
		AreaCreationDTO areaDto = modelMapper.map(post, AreaCreationDTO.class);
		return areaDto;
	}
	
	public List<AreaCreationEntity> areaCreationConvertToEntityList(List<AreaCreationDTO> areaDTO) throws ParseException {
		List<AreaCreationEntity> entity = areaDTO
				  .stream()
				  .map(area-> modelMapper.map(area, AreaCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	public AreaCreationEntity areaCreationConvertToEntity(AreaCreationDTO areaDTO) throws ParseException {
		AreaCreationEntity areaEntity = modelMapper.map(areaDTO, AreaCreationEntity.class);
		return areaEntity;
	}
	/* No Date field */
	public BoyCreationDTO boyCreationConvertToDto(BoyCreationEntity post) {
		BoyCreationDTO boyDto = modelMapper.map(post, BoyCreationDTO.class);
		return boyDto;
	}
	
	public BoyCreationEntity boyCreationConvertToEntity(BoyCreationDTO boyDTO) throws ParseException {
		BoyCreationEntity boyEntity = modelMapper.map(boyDTO, BoyCreationEntity.class);
		return boyEntity;
	}
	
	public List<BoyCreationEntity> boyCreationConvertToEntity(List<BoyCreationDTO> boyDTO) throws ParseException {
		List<BoyCreationEntity> entity = boyDTO
				  .stream()
				  .map(boy-> modelMapper.map(boy, BoyCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	/* No Date field */
	public BranchCreationDTO branchCreationConvertToDto(BranchCreationEntity post) {
		BranchCreationDTO branchDto = modelMapper.map(post, BranchCreationDTO.class);
		return branchDto;
	}
	
	public BranchCreationEntity branchCreationConvertToEntity(BranchCreationDTO branchDTO) throws ParseException {
		BranchCreationEntity branchEntity = modelMapper.map(branchDTO, BranchCreationEntity.class);
		return branchEntity;
	}
	
	public List<BranchCreationEntity> branchCreationConvertToEntityList(List<BranchCreationDTO> branchDTO) throws ParseException {
		List<BranchCreationEntity> entity = branchDTO
				  .stream()
				  .map(branch-> modelMapper.map(branch, BranchCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	/* No Date field */
	public CashWithdrawDTO cashWithdrawConvertToDto(CashWithdrawEntity post) {
		CashWithdrawDTO cashWithdrawDto = modelMapper.map(post, CashWithdrawDTO.class);
		return cashWithdrawDto;
	}
	
	public CashWithdrawEntity cashWithdrawConvertToEntity(CashWithdrawDTO cashwithdrawDTO) throws ParseException {
		CashWithdrawEntity cashWithdrawEntity = modelMapper.map(cashwithdrawDTO, CashWithdrawEntity.class);
		cashWithdrawEntity.setEntryDate(this.dateUtils.convertStringToDate(cashwithdrawDTO.getEntryDate()));
		return cashWithdrawEntity;
	}
	
	public List<CashWithdrawEntity> cashWithdrawConvertToEntityList(List<CashWithdrawDTO> cashwithdrawDTO) throws ParseException {
		List<CashWithdrawEntity> entity = cashwithdrawDTO
				  .stream()
				  .map(cash-> modelMapper.map(cash, CashWithdrawEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	/* No Date field */
	public CounterSaleDTO counterSaleConvertToDto(CounterSaleEntity post) {
		CounterSaleDTO counterSaleDto = modelMapper.map(post, CounterSaleDTO.class);
		return counterSaleDto;
	}
	
	public CounterSaleEntity counterSaleConvertToEntity(CounterSaleDTO counterSaleDTO) throws ParseException {
		CounterSaleEntity counterSaleEntity = modelMapper.map(counterSaleDTO, CounterSaleEntity.class);
		counterSaleEntity.setEntryDate(this.dateUtils.convertStringToDate(counterSaleDTO.getEntryDate()));
		return counterSaleEntity;
	}
	
	public List<CounterSaleEntity> counterSaleConvertToEntityList(List<CounterSaleDTO> counterSaleDTO) throws ParseException {
		List<CounterSaleEntity> entity = counterSaleDTO
				  .stream()
				  .map(counter-> modelMapper.map(counter, CounterSaleEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	//Customer Creation
	public CustomerCreationDTO customerCreationConvertToDto(CustomerCreationEntity post) {
		CustomerCreationDTO customerSaleDto = modelMapper.map(post, CustomerCreationDTO.class);
		return customerSaleDto;
	}
	
	public CustomerCreationEntity customerCreationConvertToEntity(CustomerCreationDTO customercreateDTO) throws ParseException {
		CustomerCreationEntity customerCreationEntity = modelMapper.map(customercreateDTO, CustomerCreationEntity.class);
		customerCreationEntity.setOpeningBalDate(this.dateUtils.convertStringToDate(customercreateDTO.getOpeningBalDate()));
		return customerCreationEntity;
	}
	
	public List<CustomerCreationEntity> customerCreationConvertToEntityList(List<CustomerCreationDTO> customercreateDTO) throws ParseException {
		List<CustomerCreationEntity> entity = customercreateDTO
				  .stream()
				  .map(customer-> modelMapper.map(customer, CustomerCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	/*Discount Creation */
	public DiscountSlabCreationDTO discountSlabConvertToDto(DiscountSlabCreationEntity post) {
		DiscountSlabCreationDTO discountDto = modelMapper.map(post, DiscountSlabCreationDTO.class);
		return discountDto;
	}
	
	public DiscountSlabCreationEntity discountConvertToEntity(DiscountSlabCreationDTO discountDTO) throws ParseException {
		DiscountSlabCreationEntity discountEntity = modelMapper.map(discountDTO, DiscountSlabCreationEntity.class);
		
		return discountEntity;
	}
	
	public List<DiscountSlabCreationEntity> discountConvertToEntityList(List<DiscountSlabCreationDTO> discountDTO) throws ParseException {
		List<DiscountSlabCreationEntity> entity = discountDTO
				  .stream()
				  .map(discount-> modelMapper.map(discount, DiscountSlabCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	
	/* Sales man Creatoin */
	
	public SalesManCreationDTO salesManConvertToDto(SalesManCreationEntity post) {
		SalesManCreationDTO salesManDTO = modelMapper.map(post, SalesManCreationDTO.class);
		return salesManDTO;
	}
	
	public SalesManCreationEntity salesManConvertToEntity(SalesManCreationDTO salesManDTO) throws ParseException {
		SalesManCreationEntity salesManEntity = modelMapper.map(salesManDTO, SalesManCreationEntity.class);
		
		return salesManEntity;
	}
	
	public List<SalesManCreationEntity> salesManConvertToEntityList(List<SalesManCreationDTO> salesManDTO) throws ParseException {
		List<SalesManCreationEntity> entity = salesManDTO
				  .stream()
				  .map(salesman -> modelMapper.map(salesman, SalesManCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	/*Group Creation */
	public GroupCreationDTO groupCreationConvertToDto(GroupCreationEntity post) {
		GroupCreationDTO groupDto = modelMapper.map(post, GroupCreationDTO.class);
		return groupDto;
	}
	public GroupCreationEntity groupConvertToEntity(GroupCreationDTO groupDTO) throws ParseException {
		GroupCreationEntity groupEntity = modelMapper.map(groupDTO, GroupCreationEntity.class);
		
		return groupEntity;
	}
	
	public List<GroupCreationEntity> groupConvertToEntityList(List<GroupCreationDTO> groupDTO) throws ParseException {
		List<GroupCreationEntity> entity = groupDTO
				  .stream()
				  .map(group-> modelMapper.map(group, GroupCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	
	/*GST Type Creation */
	public GSTTypeCreationDTO gstTypeCreationConvertToDto(GSTTypeCreationEntity post) {
		GSTTypeCreationDTO groupDto = modelMapper.map(post, GSTTypeCreationDTO.class);
		return groupDto;
	}
	public GSTTypeCreationEntity gstTypeConvertToEntity(GSTTypeCreationDTO gstTypeDTO) throws ParseException {
		GSTTypeCreationEntity gstTypeEntity = modelMapper.map(gstTypeDTO, GSTTypeCreationEntity.class);
		
		return gstTypeEntity;
	}
	/*HSN Creation */
	public HsnSacCreationDTO hsnCreationConvertToDto(HsnSacCreationEntity post) {
		HsnSacCreationDTO hsnDto = modelMapper.map(post, HsnSacCreationDTO.class);
		return hsnDto;
	}
	public HsnSacCreationEntity hsnConvertToEntity(HsnSacCreationDTO hsnDTO) throws ParseException {
		HsnSacCreationEntity hsnEntity = modelMapper.map(hsnDTO, HsnSacCreationEntity.class);
		
		return hsnEntity;
	}
	
	public List<HsnSacCreationEntity> hsnConvertToEntityList(List<HsnSacCreationDTO> hsnDTO) throws ParseException {
		List<HsnSacCreationEntity> entity = hsnDTO
				  .stream()
				  .map(hsn-> modelMapper.map(hsn, HsnSacCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	
	/* Composition Creation*/
	public CompostionCreationDTO compositionCreationConvertToDto(CompostionCreationEntity post) {
		CompostionCreationDTO compisitionDto = modelMapper.map(post, CompostionCreationDTO.class);
		return compisitionDto; 
	}
	public CompostionCreationEntity compositionConvertToEntity(CompostionCreationDTO compisitionTO) throws ParseException {
		CompostionCreationEntity compositionEntity = modelMapper.map(compisitionTO, CompostionCreationEntity.class);
		
		return compositionEntity;
	}
	
	public List<CompostionCreationEntity> compisitionConvertToEntityList(List<CompostionCreationDTO> compDTO) throws ParseException {
		List<CompostionCreationEntity> entity = compDTO
				  .stream()
				  .map(comp-> modelMapper.map(comp, CompostionCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	/*Item Creation */
	public ItemCreationDTO itemCreationConvertToDto(ItemCreationEntity post) {
		ItemCreationDTO itemDto = modelMapper.map(post, ItemCreationDTO.class);
		return itemDto;
	}
	public ItemCreationEntity itemConvertToEntity(ItemCreationDTO itemDTO) throws ParseException {
		ItemCreationEntity itemEntity = modelMapper.map(itemDTO, ItemCreationEntity.class);
		
		return itemEntity;
	}
	
	public List<ItemCreationEntity> itemConvertToEntityList(List<ItemCreationDTO> itemDTO) throws ParseException {
		List<ItemCreationEntity> entity = itemDTO
				  .stream()
				  .map(item-> modelMapper.map(item, ItemCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	
	/*manufacture Creation */
	public ManufacturerCreationDTO manufactureCreationConvertToDto(ManufacturerCreationEntity post) {
		ManufacturerCreationDTO mfcDto = modelMapper.map(post, ManufacturerCreationDTO.class);
		return mfcDto;
	}
	public ManufacturerCreationEntity manufactureConvertToEntity(ManufacturerCreationDTO mfcDTO) throws ParseException {
		ManufacturerCreationEntity mfcEntity = modelMapper.map(mfcDTO, ManufacturerCreationEntity.class);
		
		return mfcEntity;
	}
	
	public List<ManufacturerCreationEntity> manufactureConvertToEntityList(List<ManufacturerCreationDTO> mfcDTO) throws ParseException {
		List<ManufacturerCreationEntity> entity = mfcDTO
				  .stream()
				  .map(mfc-> modelMapper.map(mfc, ManufacturerCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	/*opening stock Creation */
	public OpenStockEntryDTO openingStockConvertToDto(OpenStockEntryEntity post) {
		OpenStockEntryDTO openStDTO = modelMapper.map(post, OpenStockEntryDTO.class);
		return openStDTO;
	}
	public OpenStockEntryEntity openingStockConvertToEntity(OpenStockEntryDTO openStDTO) throws ParseException {
		OpenStockEntryEntity openStEntity = modelMapper.map(openStDTO, OpenStockEntryEntity.class);
		openStEntity.setOrderDate(this.dateUtils.convertStringToDate(openStDTO.getOrderDate()));
		/*List<OpenStockEntryDetailsEntity> openDetailDao= new ArrayList<>(); 
		
		 * openStDTO.getOpenStockDetails().forEach(details->{
		 * OpenStockEntryDetailsEntity detailDto= new OpenStockEntryDetailsEntity();
		 * detailDto.setBillDate(this.dateUtils.convertStringToDate(details.getBillDate(
		 * ))); detailDto.setDiscount(details.getDiscount());
		 * detailDto.setItemName(details.getItemName());
		 * detailDto.setPurchaseRate(details.getPurchaseRate());
		 * detailDto.setQty(details.getQty());
		 * detailDto.setSchemeQty(details.getSchemeQty()); if(details.getId()!=null) {
		 * detailDto.setId(details.getId()); } openDetailDao.add(detailDto); });
		 * openStEntity.setOpenStockDetails(openDetailDao);
		 * 
		 */
		return openStEntity;
	}
	
	/*Order Generation Creation */
	public OrderGenerationDTO orderGenerateConvertToDto(OrderGenerationEntity post) {
		OrderGenerationDTO orderDTO = modelMapper.map(post, OrderGenerationDTO.class);
		return orderDTO;
	}
	public OrderGenerationEntity orderGenerateConvertToEntity(OrderGenerationDTO orderDTO) throws ParseException {
		OrderGenerationEntity orderEntity = modelMapper.map(orderDTO, OrderGenerationEntity.class);
		orderEntity.setOrderDate(this.dateUtils.convertStringToDate(orderDTO.getOrderDate()));
		return orderEntity;
	}
	
	/* Pack Creation */
	public PackCreationDTO packCreateConvertToDto(PackCreationEntity post) {
		PackCreationDTO packDTO = modelMapper.map(post, PackCreationDTO.class);
		return packDTO;
	}
	public PackCreationEntity packCreateConvertToEntity(PackCreationDTO packDTO) throws ParseException {
		PackCreationEntity packEntity = modelMapper.map(packDTO, PackCreationEntity.class);
		
		return packEntity;
	}
	
	public List<PackCreationEntity> packCreateConvertToEntityList(List<PackCreationDTO> packDTO) throws ParseException {
		List<PackCreationEntity> entity = packDTO
				  .stream()
				  .map(pack-> modelMapper.map(pack, PackCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	
	
	/* Purchase Entry Creation date contains */
	public PurchaseEntryDTO purchaseEntryConvertToDto(PurchaseEntryEntity post) {
		PurchaseEntryDTO purchaseDTO = modelMapper.map(post, PurchaseEntryDTO.class);
		return purchaseDTO;
	}
	public PurchaseEntryEntity purchaseEntryConvertToEntity(PurchaseEntryDTO purchaseDTO) throws ParseException {
		PurchaseEntryEntity purchaseEntity = modelMapper.map(purchaseDTO, PurchaseEntryEntity.class);
		purchaseEntity.setDuedate(this.dateUtils.convertStringToDate(purchaseDTO.getDuedate()));
		purchaseEntity.setEntrydate(this.dateUtils.convertStringToDate(purchaseDTO.getEntrydate()));
		purchaseEntity.setInvoiceDate(this.dateUtils.convertStringToDate(purchaseDTO.getInvoiceDate()));
		
		purchaseEntity.setFooterDate(this.dateUtils.convertStringToDate(purchaseDTO.getFooterDate()));
		List<PurchaseEntryDetailsEntity> listdetailEntity= new ArrayList<>();
		//PurchaseEntryDetailsEntity detailEntity= new PurchaseEntryDetailsEntity();
		purchaseDTO.getPurchaseDetails().forEach(details->{
			PurchaseEntryDetailsEntity testEntityDto = modelMapper.map(details , PurchaseEntryDetailsEntity.class);
			/*detailEntity.setName(details.getName());
			detailEntity.setInvoiceNo(details.getInvoiceNo());
			detailEntity.setBatch(details.getBatch());*/
			//testEntityDto.setBestBefore(this.dateUtils.convertStringToDate(details.getBestBefore()));
			//testEntityDto.setExpiryDate(this.dateUtils.convertStringToDate(details.getExpiryDate()));
			/*detailEntity.setQty(details.getQty());
			detailEntity.setFreeQty(details.getFreeQty());
			detailEntity.setMrp(details.getMrp());
			detailEntity.setPurchaseRate(details.getPurchaseRate());
			detailEntity.setDiscount(details.getDiscount());
			detailEntity.setDiscAmount(details.getDiscAmount());
			detailEntity.setSchdiscAmount(details.getSchdiscAmount());
			detailEntity.setGst(details.getGst());
			detailEntity.setTaxAmount(details.getTaxAmount());
			detailEntity.setQpk(details.getQpk());
			detailEntity.setMaxdate(details.getMaxdate());
			detailEntity.setSrt(details.getSrt());
			detailEntity.setGrossAmt(details.getGrossAmt());
			detailEntity.setNetAmt(details.getNetAmt());
			detailEntity.setMfgdateFlag(details.getMfgdateFlag());*/
			listdetailEntity.add(testEntityDto);
		});
		purchaseEntity.setPurchaseDetails(listdetailEntity);
		return purchaseEntity;
	}

	public PurchaseReportDto purchaseEntryDetailsToReportDto(PurchaseEntryDetailsEntity entity) {
		TypeMap<PurchaseEntryDetailsEntity, PurchaseReportDto> mapper = getMapping();
		mapper.addMapping(e -> e.getPurchaseEntryEntity().getInvoiceDate(), PurchaseReportDto::setDate);
		mapper.addMapping(e -> e.getPurchaseEntryEntity().getEntrydate(), PurchaseReportDto::setEntryDate);
		mapper.addMapping(e -> e.getPurchaseEntryEntity().getSupplierName(), PurchaseReportDto::setSupplierName);
		mapper.addMapping(PurchaseEntryDetailsEntity::getInvoiceNo, PurchaseReportDto::setBillNo);
		mapper.addMapping(PurchaseEntryDetailsEntity::getId, PurchaseReportDto::setEntryNo);
		mapper.addMapping(PurchaseEntryDetailsEntity::getNetAmt, PurchaseReportDto::setBillValue);
		mapper.addMapping(PurchaseEntryDetailsEntity::getGrossAmt, PurchaseReportDto::setSubTotal);
		mapper.addMapping(PurchaseEntryDetailsEntity::getGst, PurchaseReportDto::setGst);

		return this.modelMapper.map(entity, PurchaseReportDto.class);
	}

	private TypeMap<PurchaseEntryDetailsEntity, PurchaseReportDto> getMapping() {
		TypeMap<PurchaseEntryDetailsEntity, PurchaseReportDto> mapper = this.modelMapper.getTypeMap(PurchaseEntryDetailsEntity.class, PurchaseReportDto.class);
		if(mapper == null)
			mapper = this.modelMapper.createTypeMap(PurchaseEntryDetailsEntity.class, PurchaseReportDto.class, modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT));
		return mapper;
	}

//	public PurchaseReportDto purchaseEntryToReportDto(PurchaseEntryEntity purchaseEntryEntity) {
//		TypeMap<PurchaseEntryEntity, PurchaseReportDto> mapper = this.modelMapper.createTypeMap(PurchaseEntryEntity.class, PurchaseReportDto.class);
//		mapper.addMapping(PurchaseEntryEntity::getEntrydate, PurchaseReportDto::setEntryDate);
//		mapper.addMapping(PurchaseEntryEntity::getGstamount, PurchaseReportDto::setGst);
//
//
//		PurchaseReportDto purchaseReportDto = this.modelMapper.map(purchaseEntryEntity, PurchaseReportDto.class);
//
//		return purchaseReportDto;
//	}
	
	/* Purchase Order Creation date contains */
	public PurchaseOrderDTO purchaseOrderConvertToDto(PurchaseOrderEntity post) {
		PurchaseOrderDTO purchaseOrderDTO = modelMapper.map(post, PurchaseOrderDTO.class);
		return purchaseOrderDTO;
	}
	public PurchaseOrderEntity purchaseOrderConvertToEntity(PurchaseOrderDTO purchaseOrderDTO) throws ParseException {
		PurchaseOrderEntity purchaseEntity = modelMapper.map(purchaseOrderDTO, PurchaseOrderEntity.class);
		purchaseEntity.setOrderDate(this.dateUtils.convertStringToDate(purchaseOrderDTO.getOrderDate()));
		return purchaseEntity;
	}
	
	/*  Receipt Entry with Date */
	public ReceiptEntryDTO receiptConvertToDto(ReceiptEntryEntity post) {
		ReceiptEntryDTO receiptDTO = modelMapper.map(post, ReceiptEntryDTO.class);
		return receiptDTO;
	}
	public ReceiptEntryEntity receiptConvertToEntity(ReceiptEntryDTO receiptDTO) throws ParseException {
		ReceiptEntryEntity receiptEntity = modelMapper.map(receiptDTO, ReceiptEntryEntity.class);
		receiptEntity.setEntryDate(this.dateUtils.convertStringToDate(receiptDTO.getEntryDate()));
		receiptEntity.setRefernceDate(this.dateUtils.convertStringToDate(receiptDTO.getRefernceDate()));
		return receiptEntity;
	}
	

	/*  Route creation */
	public RouteCreationDTO routeConvertToDto(RouteCreationEntity post) {
		RouteCreationDTO routeDTO = modelMapper.map(post, RouteCreationDTO.class);
		return routeDTO;
	}
	public RouteCreationEntity routeConvertToEntity(RouteCreationDTO routeDTO) throws ParseException {
		RouteCreationEntity routeEntity = modelMapper.map(routeDTO, RouteCreationEntity.class);
		
		return routeEntity;
	}
	
	
	/*  Sale Invoice it contains Date*/
	public SalesInvoiceDTO saleInvoiceConvertToDto(SalesInvoiceEntity post) {
		SalesInvoiceDTO salesDTO = modelMapper.map(post, SalesInvoiceDTO.class);
		return salesDTO;
	}
	public SalesInvoiceEntity saleInvoiceConvertToEntity(SalesInvoiceDTO salesDTO) throws ParseException {
		SalesInvoiceEntity salesEntity = modelMapper.map(salesDTO, SalesInvoiceEntity.class);
		//salesEntity.setBillDate(this.dateUtils.convertStringToDate(salesDTO.getBillDate()));
	//	salesEntity.setRemainderDate(this.dateUtils.convertStringToDate(salesDTO.getRemainderDate()));
		List<SalesInvoiceEntryDetailEntity> listDetails= new ArrayList<>();
		salesDTO.getStockinvoiceDetails().forEach(details->{
			SalesInvoiceEntryDetailEntity detailsEntity=new SalesInvoiceEntryDetailEntity();
			//detailsEntity.setExpiryDate(this.dateUtils.convertStringToDate(details.getExpiryDate()));
			listDetails.add(detailsEntity);
		});
		salesEntity.setStockinvoiceDetails(listDetails);
		return salesEntity;
	}
	
	/*  Scheduler Creation*/
	public SchedulerCreationDTO schedulerConvertToDto(SchedulerCreationEntity post) {
		SchedulerCreationDTO schedulerDTO = modelMapper.map(post, SchedulerCreationDTO.class);
		return schedulerDTO;
	}
	public SchedulerCreationEntity  schedulerConvertToEntity(SchedulerCreationDTO schedulerDTO) throws ParseException {
		SchedulerCreationEntity schedulerEntity = modelMapper.map(schedulerDTO, SchedulerCreationEntity.class);
		
		return schedulerEntity;
	}
	
	/*  Shortage entry contains Date */
	public ShortageEntryDTO saleInvoiceConvertToDto(ShortageEntryEntity post) {
		ShortageEntryDTO shortageDTO = modelMapper.map(post, ShortageEntryDTO.class);
		return shortageDTO;
	}
	public ShortageEntryEntity saleInvoiceConvertToEntity(ShortageEntryDTO shortageDTO) throws ParseException {
		ShortageEntryEntity shortageEntity = modelMapper.map(shortageDTO, ShortageEntryEntity.class);
		shortageEntity.setEntryDate(this.dateUtils.convertStringToDate(shortageDTO.getEntryDate()));
		return shortageEntity;
	}
	
	
	/*  Stock Adj contains Date */
	public StockAdjDTO stockAdjConvertToDto(StockAdjEntity post) {
		StockAdjDTO stockAdjDTO = modelMapper.map(post, StockAdjDTO.class);
		return stockAdjDTO;
	}
	public StockAdjEntity stockAdjConvertToEntity(StockAdjDTO stockAdjDTO) throws ParseException {
		StockAdjEntity stockAdjEntity = modelMapper.map(stockAdjDTO, StockAdjEntity.class);
		stockAdjEntity.setEntryDate(this.dateUtils.convertStringToDate(stockAdjDTO.getEntryDate()));
		
		return stockAdjEntity;
	}
	
	
	/*  Store Type */
	public StoreTypeCreationDTO storeTypeConvertToDto(StoreTypeCreationEntity post) {
		StoreTypeCreationDTO storeTypeDTO = modelMapper.map(post, StoreTypeCreationDTO.class);
		return storeTypeDTO;
	}
	public StoreTypeCreationEntity storeTypeConvertToEntity(StoreTypeCreationDTO storeTypeDTO) throws ParseException {
		StoreTypeCreationEntity storeTypeEntity = modelMapper.map(storeTypeDTO, StoreTypeCreationEntity.class);
		
		return storeTypeEntity;
	}
	
	
	/*  Supplier creation */
	public SupplierCreationDTO supplierConvertToDto(SupplierCreationEntity post) {
		SupplierCreationDTO supplierDTO = modelMapper.map(post, SupplierCreationDTO.class);
		return supplierDTO;
	}
	public SupplierCreationEntity supplierConvertToEntity(SupplierCreationDTO supplierDTO) throws ParseException {
		SupplierCreationEntity supplierEntity = modelMapper.map(supplierDTO, SupplierCreationEntity.class);
		supplierEntity.setOpeningBalDate(this.dateUtils.convertStringToDate(supplierDTO.getOpeningBalDate()));
		return supplierEntity;
	}
	
	public List<SupplierCreationEntity> supplierConvertToEntityList(List<SupplierCreationDTO> supplierDTO) throws ParseException {
		List<SupplierCreationEntity> entity = supplierDTO
				  .stream()
				  .map(supplier-> modelMapper.map(supplier, SupplierCreationEntity.class))
				  .collect(Collectors.toList());
		return entity;
	}
	/*  Warning  creation */
	public WarningCreationDTO warningConvertToDto(WarningCreationEntity post) {
		WarningCreationDTO warningDTO = modelMapper.map(post, WarningCreationDTO.class);
		return warningDTO;
	}
	public WarningCreationEntity warningConvertToEntity(WarningCreationDTO warningDTO) throws ParseException {
		WarningCreationEntity warningEntity = modelMapper.map(warningDTO, WarningCreationEntity.class);
		
		return warningEntity;
	}
}
