package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.DTO.PurchaseReportDto;
import com.ets.SecurePharmacy.DTO.PurchaseReportFilterDTO;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.service.PurchaseEntryService;
import com.ets.SecurePharmacy.tenant.dao.BatchDetailsRepository;
import com.ets.SecurePharmacy.tenant.dao.BatchRepository;
import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.tenant.dao.PurchaseEntryDetailsRepository;
import com.ets.SecurePharmacy.tenant.dao.PurchaseEntryEntityDraftRepository;
import com.ets.SecurePharmacy.tenant.dao.PurchaseEntryRepository;
import com.ets.SecurePharmacy.tenant.dao.SupplierLedgerRepository;
import com.ets.SecurePharmacy.tenant.model.AccountLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.BatchDetailsEntity;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryDetailsEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntityDraft;
import com.ets.SecurePharmacy.tenant.model.SupplierLedgerEntity;
import com.ets.SecurePharmacy.transfomers.DTOTransfomers;
import com.ets.SecurePharmacy.util.BaseMethod;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ets.SecurePharmacy.tenant.model.QPurchaseEntryDetailsEntity.purchaseEntryDetailsEntity;

@Service
public class PurchaseEntryServiceImpl implements PurchaseEntryService {

	@Autowired
	PurchaseEntryRepository peRepo;
	@Autowired
	ItemStockRepository itemRepo;

	@Autowired
	BatchRepository batchRepository;

	@Autowired
	private DTOTransfomers transfomers;

	@Autowired
	private PurchaseEntryDetailsRepository pedRepo;

	@Autowired
	private BaseMethod baseMethod;

	@Autowired
	private PurchaseEntryEntityDraftRepository purchaseEntryEntityDraftRepository;

	@Autowired
	private SupplierLedgerRepository supplierLedgerRepository;

	@Autowired
	private BatchDetailsRepository batchDetailsRepository;
	

	@Transactional
	public List<PurchaseEntryEntity> getAllAccounts() {

		List<PurchaseEntryEntity> result;
		try {
			result = (List<PurchaseEntryEntity>) peRepo.findAll();
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<PurchaseEntryEntity>();
		}

	}

	@Transactional(rollbackFor = { Exception.class })
	public PurchaseEntryEntity createOrUpdateAccounts(PurchaseEntryEntity entity) {
		try {
			Long userId = baseMethod.getUserId();

			LocalDate date = LocalDate.now();

			final PurchaseEntryEntity purchaseEntryEntity = entity;

			entity.getPurchaseDetails().forEach(details -> {

				// Item stock
				ItemStockEntity itmEntitydetails = itemRepo.findByItemEntityId(details.getItems().getId());
				ItemStockEntity itemStockEntity = new ItemStockEntity();
				itemStockEntity.setAddStock(details.getQty());
				Long availableBalance = 0l;
				if (itmEntitydetails != null) {
					availableBalance = itmEntitydetails.getAvailableStock();
				}

				itemStockEntity.setAvailableStock(availableBalance + details.getQty());
				itemStockEntity.setCreatedTime(LocalDateTime.now());
				itemStockEntity.setLastUpdateTime(LocalDateTime.now());
				itemStockEntity.setLessStock(0.00);
				itemStockEntity.setItemEntity(details.getItems());
				itemRepo.save(itemStockEntity);

				// Batch creation
				BatchEntity batchEntity = batchRepository.getMRPand(details.getBatch(), details.getItems().getId());
				Double availableStock = 0.00;
				if (batchEntity != null) {
					BatchDetailsEntity batchDetailsEntity = batchDetailsRepository
							.getLastBatchDetailsEntityByBatchId(batchEntity.getId());
					if (batchDetailsEntity != null) {
						availableStock = batchDetailsEntity.getAvailableStock();
					}
					batchDetailsEntity(purchaseEntryEntity, details, batchEntity, availableStock);
				} else {
					BatchEntity batchCreate = new BatchEntity();
					batchCreate.setBatchNo(details.getBatch());
					batchCreate.setExpiry(details.getExpiryDate());
					batchCreate.setItems(details.getItems());
					batchCreate.setMrp((double)details.getMrp());
					batchCreate.setQtyPerPack(details.getQpk());
					batchCreate.setAvailableStock(details.getQty().doubleValue());
					batchDetailsEntity(purchaseEntryEntity, details, batchCreate, availableStock);
				}
			});

			// Supplier Ledger Details
		
			entity = peRepo.save(entity);
			// Account Ledger creation 
			this.accountLedgerCreation(entity);
			purchaseEntryEntityDraftRepository.deleteByCreatedUser(userId);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return entity;
	}

	private void accountLedgerCreation(PurchaseEntryEntity entity) {

		SupplierLedgerEntity accountLedgerEntity= new SupplierLedgerEntity();
		accountLedgerEntity.setSupplierCreationEntity(entity.getSupplierDetails());
		accountLedgerEntity.setTransactionId("PE_"+entity.getId());
		accountLedgerEntity.setLessAmount((double)entity.getNetAmount());
		accountLedgerEntity.setCreatedDate(LocalDateTime.now());
		accountLedgerEntity.setTransactionType("Purchase Entry ");
		accountLedgerEntity.setDescription("Add Net Amount from the Purchase Entry ="+ entity.getNetAmount()+" " );;
		try {
			supplierLedgerRepository.save(accountLedgerEntity);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new UnableToProcessException("Unable to process Account Ledger Creation at this moment, Try Again! After some time");
		}
		
	}
	private void batchDetailsEntity(PurchaseEntryEntity purchaseEntryEntity, PurchaseEntryDetailsEntity details,
			BatchEntity batchEntity, Double availableStock) {
		BatchDetailsEntity batchDetailsEntity = new BatchDetailsEntity();
		batchDetailsEntity.setSupplierDetails(purchaseEntryEntity.getSupplierDetails());
		batchDetailsEntity.setSRT((double)details.getSrt());
		
		batchDetailsEntity.setAvailableStock((double)availableStock + details.getQty());
		batchDetailsEntity.setPurchaseRate((double)details.getPurchaseRate());
		batchDetailsEntity.setQty((double)details.getQty());
		batchDetailsEntity.setBatchEntity(batchEntity);
		batchDetailsEntity.setLooseQty((double)details.getQty() * details.getQpk());
		BigDecimal bd = new BigDecimal(details.getPurchaseRate() / details.getQpk()).setScale(4, RoundingMode.HALF_UP);
		Double loosePuchaseRate = bd.doubleValue();
		
		BigDecimal bd1 = new BigDecimal(details.getNetAmt() / details.getQty()).setScale(4, RoundingMode.HALF_UP);
		Double netcost = bd1.doubleValue();
		BigDecimal bd2 = new BigDecimal(netcost / details.getQpk()).setScale(4, RoundingMode.HALF_UP);
		Double loosenetCost = bd2.doubleValue();
		BigDecimal bd3 = new BigDecimal(details.getSrt()/details.getQpk()).setScale(4, RoundingMode.HALF_UP);
		Double looseSRT = bd3.doubleValue();
		batchDetailsEntity.setLooseSRT(looseSRT);
		batchDetailsEntity.setLoosePurchaseRate(loosePuchaseRate);
		batchDetailsEntity.setNetCost(netcost);
		batchDetailsEntity.setLooseNetCost(loosenetCost);
		batchEntity.setBatchDetails(Collections.singletonList(batchDetailsEntity));
		batchRepository.save(batchEntity);
	}

	@Transactional
	public PurchaseEntryEntity getPurchaseById(Long id) throws RecordNotFoundException {
		Optional<PurchaseEntryEntity> purchase;
		try {
			purchase = peRepo.findById(id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (purchase.isPresent()) {
			return purchase.get();
		} else {
			throw new RecordNotFoundException("No Purchase Entry record exist for given id");
		}
	}

	public String getPurchaseDetailByInvoice(String invoice) {
		List<PurchaseEntryEntity> cus;
		try {
			cus = peRepo.findByInvoiceNo(invoice);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (cus.size() > 0) {
			throw new RecordAlreadyPresentException("Invoice Number already present");
		} else {
			return "NotPresent";
		}
	}

	@Override
	public List<PurchaseEntryEntity> getLastPuchaseDetails(Long id) {
		List<PurchaseEntryEntity> result;
		try {
			result = peRepo.findItemRecentDetails(id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<PurchaseEntryEntity>();
		}
	}

	@Override
	public Page<PurchaseEntryEntity> getAllPurchaseOrderWithPagination(int offset, int pageSize) {
		Page<PurchaseEntryEntity> result;
		try {
			result = peRepo.findAll(PageRequest.of(offset, pageSize));
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;

	}

	@Override
	public Page<PurchaseEntryEntity> getSearch(String query, Pageable pageable) {
		Page<PurchaseEntryEntity> result;

		try {
			result = peRepo.findAllBySupplierNameContains(query, pageable);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;
	}

	@Override
	public Page<PurchaseReportDto> getPurchaseReport(PurchaseReportFilterDTO filter, Pageable pageable) {

		BooleanBuilder predicate = new BooleanBuilder();

		if (!ObjectUtils.isEmpty(filter.getStartDate()))
			predicate.and(purchaseEntryDetailsEntity.purchaseEntryEntity.entrydate.goe(filter.getStartDate()));

		if (!ObjectUtils.isEmpty(filter.getEndDate()))
			predicate.and(purchaseEntryDetailsEntity.purchaseEntryEntity.entrydate.loe(filter.getEndDate()));

		if (!ObjectUtils.isEmpty(filter.getItemIds()))
			predicate.and(purchaseEntryDetailsEntity.items.Id.in(filter.getItemIds()));

		if (!ObjectUtils.isEmpty(filter.getSupplierIds()))
			predicate
					.and(purchaseEntryDetailsEntity.purchaseEntryEntity.supplierDetails.id.in(filter.getSupplierIds()));

		return pedRepo.findAll(predicate, pageable).map(transfomers::purchaseEntryDetailsToReportDto);
	}

	@Override
	public String getPurchaseDetailByInvoiceAndSupplier(String invoice, Long id) {
		// TODO Auto-generated method stub
		List<PurchaseEntryEntity> cus;
		try {
			cus = peRepo.findByinvoiceNoAndSupplierId(invoice, id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if (cus.size() > 0) {
			throw new RecordAlreadyPresentException("Invoice Number already present");
		} else {
			return "NotPresent";
		}

	}

	@Override
	public PurchaseEntryEntityDraft createOrUpdateDraft(PurchaseEntryEntityDraft pkgObj) {
		try {
			pkgObj.setCreatedUser(baseMethod.getUserId());
			pkgObj = purchaseEntryEntityDraftRepository.save(pkgObj);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return pkgObj;
	}

	@Override
	public List<PurchaseEntryEntityDraft> getDraftDetails() {
		try {
			System.out.println(baseMethod.getUserId());
			return purchaseEntryEntityDraftRepository.findByCreatedUser(baseMethod.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
	}

	@Override
	public Object deleteDraftById(Long id) {
		try {
			purchaseEntryEntityDraftRepository.deleteById(id);
			return ResponseEntity.ok().body("deleted successfully!!");
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
	}
}
