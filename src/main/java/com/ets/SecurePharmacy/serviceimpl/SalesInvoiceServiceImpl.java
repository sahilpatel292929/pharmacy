package com.ets.SecurePharmacy.serviceimpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.DTO.SalesInvoiceDTO;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.service.BatchService;
import com.ets.SecurePharmacy.service.ItemCreationService;
import com.ets.SecurePharmacy.service.SalesInvoiceService;
import com.ets.SecurePharmacy.tenant.dao.AccountLedgerRepository;
import com.ets.SecurePharmacy.tenant.dao.BatchRepository;
import com.ets.SecurePharmacy.tenant.dao.BatchTranasactionRepository;
import com.ets.SecurePharmacy.tenant.dao.CustomerLedgerRepository;
import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.tenant.dao.SalesInvoiceRepository;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.tenant.model.BatchTranasactionEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerLedgerEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceDraftEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntryDetailEntity; 

@Service
public class SalesInvoiceServiceImpl implements SalesInvoiceService {

	@Autowired
	SalesInvoiceRepository saleInvRepo;

	@Autowired
	BatchService batchService;

	@Autowired
	ItemCreationService itemCreationService;

	@Autowired
	BatchRepository batchRepository;
	
	@Autowired  
	BatchTranasactionRepository batchTranasactionRepository;

	@Autowired
	ItemStockRepository itemStockRepository;
	
	@Autowired
	AccountLedgerRepository accountLedgerRepository;
	@Autowired
	CustomerLedgerRepository customerLedgerRepository;

	private static final DecimalFormat df = new DecimalFormat("0.0000");

	@Transactional
	public List<SalesInvoiceEntity> getAlStockInvoice() {
		// TODO Auto-generated method stub
		List<SalesInvoiceEntity> result;
		try {
			result = (List<SalesInvoiceEntity>) saleInvRepo.findAll();
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<SalesInvoiceEntity>();
		}
	}

	@Transactional
	public SalesInvoiceEntity getStockInvoiceById(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<SalesInvoiceEntity> saleInv;
		try {
			saleInv = saleInvRepo.findById(id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		if (saleInv.isPresent()) {
			return saleInv.get();
		} else {
			throw new RecordNotFoundException("No Sales Invoice Entry record exist for given id");
		}
	}

	@Transactional
	@Override
	public SalesInvoiceDTO createOrUpdateStockInvoice(SalesInvoiceDTO entity) {
		// TODO Auto-generated method stub
		SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();

		salesInvoiceEntity.setCustomerName(entity.getCustomerName());
		salesInvoiceEntity.setCustomerAddress(entity.getCustomerAddress());
		salesInvoiceEntity.setDoctor_name(entity.getDoctor_name());
		salesInvoiceEntity.setDoctorAddress(entity.getDoctorAddress());
		salesInvoiceEntity.setDiscount(entity.getDiscount());
		salesInvoiceEntity.setRemainderDays(entity.getRemainderDays());
		salesInvoiceEntity.setRemainderDate(entity.getRemainderDate());
		salesInvoiceEntity.setHomeDelivery(entity.getHomeDelivery());
		salesInvoiceEntity.setBilldate(entity.getBilldate());
		salesInvoiceEntity.setDiscountAmt(entity.getDiscountAmt());
		salesInvoiceEntity.setMargin(entity.getMargin());
		salesInvoiceEntity.setMarginOn(entity.getMarginOn());
		salesInvoiceEntity.setGrossAmount(entity.getGrossAmount());
		salesInvoiceEntity.setGstAmt(entity.getGstAmt());
		salesInvoiceEntity.setRoundOffAmt(entity.getRoundOffAmt());
		salesInvoiceEntity.setNetAmt(entity.getNetAmt());
		salesInvoiceEntity.setOtherExpenses(entity.getOtherExpenses());
		salesInvoiceEntity.setExpenses(entity.getExpenses());
		salesInvoiceEntity.setStatus(entity.getStatus());
		salesInvoiceEntity.setCustomerCreationEntity(entity.getCustomerCreationEntity());
		salesInvoiceEntity.setPaymentStatus("Not Paid");
		List<SalesInvoiceEntryDetailEntity> list = new ArrayList<>();
		final SalesInvoiceEntity salesInvoiceEntity1 = salesInvoiceEntity;
		entity.getStockinvoiceDetails().forEach(details -> {

			SalesInvoiceEntryDetailEntity salesInvoiceEntryDetailEntity = new SalesInvoiceEntryDetailEntity();
			details.setItemCreationEntity(getItemsDetails(details.getItemCreationEntity().getId()));
			salesInvoiceEntryDetailEntity.setItems(details.getItemCreationEntity());
			salesInvoiceEntryDetailEntity.setBatch(details.getBatch());
			salesInvoiceEntryDetailEntity.setQty(details.getQty());
			salesInvoiceEntryDetailEntity.setDiscount(details.getDiscount());
			salesInvoiceEntryDetailEntity.setMrp(details.getMrp());
			salesInvoiceEntryDetailEntity.setSaleRate(details.getRate());
			salesInvoiceEntryDetailEntity.setDiscount(details.getDiscount());
			BatchEntity batchDetails = this.getBatchDetails(details.getBatch(),
					details.getItemCreationEntity().getId());
			salesInvoiceEntryDetailEntity.setExpiryDate(batchDetails.getExpiry());
			salesInvoiceEntryDetailEntity.setGst(details.getItemCreationEntity().getGst());
			// calulation part started
			Long gst_percentage = (long) 100 + details.getItemCreationEntity().getGst();
			BigDecimal bd = new BigDecimal((Double) ((details.getRate() * 100.0000) / (gst_percentage))).setScale(4,
					RoundingMode.HALF_UP);
			Double taxableRate = bd.doubleValue();
			System.out.println("after call tax Amount" + df.format(taxableRate));
			// Long taxableRate= details.getRate() -taxamount;
			System.out.println("after call tax" + taxableRate);
			salesInvoiceEntryDetailEntity.setTaxbleRate(taxableRate);
			BigDecimal bd1 = new BigDecimal(
					(Double) (taxableRate * (details.getDiscount() / 100.0000) * details.getQty())).setScale(4,
							RoundingMode.HALF_UP);
			Double discountAmt = bd1.doubleValue();
			salesInvoiceEntryDetailEntity.setDiscountAmt(discountAmt);
			BigDecimal bd2 = new BigDecimal(taxableRate * details.getQty()).setScale(4, RoundingMode.HALF_UP);
			Double grossAmount = bd2.doubleValue();

			salesInvoiceEntryDetailEntity.setGrossAmt(grossAmount);

			BigDecimal bd4 = new BigDecimal(grossAmount - discountAmt).setScale(4, RoundingMode.HALF_UP);
			Double taxableAmount = bd4.doubleValue();

			salesInvoiceEntryDetailEntity.setTaxbleAmount(taxableAmount);
			long gst;
			if (details.getItemCreationEntity().getGst() == null) {
				gst = 0l;

			} else {
				gst = (long) details.getItemCreationEntity().getGst();
			}

			BigDecimal bd3 = new BigDecimal((taxableAmount * (gst / 100.0000))).setScale(4, RoundingMode.HALF_UP);
			Double taxAmount = bd3.doubleValue();
			salesInvoiceEntryDetailEntity.setTaxAmount(taxAmount);
			
			BigDecimal bd6 = new BigDecimal(taxableAmount + taxAmount).setScale(4, RoundingMode.HALF_UP);
			Double netAmout = bd6.doubleValue();
			
			salesInvoiceEntryDetailEntity.setNetAmt(netAmout);
			
			BigDecimal bd7 = new BigDecimal(netAmout/details.getQty()).setScale(4, RoundingMode.HALF_UP);
			Double netSaleRate = bd7.doubleValue();
			
			salesInvoiceEntryDetailEntity.setNetSaleRate(netSaleRate);
			
			BatchEntity batchEntity= this.getBatchInvoiceDetails(details.getBatch_invoc_no());
			
			BigDecimal bd5 = new BigDecimal(!batchEntity.getBatchDetails().isEmpty() && batchEntity.getBatchDetails().get(0).getLooseNetCost()!= null ?  batchEntity.getBatchDetails().get(0).getLooseNetCost() : 0).setScale(4, RoundingMode.HALF_UP);
			Double looseNetCost = bd5.doubleValue();
			Double netCost = looseNetCost * details.getQty();
			Double marginAmount = netAmout- netCost ;
			Double marginPercentage = (marginAmount * 100.0000) / netCost;
			salesInvoiceEntryDetailEntity.setMarginAmount(marginAmount);
			salesInvoiceEntryDetailEntity.setNetCost(netCost);
			salesInvoiceEntryDetailEntity.setMarginPercentage(marginPercentage);
//			salesInvoiceEntryDetailEntity.setSaleInvEntryEntity(salesInvoiceEntity1);
			list.add(salesInvoiceEntryDetailEntity);
			
			//Batch Stock update
			this.batchStockUpdate(details.getBatch_invoc_no(),details.getQty());
			//Item Stock update 
			this.itemStockUpdate(details.getQty(),details.getItemCreationEntity());
			//Batch transction Entry 
			this.batchTransaction(  Double.valueOf(details.getBatch_invoc_no()).longValue() , Double.valueOf(details.getQty()).longValue());
		});
		salesInvoiceEntity.setStockinvoiceDetails(list);

		try {

			salesInvoiceEntity = saleInvRepo.save(salesInvoiceEntity);
			entity.setId(salesInvoiceEntity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		// LedgerCreation 
		if(entity.getPaymentMode().equalsIgnoreCase("Credit")) {
			CustomerLedgerEntity accountLedgerEntity= new CustomerLedgerEntity();
			accountLedgerEntity.setCustomerCreationEntity(salesInvoiceEntity.getCustomerCreationEntity());
			accountLedgerEntity.setTransactionId("SE"+salesInvoiceEntity.getId());
			accountLedgerEntity.setAddAmount(salesInvoiceEntity.getNetAmt());
			accountLedgerEntity.setCreatedDate(LocalDateTime.now());
			accountLedgerEntity.setTransactionType("Sales");
			accountLedgerEntity.setDescription("Add Net Amount from the sales Invoice ="+ salesInvoiceEntity.getNetAmt()+" " );;
			try {
				customerLedgerRepository.save(accountLedgerEntity);
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new UnableToProcessException("Unable to process Account Ledger Creation at this moment, Try Again! After some time");
			}
		}
		
		
		return entity;
	}

	// Item Stock update
	private void itemStockUpdate(Double qty, ItemCreationEntity item) {
		ItemStockEntity itmEntitydetails = itemStockRepository.findByItemEntityId(item.getId());
		ItemStockEntity itemStockEntity = new ItemStockEntity();
		itemStockEntity.setLessStock(qty);
		Long availableBalance = 0l;
		if (itmEntitydetails != null) {
			availableBalance = itmEntitydetails.getAvailableStock();
		}

		itemStockEntity.setAvailableStock(availableBalance - Double.valueOf(qty).longValue());
		itemStockEntity.setCreatedTime(LocalDateTime.now());
		itemStockEntity.setLastUpdateTime(LocalDateTime.now());
		itemStockEntity.setLessStock(qty);
		itemStockEntity.setItemEntity(item);
		itemStockRepository.save(itemStockEntity);
	}

	// Batch Update
	private void batchStockUpdate(Long batch_inv_no, Double qty) {
		try {
			BatchEntity batchEntity = this.getBatchInvoiceDetails(batch_inv_no);
			String status;
			if(batchEntity != null) {
				if(!batchEntity.getBatchDetails().isEmpty() && batchEntity.getBatchDetails().get(0).getAvailableStock()>0) {
					Double avialble_stock= batchEntity.getBatchDetails().get(0).getAvailableStock()-Double.valueOf(qty).longValue();
					if(avialble_stock>0) {
						status="AVAILABLE";
					}
					else {
						status="NOT AVAILABLE";
					}
					batchRepository.updateStatus(status, avialble_stock, batch_inv_no);
				}
			}

		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

	}
	//Item Details 
	private ItemCreationEntity getItemsDetails(Long id) {
		// TODO Auto-generated method stub
		ItemCreationEntity entity;
		try {
			entity = itemCreationService.getItemById(id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return entity;
	}
	// Batch Invoice details 
	BatchEntity getBatchInvoiceDetails(Long batch_inv_no)
	{
		BatchEntity batchEntity;
		try {
			batchEntity = batchRepository.getBatchInvoiceNo(batch_inv_no);
		}
		catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return batchEntity;
		
	}
	
	BatchEntity getBatchDetails(String batchNo, Long id) {
		BatchEntity batchEntity;
		try {
			batchEntity = batchService.getMRP(batchNo, id);
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		return batchEntity;
	}
	//Insert into batchTransaction table 
	void batchTransaction(Long batchNo, Long qty)
	{
		BatchTranasactionEntity batchTranasactionEntity =new BatchTranasactionEntity();
		batchTranasactionEntity.setBatchInvNo(batchNo);
		batchTranasactionEntity.setQty(qty);
		batchTranasactionEntity.setCreatedDate(LocalDate.now());
		try {
			batchTranasactionEntity=batchTranasactionRepository.save(batchTranasactionEntity);
		}
		catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		//return batchTranasactionEntity;
	}
	/*
	 * Double getTaxAmount(Long rate, Integer gst) {
	 * 
	 * 
	 * return result; }
	 */

	void batchDetailsRevert(Long batchNo,Long qty) {
		
		try {
			BatchEntity batchEntity = this.getBatchInvoiceDetails(batchNo);
			String status="AVAILABLE";
			
			if(batchEntity != null) {
				if(batchEntity.getBatchDetails().get(0).getAvailableStock()>0) {
					Double avialble_stock= batchEntity.getBatchDetails().get(0).getAvailableStock()+Double.valueOf(qty).longValue();
					if(avialble_stock>0) {
						status="AVAILABLE";
					}
					batchRepository.updateStatus(status, avialble_stock, batchNo);
				}
				else {
					batchRepository.updateStatus(status, (double)qty, batchNo);
				}
			}

		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
	}
	
	private void itemStockRevert(Double qty, ItemCreationEntity item) {
		ItemStockEntity itmEntitydetails = itemStockRepository.findByItemEntityId(item.getId());
		ItemStockEntity itemStockEntity = new ItemStockEntity();
		itemStockEntity.setLessStock(qty);
		Long availableBalance = 0l;
		if (itmEntitydetails != null) {
			availableBalance = itmEntitydetails.getAvailableStock();
		}

		itemStockEntity.setAvailableStock(availableBalance+ Double.valueOf(qty).longValue());
		itemStockEntity.setCreatedTime(LocalDateTime.now());
		itemStockEntity.setLastUpdateTime(LocalDateTime.now());
		itemStockEntity.setLessStock(qty);
		itemStockEntity.setItemEntity(item);
		itemStockRepository.save(itemStockEntity);
	}

	
	@Override
	public Page<SalesInvoiceEntity> getAllSalesInvoiceWithPagination(int offset, int pageSize) {
		Page<SalesInvoiceEntity> result;
		try {
			result = saleInvRepo.findAll(PageRequest.of(offset, pageSize));
		} catch (Exception e) {
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;

	}

	@Override
	public Page<SalesInvoiceEntity> getSearch(String query, Pageable pageable) {
		boolean numeric = true;
		Page<SalesInvoiceEntity> result;
		Long num = 1l;
		try {
			num = Long.parseLong(query);

		} catch (NumberFormatException e) {
			numeric = false;
		}

		if (numeric) {
			result = saleInvRepo.searchByMobile(num, pageable);
		} else {
			result = saleInvRepo.findAllByCustomerNameContains(query, pageable);
		}

		return result;
	}

	@Override
	public SalesInvoiceDraftEntity createOrUpdateStockInvoiceDraft(SalesInvoiceDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SalesInvoiceDraftEntity> getAlStockInvoiceDraft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deleteDraftById(Long id) {
		// TODO Auto-generated method stub
		return null;
		
	}

}
