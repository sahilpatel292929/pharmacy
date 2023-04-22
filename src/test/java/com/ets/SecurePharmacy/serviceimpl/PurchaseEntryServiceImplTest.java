package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.DTO.PurchaseReportFilterDTO;
import com.ets.SecurePharmacy.exception.RecordAlreadyPresentException;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.BatchDetailsRepository;
import com.ets.SecurePharmacy.tenant.dao.BatchRepository;
import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.tenant.dao.PurchaseEntryDetailsRepository;
import com.ets.SecurePharmacy.tenant.dao.PurchaseEntryEntityDraftRepository;
import com.ets.SecurePharmacy.tenant.dao.PurchaseEntryRepository;
import com.ets.SecurePharmacy.tenant.dao.SupplierLedgerRepository;
import com.ets.SecurePharmacy.tenant.model.BatchDetailsEntity;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryDetailsEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntityDraft;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SupplierCreationEntity;
import com.ets.SecurePharmacy.transfomers.DTOTransfomers;
import com.ets.SecurePharmacy.util.BaseMethod;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PurchaseEntryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PurchaseEntryServiceImplTest {
    @MockBean
    private BaseMethod baseMethod;

    @MockBean
    private DTOTransfomers dTOTransfomers;

    @MockBean
    private ItemStockRepository itemStockRepository;

    @MockBean
    private PurchaseEntryDetailsRepository purchaseEntryDetailsRepository;

    @MockBean
    private PurchaseEntryEntityDraftRepository purchaseEntryEntityDraftRepository;

    @MockBean
    private PurchaseEntryRepository purchaseEntryRepository;

    @MockBean
    private SupplierLedgerRepository supplierLedgerRepository;

    @MockBean
    private BatchDetailsRepository batchDetailsRepository;


    @MockBean
    private BatchRepository batchRepository;


    @Autowired
    private PurchaseEntryServiceImpl purchaseEntryServiceImpl;

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#createOrUpdateAccounts(PurchaseEntryEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts() throws Exception {
        // Arrange
        when(baseMethod.getUserId()).thenReturn(123L);

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");
        when(purchaseEntryRepository.save((PurchaseEntryEntity) any())).thenReturn(purchaseEntryEntity);
        doNothing().when(purchaseEntryEntityDraftRepository).deleteByCreatedUser((Long) any());

        // Act and Assert
        assertSame(purchaseEntryEntity, purchaseEntryServiceImpl.createOrUpdateAccounts(purchaseEntryEntity));
        verify(baseMethod).getUserId();
        verify(purchaseEntryRepository).save((PurchaseEntryEntity) any());
        verify(purchaseEntryEntityDraftRepository).deleteByCreatedUser((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#createOrUpdateAccounts(PurchaseEntryEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts2() throws Exception {
        // Arrange
        when(baseMethod.getUserId()).thenReturn(123L);

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");
        when(purchaseEntryRepository.save((PurchaseEntryEntity) any())).thenReturn(purchaseEntryEntity);
        doThrow(new UnableToProcessException("An error occurred")).when(purchaseEntryEntityDraftRepository)
                .deleteByCreatedUser((Long) any());

        SupplierCreationEntity supplierCreationEntity1 = new SupplierCreationEntity();
        supplierCreationEntity1.setAddress1("42 Main St");
        supplierCreationEntity1.setAddress2("42 Main St");
        supplierCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setCredit_days(1);
        supplierCreationEntity1.setDiscount("3");
        supplierCreationEntity1.setGstNo("Gst No");
        supplierCreationEntity1.setGstType("Gst Type");
        supplierCreationEntity1.setId(123L);
        supplierCreationEntity1.setMobileNo("Mobile No");
        supplierCreationEntity1.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity1.setOpeningBal(1L);
        supplierCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setRateSlab("Rate Slab");
        supplierCreationEntity1.setStatus("Status");
        supplierCreationEntity1.setSupplierName("Supplier Name");
        supplierCreationEntity1.setUpdated_by(1);
        supplierCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity1 = new PurchaseEntryEntity();
        purchaseEntryEntity1.setDisPercentage(1L);
        purchaseEntryEntity1.setDiscAmount(10.0d);
        purchaseEntryEntity1.setDiscount(3L);
        purchaseEntryEntity1.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setDueday(1);
        purchaseEntryEntity1.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setGrossAmounts(1L);
        purchaseEntryEntity1.setGstamount(10L);
        purchaseEntryEntity1.setId(123L);
        purchaseEntryEntity1.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setInvoiceNo("Invoice No");
        purchaseEntryEntity1.setNetAmount(1L);
        purchaseEntryEntity1.setNoItems(1000);
        purchaseEntryEntity1.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity1.setRoundAmount(1L);
        purchaseEntryEntity1.setSrtMargin("Srt Margin");
        purchaseEntryEntity1.setSupplierDetails(supplierCreationEntity1);
        purchaseEntryEntity1.setSupplierName("Supplier Name");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntryServiceImpl.createOrUpdateAccounts(purchaseEntryEntity1));
        verify(baseMethod).getUserId();
        verify(purchaseEntryEntityDraftRepository).deleteByCreatedUser((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#createOrUpdateAccounts(PurchaseEntryEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts3() throws Exception {
        // Arrange
        when(baseMethod.getUserId()).thenReturn(123L);

        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");

        ItemCreationEntity itemCreationEntity = new ItemCreationEntity();
        itemCreationEntity.setCompositionEntity(compostionCreationEntity);
        itemCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        itemCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        itemCreationEntity.setDiscSalbEntity(discountSlabCreationEntity);
        itemCreationEntity.setGroupEntity(groupCreationEntity);
        itemCreationEntity.setGst(1);
        itemCreationEntity.setHsnsac(hsnSacCreationEntity);
        itemCreationEntity.setId(123L);
        itemCreationEntity.setItemName("Item Name");
        itemCreationEntity.setManufactureEntity(manufacturerCreationEntity);
        itemCreationEntity.setMax_amt(10.0d);
        itemCreationEntity.setMax_qty(10.0d);
        itemCreationEntity.setMin_amt(10.0d);
        itemCreationEntity.setMin_qty(10.0d);
        itemCreationEntity.setPackName("Pack Name");
        itemCreationEntity.setQty_per_pack("Qty per pack");
        itemCreationEntity.setRateA("Rate A");
        itemCreationEntity.setRateB("Rate B");
        itemCreationEntity.setRateC("Rate C");
        itemCreationEntity.setRateD("Rate D");
        itemCreationEntity.setScheduleEntity(schedulerCreationEntity);
        itemCreationEntity.setStatus("Status");
        itemCreationEntity.setStoreTypeEntity(storeTypeCreationEntity);
        itemCreationEntity.setUpdated_by(1);
        itemCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");

        PurchaseEntryDetailsEntity purchaseEntryDetailsEntity = new PurchaseEntryDetailsEntity();
        purchaseEntryDetailsEntity.setBatch("Batch");
        purchaseEntryDetailsEntity.setBestBefore("Best Before");
        purchaseEntryDetailsEntity.setDiscAmount(1L);
        purchaseEntryDetailsEntity.setDiscount(3L);
        purchaseEntryDetailsEntity.setExpiryDate(LocalDate.ofEpochDay(1L));
        purchaseEntryDetailsEntity.setFreeQty(1L);
        purchaseEntryDetailsEntity.setGrossAmt(1L);
        purchaseEntryDetailsEntity.setGst(1L);
        purchaseEntryDetailsEntity.setId(123L);
        purchaseEntryDetailsEntity.setInvoiceNo("Invoice No");
        purchaseEntryDetailsEntity.setItems(itemCreationEntity);
        purchaseEntryDetailsEntity.setMaxdate(1L);
        purchaseEntryDetailsEntity.setMfgdateFlag(true);
        purchaseEntryDetailsEntity.setMrp(1L);
        purchaseEntryDetailsEntity.setNetAmt(1L);
        purchaseEntryDetailsEntity.setPurchaseEntryEntity(purchaseEntryEntity);
        purchaseEntryDetailsEntity.setPurchaseRate(1L);
        purchaseEntryDetailsEntity.setQpk(1L);
        purchaseEntryDetailsEntity.setQty(1L);
        purchaseEntryDetailsEntity.setSchdiscAmount(1L);
        purchaseEntryDetailsEntity.setSrt(1L);
        purchaseEntryDetailsEntity.setStatus("Status");
        purchaseEntryDetailsEntity.setTaxAmount(1L);

        ArrayList<PurchaseEntryDetailsEntity> purchaseEntryDetailsEntityList = new ArrayList<>();
        purchaseEntryDetailsEntityList.add(purchaseEntryDetailsEntity);

        SupplierCreationEntity supplierCreationEntity1 = new SupplierCreationEntity();
        supplierCreationEntity1.setAddress1("42 Main St");
        supplierCreationEntity1.setAddress2("42 Main St");
        supplierCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setCredit_days(1);
        supplierCreationEntity1.setDiscount("3");
        supplierCreationEntity1.setGstNo("Gst No");
        supplierCreationEntity1.setGstType("Gst Type");
        supplierCreationEntity1.setId(123L);
        supplierCreationEntity1.setMobileNo("Mobile No");
        supplierCreationEntity1.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity1.setOpeningBal(1L);
        supplierCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setRateSlab("Rate Slab");
        supplierCreationEntity1.setStatus("Status");
        supplierCreationEntity1.setSupplierName("Supplier Name");
        supplierCreationEntity1.setUpdated_by(1);
        supplierCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity1 = new PurchaseEntryEntity();
        purchaseEntryEntity1.setDisPercentage(1L);
        purchaseEntryEntity1.setDiscAmount(10.0d);
        purchaseEntryEntity1.setDiscount(3L);
        purchaseEntryEntity1.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setDueday(1);
        purchaseEntryEntity1.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setGrossAmounts(1L);
        purchaseEntryEntity1.setGstamount(10L);
        purchaseEntryEntity1.setId(123L);
        purchaseEntryEntity1.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setInvoiceNo("Invoice No");
        purchaseEntryEntity1.setNetAmount(1L);
        purchaseEntryEntity1.setNoItems(1000);
        purchaseEntryEntity1.setPurchaseDetails(purchaseEntryDetailsEntityList);
        purchaseEntryEntity1.setRoundAmount(1L);
        purchaseEntryEntity1.setSrtMargin("Srt Margin");
        purchaseEntryEntity1.setSupplierDetails(supplierCreationEntity1);
        purchaseEntryEntity1.setSupplierName("Supplier Name");

        // item stock entity
        ItemStockEntity itmEntitydetails = generateItemStockEntity();
        when(itemStockRepository.findByItemEntityId((Long)any())).thenReturn(itmEntitydetails);
        ItemStockEntity itemStockEntity = new ItemStockEntity();
        itemStockEntity.setAddStock(purchaseEntryDetailsEntity.getQty());
        itemStockEntity.setCreatedTime(LocalDateTime.now());
        itemStockEntity.setLastUpdateTime(LocalDateTime.now());
        itemStockEntity.setLessStock(0.000);
        itemStockEntity.setItemEntity(purchaseEntryDetailsEntity.getItems());
        itemStockEntity.setAvailableStock(itmEntitydetails.getAvailableStock() + purchaseEntryDetailsEntity.getQty());
        when(itemStockRepository.save(itemStockEntity)).thenReturn(itemStockEntity);
        //batch entity
        BatchEntity batchEntity = new BatchEntity();
        batchEntity.setAvailableStock(10.0d);
        batchEntity.setBatchDetails(new ArrayList<>());
        batchEntity.setBatchNo("Batch No");
        batchEntity.setExpiry(LocalDate.ofEpochDay(1L));
        batchEntity.setId(123L);
        batchEntity.setItems(itemCreationEntity);
        batchEntity.setMrp(1.900);
        batchEntity.setQtyPerPack(1L);
        when(batchRepository.getMRPand((String)any(),(Long)any())).thenReturn(batchEntity);
        BatchDetailsEntity batchDetailsEntity=new BatchDetailsEntity();
        batchDetailsEntity.setSupplierDetails(purchaseEntryEntity.getSupplierDetails());
        batchDetailsEntity.setSRT((double)purchaseEntryDetailsEntity.getSrt());
        batchDetailsEntity.setAvailableStock((double)purchaseEntryDetailsEntity.getQty());
        batchDetailsEntity.setPurchaseRate((double)purchaseEntryDetailsEntity.getPurchaseRate());
        batchDetailsEntity.setQty((double)purchaseEntryDetailsEntity.getQty());
        batchDetailsEntity.setBatchEntity(batchEntity);
        when(batchDetailsRepository.getLastBatchDetailsEntityByBatchId((Long)any())).thenReturn(batchDetailsEntity);
        when(batchRepository.save((BatchEntity) any())).thenReturn(batchEntity);


        //purchaseEntry related
        when(purchaseEntryRepository.save((PurchaseEntryEntity) any())).thenReturn(purchaseEntryEntity1);
        doNothing().when(purchaseEntryEntityDraftRepository).deleteByCreatedUser((Long) any());


        // Act and Assert
        assertSame(purchaseEntryEntity1, purchaseEntryServiceImpl.createOrUpdateAccounts(purchaseEntryEntity1));
        verify(baseMethod).getUserId();
        verify(itemStockRepository).findByItemEntityId((Long)any());
        verify(itemStockRepository).save((ItemStockEntity) any());
        verify(batchRepository).getMRPand((String)any(),(Long)any());
        verify(batchDetailsRepository).getLastBatchDetailsEntityByBatchId((Long)any());
        verify(batchRepository).save((BatchEntity) any());
        verify(purchaseEntryRepository).save((PurchaseEntryEntity) any());
        verify(purchaseEntryEntityDraftRepository).deleteByCreatedUser((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#createOrUpdateAccounts(PurchaseEntryEntity)}
     */
    @Test
    void testCreateOrUpdateAccounts4() throws Exception {
        // Arrange
        when(baseMethod.getUserId()).thenReturn(123L);

        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");

        ItemCreationEntity itemCreationEntity = new ItemCreationEntity();
        itemCreationEntity.setCompositionEntity(compostionCreationEntity);
        itemCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        itemCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        itemCreationEntity.setDiscSalbEntity(discountSlabCreationEntity);
        itemCreationEntity.setGroupEntity(groupCreationEntity);
        itemCreationEntity.setGst(1);
        itemCreationEntity.setHsnsac(hsnSacCreationEntity);
        itemCreationEntity.setId(123L);
        itemCreationEntity.setItemName("Item Name");
        itemCreationEntity.setManufactureEntity(manufacturerCreationEntity);
        itemCreationEntity.setMax_amt(10.0d);
        itemCreationEntity.setMax_qty(10.0d);
        itemCreationEntity.setMin_amt(10.0d);
        itemCreationEntity.setMin_qty(10.0d);
        itemCreationEntity.setPackName("Pack Name");
        itemCreationEntity.setQty_per_pack("Qty per pack");
        itemCreationEntity.setRateA("Rate A");
        itemCreationEntity.setRateB("Rate B");
        itemCreationEntity.setRateC("Rate C");
        itemCreationEntity.setRateD("Rate D");
        itemCreationEntity.setScheduleEntity(schedulerCreationEntity);
        itemCreationEntity.setStatus("Status");
        itemCreationEntity.setStoreTypeEntity(storeTypeCreationEntity);
        itemCreationEntity.setUpdated_by(1);
        itemCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");

        PurchaseEntryDetailsEntity purchaseEntryDetailsEntity = new PurchaseEntryDetailsEntity();
        purchaseEntryDetailsEntity.setBatch("Batch");
        purchaseEntryDetailsEntity.setBestBefore("Best Before");
        purchaseEntryDetailsEntity.setDiscAmount(1L);
        purchaseEntryDetailsEntity.setDiscount(3L);
        purchaseEntryDetailsEntity.setExpiryDate(LocalDate.ofEpochDay(1L));
        purchaseEntryDetailsEntity.setFreeQty(1L);
        purchaseEntryDetailsEntity.setGrossAmt(1L);
        purchaseEntryDetailsEntity.setGst(1L);
        purchaseEntryDetailsEntity.setId(123L);
        purchaseEntryDetailsEntity.setInvoiceNo("Invoice No");
        purchaseEntryDetailsEntity.setItems(itemCreationEntity);
        purchaseEntryDetailsEntity.setMaxdate(1L);
        purchaseEntryDetailsEntity.setMfgdateFlag(true);
        purchaseEntryDetailsEntity.setMrp(1L);
        purchaseEntryDetailsEntity.setNetAmt(1L);
        purchaseEntryDetailsEntity.setPurchaseEntryEntity(purchaseEntryEntity);
        purchaseEntryDetailsEntity.setPurchaseRate(1L);
        purchaseEntryDetailsEntity.setQpk(1L);
        purchaseEntryDetailsEntity.setQty(1L);
        purchaseEntryDetailsEntity.setSchdiscAmount(1L);
        purchaseEntryDetailsEntity.setSrt(1L);
        purchaseEntryDetailsEntity.setStatus("Status");
        purchaseEntryDetailsEntity.setTaxAmount(1L);

        ArrayList<PurchaseEntryDetailsEntity> purchaseEntryDetailsEntityList = new ArrayList<>();
        purchaseEntryDetailsEntityList.add(purchaseEntryDetailsEntity);

        SupplierCreationEntity supplierCreationEntity1 = new SupplierCreationEntity();
        supplierCreationEntity1.setAddress1("42 Main St");
        supplierCreationEntity1.setAddress2("42 Main St");
        supplierCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setCredit_days(1);
        supplierCreationEntity1.setDiscount("3");
        supplierCreationEntity1.setGstNo("Gst No");
        supplierCreationEntity1.setGstType("Gst Type");
        supplierCreationEntity1.setId(123L);
        supplierCreationEntity1.setMobileNo("Mobile No");
        supplierCreationEntity1.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity1.setOpeningBal(1L);
        supplierCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setRateSlab("Rate Slab");
        supplierCreationEntity1.setStatus("Status");
        supplierCreationEntity1.setSupplierName("Supplier Name");
        supplierCreationEntity1.setUpdated_by(1);
        supplierCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity1 = new PurchaseEntryEntity();
        purchaseEntryEntity1.setDisPercentage(1L);
        purchaseEntryEntity1.setDiscAmount(10.0d);
        purchaseEntryEntity1.setDiscount(3L);
        purchaseEntryEntity1.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setDueday(1);
        purchaseEntryEntity1.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setGrossAmounts(1L);
        purchaseEntryEntity1.setGstamount(10L);
        purchaseEntryEntity1.setId(123L);
        purchaseEntryEntity1.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity1.setInvoiceNo("Invoice No");
        purchaseEntryEntity1.setNetAmount(1L);
        purchaseEntryEntity1.setNoItems(1000);
        purchaseEntryEntity1.setPurchaseDetails(purchaseEntryDetailsEntityList);
        purchaseEntryEntity1.setRoundAmount(1L);
        purchaseEntryEntity1.setSrtMargin("Srt Margin");
        purchaseEntryEntity1.setSupplierDetails(supplierCreationEntity1);
        purchaseEntryEntity1.setSupplierName("Supplier Name");

        // item stock entity
        ItemStockEntity itmEntitydetails = generateItemStockEntity();
        when(itemStockRepository.findByItemEntityId((Long)any())).thenReturn(itmEntitydetails);
        ItemStockEntity itemStockEntity = new ItemStockEntity();
        itemStockEntity.setAddStock(purchaseEntryDetailsEntity.getQty());
        itemStockEntity.setCreatedTime(LocalDateTime.now());
        itemStockEntity.setLastUpdateTime(LocalDateTime.now());
        itemStockEntity.setLessStock(0.000);
        itemStockEntity.setItemEntity(purchaseEntryDetailsEntity.getItems());
        itemStockEntity.setAvailableStock(itmEntitydetails.getAvailableStock() + purchaseEntryDetailsEntity.getQty());
        when(itemStockRepository.save(itemStockEntity)).thenReturn(itemStockEntity);
        //batch entity
        when(batchRepository.getMRPand((String)any(),(Long)any())).thenReturn(null);
        BatchEntity batchEntity = new BatchEntity();
        batchEntity.setAvailableStock(10.0d);
        batchEntity.setBatchDetails(new ArrayList<>());
        batchEntity.setBatchNo("Batch No");
        batchEntity.setExpiry(LocalDate.ofEpochDay(1L));
        batchEntity.setId(123L);
        batchEntity.setItems(itemCreationEntity);
        batchEntity.setMrp(1.99);
        batchEntity.setQtyPerPack(1L);
        BatchDetailsEntity batchDetailsEntity=new BatchDetailsEntity();
        batchDetailsEntity.setSupplierDetails(purchaseEntryEntity.getSupplierDetails());
        batchDetailsEntity.setSRT((double)purchaseEntryDetailsEntity.getSrt());
        batchDetailsEntity.setAvailableStock((double)purchaseEntryDetailsEntity.getQty());
        batchDetailsEntity.setPurchaseRate((double)purchaseEntryDetailsEntity.getPurchaseRate());
        batchDetailsEntity.setQty((double)purchaseEntryDetailsEntity.getQty());
        batchDetailsEntity.setBatchEntity(batchEntity);
        batchEntity.setBatchDetails(Collections.singletonList(batchDetailsEntity));
        when(batchRepository.save((BatchEntity) any())).thenReturn(batchEntity);


        //purchaseEntry related
        when(purchaseEntryRepository.save((PurchaseEntryEntity) any())).thenReturn(purchaseEntryEntity1);
        doNothing().when(purchaseEntryEntityDraftRepository).deleteByCreatedUser((Long) any());


        // Act and Assert
        assertSame(purchaseEntryEntity1, purchaseEntryServiceImpl.createOrUpdateAccounts(purchaseEntryEntity1));
        verify(baseMethod).getUserId();
        verify(itemStockRepository).findByItemEntityId((Long)any());
        verify(itemStockRepository).save((ItemStockEntity) any());
        verify(batchRepository).save((BatchEntity) any());
        verify(purchaseEntryRepository).save((PurchaseEntryEntity) any());
        verify(purchaseEntryEntityDraftRepository).deleteByCreatedUser((Long) any());
    }

    private ItemStockEntity generateItemStockEntity() {
        CompostionCreationEntity compostionCreationEntity = new CompostionCreationEntity();
        compostionCreationEntity.setCompName("Comp Name");
        compostionCreationEntity.setId(123L);
        compostionCreationEntity.setStatus("Status");

        DiscountSlabCreationEntity discountSlabCreationEntity = new DiscountSlabCreationEntity();
        discountSlabCreationEntity.setDiscount("3");
        discountSlabCreationEntity.setDiscountSlabName("3");
        discountSlabCreationEntity.setFrom_amt(1);
        discountSlabCreationEntity.setId(123L);
        discountSlabCreationEntity.setStatus("Status");
        discountSlabCreationEntity.setTo_amt(1);

        GroupCreationEntity groupCreationEntity = new GroupCreationEntity();
        groupCreationEntity.setGroupName("Group Name");
        groupCreationEntity.setId(123L);
        groupCreationEntity.setStatus("Status");

        HsnSacCreationEntity hsnSacCreationEntity = new HsnSacCreationEntity();
        hsnSacCreationEntity.setDescirption("Descirption");
        hsnSacCreationEntity.setHsnName("Hsn Name");
        hsnSacCreationEntity.setId(123L);
        hsnSacCreationEntity.setStatus("Status");

        ManufacturerCreationEntity manufacturerCreationEntity = new ManufacturerCreationEntity();
        manufacturerCreationEntity.setId(123L);
        manufacturerCreationEntity.setManufacturerName("Manufacturer Name");
        manufacturerCreationEntity.setStatus("Status");

        SchedulerCreationEntity schedulerCreationEntity = new SchedulerCreationEntity();
        schedulerCreationEntity.setId(123L);
        schedulerCreationEntity.setSchedulerName("Scheduler Name");
        schedulerCreationEntity.setStatus("Status");
        schedulerCreationEntity.setWaringMsg("Waring Msg");
        schedulerCreationEntity.setWarning("Warning");

        StoreTypeCreationEntity storeTypeCreationEntity = new StoreTypeCreationEntity();
        storeTypeCreationEntity.setId(123L);
        storeTypeCreationEntity.setStatus("Status");
        storeTypeCreationEntity.setStoreTypeName("Store Type Name");

        ItemCreationEntity itemCreationEntity = new ItemCreationEntity();
        itemCreationEntity.setCompositionEntity(compostionCreationEntity);
        itemCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        itemCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        itemCreationEntity.setDiscSalbEntity(discountSlabCreationEntity);
        itemCreationEntity.setGroupEntity(groupCreationEntity);
        itemCreationEntity.setGst(1);
        itemCreationEntity.setHsnsac(hsnSacCreationEntity);
        itemCreationEntity.setId(123L);
        itemCreationEntity.setItemName("Item Name");
        itemCreationEntity.setManufactureEntity(manufacturerCreationEntity);
        itemCreationEntity.setMax_amt(10.0d);
        itemCreationEntity.setMax_qty(10.0d);
        itemCreationEntity.setMin_amt(10.0d);
        itemCreationEntity.setMin_qty(10.0d);
        itemCreationEntity.setPackName("Pack Name");
        itemCreationEntity.setQty_per_pack("Qty per pack");
        itemCreationEntity.setRateA("Rate A");
        itemCreationEntity.setRateB("Rate B");
        itemCreationEntity.setRateC("Rate C");
        itemCreationEntity.setRateD("Rate D");
        itemCreationEntity.setScheduleEntity(schedulerCreationEntity);
        itemCreationEntity.setStatus("Status");
        itemCreationEntity.setStoreTypeEntity(storeTypeCreationEntity);
        itemCreationEntity.setUpdated_by(1);
        itemCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        ItemStockEntity itemStockEntity = new ItemStockEntity();
        itemStockEntity.setAddStock(1L);
        itemStockEntity.setAvailableStock(1L);
        itemStockEntity.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        itemStockEntity.setId(123L);
        itemStockEntity.setItemEntity(itemCreationEntity);
        itemStockEntity.setLastUpdateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        itemStockEntity.setLessStock(1.00);
        return itemStockEntity;
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#createOrUpdateDraft(PurchaseEntryEntityDraft)}
     */
    @Test
    void testCreateOrUpdateDraft() throws Exception {
        // Arrange
        when(baseMethod.getUserId()).thenReturn(123L);

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntityDraft purchaseEntryEntityDraft = new PurchaseEntryEntityDraft();
        purchaseEntryEntityDraft.setCreatedUser(1L);
        purchaseEntryEntityDraft.setDisPercentage(1L);
        purchaseEntryEntityDraft.setDiscAmount(10.0d);
        purchaseEntryEntityDraft.setDiscount(3L);
        purchaseEntryEntityDraft.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setDueday(1);
        purchaseEntryEntityDraft.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setGrossAmounts(1L);
        purchaseEntryEntityDraft.setGstamount(10L);
        purchaseEntryEntityDraft.setId(123L);
        purchaseEntryEntityDraft.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setInvoiceNo("Invoice No");
        purchaseEntryEntityDraft.setNetAmount(1L);
        purchaseEntryEntityDraft.setNoItems(1000);
        purchaseEntryEntityDraft.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntityDraft.setRoundAmount(1L);
        purchaseEntryEntityDraft.setSrtMargin("Srt Margin");
        purchaseEntryEntityDraft.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntityDraft.setSupplierName("Supplier Name");
        when(purchaseEntryEntityDraftRepository.save((PurchaseEntryEntityDraft) any()))
                .thenReturn(purchaseEntryEntityDraft);

        SupplierCreationEntity supplierCreationEntity1 = new SupplierCreationEntity();
        supplierCreationEntity1.setAddress1("42 Main St");
        supplierCreationEntity1.setAddress2("42 Main St");
        supplierCreationEntity1.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity1.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setCredit_days(1);
        supplierCreationEntity1.setDiscount("3");
        supplierCreationEntity1.setGstNo("Gst No");
        supplierCreationEntity1.setGstType("Gst Type");
        supplierCreationEntity1.setId(123L);
        supplierCreationEntity1.setMobileNo("Mobile No");
        supplierCreationEntity1.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity1.setOpeningBal(1L);
        supplierCreationEntity1.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity1.setRateSlab("Rate Slab");
        supplierCreationEntity1.setStatus("Status");
        supplierCreationEntity1.setSupplierName("Supplier Name");
        supplierCreationEntity1.setUpdated_by(1);
        supplierCreationEntity1.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntityDraft purchaseEntryEntityDraft1 = new PurchaseEntryEntityDraft();
        purchaseEntryEntityDraft1.setCreatedUser(1L);
        purchaseEntryEntityDraft1.setDisPercentage(1L);
        purchaseEntryEntityDraft1.setDiscAmount(10.0d);
        purchaseEntryEntityDraft1.setDiscount(3L);
        purchaseEntryEntityDraft1.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft1.setDueday(1);
        purchaseEntryEntityDraft1.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft1.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft1.setGrossAmounts(1L);
        purchaseEntryEntityDraft1.setGstamount(10L);
        purchaseEntryEntityDraft1.setId(123L);
        purchaseEntryEntityDraft1.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft1.setInvoiceNo("Invoice No");
        purchaseEntryEntityDraft1.setNetAmount(1L);
        purchaseEntryEntityDraft1.setNoItems(1000);
        purchaseEntryEntityDraft1.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntityDraft1.setRoundAmount(1L);
        purchaseEntryEntityDraft1.setSrtMargin("Srt Margin");
        purchaseEntryEntityDraft1.setSupplierDetails(supplierCreationEntity1);
        purchaseEntryEntityDraft1.setSupplierName("Supplier Name");

        // Act and Assert
        assertSame(purchaseEntryEntityDraft, purchaseEntryServiceImpl.createOrUpdateDraft(purchaseEntryEntityDraft1));
        verify(baseMethod).getUserId();
        verify(purchaseEntryEntityDraftRepository).save((PurchaseEntryEntityDraft) any());
        assertEquals(123L, purchaseEntryEntityDraft1.getCreatedUser().longValue());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#createOrUpdateDraft(PurchaseEntryEntityDraft)}
     */
    @Test
    void testCreateOrUpdateDraft2() throws Exception {
        // Arrange
        when(baseMethod.getUserId()).thenReturn(123L);
        when(purchaseEntryEntityDraftRepository.save((PurchaseEntryEntityDraft) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntityDraft purchaseEntryEntityDraft = new PurchaseEntryEntityDraft();
        purchaseEntryEntityDraft.setCreatedUser(1L);
        purchaseEntryEntityDraft.setDisPercentage(1L);
        purchaseEntryEntityDraft.setDiscAmount(10.0d);
        purchaseEntryEntityDraft.setDiscount(3L);
        purchaseEntryEntityDraft.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setDueday(1);
        purchaseEntryEntityDraft.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setGrossAmounts(1L);
        purchaseEntryEntityDraft.setGstamount(10L);
        purchaseEntryEntityDraft.setId(123L);
        purchaseEntryEntityDraft.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntityDraft.setInvoiceNo("Invoice No");
        purchaseEntryEntityDraft.setNetAmount(1L);
        purchaseEntryEntityDraft.setNoItems(1000);
        purchaseEntryEntityDraft.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntityDraft.setRoundAmount(1L);
        purchaseEntryEntityDraft.setSrtMargin("Srt Margin");
        purchaseEntryEntityDraft.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntityDraft.setSupplierName("Supplier Name");

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntryServiceImpl.createOrUpdateDraft(purchaseEntryEntityDraft));
        verify(baseMethod).getUserId();
        verify(purchaseEntryEntityDraftRepository).save((PurchaseEntryEntityDraft) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#deleteDraftById(Long)}
     */
    @Test
    void testDeleteDraftById() {
        // Arrange
        doNothing().when(purchaseEntryEntityDraftRepository).deleteById((Long) any());

        // Act and Assert
        assertEquals("deleted successfully!!",
                ((ResponseEntity<Object>) purchaseEntryServiceImpl.deleteDraftById(123L)).getBody());
        assertEquals(HttpStatus.OK,
                ((ResponseEntity<Object>) purchaseEntryServiceImpl.deleteDraftById(123L)).getStatusCode());
        assertTrue(((ResponseEntity<Object>) purchaseEntryServiceImpl.deleteDraftById(123L)).getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#deleteDraftById(Long)}
     */
    @Test
    void testDeleteDraftById2() {
        // Arrange
        doThrow(new UnableToProcessException("An error occurred")).when(purchaseEntryEntityDraftRepository)
                .deleteById((Long) any());

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> purchaseEntryServiceImpl.deleteDraftById(123L));
        verify(purchaseEntryEntityDraftRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() {
        // Arrange
        when(purchaseEntryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(purchaseEntryServiceImpl.getAllAccounts().isEmpty());
        verify(purchaseEntryRepository).findAll();
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts2() {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");

        ArrayList<PurchaseEntryEntity> purchaseEntryEntityList = new ArrayList<>();
        purchaseEntryEntityList.add(purchaseEntryEntity);
        when(purchaseEntryRepository.findAll()).thenReturn(purchaseEntryEntityList);

        // Act
        List<PurchaseEntryEntity> actualAllAccounts = purchaseEntryServiceImpl.getAllAccounts();

        // Assert
        assertSame(purchaseEntryEntityList, actualAllAccounts);
        assertEquals(1, actualAllAccounts.size());
        verify(purchaseEntryRepository).findAll();
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts3() {
        // Arrange
        when(purchaseEntryRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> purchaseEntryServiceImpl.getAllAccounts());
        verify(purchaseEntryRepository).findAll();
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getAllPurchaseOrderWithPagination(int, int)}
     */
    @Test
    void testGetAllPurchaseOrderWithPagination() {
        // Arrange
        PageImpl<PurchaseEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(purchaseEntryRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<PurchaseEntryEntity> actualAllPurchaseOrderWithPagination = purchaseEntryServiceImpl
                .getAllPurchaseOrderWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllPurchaseOrderWithPagination);
        assertTrue(actualAllPurchaseOrderWithPagination.toList().isEmpty());
        verify(purchaseEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getAllPurchaseOrderWithPagination(int, int)}
     */
    @Test
    void testGetAllPurchaseOrderWithPagination2() {
        // Arrange
        when(purchaseEntryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntryServiceImpl.getAllPurchaseOrderWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getAllPurchaseOrderWithPagination(int, int)}
     */
    @Test
    void testGetAllPurchaseOrderWithPagination3() {
        // Arrange
        when(purchaseEntryRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntryServiceImpl.getAllPurchaseOrderWithPagination(2, 3));
        verify(purchaseEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getDraftDetails()}
     */
    @Test
    void testGetDraftDetails() throws Exception {
        // Arrange
        when(baseMethod.getUserId()).thenReturn(123L);
        ArrayList<PurchaseEntryEntityDraft> purchaseEntryEntityDraftList = new ArrayList<>();
        when(purchaseEntryEntityDraftRepository.findByCreatedUser((Long) any())).thenReturn(purchaseEntryEntityDraftList);

        // Act
        List<PurchaseEntryEntityDraft> actualDraftDetails = purchaseEntryServiceImpl.getDraftDetails();

        // Assert
        assertSame(purchaseEntryEntityDraftList, actualDraftDetails);
        assertTrue(actualDraftDetails.isEmpty());
        verify(baseMethod, atLeast(1)).getUserId();
        verify(purchaseEntryEntityDraftRepository).findByCreatedUser((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getDraftDetails()}
     */
    @Test
    void testGetDraftDetails2() throws Exception {
        // Arrange
        when(baseMethod.getUserId()).thenReturn(123L);
        when(purchaseEntryEntityDraftRepository.findByCreatedUser((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> purchaseEntryServiceImpl.getDraftDetails());
        verify(baseMethod, atLeast(1)).getUserId();
        verify(purchaseEntryEntityDraftRepository).findByCreatedUser((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getLastPuchaseDetails(Long)}
     */
    @Test
    void testGetLastPuchaseDetails() {
        // Arrange
        when(purchaseEntryRepository.findItemRecentDetails((Long) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(purchaseEntryServiceImpl.getLastPuchaseDetails(123L).isEmpty());
        verify(purchaseEntryRepository).findItemRecentDetails((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getLastPuchaseDetails(Long)}
     */
    @Test
    void testGetLastPuchaseDetails2() {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");

        ArrayList<PurchaseEntryEntity> purchaseEntryEntityList = new ArrayList<>();
        purchaseEntryEntityList.add(purchaseEntryEntity);
        when(purchaseEntryRepository.findItemRecentDetails((Long) any())).thenReturn(purchaseEntryEntityList);

        // Act
        List<PurchaseEntryEntity> actualLastPuchaseDetails = purchaseEntryServiceImpl.getLastPuchaseDetails(123L);

        // Assert
        assertSame(purchaseEntryEntityList, actualLastPuchaseDetails);
        assertEquals(1, actualLastPuchaseDetails.size());
        verify(purchaseEntryRepository).findItemRecentDetails((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getLastPuchaseDetails(Long)}
     */
    @Test
    void testGetLastPuchaseDetails3() {
        // Arrange
        when(purchaseEntryRepository.findItemRecentDetails((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> purchaseEntryServiceImpl.getLastPuchaseDetails(123L));
        verify(purchaseEntryRepository).findItemRecentDetails((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseById(Long)}
     */
    @Test
    void testGetPurchaseById() throws RecordNotFoundException {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("Gst No");
        supplierCreationEntity.setGstType("Gst Type");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("Mobile No");
        supplierCreationEntity.setModeOfPayment("Mode Of Payment");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("Rate Slab");
        supplierCreationEntity.setStatus("Status");
        supplierCreationEntity.setSupplierName("Supplier Name");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("Invoice No");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("Srt Margin");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("Supplier Name");
        Optional<PurchaseEntryEntity> ofResult = Optional.of(purchaseEntryEntity);
        when(purchaseEntryRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(purchaseEntryEntity, purchaseEntryServiceImpl.getPurchaseById(123L));
        verify(purchaseEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseById(Long)}
     */
    @Test
    void testGetPurchaseById2() throws RecordNotFoundException {
        // Arrange
        when(purchaseEntryRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> purchaseEntryServiceImpl.getPurchaseById(123L));
        verify(purchaseEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseById(Long)}
     */
    @Test
    void testGetPurchaseById3() throws RecordNotFoundException {
        // Arrange
        when(purchaseEntryRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> purchaseEntryServiceImpl.getPurchaseById(123L));
        verify(purchaseEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseDetailByInvoice(String)}
     */
    @Test
    void testGetPurchaseDetailByInvoice() {
        // Arrange
        when(purchaseEntryRepository.findByInvoiceNo((String) any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertEquals("NotPresent", purchaseEntryServiceImpl.getPurchaseDetailByInvoice("Invoice"));
        verify(purchaseEntryRepository).findByInvoiceNo((String) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseDetailByInvoice(String)}
     */
    @Test
    void testGetPurchaseDetailByInvoice2() {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("NotPresent");
        supplierCreationEntity.setGstType("NotPresent");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("NotPresent");
        supplierCreationEntity.setModeOfPayment("NotPresent");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("NotPresent");
        supplierCreationEntity.setStatus("NotPresent");
        supplierCreationEntity.setSupplierName("NotPresent");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("NotPresent");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("NotPresent");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("NotPresent");

        ArrayList<PurchaseEntryEntity> purchaseEntryEntityList = new ArrayList<>();
        purchaseEntryEntityList.add(purchaseEntryEntity);
        when(purchaseEntryRepository.findByInvoiceNo((String) any())).thenReturn(purchaseEntryEntityList);

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> purchaseEntryServiceImpl.getPurchaseDetailByInvoice("Invoice"));
        verify(purchaseEntryRepository).findByInvoiceNo((String) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseDetailByInvoice(String)}
     */
    @Test
    void testGetPurchaseDetailByInvoice3() {
        // Arrange
        when(purchaseEntryRepository.findByInvoiceNo((String) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntryServiceImpl.getPurchaseDetailByInvoice("Invoice"));
        verify(purchaseEntryRepository).findByInvoiceNo((String) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseDetailByInvoiceAndSupplier(String, Long)}
     */
    @Test
    void testGetPurchaseDetailByInvoiceAndSupplier() {
        // Arrange
        when(purchaseEntryRepository.findByinvoiceNoAndSupplierId((String) any(), (Long) any()))
                .thenReturn(new ArrayList<>());

        // Act and Assert
        assertEquals("NotPresent", purchaseEntryServiceImpl.getPurchaseDetailByInvoiceAndSupplier("Invoice", 123L));
        verify(purchaseEntryRepository).findByinvoiceNoAndSupplierId((String) any(), (Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseDetailByInvoiceAndSupplier(String, Long)}
     */
    @Test
    void testGetPurchaseDetailByInvoiceAndSupplier2() {
        // Arrange
        SupplierCreationEntity supplierCreationEntity = new SupplierCreationEntity();
        supplierCreationEntity.setAddress1("42 Main St");
        supplierCreationEntity.setAddress2("42 Main St");
        supplierCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        supplierCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setCredit_days(1);
        supplierCreationEntity.setDiscount("3");
        supplierCreationEntity.setGstNo("NotPresent");
        supplierCreationEntity.setGstType("NotPresent");
        supplierCreationEntity.setId(123L);
        supplierCreationEntity.setMobileNo("NotPresent");
        supplierCreationEntity.setModeOfPayment("NotPresent");
        supplierCreationEntity.setOpeningBal(1L);
        supplierCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        supplierCreationEntity.setRateSlab("NotPresent");
        supplierCreationEntity.setStatus("NotPresent");
        supplierCreationEntity.setSupplierName("NotPresent");
        supplierCreationEntity.setUpdated_by(1);
        supplierCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        PurchaseEntryEntity purchaseEntryEntity = new PurchaseEntryEntity();
        purchaseEntryEntity.setDisPercentage(1L);
        purchaseEntryEntity.setDiscAmount(10.0d);
        purchaseEntryEntity.setDiscount(3L);
        purchaseEntryEntity.setDuedate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setDueday(1);
        purchaseEntryEntity.setEntrydate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setFooterDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setGrossAmounts(1L);
        purchaseEntryEntity.setGstamount(10L);
        purchaseEntryEntity.setId(123L);
        purchaseEntryEntity.setInvoiceDate(LocalDate.ofEpochDay(1L));
        purchaseEntryEntity.setInvoiceNo("NotPresent");
        purchaseEntryEntity.setNetAmount(1L);
        purchaseEntryEntity.setNoItems(1000);
        purchaseEntryEntity.setPurchaseDetails(new ArrayList<>());
        purchaseEntryEntity.setRoundAmount(1L);
        purchaseEntryEntity.setSrtMargin("NotPresent");
        purchaseEntryEntity.setSupplierDetails(supplierCreationEntity);
        purchaseEntryEntity.setSupplierName("NotPresent");

        ArrayList<PurchaseEntryEntity> purchaseEntryEntityList = new ArrayList<>();
        purchaseEntryEntityList.add(purchaseEntryEntity);
        when(purchaseEntryRepository.findByinvoiceNoAndSupplierId((String) any(), (Long) any()))
                .thenReturn(purchaseEntryEntityList);

        // Act and Assert
        assertThrows(RecordAlreadyPresentException.class,
                () -> purchaseEntryServiceImpl.getPurchaseDetailByInvoiceAndSupplier("Invoice", 123L));
        verify(purchaseEntryRepository).findByinvoiceNoAndSupplierId((String) any(), (Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseDetailByInvoiceAndSupplier(String, Long)}
     */
    @Test
    void testGetPurchaseDetailByInvoiceAndSupplier3() {
        // Arrange
        when(purchaseEntryRepository.findByinvoiceNoAndSupplierId((String) any(), (Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntryServiceImpl.getPurchaseDetailByInvoiceAndSupplier("Invoice", 123L));
        verify(purchaseEntryRepository).findByinvoiceNoAndSupplierId((String) any(), (Long) any());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getPurchaseReport(PurchaseReportFilterDTO, Pageable)}
     */
    @Test
    void testGetPurchaseReport() {
        // Arrange
        when(purchaseEntryDetailsRepository.findAll((Predicate) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        PurchaseReportFilterDTO purchaseReportFilterDTO = new PurchaseReportFilterDTO();
        purchaseReportFilterDTO.setEndDate(LocalDate.ofEpochDay(1L));
        purchaseReportFilterDTO.setItemIds(new ArrayList<>());
        purchaseReportFilterDTO.setStartDate(LocalDate.ofEpochDay(1L));
        purchaseReportFilterDTO.setSupplierIds(new ArrayList<>());
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act and Assert
        assertTrue(purchaseEntryServiceImpl.getPurchaseReport(purchaseReportFilterDTO, ofResult).toList().isEmpty());
        verify(purchaseEntryDetailsRepository).findAll((Predicate) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<PurchaseEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(purchaseEntryRepository.findAllBySupplierNameContains((String) any(), (Pageable) any()))
                .thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<PurchaseEntryEntity> actualSearch = purchaseEntryServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(purchaseEntryRepository).findAllBySupplierNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link PurchaseEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(purchaseEntryRepository.findAllBySupplierNameContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> purchaseEntryServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(purchaseEntryRepository).findAllBySupplierNameContains((String) any(), (Pageable) any());
    }
}

