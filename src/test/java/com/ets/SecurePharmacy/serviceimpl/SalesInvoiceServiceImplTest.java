package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.DTO.SalesInvoiceDTO;
import com.ets.SecurePharmacy.DTO.SalesInvoiceEntryDetailDTO;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.service.BatchService;
import com.ets.SecurePharmacy.service.ItemCreationService;
import com.ets.SecurePharmacy.tenant.dao.AccountLedgerRepository;
import com.ets.SecurePharmacy.tenant.dao.BatchRepository;
import com.ets.SecurePharmacy.tenant.dao.BatchTranasactionRepository;
import com.ets.SecurePharmacy.tenant.dao.CustomerLedgerRepository;
import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.tenant.dao.SalesInvoiceRepository;
import com.ets.SecurePharmacy.tenant.model.BatchEntity;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.CustomerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntryDetailEntity;
import com.ets.SecurePharmacy.tenant.model.SchedulerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.StoreTypeCreationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SalesInvoiceServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SalesInvoiceServiceImplTest {
    @MockBean
    private SalesInvoiceRepository salesInvoiceRepository;

    @MockBean
    private BatchService batchService;

    @MockBean
    private ItemCreationService itemCreationService;

    @MockBean
    private BatchRepository batchRepository;

    @MockBean
    private BatchTranasactionRepository batchTranasactionRepository;

    @MockBean
    private ItemStockRepository itemStockRepository;

    @MockBean
    private AccountLedgerRepository accountLedgerRepository;

    @MockBean
    private CustomerLedgerRepository customerLedgerRepository;

    @Autowired
    private SalesInvoiceServiceImpl salesInvoiceServiceImpl;

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getAlStockInvoice()}
     */
    @Test
    void testGetAlStockInvoice() {
        // Arrange
        when(salesInvoiceRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(salesInvoiceServiceImpl.getAlStockInvoice().isEmpty());
        verify(salesInvoiceRepository).findAll();
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getAlStockInvoice()}
     */
    @Test
    void testGetAlStockInvoice2() {
        // Arrange
        SalesInvoiceEntity salesInvoiceEntity = salesInvoiceEntity();
        ;

        ArrayList<SalesInvoiceEntity> salesInvoiceEntityList = new ArrayList<>();
        salesInvoiceEntityList.add(salesInvoiceEntity);
        when(salesInvoiceRepository.findAll()).thenReturn(salesInvoiceEntityList);

        // Act
        List<SalesInvoiceEntity> actualAlStockInvoice = salesInvoiceServiceImpl.getAlStockInvoice();

        // Assert
        assertSame(salesInvoiceEntityList, actualAlStockInvoice);
        assertEquals(1, actualAlStockInvoice.size());
        verify(salesInvoiceRepository).findAll();
    }

    private SalesInvoiceEntity salesInvoiceEntity() {
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
        salesInvoiceEntity.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceEntity.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setCustomerAddress("42 Main St");
        salesInvoiceEntity.setCustomerName("Customer Name");
        salesInvoiceEntity.setDiscountAmt(10.00);
        salesInvoiceEntity.setDiscount(10.000);
        salesInvoiceEntity.setDoctor_name("Doctor");
        salesInvoiceEntity.setDoctorAddress("42 Main St");
        salesInvoiceEntity.setExpenses(10.00);
        salesInvoiceEntity.setGrossAmount(10.999);
        salesInvoiceEntity.setGstAmt(10.888);
        salesInvoiceEntity.setHomeDelivery("Home Delivery");
        salesInvoiceEntity.setId(123L);
        salesInvoiceEntity.setNetAmt(10.999);
        salesInvoiceEntity.setOtherExpenses("Other Expenses");
        salesInvoiceEntity.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceEntity.setRemainderDays(1);
        salesInvoiceEntity.setRoundOffAmt(10.990);
        salesInvoiceEntity.setMargin(10.900);
        salesInvoiceEntity.setMarginOn("margin on");
        salesInvoiceEntity.setStatus("Status");
        salesInvoiceEntity.setStockinvoiceDetails(new ArrayList<>());
        return salesInvoiceEntity;
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getAlStockInvoice()}
     */
    @Test
    void testGetAlStockInvoice3() {
        // Arrange
        when(salesInvoiceRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> salesInvoiceServiceImpl.getAlStockInvoice());
        verify(salesInvoiceRepository).findAll();
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getStockInvoiceById(Long)}
     */
    @Test
    void testGetStockInvoiceById() throws RecordNotFoundException {
        // Arrange
        SalesInvoiceEntity salesInvoiceEntity = salesInvoiceEntity();
        Optional<SalesInvoiceEntity> ofResult = Optional.of(salesInvoiceEntity);
        when(salesInvoiceRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(salesInvoiceEntity, salesInvoiceServiceImpl.getStockInvoiceById(123L));
        verify(salesInvoiceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getStockInvoiceById(Long)}
     */
    @Test
    void testGetStockInvoiceById2() throws RecordNotFoundException {
        // Arrange
        when(salesInvoiceRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> salesInvoiceServiceImpl.getStockInvoiceById(123L));
        verify(salesInvoiceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getStockInvoiceById(Long)}
     */
    @Test
    void testGetStockInvoiceById3() throws RecordNotFoundException {
        // Arrange
        when(salesInvoiceRepository.findById((Long) any())).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> salesInvoiceServiceImpl.getStockInvoiceById(123L));
        verify(salesInvoiceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#createOrUpdateStockInvoice(com.ets.SecurePharmacy.DTO.SalesInvoiceDTO)}
     */
    @Test
    void testCreateOrUpdateStockInvoice() throws RecordNotFoundException {
        // Arrange
        SalesInvoiceEntity salesInvoiceEntity = salesInvoiceEntity();

        SalesInvoiceEntryDetailDTO salesInvoiceEntryDetailDTO = new SalesInvoiceEntryDetailDTO();
        salesInvoiceEntryDetailDTO.setDiscount(1.00);
        salesInvoiceEntryDetailDTO.setId(123L);
        salesInvoiceEntryDetailDTO.setBatch("Batch");
        salesInvoiceEntryDetailDTO.setNetAmt(10.999);
        salesInvoiceEntryDetailDTO.setMrp(1.899);
        salesInvoiceEntryDetailDTO.setQty(1.900);
        salesInvoiceEntryDetailDTO.setRate(1.99);
        salesInvoiceEntryDetailDTO.setBatch_invoc_no(123L);


        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SalesInvoiceDTO salesInvoiceDTO = new SalesInvoiceDTO();
        salesInvoiceDTO.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceDTO.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceDTO.setCustomerAddress("42 Main St");
        salesInvoiceDTO.setCustomerName("Customer Name");
        salesInvoiceDTO.setDiscountAmt(10.000);
        salesInvoiceDTO.setDiscount(10.999);
        salesInvoiceDTO.setDoctor_name("Doctor");
        salesInvoiceDTO.setDoctorAddress("42 Main St");
        salesInvoiceDTO.setExpenses(10.888);
        salesInvoiceDTO.setGrossAmount(10.895);
        salesInvoiceDTO.setGstAmt(10.899);
        salesInvoiceDTO.setHomeDelivery("Home Delivery");
        salesInvoiceDTO.setId(123L);
        salesInvoiceDTO.setNetAmt(10.900);
        salesInvoiceDTO.setOtherExpenses("Other Expenses");
        salesInvoiceDTO.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceDTO.setRemainderDays(1);
        salesInvoiceDTO.setRoundOffAmt(10.999);
        salesInvoiceDTO.setMargin(10.899);
        salesInvoiceDTO.setMarginOn("margin on");
        salesInvoiceDTO.setStatus("Status");
        salesInvoiceDTO.setPaymentMode("Debit");
        salesInvoiceDTO.setStockinvoiceDetails(Collections.singletonList(salesInvoiceEntryDetailDTO));

        //--------
        SalesInvoiceEntryDetailEntity salesInvoiceEntryDetailEntity = new SalesInvoiceEntryDetailEntity();
        ItemCreationEntity itemCreationEntity = itemCreationEntity();
        when(itemCreationService.getItemById((Long) any())).thenReturn(itemCreationEntity);
        salesInvoiceEntryDetailDTO.setItemCreationEntity(itemCreationEntity);
        salesInvoiceEntryDetailEntity.setBatch(salesInvoiceEntryDetailDTO.getBatch());
        salesInvoiceEntryDetailEntity.setQty(salesInvoiceEntryDetailDTO.getQty());
        salesInvoiceEntryDetailEntity.setDiscount(salesInvoiceEntryDetailDTO.getDiscount());
        salesInvoiceEntryDetailEntity.setMrp(salesInvoiceEntryDetailDTO.getMrp());
        salesInvoiceEntryDetailEntity.setSaleRate(salesInvoiceEntryDetailDTO.getRate());
        salesInvoiceEntryDetailEntity.setDiscount(salesInvoiceEntryDetailDTO.getDiscount());


        BatchEntity batchEntity = new BatchEntity();
        batchEntity.setAvailableStock(10.0d);
        batchEntity.setBatchDetails(new ArrayList<>());
        batchEntity.setBatchNo("Batch No");
        batchEntity.setExpiry(LocalDate.ofEpochDay(1L));
        batchEntity.setId(123L);
        batchEntity.setItems(itemCreationEntity);
        batchEntity.setMrp(1.00);
        batchEntity.setQtyPerPack(1L);
        when(batchService.getMRP((String) any(), (Long) any())).thenReturn(batchEntity);

        salesInvoiceEntryDetailEntity.setExpiryDate(batchEntity.getExpiry());
        salesInvoiceEntryDetailEntity.setGst(salesInvoiceEntryDetailDTO.getItemCreationEntity().getGst());
        // calulation part started
        Long gst_percentage = (long) 100 + salesInvoiceEntryDetailDTO.getItemCreationEntity().getGst();
        BigDecimal bd = new BigDecimal((Double) ((salesInvoiceEntryDetailDTO.getRate() * 100.0000) / (gst_percentage))).setScale(4,
                RoundingMode.HALF_UP);
        Double taxableRate = bd.doubleValue();
        // Long taxableRate= details.getRate() -taxamount;
        System.out.println("after call tax" + taxableRate);
        salesInvoiceEntryDetailEntity.setTaxbleRate(taxableRate);
        BigDecimal bd1 = new BigDecimal(
                (Double) (taxableRate * (salesInvoiceEntryDetailDTO.getDiscount() / 100.0000) * salesInvoiceEntryDetailDTO.getQty())).setScale(4,
                RoundingMode.HALF_UP);
        Double discountAmt = bd1.doubleValue();
        salesInvoiceEntryDetailEntity.setDiscountAmt(discountAmt);
        BigDecimal bd2 = new BigDecimal(taxableRate * salesInvoiceEntryDetailDTO.getQty()).setScale(4, RoundingMode.HALF_UP);
        Double grossAmount = bd2.doubleValue();
        salesInvoiceEntryDetailEntity.setGrossAmt(grossAmount);

        BigDecimal bd4 = new BigDecimal(grossAmount - discountAmt).setScale(4, RoundingMode.HALF_UP);
        Double taxableAmount = bd4.doubleValue();
        salesInvoiceEntryDetailEntity.setTaxbleAmount(taxableAmount);
        long gst = salesInvoiceEntryDetailDTO.getItemCreationEntity().getGst();
        BigDecimal bd3 = new BigDecimal((taxableAmount * (gst / 100.0000))).setScale(4, RoundingMode.HALF_UP);
        Double taxAmount = bd3.doubleValue();
        salesInvoiceEntryDetailEntity.setTaxAmount(taxAmount);

        BigDecimal bd6 = new BigDecimal(taxableAmount + taxAmount).setScale(4, RoundingMode.HALF_UP);
        Double netAmout = bd6.doubleValue();

        salesInvoiceEntryDetailEntity.setNetAmt(netAmout);

        BigDecimal bd7 = new BigDecimal(netAmout/salesInvoiceEntryDetailDTO.getQty()).setScale(4, RoundingMode.HALF_UP);
        Double netSaleRate = bd7.doubleValue();
        salesInvoiceEntryDetailEntity.setNetSaleRate(netSaleRate);

        when(batchRepository.getBatchInvoiceNo(123L)).thenReturn(batchEntity);

        BigDecimal bd5 = new BigDecimal(!batchEntity.getBatchDetails().isEmpty() && batchEntity.getBatchDetails().get(0).getLooseNetCost()!= null ?  batchEntity.getBatchDetails().get(0).getLooseNetCost() :0 ).setScale(4, RoundingMode.HALF_UP);
        Double looseNetCost = bd5.doubleValue();
        Double netCost = looseNetCost * salesInvoiceEntryDetailDTO.getQty();
        Double marginAmount = netAmout- netCost ;
        Double marginPercentage = (marginAmount * 100.0000) / netCost;
        salesInvoiceEntryDetailEntity.setMarginAmount(marginAmount);
        salesInvoiceEntryDetailEntity.setNetCost(netCost);
        salesInvoiceEntryDetailEntity.setMarginPercentage(marginPercentage);

        salesInvoiceEntryDetailEntity.setSaleInvEntryEntity(salesInvoiceEntity);
        List<SalesInvoiceEntryDetailEntity> list = new ArrayList<>();
        list.add(salesInvoiceEntryDetailEntity);
        salesInvoiceEntity.setStockinvoiceDetails(list);

        when(salesInvoiceRepository.save((SalesInvoiceEntity) any())).thenReturn(salesInvoiceEntity);
        // Act and Assert
        assertSame(salesInvoiceDTO, salesInvoiceServiceImpl.createOrUpdateStockInvoice(salesInvoiceDTO));
        verify(itemCreationService).getItemById((Long) any());
        verify(batchService).getMRP((String) any(), (Long) any());
        verify(salesInvoiceRepository).save((SalesInvoiceEntity) any());
    }

    Long getTaxAmount(Long rate, Integer gst) {

        return ((rate*100)/(100+(gst/100)));
    }

    private ItemCreationEntity itemCreationEntity() {
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
        return itemCreationEntity;
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#createOrUpdateStockInvoice(com.ets.SecurePharmacy.DTO.SalesInvoiceDTO)}
     */
    @Test
    void testCreateOrUpdateStockInvoice1() throws RecordNotFoundException {
        // Arrange
        when(salesInvoiceRepository.save((SalesInvoiceEntity) any())).thenThrow(new UnableToProcessException("Unable to process at this moment, Try Again! After some time"));
        CustomerCreationEntity customerCreationEntity = new CustomerCreationEntity();
        customerCreationEntity.setAddress1("42 Main St");
        customerCreationEntity.setAddress2("42 Main St");
        customerCreationEntity.setCreated_by("Jan 1, 2020 8:00am GMT+0100");
        customerCreationEntity.setCreated_date(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setCreditLimit(1);
        customerCreationEntity.setCustomerName("Customer Name");
        customerCreationEntity.setDiscount(3);
        customerCreationEntity.setDueDays(1);
        customerCreationEntity.setGstNo("Gst No");
        customerCreationEntity.setGstType("Gst Type");
        customerCreationEntity.setId(123L);
        customerCreationEntity.setMobileNo("Mobile No");
        customerCreationEntity.setModeOfPayment("Mode Of Payment");
        customerCreationEntity.setOpeningBal(1);
        customerCreationEntity.setOpeningBalDate(LocalDate.ofEpochDay(1L));
        customerCreationEntity.setRateSlab(1);
        customerCreationEntity.setStatus("Status");
        customerCreationEntity.setUpdated_by("2020-03-01");
        customerCreationEntity.setUpdated_date(LocalDate.ofEpochDay(1L));

        SalesInvoiceDTO salesInvoiceDTO = new SalesInvoiceDTO();
        salesInvoiceDTO.setCustomerCreationEntity(customerCreationEntity);
        salesInvoiceDTO.setBilldate(LocalDate.ofEpochDay(1L));
        salesInvoiceDTO.setCustomerAddress("42 Main St");
        salesInvoiceDTO.setCustomerName("Customer Name");
        salesInvoiceDTO.setDiscountAmt(10.00);
        salesInvoiceDTO.setDiscount(10.00);
        salesInvoiceDTO.setDoctor_name("Doctor");
        salesInvoiceDTO.setDoctorAddress("42 Main St");
        salesInvoiceDTO.setExpenses(10.00);
        salesInvoiceDTO.setGrossAmount(10.99);
        salesInvoiceDTO.setGstAmt(10.666);
        salesInvoiceDTO.setHomeDelivery("Home Delivery");
        salesInvoiceDTO.setId(123L);
        salesInvoiceDTO.setNetAmt(10.66);
        salesInvoiceDTO.setOtherExpenses("Other Expenses");
        salesInvoiceDTO.setRemainderDate(LocalDate.ofEpochDay(1L));
        salesInvoiceDTO.setRemainderDays(1);
        salesInvoiceDTO.setRoundOffAmt(10.6655);
        salesInvoiceDTO.setMargin(10.6544);
        salesInvoiceDTO.setMarginOn("margin on");
        salesInvoiceDTO.setStatus("Status");
        salesInvoiceDTO.setStockinvoiceDetails(new ArrayList<>());

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> salesInvoiceServiceImpl.createOrUpdateStockInvoice(salesInvoiceDTO));
        verify(salesInvoiceRepository).save((SalesInvoiceEntity) any());
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getAllSalesInvoiceWithPagination(int, int)}
     */
    @Test
    void testGetAllSalesInvoiceWithPagination() {
        // Arrange
        PageImpl<SalesInvoiceEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(salesInvoiceRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<SalesInvoiceEntity> actualAllSalesInvoiceWithPagination = salesInvoiceServiceImpl
                .getAllSalesInvoiceWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllSalesInvoiceWithPagination);
        assertTrue(actualAllSalesInvoiceWithPagination.toList().isEmpty());
        verify(salesInvoiceRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getAllSalesInvoiceWithPagination(int, int)}
     */
    @Test
    void testGetAllSalesInvoiceWithPagination2() {
        // Arrange
        when(salesInvoiceRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesInvoiceServiceImpl.getAllSalesInvoiceWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getAllSalesInvoiceWithPagination(int, int)}
     */
    @Test
    void testGetAllSalesInvoiceWithPagination3() {
        // Arrange
        when(salesInvoiceRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesInvoiceServiceImpl.getAllSalesInvoiceWithPagination(2, 3));
        verify(salesInvoiceRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<SalesInvoiceEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(salesInvoiceRepository.findAllByCustomerNameContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<SalesInvoiceEntity> actualSearch = salesInvoiceServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(salesInvoiceRepository).findAllByCustomerNameContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        PageImpl<SalesInvoiceEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(salesInvoiceRepository.searchByMobile((Long) any(), (Pageable) any())).thenReturn(pageImpl);
        when(salesInvoiceRepository.findAllByCustomerNameContains((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<SalesInvoiceEntity> actualSearch = salesInvoiceServiceImpl.getSearch("42", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(salesInvoiceRepository).searchByMobile((Long) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link SalesInvoiceServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch3() {
        // Arrange
        when(salesInvoiceRepository.searchByMobile((Long) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));
        when(salesInvoiceRepository.findAllByCustomerNameContains((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> salesInvoiceServiceImpl.getSearch("42", QPageRequest.of(1, 3)));
        verify(salesInvoiceRepository).searchByMobile((Long) any(), (Pageable) any());
    }
}

