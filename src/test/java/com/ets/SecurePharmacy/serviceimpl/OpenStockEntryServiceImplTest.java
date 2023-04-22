package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.dao.ItemStockRepository;
import com.ets.SecurePharmacy.tenant.dao.OpenStockEntryRepository;
import com.ets.SecurePharmacy.tenant.model.CompostionCreationEntity;
import com.ets.SecurePharmacy.tenant.model.DiscountSlabCreationEntity;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;
import com.ets.SecurePharmacy.tenant.model.HsnSacCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemCreationEntity;
import com.ets.SecurePharmacy.tenant.model.ItemStockEntity;
import com.ets.SecurePharmacy.tenant.model.ManufacturerCreationEntity;
import com.ets.SecurePharmacy.tenant.model.OpenStockEntryEntity;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OpenStockEntryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OpenStockEntryServiceImplTest {
    @MockBean
    private ItemStockRepository itemStockRepository;

    @MockBean
    private OpenStockEntryRepository openStockEntryRepository;

    @Autowired
    private OpenStockEntryServiceImpl openStockEntryServiceImpl;

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getAllOpenStock()}
     */
    @Test
    void testGetAllOpenStock() {
        // Arrange
        when(openStockEntryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(openStockEntryServiceImpl.getAllOpenStock().isEmpty());
        verify(openStockEntryRepository).findAll();
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getAllOpenStock()}
     */
    @Test
    void testGetAllOpenStock2() {
        // Arrange
        OpenStockEntryEntity openStockEntryEntity = new OpenStockEntryEntity();
        openStockEntryEntity.setDelStatus("Service iMpla");
        openStockEntryEntity.setId(123L);
        openStockEntryEntity.setMrpAmount(10.0d);
        openStockEntryEntity.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity.setPurchaseVal(10.0d);
        openStockEntryEntity.setSalesVal(10.0d);
        openStockEntryEntity.setSrt(10.0d);
        openStockEntryEntity.setStatus("Service iMpla");
        openStockEntryEntity.setTotalItems(1000);

        ArrayList<OpenStockEntryEntity> openStockEntryEntityList = new ArrayList<>();
        openStockEntryEntityList.add(openStockEntryEntity);
        when(openStockEntryRepository.findAll()).thenReturn(openStockEntryEntityList);

        // Act
        List<OpenStockEntryEntity> actualAllOpenStock = openStockEntryServiceImpl.getAllOpenStock();

        // Assert
        assertSame(openStockEntryEntityList, actualAllOpenStock);
        assertEquals(1, actualAllOpenStock.size());
        verify(openStockEntryRepository).findAll();
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getAllOpenStock()}
     */
    @Test
    void testGetAllOpenStock3() {
        // Arrange
        when(openStockEntryRepository.findAll()).thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> openStockEntryServiceImpl.getAllOpenStock());
        verify(openStockEntryRepository).findAll();
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getOpenStockeById(Long)}
     */
    @Test
    void testGetOpenStockeById() throws RecordNotFoundException {
        // Arrange
        OpenStockEntryEntity openStockEntryEntity = new OpenStockEntryEntity();
        openStockEntryEntity.setDelStatus("Del Status");
        openStockEntryEntity.setId(123L);
        openStockEntryEntity.setMrpAmount(10.0d);
        openStockEntryEntity.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity.setPurchaseVal(10.0d);
        openStockEntryEntity.setSalesVal(10.0d);
        openStockEntryEntity.setSrt(10.0d);
        openStockEntryEntity.setStatus("Status");
        openStockEntryEntity.setTotalItems(1000);
        Optional<OpenStockEntryEntity> ofResult = Optional.of(openStockEntryEntity);
        when(openStockEntryRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(openStockEntryEntity, openStockEntryServiceImpl.getOpenStockeById(123L));
        verify(openStockEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getOpenStockeById(Long)}
     */
    @Test
    void testGetOpenStockeById2() throws RecordNotFoundException {
        // Arrange
        when(openStockEntryRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> openStockEntryServiceImpl.getOpenStockeById(123L));
        verify(openStockEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getOpenStockeById(Long)}
     */
    @Test
    void testGetOpenStockeById3() throws RecordNotFoundException {
        // Arrange
        when(openStockEntryRepository.findById((Long) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class, () -> openStockEntryServiceImpl.getOpenStockeById(123L));
        verify(openStockEntryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#createOrUpdateOpenStock(OpenStockEntryEntity)}
     */
    @Test
    void testCreateOrUpdateOpenStock() {
        // Arrange
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
        when(itemStockRepository.findByItemEntityId((Long) any())).thenReturn(itemStockEntity);

        OpenStockEntryEntity openStockEntryEntity = new OpenStockEntryEntity();
        openStockEntryEntity.setDelStatus("Del Status");
        openStockEntryEntity.setId(123L);
        openStockEntryEntity.setMrpAmount(10.0d);
        openStockEntryEntity.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity.setPurchaseVal(10.0d);
        openStockEntryEntity.setSalesVal(10.0d);
        openStockEntryEntity.setSrt(10.0d);
        openStockEntryEntity.setStatus("Status");
        openStockEntryEntity.setTotalItems(1000);
        when(openStockEntryRepository.save((OpenStockEntryEntity) any())).thenReturn(openStockEntryEntity);

        OpenStockEntryEntity openStockEntryEntity1 = new OpenStockEntryEntity();
        openStockEntryEntity1.setDelStatus("Del Status");
        openStockEntryEntity1.setId(123L);
        openStockEntryEntity1.setMrpAmount(10.0d);
        openStockEntryEntity1.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity1.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity1.setPurchaseVal(10.0d);
        openStockEntryEntity1.setSalesVal(10.0d);
        openStockEntryEntity1.setSrt(10.0d);
        openStockEntryEntity1.setStatus("Status");
        openStockEntryEntity1.setTotalItems(1000);

        // Act and Assert
        assertSame(openStockEntryEntity, openStockEntryServiceImpl.createOrUpdateOpenStock(openStockEntryEntity1));
        verify(itemStockRepository).findByItemEntityId((Long) any());
        verify(openStockEntryRepository).save((OpenStockEntryEntity) any());
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#createOrUpdateOpenStock(OpenStockEntryEntity)}
     */
    @Test
    void testCreateOrUpdateOpenStock2() {
        // Arrange
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
        itemStockEntity.setLessStock(1.000);
        when(itemStockRepository.findByItemEntityId((Long) any())).thenReturn(itemStockEntity);
        when(openStockEntryRepository.save((OpenStockEntryEntity) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        OpenStockEntryEntity openStockEntryEntity = new OpenStockEntryEntity();
        openStockEntryEntity.setDelStatus("Del Status");
        openStockEntryEntity.setId(123L);
        openStockEntryEntity.setMrpAmount(10.0d);
        openStockEntryEntity.setOpenStockDetails(new ArrayList<>());
        openStockEntryEntity.setOrderDate(LocalDate.ofEpochDay(1L));
        openStockEntryEntity.setPurchaseVal(10.0d);
        openStockEntryEntity.setSalesVal(10.0d);
        openStockEntryEntity.setSrt(10.0d);
        openStockEntryEntity.setStatus("Status");
        openStockEntryEntity.setTotalItems(1000);

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> openStockEntryServiceImpl.createOrUpdateOpenStock(openStockEntryEntity));
        verify(openStockEntryRepository).save((OpenStockEntryEntity) any());
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getAllOpenStockEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllOpenStockEntryWithPagination() {
        // Arrange
        PageImpl<OpenStockEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(openStockEntryRepository.findAll((Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<OpenStockEntryEntity> actualAllOpenStockEntryWithPagination = openStockEntryServiceImpl
                .getAllOpenStockEntryWithPagination(2, 3);

        // Assert
        assertSame(pageImpl, actualAllOpenStockEntryWithPagination);
        assertTrue(actualAllOpenStockEntryWithPagination.toList().isEmpty());
        verify(openStockEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getAllOpenStockEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllOpenStockEntryWithPagination2() {
        // Arrange
        when(openStockEntryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> openStockEntryServiceImpl.getAllOpenStockEntryWithPagination(-1, 3));
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getAllOpenStockEntryWithPagination(int, int)}
     */
    @Test
    void testGetAllOpenStockEntryWithPagination3() {
        // Arrange
        when(openStockEntryRepository.findAll((Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> openStockEntryServiceImpl.getAllOpenStockEntryWithPagination(2, 3));
        verify(openStockEntryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch() {
        // Arrange
        PageImpl<OpenStockEntryEntity> pageImpl = new PageImpl<>(new ArrayList<>());
        when(openStockEntryRepository.findAllByOrderDateContains((String) any(), (Pageable) any())).thenReturn(pageImpl);
        QPageRequest ofResult = QPageRequest.of(1, 3);

        // Act
        Page<OpenStockEntryEntity> actualSearch = openStockEntryServiceImpl.getSearch("Query", ofResult);

        // Assert
        assertSame(pageImpl, actualSearch);
        assertTrue(actualSearch.toList().isEmpty());
        verify(openStockEntryRepository).findAllByOrderDateContains((String) any(), (Pageable) any());
        assertTrue(ofResult.getSort().toList().isEmpty());
    }

    /**
     * Method under test: {@link OpenStockEntryServiceImpl#getSearch(String, Pageable)}
     */
    @Test
    void testGetSearch2() {
        // Arrange
        when(openStockEntryRepository.findAllByOrderDateContains((String) any(), (Pageable) any()))
                .thenThrow(new UnableToProcessException("An error occurred"));

        // Act and Assert
        assertThrows(UnableToProcessException.class,
                () -> openStockEntryServiceImpl.getSearch("Query", QPageRequest.of(1, 3)));
        verify(openStockEntryRepository).findAllByOrderDateContains((String) any(), (Pageable) any());
    }
}

